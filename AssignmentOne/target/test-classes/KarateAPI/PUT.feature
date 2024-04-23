Feature: GET Api feature

Background:
  * url 'https://restful-booker.herokuapp.com'
 	* def requestpayload = 
 	"""
{
    "firstname" : "James",
    "lastname" : "Brown",
    "totalprice" : 111,
    "depositpaid" : true,
    "bookingdates" : {
        "checkin" : "2018-01-01",
        "checkout" : "2019-01-01"
    },
    "additionalneeds" : "Breakfast"
}
"""
Scenario: Create user
Given path '/booking/1'
And request requestpayload
And header Content-Type = 'application/json'
And header Accept = 'application/json'
And header Authorization = 'Basic YWRtaW46cGFzc3dvcmQxMjM='
When method PUT
Then status 200
 * print response
 * def jsonresponse = response
 * print jsonresponse
