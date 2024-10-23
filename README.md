# Animal Care Scheduling System



### **Overview**

The Animal Care Scheduling System is designed to manage and automate the scheduling of several tasks including: feeding, treatment, and cage-cleaning tasks for animals in a veterinary or animal rehabilitation setting. The system organizes tasks based on priority, duration, and dependencies while ensuring that time constraints are respected.


### **Features**

Task Prioritization: Dynamically allocates priority to feeding, treatment, and cage-cleaning tasks based on urgency and resource constraints.
Automated Schedule Generation: Automatically generates a detailed 24-hour schedule with specific tasks assigned to each hour, including time estimations for tasks.
Error Handling & Validation: Validates that all tasks fit within the available time slots, and provides error feedback if tasks cannot be completed within constraints.
Backup Volunteer Assignment: Assigns a backup volunteer if critical tasks exceed available time within an hour, ensuring important tasks are completed.
Flexible Feeding Strategy: Groups animals by type to efficiently schedule feeding tasks and optimize preparation times.
GUI Display: Displays a detailed, human-readable schedule indicating tasks for each hour, including treatments, feeding, and cage-cleaning activities, including the need for backup volunteers.



### **How It Works**

Initialization: Copies the list of tasks and animals from the system database. The system separates tasks into feeding, treatment, and cage-cleaning lists.
Task Assignment: For each hour, the system attempts to allocate as many tasks as possible within the hour, based on task duration and constraints. It prioritizes treatments that must be done within a specific hour window and groups feeding tasks based on animal type.
Conflict Resolution: If tasks exceed the available hour time, a backup volunteer is assigned. If tasks exceed even with a backup volunteer, the system throws an IllegalScheduleException and suggests rescheduling.
Task Validation: At the end of the scheduling, the system checks if all feeding, treatment, and cage-cleaning tasks have been completed. If any tasks are left unassigned, the system raises an exception.


### **Technical Details**

Time and Task Management: Utilizes methods to check whether tasks can be completed within an hour and dynamically adjusts minute counts as tasks are scheduled.
Priority Queues for Tasks: Uses priority queues to prioritize tasks based on urgency and availability.
Flexible Scheduling Algorithms: Adapts to changing constraints, such as varying feeding durations, animal types, and treatment time windows.
Integration with Animal Records: Interacts with the AnimalRecords class to retrieve animals and tasks stored in a central database.
Error Detection: Throws IllegalScheduleException if tasks exceed the allocated time for an hour or if all tasks cannot be scheduled.
JUnit Testing: Conducted JUnit tests cases to validate the system's behavior under various scheduling scenarios.



### ***Possible Future Improvements***

Expand scheduling flexibility to allow different volunteer roles and capacities.
Allow using a database that is hosted on a different server (not local).
Integrate real-time data tracking to adapt schedules on the fly if tasks are delayed.


### **Running the System**

Compile the Classes: Ensure all class files are compiled.
Turn on the database: Locally host the sql database.
Execute the Main file: Run the GUI.java, and the system will automatically display the GUI to be able to enter your sql database data.

