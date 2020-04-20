# ENSF409-FinalProject
Aidan Johnson's and Michele Piperni's ENSF 409 Final Project.

Authors:
----------------------------------------------------------------------------------------------------------------------------------------------------------------
Name: Aidan Johnson 
Email: aidan.johnson1@ucalgary.ca

Name: Michele Piperni 
Email: michele.piperni@ucalgary.ca

Instructions on how to use the Milestone 3 Course Registration System:
----------------------------------------------------------------------------------------------------------------------------------------------------------------

Note: We have attached three text files in the project folder called courses.txt, Admins.txt and StudentList.txt. These will be loaded into the SQL database once
      at the start of the program.

1. First make a myDB database on your computer and change the credientials in the credentials java interface clase for a database to be created on your computer.
2. Secondly run the java program called DBManager to create and populate the Admin, Student and Course table from the three text files into the SQL database.
3. Then run the java program called ServerController (this is our server class) to start the server.
4. Run the java program called ClientController (this is our client class). You can run the java program ClientController multiple times to create multiple
   different clients as our program uses a threadpool.
5. For each time a java program called ClientController is run, a GUI will pop up allowing you to select if you are an admin or student and then filling out your
   corresponding UCID and password for the student or admin (based upon which button you select that you are logging in as).

For Student Features:
----------------------------------------------------------------------------------------------------------------------------------------------------------------
6. Enter a student's UCID number and their respective password from the StudentList.txt file (the UCID is the first number on each line and it's respective 
   password is the last piece of text on eachline) into the UCID and password text field and then press login. 
7. If the correct UCID and it's respective password was entered a new GUI frame will pop up which is the student menu allowing you to search for a catalogue
   course, add or remove a course to the repsective student you loggeed in as, view all the courses in the catalogue, view that student's courses,
   logout of the student's account or just exit the program.

For Admin Features:
----------------------------------------------------------------------------------------------------------------------------------------------------------------
6. Enter an admin's UCID number and their respective password from the Admins.txt file (the UCID is the first number on each line and it's respective 
   password is the last piece of text on eachline) into the UCID and password text field and then press login. 
7. If the correct UCID and it's respective password was entered a new GUI frame will pop up which is the admin menu allowing you to add a new course 
   to the course catalogue, view all the courses in the catalogue, logout of the admin's account or just exit the program.

Note: Logging out takes you back to the main menu where you are able to login as a different student or admin. Also by closing the frame you also exit
      the program.

Bonus Features Implemented:
----------------------------------------------------------------------------------------------------------------------------------------------------------------
1. Login and logout feature for both the admins and students by having a student and admin list.
2. Admin GUI with the functionality of creating new courses for the Database.
3. Deploying the project on two different machines (running the server and client on seperate machines).
