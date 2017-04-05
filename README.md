# Sales Message Processor

Version 1.0 05/04/2017

A message processing application that satisfies the below requirements for processing sales notification messages. Assume that an external company will be sending the input messages, but for the purposes of this exercise you are free to define the interfaces. 


## Message Processing Requirements

* A sale has a product type field and a value – you should choose sensible types for these. 
* Any number of different product types can be expected. There is no fixed set. 
* A message notifying you of a sale could be one of the following types :
  1. Message Type 1 - contains the details of 1 sale. _E.g apple at 10p_
  2. Message Type 2 - contains the details of a sale and the number of occurrences of that sale. _E.g 20 sales of apples at 10p each_
  3. Message Type 3 - contains the details of a sale and an adjustment operation to be applied to all stored sales of this product type. Operations can be add, subtract, or multiply. _E.g Add 20p apples_ - would instruct your application to add 20p to each sale of apples you have recorded. 


## Constraints

* No database/UI required.
* Assume the code will only ever be executed in a single threaded environment.
* Minimize the number of external jar dependencies your code has. 
* All data to be stored in memory. 
* Output format to be plain text, printed out to the console. 
* Create more sample data as needed. 


## Solution

* Architecture  
This is a simple stand-alone Java based application with the following modules to separate the different components:
  * Service - Entry point for the application.
  * Model - POJO classes for defining specific entities.
  * Manager - All the code logic goes here.
  * Util - Utility/helper classes.
  * Test - Test cases for TDD implementation.

* Test Framework used - TestNG (for Test Driven Development)

* External Jar dependencies (1)
  * TestNG for TDD.

