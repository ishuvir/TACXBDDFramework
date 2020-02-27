Feature: Tacx Smoke Automation Test

Scenario: Tacx Demo Trainer Test For Registered Users

Given user is already on TACX mobile app
When User loggedin <Username> & <Password> as registered user
Then User validate the Tacx user account
Then User select the New Ride option
Then User search <CityName> city for ride
Then User Enable location service for new trip
Then User starts the demo trip
Then User validated the features in trip 
Then User Stops the trip 
Then User logOut of application 