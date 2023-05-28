# Theater-Box-Office
## SonarCloud
[![SonarCloud](https://sonarcloud.io/images/project_badges/sonarcloud-orange.svg)](https://sonarcloud.io/summary/new_code?id=dk1553_Theater-Box-Office)

## Starten
Anwendung starten\
Die Anwendung läuft standardisiert unter dem Port: 7771
#### Anwendung in Insomnia-Client benutzen
Die Vorlage muss in die Insomnia-App importiert werden:
[Insomnia JSON-Vorlage](https://github.com/dk1553/Theater-Box-Office/blob/master/0-cleanproject-plugins/0-cleanproject-plugins/src/main/java/rest/Insomnia.json)

#### Anwendung in einem anderen Client benutzen
View theater repertoire: [Get] http://localhost:7771/performances \
View theater program: [Get] http://localhost:7771/events \
View event details: [Get] http://localhost:7771/events/{eventID} \
Login (for admins only): [Post] http://localhost:7771/login \
Logout (for admins only): [Post] http://localhost:7771/logout \
Add new events: [Post] http://localhost:7771/addEvents \
Add new performances: [Post] http://localhost:7771/addPerformances \
Buy One Way Ticket: [Post] http://localhost:7771/buyOneWayTicket/{ticketID} \
Buy Year Ticket: [Post] http://localhost:7771/buyYearTicket 
