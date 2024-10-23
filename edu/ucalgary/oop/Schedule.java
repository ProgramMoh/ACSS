/*
 * version: 37.2
 * since: 1.0
 *
 */

package edu.ucalgary.oop;

import java.util.Map.Entry;
import java.io.*;
import java.util.*;


public class Schedule {
	private ArrayList<ArrayList<String>> theSchedule = new ArrayList<>();

	public Schedule() throws IllegalScheduleException {
		boolean backupVolunteer = false;
		// Make copy of treatment list
		HashMap<Integer, Treatment> remainingTreatments = new HashMap<>(AnimalRecords.getTreatmentList());
		// Make copy of animal list for feeding list
		HashMap<Integer, Animal> remainingAnimals = new HashMap<>(AnimalRecords.getAnimalList());
		// Remove orphans from feeding list
		removeOrphans(remainingAnimals);
		// Make copy of animal list for cages list
		Queue<Animal> remainingCages = new LinkedList<>(AnimalRecords.getAnimalList().values());

		for (int hour = 0; hour < 24; hour++) {
			//Iterate through each hour
			ArrayList<String> hourlySchedule = new ArrayList<>();
			hourlySchedule.add(hour + ":00");
			int minute = 0;
			Queue<Treatment> treatmentPriorityTasks;
			Queue<Animal> feedingPriorityTasks;
			int hourWindow = 1;
			while (hourWindow < 24 && minute < 60) {
				//Keep looping through tasks until either minutes is exceeded or hourWindow is exceeded

				//Retrieve all Treatment and Feeding Tasks that need to be done at the hourWindow
				treatmentPriorityTasks = retrieveTreatmentPriorityTasks(hour, remainingTreatments, hourWindow);
				feedingPriorityTasks = retrieveFeedingPriorityTasks(hour, remainingAnimals, hourWindow);
				if (hourWindow == 1) { //If tasks have to be done within the hour, then add tasks without restriction
					minute += addTreatmentsToSchedule(hourlySchedule, remainingTreatments, treatmentPriorityTasks);
				} else if (!treatmentPriorityTasks.isEmpty()) {
					while (!canBeTreatedWithinHour(minute, treatmentPriorityTasks)) { // Do as many treatments within the hour as possible
						treatmentPriorityTasks.poll();
					}
					minute += addTreatmentsToSchedule(hourlySchedule, remainingTreatments, treatmentPriorityTasks);
				}
				while (!feedingPriorityTasks.isEmpty()) {
					Queue<Animal> groupOfAnimals = retrieveSameAnimalType(remainingAnimals, feedingPriorityTasks.poll().getAnimalType());
					if (hourWindow == 1) { // Feed as many animals as possible
						minute += addPriorityFeedingToSchedule(hourlySchedule, remainingAnimals, feedingPriorityTasks, groupOfAnimals);
					} else if (canBeFedWithinHour(minute, groupOfAnimals)) { // Add all animals if all animals can be fed within hour
						minute += addPriorityFeedingToSchedule(hourlySchedule, remainingAnimals, feedingPriorityTasks, groupOfAnimals);
					}
				}
				if (minute > 60 && hourWindow == 1) {
					// Change backupVolunteer to true only if tasks that have to be done within hour is greater than 60
					backupVolunteer = true;
					minute -= 60;
				}
				if (minute > 60 && backupVolunteer){
					hourlySchedule.remove(0);
					throw new IllegalScheduleException("Too many tasks scheduled at hour: " + hour + "\nPlease reschedule some of the following tasks: " + hourlySchedule);
				}
				hourWindow++;
			}
			minute = addRemainingFeedingToSchedule(hourlySchedule, remainingAnimals, minute, hour);
			// Add as many cage tasks as possible
			minute = addCageTasksToSchedule(hourlySchedule, remainingCages, minute);
			theSchedule.add(hourlySchedule);
			if (backupVolunteer){
				hourlySchedule.set(0, hourlySchedule.get(0).concat(" 0 minutes remaining [+ backup volunteer]"));
				backupVolunteer = false;
			}
			else{
				hourlySchedule.set(0, hourlySchedule.get(0).concat(" " + (60 - minute) + " minutes remaining"));
			}
		}
		if(!remainingAnimals.isEmpty()){
			throw new IllegalScheduleException("Could not fit all feeding tasks");
		}
		else if (!remainingTreatments.isEmpty()){
			throw new IllegalScheduleException("Could not fit all treatment tasks");
		}
		else if (!remainingCages.isEmpty()){
			throw new IllegalScheduleException("Could not fit all cage cleaning tasks");
		}
	}

	public String toString(){
		// Returns Schedule in Readable Format as String
		String theString = "";
		for (ArrayList<String> theList : theSchedule) {
			for (String theList2 : theList) {
				theString = theString.concat(theList2).concat("\n");
			}
			theString = theString.concat("\n");
		}
		return theString;
	}

	private boolean canBeTreatedWithinHour(int minute, Queue<Treatment> treatments) {
		// Checks if all treatments in queue can be done within the remaining minutes
		for (Treatment treatment : treatments) {
			minute += AnimalRecords.getTaskList().get(treatment.getTaskID()).getDuration();
		}
		return minute <= 60;
	}

	private boolean canBeFedWithinHour(int minute, Queue<Animal> animals) {
		// Checks if all feeding tasks in queue can be done within the remaining minutes
		minute += animals.peek().getFeedPrepDuration();
		for (Animal animal : animals) {
			minute += animal.getFeedingDuration();
		}
		return minute <= 60;
	}

	private int addRemainingFeedingToSchedule(ArrayList<String> hourlySchedule, HashMap<Integer, Animal> remainingAnimals, int minute, int hour){
		// Try to fit as many feeding tasks within remaining time
		String[] animalTypes = {"porcupine", "raccoon", "fox", "coyote", "beaver"};
		int i = 0;
		while (i < animalTypes.length && minute < 60){
			Queue<Animal> sameTypes = retrieveSameAnimalType(remainingAnimals, animalTypes[i]);
			if (!sameTypes.isEmpty() && sameTypes.peek().getFeedingStartHour() < hour){
				while(!canBeFedWithinHour(minute, sameTypes) && !sameTypes.isEmpty()){
					sameTypes.poll();
				}
				if (!sameTypes.isEmpty()) {
					ArrayList<Animal> animalList = new ArrayList<>();
					minute += sameTypes.peek().getFeedPrepDuration();
					while (!sameTypes.isEmpty()){
						Animal currentAnimal = sameTypes.poll();
						animalList.add(currentAnimal);
						remainingAnimals.remove(currentAnimal.getAnimalID());
						minute += currentAnimal.getFeedingDuration();
					}
					hourlySchedule.add(formatToString(animalList));
				}
			}
			i++;
		}
		return minute;
	}

	private int addCageTasksToSchedule(ArrayList<String> hourlySchedule, Queue<Animal> remainingCages, int minute){
		// Add as many cage tasks as possible
		while (minute < 60 && !remainingCages.isEmpty()){
			Animal currentCage = remainingCages.poll();
			while (currentCage.getCageCleanDuration() > 60 - minute){
				remainingCages.add(currentCage);
				currentCage = remainingCages.poll();
			}
			minute += currentCage.getCageCleanDuration();
			hourlySchedule.add("* Cage Cleaning - " + currentCage.getAnimalNickname());
		}
		return minute;
	}


	private int addTreatmentsToSchedule(ArrayList<String> hourlySchedule, HashMap<Integer, Treatment> remainingTreatments, Queue<Treatment> treatments) {
		// Add all treatments in the queue to hourlySchedule, and removes all treatments that are added from remainingTreatments
		int minute = 0;
		while (!treatments.isEmpty()) { //Do all treatment tasks that need to be done within the hour
			Treatment currentPriorityTask = treatments.poll();
			minute += AnimalRecords.getTaskList().get(currentPriorityTask.getTaskID()).getDuration();
			remainingTreatments.remove(currentPriorityTask.getTreatmentID());
			hourlySchedule.add(formatToString(currentPriorityTask));
		}
		return minute;
	}

	private int addPriorityFeedingToSchedule(ArrayList<String> hourlySchedule, HashMap<Integer, Animal> remainingAnimals, Queue<Animal> feedingPriorityTasks, Queue<Animal> animals) {
		// Add all feeding tasks in the queue to hourlySchedule, and removes all feeding tasks that are added from feedingPriorityTasks and remainingAnimals
		int minute = 0;
		ArrayList<Animal> animalList = new ArrayList<>();
		minute += animals.peek().getFeedPrepDuration();
		while (!animals.isEmpty()) {
			Animal currentAnimal = animals.poll();
			animalList.add(currentAnimal);
			remainingAnimals.remove(currentAnimal.getAnimalID());
			feedingPriorityTasks.remove(currentAnimal);
			minute += currentAnimal.getFeedingDuration();
		}

		String animalString = formatToString(animalList);
		hourlySchedule.add(animalString);
		return minute;
	}

	private String formatToString(Treatment treatment) {
		// Format treatment to string
		String theString = ("* " + AnimalRecords.getTaskList().get(treatment.getTaskID()).getDescription() + " (" +
				AnimalRecords.getAnimalList().get(treatment.getAnimalID()).getAnimalNickname() + ")");
		return theString;
	}

	private String formatToString(ArrayList<Animal> animalList) {
		// Format animal to string
		String animalString = ("* Feeding - " + animalList.get(0).getAnimalType() + " (" + animalList.size() + ": ");
		for (Animal animal : animalList) {
			animalString = animalString.concat(animal.getAnimalNickname() + ", ");
		}
		animalString = animalString.substring(0, animalString.length() - 2) + ")";
		return animalString;
	}

	private Queue<Treatment> retrieveTreatmentPriorityTasks(int hour, HashMap<Integer, Treatment> remainingTreatments, int hourWindow) {
		// Return a queue of treatments that match the hourWindow given
		Queue<Treatment> priorityTasks = new LinkedList<>();
		for (Map.Entry<Integer, Treatment> integerTreatmentEntry : remainingTreatments.entrySet()) {
			Treatment currentTreatment = remainingTreatments.get(integerTreatmentEntry.getKey());
			if (currentTreatment.calculateEndHour() - hour == hourWindow && currentTreatment.getStartHour() <= hour) {
				priorityTasks.add(currentTreatment);
			}
		}
		return priorityTasks;
	}

	private Queue<Animal> retrieveFeedingPriorityTasks(int hour, HashMap<Integer, Animal> remainingAnimals, int hourWindow) {
		// Return a queue of animals that match the hourWindow given
		Queue<Animal> priorityTasks = new LinkedList<>();
		for (Map.Entry<Integer, Animal> integerAnimalEntry : remainingAnimals.entrySet()) {
			Animal currentAnimal = remainingAnimals.get(integerAnimalEntry.getKey());
			if (currentAnimal.calculateFeedEndHour() - hour == hourWindow && currentAnimal.getFeedingStartHour() <= hour) {
				priorityTasks.add(currentAnimal);
			}
		}
		return priorityTasks;
	}

	private Queue<Animal> retrieveSameAnimalType(HashMap<Integer, Animal> remainingAnimals, String animalType) {
		// Return a queue of animals with the same type from the animals given (Coyote, Porcupine, etc)
		Queue<Animal> speciesList = new LinkedList<>();
		for (Map.Entry<Integer, Animal> integerAnimalEntry : remainingAnimals.entrySet()) {
			Animal currentAnimal = remainingAnimals.get(integerAnimalEntry.getKey());
			if (currentAnimal.getAnimalType().equals(animalType)) {
				speciesList.add(currentAnimal);
			}
		}
		return speciesList;
	}

	private void removeOrphans(HashMap<Integer, Animal> remainingAnimals) {
		Set<Entry<Integer, Animal>> setOfAnimals = remainingAnimals.entrySet();
		Iterator<Entry<Integer, Animal>> iterator = setOfAnimals.iterator();
		while (iterator.hasNext()) {
			Entry<Integer, Animal> entry = iterator.next();
			Animal animal = entry.getValue();
			if (animal.isOrphanAnimal()) {
				iterator.remove();
			}
		}
	}

	public ArrayList<ArrayList<String>> getTheSchedule() {
		return this.theSchedule;
	}
}