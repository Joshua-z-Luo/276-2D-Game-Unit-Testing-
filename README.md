Unit Testing!
---------------------

 * Introduction
 * How To Build
 * How To Run
 * How To Test
 * How To Build Artifacts
 * Maintainers


INTRODUCTION
------------

Unit Testing is a simple 2D action maze chase game. In the game, you play as TV Guy who is an experimental subject ready for testing. If he passes all his tests, he will evade the erasure of his code and become immortalized in the master branch. To escape, you need to collect all three key cards on each level to be able to access the staircase to the next test set. Stopping his escape will be gaping holes in the floor and savage screwdrivers designed to chase you relentlessly and ensure that you get taken apart, but be warned your batteries aren’t meant to last forever, so be sure to pick up some batteries along the way to keep you powered up! You were programmed for this TV guy, it's in your circuits! 

Link to our video: https://www.youtube.com/watch?v=fdy485CQceE 

HOW TO BUILD
------------

Ensure that you have at least JDK 18 to build the game.<br>
To have the game up and running on your machine go to Build > Build Project and the game will be ready to launch.<br>
Alternatively, you can build the project in your IDE's terminal by entering 'mvn validate' and then entering 'mvn compile'.<br>
Note: This game was built in IntelliJ using Maven.


HOW TO RUN
------------

After building the project, press the run button in your IDE and the game will start. When the starting screen shows up, you can press W and S to traverse the menu and then press Enter when you are ready for your selection.<br>
Additional instructions are shown on the menu screen as well. After selecting Play Game, the game will start running and you should be able to move the character around.


HOW TO TEST
-------------

Press the run buttons on the tests you would like to run to ensure everything is correct and running as expected. Alternatively, there is a All Test run configuration that will run all tests at once.<br>
Another way to run all tests is to bring up your IDE's terminal and enter 'mvn test', all of the tests should then start running.


HOW TO BUILD ARTIFACTS
-------------

Go to your IDE's terminal to build the JavaDocs or the JAR file.

For the JavaDocs type in the command 'mvn site' and the JavaDocs will be show up in target/site/apidocs/... as HTML files which can be opened in your browser.<br>

For the JAR file type in the command 'mvn package' and the JAR file will show up in target/site/...


MAINTAINERS
-----------

 * Rose Epstein
 * Connor Tung
 * Hayato Koyama
 * Joshua Luo



