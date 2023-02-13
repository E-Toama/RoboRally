# RoboRally

Nachstehend finden Sie die Hinweise zum Ausführen des Spiels. 
Unserem [Wiki](https://gitlab2.cip.ifi.lmu.de/dbs_sep/dbs_sep2020-21/vp-neidische-narwale/-/wikis/home) können Sie zahlreiche weitere Informationen entnehmen, darunter:
- **Anwenderdokumentation** zum Spielverlauf und den verfügbaren Cheats
- **Entwicklerdokumentation** mit aktuellen Klassendiagrammen, einer Visualisierung der implementierten Abläufe des Netzwerkprotokolls sowie Vorstufen, Skizzen und Hintergrundmaterial
- sämtliche **Protokolle** der Tutorentreffen


## RoboRally starten
Die ausführbare JAR-Datei **RoboRally.jar** findet sich im Projekt unter `otherStuff/RoboRally.jar`.
Laden Sie sich diese Datei herunter und navigieren Sie im Terminal zum Verzeichnis, in dem Sie die Datei abgelegt haben.

### 1. Server starten
Mit dem Befehl

`java -cp RoboRally.jar server.network.Server`

starten Sie den Server. War der Start erfolgreich, wird folgende Nachricht angezeigt:
"Server is running on port: 9090"

### 2. Client-Anwendung starten
Öffnen Sie ein **neues Terminal** im selben Verzeichnis wie zuvor. Um den Client zu starten, benötigen Sie den Pfad zum Verzeichnis der javafx-Bibliotheken auf Ihrem Rechner. Fügen Sie diesen an der markierten Stelle (ohne eckige Klammern) in den folgenden Befehl ein:

`java --module-path [JAVAFX-PFAD] --add-modules javafx.controls,javafx.fxml -cp RoboRally.jar client.RoboRallyClient`

Beispiel:

`java --module-path /home/user/javaFx/javafx-sdk-15.0.1/lib --add-modules javafx.controls,javafx.fxml -cp RoboRally.jar client.RoboRallyClient`

Anschließend öffnet sich eine Eingabemaske, bei der Sie Ihren Nutzernamen und Ihren Roboter festlegen können. 
Weiterführende Informationen zum Ablauf finden Sie in der Dokumentation unter [Spielverlauf](https://gitlab2.cip.ifi.lmu.de/dbs_sep/dbs_sep2020-21/vp-neidische-narwale/-/wikis/Home/Hauptprojekt/Anwender/Spielverlauf). 


### Weitere Clients starten
Öffnen Sie für jeden neuen Client ein neues Terminalfenster und gehen analog zu Schritt 2 vor.

### AI starten
Mit dem Befehl
`java -cp RoboRally.jar AI.AIClient`
starten Sie einen Bot. 

### Anwendungen schließen
Um den Server zu beenden, schließen sie das Terminalfenster, in dem Sie den Server gestartet haben.

Um die Applikation zu beenden, bieten sich mehrere Möglichkeiten:
- Schließen des entsprechenden Terminalfensters
- Schließen-Button des Fensters betätigen
