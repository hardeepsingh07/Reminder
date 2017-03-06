Binary Programmer

Overview
--------
A Reminder Service
Project allows user to setup bill reminders. Reminders are delivered by text messages on pecifiied remidinng times.
1. 7 Days Before Due Date -- 1 Reminder/day
2. 5 Days Before Due Date -- 2 Reminders/day
3. 3 Days Before Due Date -- 4 Reminders/day

Deployed Project
----------------
URL: http://ec2-52-32-222-6.us-west-2.compute.amazonaws.com/
Website: http://cs580.yusun.io/teams-winter2017/bProgrammers/reminder.html

Environment Setup
-----------------
1. Install the latests Maven (http://maven.apache.org/download.cgi)
2. Install the Eclipse 4+ (http://www.eclipse.org/)
3. Install the Maven Eclipse Plug-in using the update site (http://www.eclipse.org/m2e/download/) (The latest Eclipse should have Maven plug-in installed so you may not need to do this step)

Importing the Project into Eclipse
----------------------------------
1. File->Import->Maven->Existing Maven Projects
2. Select the directory containing the pom.xml file
3. Finish

Building the Project for the First Time
---------------------------------------
1. Right-click on the project root folder->Maven->Update Project

Running the Project Locally
----------------------------------------
1. Locate the App.java in src/main/java source folder and right-click on it->Run As->Java Application
2. Verify the running process in your web browser by the following URLs:
 - http://localhost:8080/home
