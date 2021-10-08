# RandomSprintNameGenerator #

The RandomSprintNameGenerator is a small GUI application, which generates random names from an open source API. 
You can entry the initial letter, the names have to start with and how many names should be generated. 
If some names have been generated, you can start a voting and vote which name should be your new sprint-name.


## Information ##

The source code is basically in following files:
* `RandomSprintNameGenerator.java`: RandomSprintNameGenerator-Class has a methode, which generates random names from
the open source API with a given initial letter and the number of names that should be generated and returns a list of sprint-names.

```java
public List<SprintName> getRandomSprintNames( String character, int count)
```

This is the open source API:

```java
https://random-word-form.herokuapp.com
```

* `RunnerSprintNameGenerator.java`: In Runner-Class the RandomSprintNameGenerator-Object is created and the methode getRandomSprintNames( String character, int count) is called in here. Also the GUI is set up.

* `SprintName.java`: SprintName-Class has different attributes, a sprint-name should have.


## Introduction ##

* After the project has been imported to an IDE like Eclipse or IntelliJ, you have to run the `RunnerSprintNameGenerator.java`.

* When the programm has been started, you can entry an initial Letter, the random names should start with and how many
names should be generated. It is just possible to entry one letter and a number between 0 and 10. After that you can 
click on the button "Generate random names!".

* After some names have been generated you are able to start a voting with the button below "Start voting". 
Then you have to set the number of voters in the popup inputdialog. To vote for a name, you just have to click on the
name's "Vote"-button. The leading name will be highlighted green. If there are more names with the same amount of votes,
the names will be highlighted yellow.

* If everybody has been voted for a name, there will be a popup with the name which won the voting or if different
names have the same amount of votes, there will be a popup that you have to revote between those names.

* If you click on the button "Generate random names!" again, new names will be generated. Even if you have already started a voting. 

* The button "Vote again" will start a new voting with the same number of voters you have set before.
