Feature: GET Api feature



Scenario: ID for the booking you want to update
Given url 'https://restful-booker.herokuapp.com'
And path '/booking/4'
And header Cookie = 'token=<token_value>'
And header Authorization = 'Basic YWRtaW46cGFzc3dvcmQxMjM='
When method delete
Then status 201
