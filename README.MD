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
 
---
 
---
 

## Vorprojekt: Loveletter 

###Anwendung starten
Die ausführbare JAR-Datei **vpNeidischeNarwale.jar** findet sich im Projekt unter `otherStuff/vpNeidischeNarwale.jar`.
Laden Sie sich diese Datei herunter und navigieren Sie im Terminal zum Verzeichnis, in dem Sie die Datei abgelegt haben.
### 1. Server starten
Mit dem Befehl

`java -cp vpNeidischeNarwale.jar model.network.ChatServer`

starten Sie den ChatServer. War der Start erfolgreich, wird folgende Nachricht angezeigt:
"Server is running on port: 9090"

### 2. Client-Anwendung starten
Öffnen Sie ein **neues Terminal** im selben Verzeichnis wie zuvor. Um den Client zu starten, benötigen Sie den Pfad zum Verzeichnis der javafx-Bibliotheken auf Ihrem Rechner. Fügen Sie diesen an der markierten Stelle (ohne eckige Klammern) in den folgenden Befehl ein:

`java --module-path [JAVAFX-PFAD] --add-modules javafx.controls,javafx.fxml -cp vpNeidischeNarwale.jar viewModel.ChatApp`

Beispiel:

`java --module-path /home/user/javaFx/javafx-sdk-15.0.1/lib --add-modules javafx.controls,javafx.fxml -cp vpNeidischeNarwale.jar viewModel.ChatApp`

Anschließend öffnet sich eine Eingabemaske, bei der Sie Ihren Nutzernamen festlegen können. 
Weiterführende Informationen zum Ablauf finden Sie in der Dokumentation unter [Spielverlauf](https://gitlab2.cip.ifi.lmu.de/dbs_sep/dbs_sep2020-21/vp-neidische-narwale/-/wikis/Home/Anwender/Spielverlauf). 
Ebenfalls in der Dokumentation befindet sich ein Überblick über alle verfügbaren [Chat- und Spielbefehle](https://gitlab2.cip.ifi.lmu.de/dbs_sep/dbs_sep2020-21/vp-neidische-narwale/-/wikis/Home/Anwender/Spielbefehle).

### Weitere Clients starten
Öffnen Sie für jeden neuen Client ein neues Terminalfenster und gehen analog zu Schritt 2 vor.

### Anwendungen schließen
Um den Server zu beenden, schließen sie das Terminalfenster, in dem Sie den Server gestartet haben.

Um die Chat-Applikation zu beenden, bieten sich mehrere Möglichkeiten:
- Schließen des entsprechenden Terminalfensters
- Schließen-Button des Chat-Fensters betätigen
- Eingabe von !BYE in das Textfeld des Chat-Fensters


---

---

# Informationen
Hallo Neidische Narwale,

dies hier ist euer Git-Repository, welches ihr im Rahmen des Softwareentwicklungspraktikums nutzen könnt und sollt. Im derzeitigen Zustand befindet sich in diesem dieses Readme, welches ihr grade lest, und ein [.gitignore](https://git-scm.com/docs/gitignore)-File.

## Handhabung

Eine sehr einfach und verständliche Anleitung zum Thema "git" findet ihr unter folgender Adresse:  https://rogerdudler.github.io/git-guide/index.de.html (Bitte schaut euch diese mindestens einmal an!)

### Download
* Git für eure Kommandozeile könnt ihr euch hier herunterladen: https://git-scm.com/downloads
* Als Plugin für Eclipse: https://www.eclipse.org/egit/
* Einbindung in IntelliJ: https://www.jetbrains.com/help/idea/using-git-integration.html


### Einstellungen

    git config --global user.name "Vorname Nachname"
    git config --global user.email "name@cip.ifi.lmu.de"

### Klonen des Repositorys
Die Adresse eures Repositorys findet ihr oben rechts auf dieser Seite, wenn ihr auf den blauen `Clone` Knopf klickt. 
Auf euren Computer bekommt ihr dieses dann, indem ihr im gewünschten Verzeichnis folgenden Befehl aufruft:

    
    git clone git@gitlab2.cip.ifi.lmu.de:dbs_sep/dbs_sep2020-21/vp-neidische-narwale.git

Dies setzt einen von euch erstellten SSH Schlüssel voraus. Informiert euch bitte, wie ihr dies mit eurem Betriebssystem am Besten macht. Alternativ auch möglich mit https zu arbeiten. 

## Keine Angst!
Git und GitLab bieten eine Menge an Funktionen. Wir möchten euch ermutigen diese zu erkunden und zu experimentieren. Solltet ihr Hemmungen haben an diesem Repository zu werkeln, könnt ihr euch auch gerne beliebig viele eigene Repositorys hier im GitLab erstellen und dort euer Wissen erweitern.

Solltet ihr im Laufe des Praktikums dazu entscheiden mit Branches zu arbeiten, würden wir euch bitten diese nicht mutwillig zu löschen. Um die schlimmsten Unfälle zu verhindern, ist in diesem Repository das Löschen des `master` Branches sowie `git push --force` auf diesem Branch nicht möglich. Falls ihr zusätzliche Branches geschützt haben möchtet, wendet euch bitte an euren Tutor.