#Author: your.email@your.domain.com
#Keywords Summary :
#Feature: List of scenarios.
#Scenario: Business rule through list of steps with arguments.
#Given: Some precondition step
#When: Some key actions
#Then: To observe outcomes or validation
#And,But: To enumerate more Given,When,Then steps
#Scenario Outline: List of steps for data-driven as an Examples and <placeholder>
#Examples: Container for s table
#Background: List of steps run before each of the scenarios
#""" (Doc Strings)
#| (Data Tables)
#@ (Tags/Labels):To group Scenarios
#<> (placeholder)
#""
## (Comments)
#Sample Feature Definition Template
@tag
Feature: Expedia Search

  @tag1
  Scenario Outline: Expedia Search by providing multiple inputs
    Given I want to execute Expedia Search by passing inputs
    |browser|platform|
		|chrome|windows|
		|internetexplorer|linux|
    When I run the test suite by passing <source>, <destination>, <flyingdate>, <returningdate>
    Then I should be able to make the search successfully
    And Write output to sheet
    
     Examples: 
     |source|destination|flyingdate|returningdate|
     |LAX|SEA|04/05/2019|06/12/2019|
     |SEA|LAX|05/07/2019|08/07/2019|

