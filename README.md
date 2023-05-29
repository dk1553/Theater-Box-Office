# Theater-Box-Office
## SonarCloud
[![SonarCloud](https://sonarcloud.io/images/project_badges/sonarcloud-orange.svg)](https://sonarcloud.io/summary/new_code?id=dk1553_Theater-Box-Office)

## Starten
Anwendung starten: [TheaterBoxOfficeApp.java](0-cleanproject-plugins/0-cleanproject-plugins/src/main/java/rest/TheaterBoxOfficeApp.java) \
Die Anwendung läuft standardisiert unter dem Port: 7771
#### Anwendung in Insomnia-Client benutzen
Die Vorlage muss in die Insomnia-App importiert werden:
[Insomnia JSON-Vorlage](https://github.com/dk1553/Theater-Box-Office/blob/master/0-cleanproject-plugins/0-cleanproject-plugins/src/main/java/rest/Insomnia.json)

#### Anwendung in einem anderen Client benutzen
View theater repertoire: [Get] http://localhost:7771/performances \
View theater program: [Get] http://localhost:7771/events \
View event details: [Get] http://localhost:7771/events/{eventID} \
Login (for admins only): [Post] http://localhost:7771/login 
```
{
	"admin":
		{
		
			"username": "admin",
			"password": "1234"
		}
}
```
Logout (for admins only): [Get] http://localhost:7771/logout \
Add new events: [Post] http://localhost:7771/addEvents 
```
{
	"program": [
		{
		
			"performance": "DAS KUNSTSEIDENE MÄDCHEN",
			"date": "15.06.2023",
			"time": "10:10",
			"hall":"Große Halle",
			"basic price":"10.90"
		}
	]
}
```
Add new performances: [Post] http://localhost:7771/addPerformances 
```
{
	"performances": [
		{
			"name": "DAS KUNSTSEIDENE MÄDCHEN",
			"description": "nach dem Roman von Irmgard Keun"
		}
	]
}
```
Buy One Way Ticket: [Post] http://localhost:7771/buyOneWayTicket/{ticketID} 
```
{
	"user":
		{
		
			"first name": "A",
			"last name": "B"
		}
}
```
Buy Year Ticket: [Post] http://localhost:7771/buyYearTicket 
```
{
	"user":
		{
		
			"first name": "A",
			"last name": "B"
		}
}
```
