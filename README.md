# TottoLotto
This is number lottery. Users make choise six numbers from forty nine or choice lucky dip number.

## Built With
- Java
- Maven
- MongoDB

## Installation
* Clone the repo
  ```sh
  git clone https://github.com/WJarze/TottoLottek.git
  ```
  
## Functionality
Main menu
* 0 - exit
* 1 - choice number - user draw 6 numbers from 49 available (each draw is recorded in the database)
* 2 - lucky dip number - automatic draw of 6 numbers from 49 available (each draw is recorded in the database)
* 3 - Hits - all lottery results in the current game
* 4 - hits form database - library of all draws
 
Side menu - hits form database
* 0 - exit
* 1 - choice numbers - results of user choice
* 2 - lucky dip numbers - results of random draws
 
Side menu - hits form database - choice numbers
* 0 - exit
* 1 - percentage of number hit
* 2 - number of all lotteries hit in draw
* 3 - number of all lotteries
* 4 - hit percentage
* 5 - clear database

  side menu - hits form database - lucky dip numbers
* 0 - exit
* 1 - percentage of number hit
* 2 - number of all lotteries hit in draw
* 3 - number of all lotteries
* 4 - hit percentage
* 5 - clear database

## Implementation details

Package data includes class:
* AllHitsChoiceDrawFromDB - class representing all ussers lottery writed in database
* AllHitsLuckyDipDrawFromDB - class replresenting all lucky dip lottery writed in database
* Choice - class represent the numbers recivied from users. Inherits from lotto class. Retrieves using the scanner class object as a method parameter.
  Validates the numbers retrieved from the user
* Conditions - enum class defines the conditions of the lottery 
* Hit - class represent single draw. Includes methods that are responsible for creating a Document object to be added to the database
* Hits - class represents single-game draws
* Lotto - class represents the rules of the game. In the constructor set up the game conditions using the enum class.
* LuckyDip - inherits from lotto class
* Interface Generator - contains one method to be written , intended to implement a random number generator.
* GeneratorConditions - class represents the conditions a random number generator.
* RandomGenerator - implemetation Interface Generator. Uses streams and GeneratorConditions as method arguments. 

Package databaseService includes class:
*

## Future
* adding more numerical games of chance

