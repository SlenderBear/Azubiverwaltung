-- drop database if exists AzubiVerwaltung;
create database AzubiVerwaltung;
use AzubiVerwaltung;

-- Anlegen der Tabelle Betrieb
create table Betrieb(BetriebID varchar(50),Firmenbezeichnung varchar(50) NOT NULL,Strasse varchar(50),Plz varchar(5),Ort varchar(20),EMail varchar(50),Telefonnummer varchar(20),Faxnummer varchar(20),primary key(BetriebID));
-- Tabelle Ausbilder erstellen
create table Ausbilder(AusbilderID varchar(50) ,Name varchar(50) NOT NULL,Vorname varchar(50) NOT NULL,Telefonnummer varchar(15),EMail varchar(50) NOT NULL,BetriebID varchar(50),primary key(AusbilderID));
-- Tabelle Azubi erstellen
create table Azubi(AzubiID varchar(50),Name varchar(50) NOT NULL,Vorname varchar(50) NOT NULL,Telefonnummer varchar(15),Mobiltelefon varchar(15),EMail varchar(50),Strasse varchar(50),Plz varchar(20),Ort varchar(30),Geburtsdatum varchar(30),Volljaehrigkeit varchar(1),Inklusionsberatung varchar(1),Geburtsort varchar(30),Geburtsname varchar(50),Geburtsland varchar(30),Staatsangehoerigkeit_1 varchar(30),Staatsangehoerigkeit_2 varchar(30),Zuzugsjahr integer,Geburtsland_Vater varchar(30),Geburtsland_Mutter varchar(30),Geschlecht varchar(1),Konfession varchar(25),Fachrichtung varchar(20),Lehrjahr integer,Ausbildungsbeginn varchar(10),Ausbildungsende varchar(10),Letzte_Schulform varchar(30),Schulabschluss varchar(20),Anmerkung_Schulabschluss varchar(500),Fehltage integer,KlassenID varchar(50),BetriebID varchar(50),AusbilderID varchar(50),primary key(AzubiID));
-- Tabelle Zeugnis erstellen
create table Zeugnis(ZeugnisID varchar(50) ,Jahr integer,Zeugniskonferenz varchar(10),AzubiID varchar(50),primary key(ZeugnisID));
-- Tabelle Fach erstellen
create table Fach(FachID varchar(50) ,Bezeichnung varchar(50),primary key(FachID));
-- Tabelle Lehrer anlegen
create table Lehrer(LehrerID varchar(50) ,Name varchar(30) NOT NULL,Vorname varchar(30) NOT NULL,Telefonnummer varchar(15),LoginID varchar(50),primary key(LehrerID));
-- Tabelle Berechtigung erstellen
create table Berechtigung(BerechtigungID integer ,Bezeichnung varchar(30),primary key(BerechtigungID));
-- Tabelle Klasse erstellen
create table Klasse(KlassenID varchar(50) ,Bezeichnung varchar(10),Jahr integer,LehrerID varchar(50),primary key(KlassenID));
-- Tabelle Note erstellen
create table Note(NoteID varchar(50) ,Beschreibung varchar(30),primary key(NoteID));
-- Tabelle Login_Daten erstellen
create table Login_Daten(LoginID varchar(50),Benutzername varchar(50),Passwort varchar(30),BerechtigungID integer,primary key(LoginID));
-- Tabelle Zeugnisposition erstellen
create table Zeugnisposition(ZeugnispositionID varchar(50),ZeugnisID varchar(50),NoteID varchar(50),FachID varchar(50),primary key(ZeugnispositionID));

-- Setzen der Foreign Key
alter table Ausbilder add foreign key(BetriebID) REFERENCES Betrieb(BetriebID);
alter table Azubi add foreign key(KlassenID) REFERENCES Klasse(KlassenID), add foreign key(BetriebID) REFERENCES Betrieb(BetriebID),add foreign key(AusbilderID) REFERENCES Ausbilder(AusbilderID);
alter table Login_Daten add foreign key(BerechtigungID) REFERENCES Berechtigung(BerechtigungID);
alter table Klasse add foreign key(LehrerID) REFERENCES Lehrer(LehrerID);
alter table Zeugnis add foreign key(AzubiID) REFERENCES Azubi(AzubiID);
alter table Lehrer add foreign key(LoginID) REFERENCES Login_Daten(LoginID);
alter table Zeugnisposition add foreign key(ZeugnisID) REFERENCES Zeugnis(ZeugnisID),add foreign key(NoteID) REFERENCES Note(NoteID),add foreign key(FachID) REFERENCES Fach(FachID);

INSERT INTO Note values(1,'Sehr gut');
INSERT INTO Note values(2,'Gut');
INSERT INTO Note values(3,'Befriedigend');
INSERT INTO Note values(4,'Ausreichend');
INSERT INTO Note values(5,'Mangelhaft');
INSERT INTO Note values(6,'Ungenügend');

INSERT INTO Berechtigung values(0,'Admin');
INSERT INTO Berechtigung values(1,'Klassenlehrer');
INSERT INTO Berechtigung values(2,'Bereichsleiter');

INSERT INTO Fach VALUES ('79c6c8de-63d8-462c-ba6e-0471499e90a6','Deutsch');
INSERT INTO Fach VALUES ('eb810b59-4d2c-48b3-b2bd-599d418952e0','Gesellschaftslehre');
INSERT INTO Fach VALUES ('f56023c8-2a78-455a-a854-bb62493b641d','Religion');
INSERT INTO Fach VALUES ('b575279b-5c46-4613-8519-856575ea73b7','Sport');
INSERT INTO Fach VALUES ('2d3b9342-ba36-4320-9470-91b10bb92e65','Informations- und Telekommunikationssysteme');
INSERT INTO Fach VALUES ('11fad0e6-86b4-4d77-ac37-9343dfdad468','Anwendungsentwicklung');
INSERT INTO Fach VALUES ('20228e27-31bc-4dce-879e-85d2403e1d77','Wirtschafts- und Geschaeftsprozesse');
INSERT INTO Fach VALUES ('4135c274-b7f2-4f31-994a-943834469307','Englisch');



