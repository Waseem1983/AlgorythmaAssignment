Algorythma Assignment
-------------------------------------

This is a simple java Maven application, as per the requirements provided in the readme.txt
the application logic is implemented as a JUnit (JUnit 5) test case.

Ruuning the application:
---------------------------
to run the application using command line, run the command
mvn -DcsvFile=CSV_FILE_PATH/CSV_FILES_DIRECTORY test
Example: mvn -DcsvFile=/home/waseem/Desktop/Backend-Dev-Test/ test

If the environment parameter -DcsvFile was not provided a FileChooser will popup asking for a CSV File/Directory
Java 8 at least need to be installed to run the application.

If you import the application in any IDE, just make sure in the run configuration to pass the -DcsvFile parameter


Description:
----------------------
After reviewing the requirements it was clear that a Weighted Graph data structure should be used to implement 
the Core of the app, hence FriendsNetwork.java and Friend.java classes represent the graph and node in our case.

And to get the optimal shortest path we implement the Djikastra algorithm the class (FriendsShipmentEvaluator.java) 
contains the implementation where we evaluate each node in the graph starting from the source and assign the optimal weight to it
for a specific search case.

The CSVFileReader.java is responsible for reading a specific .csv file, parse it and build the FriendsNetwork graph object.
in addition to creat a list of test cases represented by the class (ShipmentDetails) to represent each scenario 
that we are going to evaluate againest our implementation.


Resources:
---------------------------
Multiple Books and Websites were referenced while trying to come out with the best implementation
mainly the Book "Data Structures and Algorithms Analysis in Java"
https://www.geeksforgeeks.org/java-program-for-dijkstras-shortest-path-algorithm-greedy-algo-7/
https://www.baeldung.com/java-dijkstra

