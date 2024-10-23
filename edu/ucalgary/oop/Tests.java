package edu.ucalgary.oop;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.HashMap;
import java.util.Map;


import java.util.ArrayList;
import java.util.regex.*;

public class Tests {

	//Treatment Class tests:
	@Test
	public void testTreatmentConstructor() {
		//Tests the TreatmentConstructor
		Treatment treatment = new Treatment(11,1,1111,4);
		//Instantiation of Treatment Constructor
		int actualTreatmentID = treatment.getTreatmentID(); 
		//actualTreatmentID gets the treatmentID from treatment
		int actualAnimalID = treatment.getAnimalID(); 
		//actualAnimalID gets the animalID from treatment
		int actualTaskID = treatment.getTaskID(); 
		//actualTaskID gets the taskID from treatment
		int actualStartHour = treatment.getStartHour(); 
		//actualStartHour gets the startHour from treatment
		assertEquals("Wrong Treatment TreatmentID, expected 11 but was " + actualTreatmentID , 11,actualTreatmentID);
		assertEquals("Wrong Treatment AnimalID, expected 1 but was " + actualAnimalID, 1, actualAnimalID);
		assertEquals("Wrong Treatment TaskID, expected 1111 but was " + actualTaskID, 1111, actualTaskID);
		assertEquals("Wrong Treatment StartHour, expected 4 but was " + actualStartHour, 4, actualStartHour);
		//returns the statement when actual != expected
	}

	@Test
	public void testTreatmentCalculateEndHour() {
		HashMap<Integer, Animal> animalList = new HashMap<>();
		//instantiating animalList to be a HashMap<Integer, Animal>
		animalList.put(1, new Animal(1, "Mike", "coyote"));
		//adding animal to animalList
		HashMap<Integer, Task> taskList = new HashMap<>();
		//instantiating taskList to be a HashMap<Integer, Task>
		taskList.put(1111, new Task(1111, "Killing", 10, 2));
		//adding task to taskList
		HashMap<Integer, Treatment> treatmentList = new HashMap<>();
		//instantiating treatmentList to be a HashMap<Integer, Treatment>
		Treatment treat = new Treatment(5,1, 1111, 3);
		treatmentList.put(5,treat );
		//adding treatment to treatmentList
		AnimalRecords animrec = new AnimalRecords(animalList, treatmentList, taskList);
		int expected = 3 + 2;
		int actual = animrec.getTreatmentList().get(5).calculateEndHour();
		assertEquals("Error in calculateEndHour, expected 5 but was " + actual,expected,actual);
		//returns the statement when actual != expected
	}
	
	@Test
	public void testTreatmentSetTreatmentID() {
		//Test for setTreatmentID
		Treatment treatment = new Treatment(11,1,1111,4); 
		//Instantiation of Treatment Constructor
		int expected = 22; 
		//Deciding the value of treatmentID
		treatment.setTreatmentID(expected); 
		//Sets the TreatmentID to 22;
		int actual = treatment.getTreatmentID(); 
		//actual gets the treatmentID from treatment
		assertEquals("Error in setTreatmentID, expected 22 but was " + actual, expected, actual); 
		//returns the statement when actual != expected
	}
	
	@Test
	public void testTreatmentSetAnimalID() {
		//Test for setAnimalID
		Treatment treatment = new Treatment(11,1,1111,4);
		//Instantiation of Treatment Constructor
		int expected = 2;
		//Deciding the value of animalID
		treatment.setAnimalID(expected);
		//Sets the animalID to 2;
		int actual = treatment.getAnimalID();
		//actual gets the animalID from treatment
		assertEquals("Error in setAnimalID, expected 2 but was " + actual, expected, actual);
		//returns the statement when actual != expected
	}
	
	@Test
	public void  testTreatmentSetTaskID() {
		//Test for setTaskID
		Treatment treatment = new Treatment(11,1,1111,4);
		//Instantiation of Treatment Constructor
		int expected = 2222;
		//Deciding the value of taskID
		treatment.setTaskID(expected);
		//Sets the taskID to 2222;
		int actual = treatment.getTaskID();
		//actual gets the taskID from treatment
		assertEquals("Error in setTaskID, expected 2222 but was " + actual, expected, actual);
		//returns the statement when actual != expected
	}
	
	@Test
	public void testTreatmentSetStartHour() {
		//Test for setStartHour
		Treatment treatment = new Treatment(11,1,1111,4);
		//Instantiation of Treatment Constructor
		int expected = 8;
		// Deciding the value of startHour
		treatment.setStartHour(expected);
		// Sets the startHour to 8
		int actual = treatment.getStartHour();
		//actual gets the startHour from treatment
		assertEquals("Error in setStartHour, expected 8 but was " + actual, expected, actual);
		//returns the statement when actual != expected
	}
	
	@Test
	public void testTreatmentGetTreatmentID() {
		Treatment treatment = new Treatment(11,1,1111,4);
		//Instantiation of Treatment Constructor
		int expected = 11;
		//value of treatmentID
		int actual = treatment.getTreatmentID();
		//actual gets the treatmentID from treatment
		assertEquals("Error in getTreatmentID, expected 11 but was " + actual, expected, actual);
		//returns the statement when actual != expected
	}
	
	@Test
	public void testTreatmentGetAnimalID() {
		//test for getAnimalID
		Treatment treatment = new Treatment(11,1,1111,4);
		//Instantiation of Treatment Constructor
		int expected = 1;
		//value of animalID
		int actual = treatment.getAnimalID();
		//actual gets the animalID from treatment
		assertEquals("Error in getAnimalID, expected 1 but was " + actual, expected, actual);
		//returns the statement when actual != expected
	}
	
	@Test
	public void testTreatmentGetTaskID() {
		//test for getTaskID
		Treatment treatment = new Treatment(11,1,1111,4);
		//Instantiation of Treatment Constructor
		int expected = 1111;
		//value of taskID
		int actual = treatment.getTaskID();
		//actual gets the taskID from treatment
		assertEquals("Error in getTaskID, expected 1111 but was " + actual, expected, actual);
		//returns the statement when actual != expected
	}
	
	@Test
	public void testTreatmentGetStartHour() {
		//test for getStartHour
		Treatment treatment = new Treatment(11,1,1111,4);
		//Instantiation of Treatment Constructor
		int expected = 4;
		//value of startHour
		int actual = treatment.getStartHour();
		//actual gets the startHour from treatment
		assertEquals("Error in getStartHour, expected 4 but was " + actual, expected, actual);
		//returns the statement when actual != expected
	}
	
	//Task Class Tests:
	@Test
	public void testTaskConstructor() {
		//test for Task constructor
		Task testTask = new Task(1,"Tested Task",5,1);
		//Instantiation of Task Constructor
		int actualTaskID = testTask.getTaskID(); 
		//actualTaskID gets the taskID from testTask
		String actualTaskDescription = testTask.getDescription(); 
		//actualTaskDescription gets the description from testTask
		int actualDurationTime = testTask.getDuration();
		//actualDurationTime gets the duration time from testTask
		int actualMaxWindow = testTask.getMaxWindow(); 
		//actualMaxWindow gets the max window from testTask
		assertEquals("Wrong Task TaskID expected 1 but was " + actualTaskID, 1, actualTaskID);
		assertEquals("Wrong Task TaskDescription expected 'Tested Task' but was " + actualTaskDescription, "Tested Task", actualTaskDescription);
		assertEquals("Wrong Task DurationTime expected 5 but was " + actualDurationTime, 5, actualDurationTime);
		assertEquals("Wrong Task TaskID expected 1 but was " + actualMaxWindow, 1, actualMaxWindow);
		//returns the statement when actual != expected
	}
	
	@Test
	public void testTaskGetTaskID() {
		//test for getTaskID
		Task testTask = new Task(1,"Tested Task",5,1);
		//Instantiation of Task Constructor
		int actual = testTask.getTaskID();
		//actual gets the taskID from testTask
		assertEquals("Wrong Task TaskID expected 1 but was " + actual, 1, actual);
		//returns the statement when actual != expected
	}
	
	@Test
	public void testTaskGetDuration() {
		//test for getDuration
		Task testTask = new Task(1,"Tested Task",5,1);
		//Instantiation of Task Constructor
		int actual = testTask.getDuration();
		//actual gets the Duration from testTask
		assertEquals("Wrong Task Duration expected 5 but was " + actual, 5, actual);
		//returns the statement when actual != expected
	}
	
	@Test
	public void testTaskGetMaxWindow() {
		//test for getMaxWindow
		Task testTask = new Task(1,"Tested Task",5,1);
		//Instantiation of Task Constructor
		int actual = testTask.getMaxWindow();
		//actual gets maxWindow for testTask
		assertEquals("Wrong Task MaxWindow expected 1 but was " + actual, 1, actual);
		//returns the statement when actual != expected
	}
	
	@Test
	public void testTaskGetDescription() {
		//test for getDescription
		Task testTask = new Task(1,"Tested Task",5,1);
		//Instantiation of Task Constructor
		String actual = testTask.getDescription();
		//actual gets description for testTask
		assertEquals("Wrong Task TaskDescription, expected 'Tested Task' but was " + actual, "Tested Task", actual);
		//returns the statement when actual != expected
	}
	
	@Test
	public void testTaskSetDescription() {
		//test setDescription
		Task testTask = new Task(1,"Tested Task",5,1);
		//Instantiation of Task Constructor
		testTask.setDescription("New Tested Task Description");
		// sets the description to 'New Tested Task Description'
		String actual = testTask.getDescription();
		// actual gets the description from testTask
		assertEquals("Wrong Task TaskDescription, expected 'New Tested Task Description' but was " + actual,"New Tested Task Description", actual);
		//returns the statement when actual != expected
	}
	
	@Test
	public void testTaskSetTaskID() {
		//test for setTaskID
		Task testTask = new Task(1,"Tested Task",5,1);
		//Instantiation of Task Constructor
		testTask.setTaskID(10);
		//sets the taskID to 10;
		int actual = testTask.getTaskID();
		// actual gets the duration from testTask
		assertEquals("Wrong Task TaskID, expected 10 but was " + actual ,10, actual);
		//returns the statement when actual != expected
	}
	
	@Test
	public void testTaskSetDuration() {
		//test for setDuration
		Task testTask = new Task(1,"Tested Task",5,1);
		//Instantiation of Task Constructor
		testTask.setDuration(20);
		//sets the duration to 20;
		int actual = testTask.getDuration();
		// actual gets the duration from testTask
		assertEquals("Wrong Task DurationTime, expected 20 but was " + actual ,20, actual);
		//returns the statement when actual != expected
	}
	
	@Test
	public void testTaskSetMaxWindow() {
		//test setMaxWindow
		Task testTask = new Task(1,"Tested Task",5,1);
		//Instantiation of Task Constructor
		testTask.setMaxWindow(4);
		//sets the maxWindow to 4;
		int actual = testTask.getMaxWindow();
		// actual gets the maxWindow from testTask
		assertEquals("Wrong Task MaxWindow, expected 4 but was " + actual ,4, actual);
		//returns the statement when actual != expected
	}
	
	//Animal Class tests:
	@Test
	public void testAnimalConstructor() {
		//test for Animal constructor
		Animal testAnimal = new Animal(1,"TestedAnimal","coyote");
		//instantiation of Animal Constructor
		int animalID = testAnimal.getAnimalID();
		//animalID gets the animalID from testAnimal
		String animalNickname = testAnimal.getAnimalNickname();
		//animalNickname gets the animalNickname from testAnimal
		String animalType = testAnimal.getAnimalType();
		//animalType gets the animalType from testAnimal
		assertEquals("Wrong Animal AnimalID, expected 1 but was " + animalID,1, animalID);
		assertEquals("Wrong Animal AnimalNickName, expected 'TestedAnimal' but was " + animalNickname,"TestedAnimal", animalNickname);
		assertEquals("Wrong Animal AnimalType, expected 'coyote' but was " + animalType,"coyote", animalType);
		//returns the statement when actual != expected

		int feedingStartingHour = testAnimal.getFeedingStartHour();
		//feedingStartingHour gets the feedStartHour from testAnimal
		int feedingPreparationTime = testAnimal.getFeedPrepDuration();
		//feedingPreparationTime gets the feedPrepTime from testAnimal
		String animalSpecies = testAnimal.getAnimalSpecies();
		//feedingStartingHour gets the feedStartHour from testAnimal
		int feedEndHour = testAnimal.calculateFeedEndHour();
		//feedingStartingHour gets the feedStartHour from testAnimal
		assertEquals("Wrong Animal FeedingStartHour, expected 19 but was " + feedingStartingHour,19, feedingStartingHour);
		assertEquals("Wrong Animal FeedingPreparationTime, expected 10 but was " + feedingPreparationTime, 10, feedingPreparationTime);
		assertEquals("Wrong Animal AnimalSpecies, expected 'crepuscular' but was " + animalSpecies,"crepuscular", animalSpecies);
		assertEquals("Wrong Animal AnimalFeedEndHour, expected 22 but was " + feedEndHour,22, feedEndHour);
		assertFalse(testAnimal.isOrphanAnimal());	
		//returns the statement when actual != expected
		
		//this test makes sure that the argument provided are being assigned to the correct variables

	}

	@Test
	public void testAnimalCalculateFeedEndHour() {
		Animal testAnimal = new Animal(1,"TestedAnimal","coyote");
		//instantiation of Animal Constructor
		int expected = testAnimal.getFeedingStartHour() + 3;
		int actual = testAnimal.calculateFeedEndHour();
		assertEquals("Wrong Animal FeedEndHour, expected " + expected + " but was " + actual, expected, actual);
		//returns the statement when actual != expected

		//this test ensures that the method calculates the correct endHour for feeding specific animals
	}
	
	@Test 
	public void testAnimalGetAnimalID() {
		Animal testAnimal = new Animal(1,"TestedAnimal","coyote");
		//instantiation of Animal Constructor
		int expected = 1;
		int actual = testAnimal.getAnimalID();
		assertEquals("Wrong Animal AnimalID, expected " + expected + " but was " + actual, expected, actual);
		//returns the statement when actual != expected
		
		//this test ensures that the method gets the correct animalID of a specific animal
	}
	
	@Test
	public void testAnimalGetAnimalNickname() {
		Animal testAnimal = new Animal(1,"TestedAnimal","coyote");
		//instantiation of Animal Constructor
		String expected = "TestedAnimal";
		String actual = testAnimal.getAnimalNickname();
		assertEquals("Wrong Animal AnimalNickname, expected " + expected + " but was " + actual, expected, actual);
		//returns the statement when actual != expected
		
		//this test ensures that the method gets the correct animalNickname of a specific animal
	}
	
	@Test
	public void testAnimalGetAnimalSpecies() {
		Animal testAnimal = new Animal(1,"TestedAnimal","coyote");
		//instantiation of Animal Constructor
		String expected = "crepuscular";
		String actual = testAnimal.getAnimalSpecies();
		assertEquals("Wrong Animal AnimalSpecies, expected " + expected + " but was " + actual, expected, actual);
		//returns the statement when actual != expected

		//this test ensures that the method gets the correct animalSpecies of a specific animal

	}
	
	@Test
	public void testAnimalGetTasks() {
		Animal testAnimal = new Animal(1,"TestedAnimal","coyote");
		//instantiation of Animal Constructor
		Task[] tasks = new Task[1];
		tasks[0] = new Task(1111, "Cleaning", 10, 3);
		testAnimal.setTasks(tasks);
		Task[] expected = tasks;
		Task[] actual = testAnimal.getTasks();
		assertArrayEquals("Wrong Animal Tasks, expected " + expected + " but was " + actual, expected, actual);
		//returns the statement when actual != expected
		
		//this test ensures that the method gets the correct tasks for a specific animal

	}
	
	@Test
	public void testAnimalGetTreatments() {
		Animal testAnimal = new Animal(1,"TestedAnimal","coyote");
		//instantiation of Animal Constructor
		Treatment[] treatments = new Treatment[1];
		treatments[0] = new Treatment(11, testAnimal.getAnimalID(),2222,4);
		testAnimal.setTreatments(treatments);
		Treatment[] expected = treatments;
		Treatment[] actual = testAnimal.getTreatments();
		assertArrayEquals("Wrong Animal Treatments, expected " + expected + " but was " + actual, expected, actual);
		//returns the statement when actual != expected

		//this test ensures that the method gets the correct Treatments for a specific animal

	}
	
	@Test
	public void testAnimalSetAnimalID() {
		Animal testAnimal = new Animal(1,"TestedAnimal","coyote");
		//instantiation of Animal Constructor
		testAnimal.setAnimalID(5);
		int expected = 5;
		int actual = testAnimal.getAnimalID();
		assertEquals("Wrong Animal AnimalID, expected " + expected + " but was " + actual, expected, actual);
		//returns the statement when actual != expected

		//this test ensures that the method sets the correct AnimalID for a specific animal
	}
	
	@Test
	public void testAnimalSetAnimalNickname() {
		Animal testAnimal = new Animal(1,"TestedAnimal","coyote");
		//instantiation of Animal Constructor
		testAnimal.setAnimalNickname("new nickname");
		String expected = "new nickname";
		String actual = testAnimal.getAnimalNickname();
		assertEquals("Wrong Animal AnimalNickName, expected " + expected + " but was " + actual, expected, actual);
		//returns the statement when actual != expected

		//this test ensures that the method sets the correct AnimalNickname for a specific animal

	}
	
	@Test
	public void testAnimalSetAnimalType() {
		Animal testAnimal = new Animal(1,"TestedAnimal","coyote");
		//instantiation of Animal Constructor
		testAnimal.setAnimalType("fox");
		String expected = "fox";
		String actual = testAnimal.getAnimalType();
		assertEquals("Wrong Animal AnimalType, expected " + expected + " but was " + actual, expected, actual);
		//returns the statement when actual != expected

		//this test ensures that the method sets the correct AnimalType for a specific animal

	}

	@Test
	public void testAnimalSetTasks() {
		Animal testAnimal = new Animal(1,"TestedAnimal","coyote");
		//instantiation of Animal Constructor
		Task[] tasks = new Task[1];
		tasks[0] = new Task(1111, "Cleaning", 10, 3);
		testAnimal.setTasks(tasks);
		Task[] expected = tasks;
		Task[] actual = testAnimal.getTasks();
		assertArrayEquals("Wrong Animal Tasks, expected " + expected + " but was " + actual, expected, actual);
		//returns the statement when actual != expected
		
		//this test ensures that the method sets the correct Tasks for a specific animal

	}
	@Test
	public void testAnimalSetTreatments() {
		Animal testAnimal = new Animal(1,"TestedAnimal","coyote");
		//instantiation of Animal Constructor
		Treatment[] treatments = new Treatment[1];
		treatments[0] = new Treatment(11, testAnimal.getAnimalID(),2222,4);
		testAnimal.setTreatments(treatments);
		Treatment[] expected = treatments;
		Treatment[] actual = testAnimal.getTreatments();
		assertArrayEquals("Wrong Animal Treatments, expected " + expected + " but was " + actual, expected, actual);
		//returns the statement when actual != expected

		//this test ensures that the method sets the correct Treatments for a specific animal
	}
	
	@Test
	public void testAnimalSetAnimalSpecies() {
		Animal testAnimal = new Animal(1,"TestedAnimal","coyote");
		//instantiation of Animal Constructor
		String expected = "porcupine";
		testAnimal.setAnimalSpecies(expected);
		String actual = testAnimal.getAnimalSpecies();
		assertEquals("Wrong Animal AnimalSpecies, expected " + expected + " but was " + actual, expected, actual);
		//returns the statement when actual != expected
		
		//this test ensures that the method sets the correct AnimalSpecies for a specific animal

	}
	
	@Test
	public void testAnimalGetCageCleanDuration() {
		Animal testAnimal = new Animal(1,"TestedAnimal","porcupine");
		//instantiation of Animal Constructor
		int expected = 10;
		int actual = testAnimal.getCageCleanDuration();
		assertEquals("Wrong Animal CageCleanDuration, expected " + expected + " but was " + actual, expected, actual);
		//returns the statement when actual != expected

		//this test ensures that the method gets the correct cageCleanDuration for a specific animal
	}
	
	@Test
	public void testAnimalGetFeedingDuration() {
		Animal testAnimal = new Animal(1,"TestedAnimal","coyote");
		//instantiation of Animal Constructor
		int expected = 5;
		int actual = testAnimal.getFeedingDuration();
		assertEquals("Wrong Animal FeedingDuration, expected " + expected + " but was " + actual, expected, actual);
		//returns the statement when actual != expected

		//this test ensures that the method gets the correct feedingDuration for a specific animal

	}
	
	@Test
	public void testAnimalGetFeedingStartHour() {
		Animal testAnimal = new Animal(1,"TestedAnimal","coyote");
		//instantiation of Animal Constructor
		int expected = 19;
		int actual = testAnimal.getFeedingStartHour();
		assertEquals("Wrong Animal FeedingStartHour, expected " + expected + " but was " + actual, expected, actual);
		//returns the statement when actual != expected

		//this test ensures that the method gets the correct feedingStartHour for a specific animal
	}
	
	@Test
	public void testAnimalGetFeedPrepDuration() {
		Animal testAnimal = new Animal(1,"TestedAnimal","coyote");
		//instantiation of Animal Constructor
		int expected = 10;
		int actual = testAnimal.getFeedPrepDuration();
		assertEquals("Wrong Animal FeedPrepDuration, expected " + expected + " but was " + actual, expected, actual);
		//returns the statement when actual != expected
		
		//this test ensures that the method gets the correct feedPrepDuration for a specific animal

	}
	
	@Test
	public void testAnimalIsBeenFed() {
		Animal testAnimal = new Animal(1,"TestedAnimal","coyote");
		//instantiation of Animal Constructor
		testAnimal.setBeenFed(false);
		assertFalse("not fed", testAnimal.isBeenFed());
		testAnimal.setBeenFed(true);
		assertTrue("Been fed", testAnimal.isBeenFed());
		//returns the statement when actual != expected
		
		//this test ensures that the method returns true or false when animal has been fed or not respectively

	}
	
	@Test
	public void testAnimalGetAnimalType() {
		Animal testAnimal = new Animal(1,"TestedAnimal","coyote");
		//instantiation of Animal Constructor
		String expected = "coyote";
		String actual = testAnimal.getAnimalType();
		assertEquals("Wrong Animal AnimalType, expected " + expected + " but was " + actual, expected, actual);
		//returns the statement when actual != expected

		//this test ensures that the method gets the correct AnimalType for a specific animal

	}
	
	@Test
	public void testAnimalIsCageCleaned() {
		Animal testAnimal = new Animal(1,"TestedAnimal","coyote");
		//instantiation of Animal Constructor
		testAnimal.setCageCleaned(false);
		assertFalse("Not cleaned", testAnimal.isCageCleaned());
		testAnimal.setCageCleaned(true);
		assertTrue("Cleaned", testAnimal.isCageCleaned());
		//returns the statement when actual != expected

		//this test ensures that the method returns true or false when cage has been cleaned or not respectively

	}
	
	@Test
	public void testAnimalSetBeenFed() {
		Animal testAnimal = new Animal(1,"TestedAnimal","coyote");
		//instantiation of Animal Constructor
		testAnimal.setBeenFed(false);
		assertFalse("Not fed", testAnimal.isBeenFed());
		testAnimal.setBeenFed(true);
		assertTrue("Fed", testAnimal.isBeenFed());
		//returns the statement when actual != expected
		
		//this test ensures that the method returns true or false about whether the animal has been fed or not respectively

	}
	
	@Test
	public void testAnimalSetCageCleanDuration() {
		Animal testAnimal = new Animal(1,"TestedAnimal","porcupine");
		//instantiation of Animal Constructor
		int expected = 50;
		testAnimal.setCageCleanDuration(expected);
		int actual = testAnimal.getCageCleanDuration();
		assertEquals("Wrong Animal CageCleanDuration, expected " + expected + " but was " + actual, expected, actual);
		//returns the statement when actual != expected
		
		//this test ensures that the method gets the correct cageCleaningDuration for a specific animal

	}
	
	@Test
	public void testAnimalSetCageCleaned() {
		Animal testAnimal = new Animal(1,"TestedAnimal","coyote");
		//instantiation of Animal Constructor
		testAnimal.setCageCleaned(false);
		assertFalse("Not cleaned", testAnimal.isCageCleaned());
		testAnimal.setCageCleaned(true);
		assertTrue("Cleaned", testAnimal.isCageCleaned());
		//returns the statement when actual != expected

		//this test ensures that the method returns true or false about whether the cage has been cleaned or not respectively

	}
	
	@Test
	public void testAnimalSetFeedingStartHour() {
		Animal testAnimal = new Animal(1,"TestedAnimal","coyote");
		//instantiation of Animal Constructor
		int expected = 30;
		testAnimal.setFeedingStartHour(expected);
		int actual = testAnimal.getFeedingStartHour();
		assertEquals("Wrong Animal FeedingStartingHour, expected " + expected + " but was " + actual, expected, actual);
		//returns the statement when actual != expected

		//this test ensures that the method gets the correct feedingStartingHour for a specfic animal

	}
	
	@Test
	public void testAnimalSetFeedPrepDuration() {
		Animal testAnimal = new Animal(1,"TestedAnimal","coyote");
		//instantiation of Animal Constructor
		int expected = 20;
		testAnimal.setFeedPrepDuration(expected);
		int actual = testAnimal.getFeedPrepDuration();
		assertEquals("Wrong Animal FeedingPrepDuration, expected " + expected + " but was " + actual, expected, actual);
		//returns the statement when actual != expected
		
		//this test ensures that the method gets the correct feedPrepDuration for a specfic animal

	}
	
	@Test
	public void testAnimalIsOrphanAnimal() {
		Animal testAnimal1 = new Animal(1,"Annie, Oliver and Mowgli","coyote");
		Treatment treat = new Treatment(1, 1 ,1 ,1);
		Task task = new Task(1, "feeding", 2, 2);
		HashMap<Integer,Animal> animlist = new HashMap<>();
		animlist.put(1, testAnimal1);
		HashMap<Integer,Task> tasklist = new HashMap<>();
		tasklist.put(1, task);
		HashMap<Integer,Treatment> treatlist = new HashMap<>();
		treatlist.put(1, treat);
		AnimalRecords animrec = new AnimalRecords(animlist, treatlist, tasklist);

		assertTrue("Is orphan", testAnimal1.isOrphanAnimal());
		// Animal testAnimal2 = new Animal(1,"TestedAnimal","coyote");
		// assertFalse("Not orphan", testAnimal2.isOrphanAnimal());
		
		//returns true if animal is orphan
	}
	
	//AnimalRecords Class tests:
	@Test
	public void TestAnimalRecordsConstructor() {
		HashMap<Integer, Animal> animalList = new HashMap<>();
		animalList.put(1, new Animal(1, "Miky", "coyote"));
		animalList.put(2, new Animal(2, "Harry", "fox"));
		HashMap<Integer, Task> taskList = new HashMap<>();
		taskList.put(3, new Task(1111, "Killing", 10, 2));
		taskList.put(4, new Task(2222, "Chopping", 5, 1));
		HashMap<Integer, Treatment> treatmentList = new HashMap<>();
		treatmentList.put(5, new Treatment(33,1, 1111, 3));
		treatmentList.put(6, new Treatment(44, 2, 2222, 4));
		AnimalRecords animrec = new AnimalRecords(animalList, treatmentList, taskList);
		assertEquals("Wrong AnimalRecords animalList, expected " + animalList + " but was " + animrec.getAnimalList(), animalList, animrec.getAnimalList());
		assertEquals("Wrong AnimalRecords taskList, expected " + taskList + " but was " + animrec.getTaskList(), taskList, animrec.getTaskList());
		assertEquals("Wrong AnimalRecords treatmentList, expected " + treatmentList + " but was " + animrec.getTreatmentList(), treatmentList, animrec.getTreatmentList());

		//this test makes sure that the argument provided are being assigned to the correct variables

	}
	
	@Test
	public void testAnimalRecordsGetAnimalList() {
		HashMap<Integer, Animal> animalList = new HashMap<>();
		animalList.put(1, new Animal(1, "Miky", "coyote"));
		animalList.put(2, new Animal(2, "Harry", "fox"));
		HashMap<Integer, Task> taskList = new HashMap<>();
		taskList.put(3, new Task(1111, "Killing", 10, 2));
		taskList.put(4, new Task(2222, "Chopping", 5, 1));
		HashMap<Integer, Treatment> treatmentList = new HashMap<>();
		treatmentList.put(5, new Treatment(33,3, 3333, 3));
		treatmentList.put(6, new Treatment(44, 4, 4444, 4));
		new AnimalRecords(animalList, treatmentList, taskList);
		HashMap<Integer, Animal> expected = animalList; 
		HashMap<Integer, Animal> actual = AnimalRecords.getAnimalList();
		assertEquals("Wrong AnimalRecords animalList, expected " + expected + " but was " + actual, expected, actual);
		
		//this test makes sure that the method gets the correct animalList from AnimalRecords 
	}
	
	@Test
	public void testAnimalRecordsGetTaskList() {
		HashMap<Integer, Animal> animalList = new HashMap<>();
		animalList.put(1, new Animal(1, "Miky", "coyote"));
		animalList.put(2, new Animal(2, "Harry", "fox"));
		HashMap<Integer, Task> taskList = new HashMap<>();
		taskList.put(3, new Task(1111, "Killing", 10, 2));
		taskList.put(4, new Task(2222, "Chopping", 5, 1));
		HashMap<Integer, Treatment> treatmentList = new HashMap<>();
		treatmentList.put(5, new Treatment(33,3, 3333, 3));
		treatmentList.put(6, new Treatment(44, 4, 4444, 4));
		new AnimalRecords(animalList, treatmentList, taskList);
		HashMap<Integer, Task> expected = taskList; 
		HashMap<Integer, Task> actual = AnimalRecords.getTaskList();
		assertEquals("Wrong AnimalRecords taskList, expected " + expected + " but was " + actual, expected, actual);
		
		//this test makes sure that the method gets the correct taskList from AnimalRecords 
	}
	
	@Test
	public void testAnimalRecordsSetAnimalList() {
		HashMap<Integer, Animal> animalList = new HashMap<>();
		animalList.put(1, new Animal(1, "Miky", "coyote"));
		animalList.put(2, new Animal(2, "Harry", "fox"));
		HashMap<Integer, Task> taskList = new HashMap<>();
		taskList.put(3, new Task(1111, "Killing", 10, 2));
		taskList.put(4, new Task(2222, "Chopping", 5, 1));
		HashMap<Integer, Treatment> treatmentList = new HashMap<>();
		treatmentList.put(5, new Treatment(33,3, 3333, 3));
		treatmentList.put(6, new Treatment(44, 4, 4444, 4));
		AnimalRecords animalRecords = new AnimalRecords(animalList, treatmentList, taskList);
		HashMap<Integer, Animal> newAnimalList = new HashMap<>();
		newAnimalList.put(10, new Animal(10, "Miky10", "coyote"));
		newAnimalList.put(2, new Animal(2, "Harry10", "fox"));
		HashMap<Integer, Animal> expected = newAnimalList;
		animalRecords.setAnimalList(newAnimalList);
		HashMap<Integer, Animal> actual = animalRecords.getAnimalList();
		assertEquals("Wrong AnimalRecords animalList, expected " + expected + " but was " + actual, expected, actual);
		
		//this test makes sure that the method sets the correct animalList to AnimalRecords 
	}
	
	@Test
	public void testAnimalRecordsSetTaskList() {
		HashMap<Integer, Animal> animalList = new HashMap<>();
		animalList.put(1, new Animal(1, "Miky", "coyote"));
		animalList.put(2, new Animal(2, "Harry", "fox"));
		HashMap<Integer, Task> taskList = new HashMap<>();
		taskList.put(3, new Task(1111, "Killing", 10, 2));
		taskList.put(4, new Task(2222, "Chopping", 5, 1));
		HashMap<Integer, Treatment> treatmentList = new HashMap<>();
		treatmentList.put(5, new Treatment(33,3, 3333, 3));
		treatmentList.put(6, new Treatment(44, 4, 4444, 4));
		AnimalRecords animalRecords = new AnimalRecords(animalList, treatmentList, taskList);
		HashMap<Integer, Task> newTaskList = new HashMap<>();
		newTaskList.put(11, new Task(8888, "Feeding", 20,12));
		newTaskList.put(12, new Task(9999, "Cleaning",10,10));
		HashMap<Integer, Task> expected = newTaskList;
		animalRecords.setTaskList(newTaskList);
		HashMap<Integer, Task> actual = animalRecords.getTaskList();
		assertEquals("Wrong AnimalRecords taskList, expected " + expected + " but was " + actual, expected, actual);
		
		//this test makes sure that the method sets the correct taskList to AnimalRecords 

	}
	
	@Test
	public void testAnimalRecordsSetTreatmentList() {
		HashMap<Integer, Animal> animalList = new HashMap<>();
		animalList.put(1, new Animal(1, "Miky", "coyote"));
		animalList.put(2, new Animal(2, "Harry", "fox"));
		HashMap<Integer, Task> taskList = new HashMap<>();
		taskList.put(3, new Task(1111, "Killing", 10, 2));
		taskList.put(4, new Task(2222, "Chopping", 5, 1));
		HashMap<Integer, Treatment> treatmentList = new HashMap<>();
		treatmentList.put(5, new Treatment(33,3, 3333, 3));
		treatmentList.put(6, new Treatment(44, 4, 4444, 4));
		AnimalRecords animalRecords = new AnimalRecords(animalList, treatmentList, taskList);
		HashMap<Integer, Treatment> newTreatmentList = new HashMap<>();
		newTreatmentList.put(15, new Treatment(66, 6, 6666, 15 ));
		newTreatmentList.put(16, new Treatment(77, 7, 7777, 20 ));
		HashMap<Integer, Treatment> expected = newTreatmentList;
		animalRecords.setTreatmentList(newTreatmentList);
		HashMap<Integer, Treatment> actual = animalRecords.getTreatmentList();
		assertEquals("Wrong AnimalRecords treatmentList, expected " + expected + " but was " + actual, expected, actual);
		
		//this test makes sure that the method sets the correct treatmentList to AnimalRecords 

	}
	
	@Test
	public void testAnimalRecordsGetTreatmentList() {
		HashMap<Integer, Animal> animalList = new HashMap<>();
		animalList.put(1, new Animal(1, "Mike", "coyote"));
		animalList.put(2, new Animal(2, "Potter", "fox"));
		HashMap<Integer, Task> taskList = new HashMap<>();
		taskList.put(3, new Task(1111, "Killing", 10, 2));
		taskList.put(4, new Task(2222, "Chopping", 5, 1));
		HashMap<Integer, Treatment> treatmentList = new HashMap<>();
		treatmentList.put(5, new Treatment(33,3, 3333, 3));
		treatmentList.put(6, new Treatment(44, 4, 4444, 4));
		new AnimalRecords(animalList, treatmentList, taskList);
		HashMap<Integer, Treatment> expected = treatmentList; 
		HashMap<Integer, Treatment> actual = AnimalRecords.getTreatmentList();;
		assertEquals("Wrong AnimalRecords treatmentList, expected " + expected + " but was " + actual, expected, actual);
		
		//this test makes sure that the method gets the correct treatmentList from AnimalRecords 

	}
	//Schedule Class Tests:
	 @Test
	    /*
	    Two tasks with 45 minute duration and 2 hour window should be scheduled for the two separate hours
	     */
	    public void testTwoHourWindow() throws IllegalScheduleException {
	        HashMap<Integer, Treatment> treatmentList = new HashMap<>();
	        HashMap<Integer, Animal> animalList = new HashMap<>();
	        HashMap<Integer, Task> taskList = new HashMap<>();

	        taskList.put(1, new Task(1, "Task1", 45, 2));
	        treatmentList.put(1, new Treatment(1, 1, 1, 12));
	        treatmentList.put(2, new Treatment(2, 1, 1, 12));
	        animalList.put(1, new Animal(1, "Bob", "coyote"));

	        new AnimalRecords(animalList, treatmentList, taskList);
	        boolean c1 = false;
	        if (new Schedule().getTheSchedule().get(12).get(1).contains("Task1") && new Schedule().getTheSchedule().get(13).get(1).contains("Task1")){
	            c1 = true;
	        }
	        assertTrue("Task isn't split between 2 hours ", c1);
	    }

	    @Test
	    /*
	    Two tasks with 45 minute duration and 1 hour window should be scheduled for the same hour and call backup volunteer
	     */
	    public void testBackupVolunteer() throws IllegalScheduleException {
	        HashMap<Integer, Treatment> treatmentList = new HashMap<>();
	        HashMap<Integer, Animal> animalList = new HashMap<>();
	        HashMap<Integer, Task> taskList = new HashMap<>();

	        taskList.put(1, new Task(1, "Task1", 45, 1));
	        treatmentList.put(1, new Treatment(1, 1, 1, 12));
	        treatmentList.put(2, new Treatment(2, 1, 1, 12));
	        animalList.put(1, new Animal(1, "Bob", "coyote"));

	        new AnimalRecords(animalList, treatmentList, taskList);
	        boolean c1 = false;
	        boolean c2 = false;
	        if (new Schedule().getTheSchedule().get(12).get(1).contains("Task1") && new Schedule().getTheSchedule().get(12).get(2).contains("Task1")){
	            c1 = true;
	        }
	        if (new Schedule().getTheSchedule().get(12).get(0).contains("backup")){
	            c2 = true;
	        }
	        assertTrue("Task isn't split between 2 hours ", c1&&c2);
	    }

	    @Test
	    /*
	    Three tasks with 45 minute duration and 1 hour window should return
	     */
	    public void testThrowExceptionWithTooManyTreatments() {
	        HashMap<Integer, Treatment> treatmentList = new HashMap<>();
	        HashMap<Integer, Animal> animalList = new HashMap<>();
	        HashMap<Integer, Task> taskList = new HashMap<>();

	        taskList.put(1, new Task(1, "Task1", 45, 1));
	        treatmentList.put(1, new Treatment(1, 1, 1, 12));
	        treatmentList.put(2, new Treatment(2, 1, 1, 12));
	        treatmentList.put(3, new Treatment(3, 1, 1, 12));
	        animalList.put(1, new Animal(1, "Bob", "coyote"));

	        new AnimalRecords(animalList, treatmentList, taskList);
	        boolean c1 = false;
	        try {
	            new Schedule();
	        }
	        catch(IllegalScheduleException e){
	            c1 = true;
	        }
	        catch(Exception e){
	        }
	        assertTrue("Schedule does not throw IllegalScheduleException when given treatments that cannot be all done within hour", c1);
	    }

	    @Test
	    /*
	    Check to see if treatmentID 1's animal is considered an orphan
	     */
	    public void testIsOrphan(){
	        HashMap<Integer, Treatment> treatmentList = new HashMap<>();
	        HashMap<Integer, Animal> animalList = new HashMap<>();
	        HashMap<Integer, Task> taskList = new HashMap<>();

	        taskList.put(1, new Task(1, "Task1", 45, 1));
	        treatmentList.put(1, new Treatment(1, 1, 1, 12));
	        animalList.put(1, new Animal(1, "Bob", "coyote"));

	        new AnimalRecords(animalList, treatmentList, taskList);
	        assertTrue("TreatmentID 1's animal is not considered an orphan", animalList.get(1).isOrphanAnimal());
	    }
	    @Test
	    /*
	    Check to see if orphan's feeding task is considered only a medical task
	     */
	    public void testOrphanNotConsideredFeedingTask(){
	        HashMap<Integer, Treatment> treatmentList = new HashMap<>();
	        HashMap<Integer, Animal> animalList = new HashMap<>();
	        HashMap<Integer, Task> taskList = new HashMap<>();

	        taskList.put(1, new Task(1, "Task1", 45, 1));
	        treatmentList.put(1, new Treatment(1, 1, 1, 12));
	        animalList.put(1, new Animal(1, "Bob", "coyote"));

	        new AnimalRecords(animalList, treatmentList, taskList);

	        boolean c1 = false;
	        try {
	            c1 = new Schedule().toString().contains("Feeding");
	        }
	        catch(Exception e){
	        }
	        assertFalse("Regular feeding task should not be in tasks for orphans", c1);
	    }

	    @Test
	    /*
	    Check if every animal's cage has been cleaned
	     */
	    public void allCagesCleaned(){
	        HashMap<Integer, Treatment> treatmentList = new HashMap<>();
	        HashMap<Integer, Animal> animalList = new HashMap<>();
	        HashMap<Integer, Task> taskList = new HashMap<>();

	        animalList.put(1, new Animal(1, "Bob", "coyote"));
	        animalList.put(2, new Animal(2, "Bartholomew", "coyote"));
	        animalList.put(3, new Animal(3, "Mark", "porcupine"));
	        animalList.put(4, new Animal(4, "Josh", "beaver"));

	        new AnimalRecords(animalList, treatmentList, taskList);

	        boolean c1 = true;
	        String theString = "";
	        try {
	            theString = new Schedule().toString();
	        }
	        catch(Exception e){
	        }
	        for(int i = 1; i <= 4; i++){
	            if(!theString.contains("Cage Cleaning - " + animalList.get(i).getAnimalNickname())){
	                c1 = false;
	            }
	        }
	        assertTrue("Cage Cleaning Tasks for all Animals are not all added", c1);
	    }

	    @Test
	    /*
	    Check if every animal's cage has been cleaned
	     */
	    public void allTreatmentsDone(){
	        HashMap<Integer, Treatment> treatmentList = new HashMap<>();
	        HashMap<Integer, Animal> animalList = new HashMap<>();
	        HashMap<Integer, Task> taskList = new HashMap<>();

	        taskList.put(1, new Task(1, "Task1", 45, 2));
	        taskList.put(2, new Task(2, "Task2", 45, 2));
	        taskList.put(3, new Task(3, "Task3", 45, 2));
	        treatmentList.put(1, new Treatment(1, 1, 1, 12));
	        treatmentList.put(2, new Treatment(2, 2, 2, 13));
	        treatmentList.put(3, new Treatment(3, 1, 3, 14));
	        treatmentList.put(4, new Treatment(4, 2, 1, 15));
	        treatmentList.put(5, new Treatment(5, 1, 2, 16));
	        animalList.put(1, new Animal(1, "Bob", "coyote"));
	        animalList.put(2, new Animal(2, "Bartholomew", "porcupine"));


	        new AnimalRecords(animalList, treatmentList, taskList);

	        boolean c1 = true;
	        String theString = "";
	        try {
	            theString = new Schedule().toString();
	        }
	        catch(Exception e){
	        }
	        for(int i = 1; i <= 5; i++){
	            if(!theString.contains(taskList.get(treatmentList.get(i).getTaskID()).getDescription())){
	                c1 = false;
	            }
	        }
	        assertTrue("Not all treatments added properly", c1);
	    }
	    @Test
	    /*
	    Check if every animal has been fed
	     */
	    public void allAnimalsFed(){
	        HashMap<Integer, Treatment> treatmentList = new HashMap<>();
	        HashMap<Integer, Animal> animalList = new HashMap<>();
	        HashMap<Integer, Task> taskList = new HashMap<>();

	        animalList.put(1, new Animal(1, "Bob", "coyote"));
	        animalList.put(2, new Animal(2, "Bartholomew", "porcupine"));
	        animalList.put(3, new Animal(3, "Barth", "porcupine"));
	        animalList.put(4, new Animal(4, "Josh", "coyote"));
	        animalList.put(5, new Animal(5, "Drake", "beaver"));
	        animalList.put(6, new Animal(6, "George", "porcupine"));

	        new AnimalRecords(animalList, treatmentList, taskList);

	        boolean c1 = true;
	        String theString = "";
	        try {
	            theString = new Schedule().toString();
	        }
	        catch(Exception e){
	        }
	        for(int i = 1; i <= 6; i++){
	            theString = theString.replace("Cage Cleaning - " + animalList.get(i).getAnimalNickname(), "");
	            if(!theString.contains(animalList.get(i).getAnimalNickname())){
	                c1 = false;
	            }
	        }
	        assertTrue("Not all animal feeding tasks added properly", c1);
	    }

	    @Test
	    /*
	    Check if every animal has been fed
	     */
	    public void testThrowsExceptionWithTooManyFeeding(){
	        HashMap<Integer, Treatment> treatmentList = new HashMap<>();
	        HashMap<Integer, Animal> animalList = new HashMap<>();
	        HashMap<Integer, Task> taskList = new HashMap<>();

	        taskList.put(1, new Task(1, "Task1", 60, 1));
	        taskList.put(2, new Task(2, "Task2", 30, 1));
	        treatmentList.put(2, new Treatment(2, 1, 1, 19));
	        treatmentList.put(3, new Treatment(3, 1, 1, 20));
	        treatmentList.put(4, new Treatment(4, 1, 2, 21));

	        animalList.put(1, new Animal(1, "Bob", "coyote"));
	        animalList.put(2, new Animal(2, "Bartholomew", "coyote"));
	        animalList.put(3, new Animal(3, "Bartholomew", "coyote"));
	    }
	
}