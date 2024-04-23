
Feature: GET Api feature
  
Scenario: Ids of all booking
Given url 'https://restful-booker.herokuapp.com'
And path 'booking'
When method GET
Then status 200
 * print response
 * def jsonresponse = response
 * print jsonresponse
 
