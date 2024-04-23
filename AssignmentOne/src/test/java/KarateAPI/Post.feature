Feature: GET Api feature

Background:
  * url 'https://restful-booker.herokuapp.com'
 	* def requestpayload = 
 	"""
{
    "username" : "admin",
    "password" : "password123"
}
"""
Scenario: Create user
Given path '/auth'
And request requestpayload
When method POST
Then status 200
 * print response
 * def jsonresponse = response
 * print jsonresponse
