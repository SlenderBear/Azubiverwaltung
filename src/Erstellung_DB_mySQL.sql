-- drop database if exists AzubiVerwaltung;
create database AzubiVerwaltung;
use AzubiVerwaltung;


-- Anlegen der Tabelle Betrieb
create table Betrieb(BetriebID varchar(50),Firmenbezeichnung varchar(50) NOT NULL,Strasse varchar(50),Plz varchar(5),Ort varchar(20),EMail varchar(50),Telefonnummer varchar(20),Faxnummer varchar(20),primary key(BetriebID));
-- Tabelle Ausbilder erstellen
create table Ausbilder(AusbilderID varchar(50) ,Name varchar(50) NOT NULL,Vorname varchar(50) NOT NULL,Telefonnummer varchar(15),EMail varchar(50) NOT NULL,BetriebID varchar(50),primary key(AusbilderID));
-- Tabelle Azubi erstellen
create table Azubi(AzubiID varchar(50),Name varchar(50) NOT NULL,Vorname varchar(50) NOT NULL,Telefonnummer varchar(15),Mobiltelefon varchar(15),EMail varchar(50),Strasse varchar(50),Plz varchar(20),Ort varchar(30),Geburtsdatum varchar(30),Volljaehrigkeit varchar(1),Inklusionsberatung varchar(1),Geburtsort varchar(30),Geburtsname varchar(50),Geburtsland varchar(30),Staatsangehoerigkeit_1 varchar(30),Staatsangehoerigkeit_2 varchar(30),Zuzugsjahr integer,Geburtsland_Vater varchar(30),Geburtsland_Mutter varchar(30),Geschlecht varchar(1),Konfession varchar(25),Fachrichtung varchar(40),Lehrjahr integer,Ausbildungsbeginn varchar(10),Ausbildungsende varchar(10),Letzte_Schulform varchar(30),Schulabschluss varchar(20),Anmerkung_Schulabschluss varchar(500),Fehltage integer,KlassenID varchar(50),AusbilderID varchar(50),primary key(AzubiID));
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
-- alter table Ausbilder add foreign key(BetriebID) REFERENCES Betrieb(BetriebID);
-- alter table Azubi add foreign key(KlassenID) REFERENCES Klasse(KlassenID),add foreign key(AusbilderID) REFERENCES Ausbilder(AusbilderID);
-- alter table Login_Daten add foreign key(BerechtigungID) REFERENCES Berechtigung(BerechtigungID);
-- alter table Klasse add foreign key(LehrerID) REFERENCES Lehrer(LehrerID);
-- alter table zeugnis add foreign key(AzubiID) REFERENCES Azubi(AzubiID);
-- alter table Lehrer add foreign key(LoginID) REFERENCES Login_Daten(LoginID);
-- alter table Zeugnisposition add foreign key(ZeugnisID) REFERENCES Zeugnis(ZeugnisID),add foreign key(NoteID) REFERENCES Note(NoteID),add foreign key(FachID) REFERENCES Fach(FachID);

INSERT INTO NOTE values(1,'Sehr gut');
INSERT INTO NOTE values(2,'Gut');
INSERT INTO NOTE values(3,'Befriedigend');
INSERT INTO NOTE values(4,'Ausreichend');
INSERT INTO NOTE values(5,'Mangelhaft');
INSERT INTO NOTE values(6,'Ungenügend');

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

-- Testdaten
INSERT INTO Betrieb VALUES ('3472a5c1-fb41-4810-8c05-77e8c39bf39c', 'Provinzial Rheinland Versicherungen', 'Provinzialplatz 1', '40591', 'Duesseldorf', 'zentrale@provinzial.com', '02119781000', '02119781001');
INSERT INTO Betrieb VALUES ('44887cfc-4d96-4331-8e84-2de7d0e7f50c', 'codecentric AG', 'Koelner Landstraße 125', '40591', 'Duesseldorf', 'zentrale@codecentric.de', '02113331000', '02113331001');
INSERT INTO Betrieb VALUES ('b99f2a19-69db-4c7f-b5dd-d90be21301f4', 'ElectronicPartners', 'Mündelheimer Weg 40 ', '40472', 'Duesseldorf', 'zentrale@ep.de', '02114441000', '02114441001');
INSERT INTO Betrieb VALUES ('f5fb6e9f-1499-4019-9047-149cc6038dae', 'Random Computer Service', 'Am Bahnhof 43', '40699', 'Erkrath', 'zentrale@randomcomputer.de', '021045651000', '021045651001');
INSERT INTO Betrieb VALUES ('e92ce903-20d3-4606-9c96-148c3f58e143', 'Mannis Schlepptopbude', 'Castroper Straße 77', '44791', 'Bochum', 'zentrale@schlepptop.ru', '02344454001', '02344454002');

INSERT INTO Ausbilder VALUES ('48fcfe60-a425-43e3-922d-b978e1a324cc', 'Loetkolben', 'Manfred', '02344454994', 'manni@schlepptop.ru', 'e92ce903-20d3-4606-9c96-148c3f58e143');
INSERT INTO Ausbilder VALUES ('ce00a126-0fed-4d91-a4b7-ae8a5d7faf73', 'Schneider', 'Eberhard', '02104565678', 'schneider@randomcomputer.de', 'f5fb6e9f-1499-4019-9047-149cc6038dae');
INSERT INTO Ausbilder VALUES ('78ae2f3a-147f-491b-81c8-782b7ca6447d', 'Bioleck', 'Alfred', '02113333456', 'bioleck@codecentric.de', '44887cfc-4d96-4331-8e84-2de7d0e7f50c');
INSERT INTO Ausbilder VALUES ('6ba28b3b-ea7e-4182-9824-257f3c0bf9b7', 'Mönchhausen', 'Jupp', '02119783666', 'jupp.moenchhausen@provinzial.com', '3472a5c1-fb41-4810-8c05-77e8c39bf39c');
INSERT INTO Ausbilder VALUES ('9db37532-2ed2-44e6-b822-3432c9df3c80', 'Richter',  'Hannelore', '02114445557', 'richter@ep.de', 'b99f2a19-69db-4c7f-b5dd-d90be21301f4');
INSERT INTO Ausbilder VALUES ('c4579e85-23a4-4960-9260-b12cd35836f8', 'von Bingen', 'Sieglinde', '02119781337', 'siegline.bingen@provinzial.com', '3472a5c1-fb41-4810-8c05-77e8c39bf39c');
INSERT INTO Ausbilder VALUES ('da2b0f99-2dbe-4735-bf0c-8b5cdde0059e', 'Arkanz', 'Helga', '02119781397', 'helga.arkanz@provinzial.com', '3472a5c1-fb41-4810-8c05-77e8c39bf39c');

INSERT INTO Azubi VALUES ('a7f74819-3503-46f6-bac8-455af7aa9d7c', 'Pezeh', 'Horst', '02341114446', '017187235331', 'horst@schlepptop.ru', 'Holundergasse 2', '44791', 'Bochum', '04.04.1994', 'j', 'n', 'Erkelenz', '', 'Deutschland', 'Deutsch', '', 0, 'Deutschland', 'Deutschland', 'm', 'rk', 'Systemintegration', '1', '01.08.2015', '01.08.2017', 'Gymnasium', 'FHR', 'keine Besonderheiten', 0, '6b5cf080-5911-4654-810f-c6360e3cea90', '48fcfe60-a425-43e3-922d-b978e1a324cc');
INSERT INTO Azubi VALUES ('541c56e5-d4a6-439e-9b55-67ae327310fd', 'Hampel', 'Lutz', '02113331238', '017187235441', 'hampel@codecentric.de', 'Ahornweg 45', '40591', 'Duesseldorf', '23.06.1990', 'j', 'n', 'Duesseldorf', '', 'Deutschland', 'Deutsch', '', 0, 'Deutschland', 'Deutschland',  'm', 'ev', 'Anwendungsentwickler', '1', '01.08.2015', '01.08.2017', 'Realschule', 'FOR Q', 'kein Englisch', 0,  '6b5cf080-5911-4654-810f-c6360e3cea90', '78ae2f3a-147f-491b-81c8-782b7ca6447d');
INSERT INTO Azubi VALUES ('0ae7bf33-5013-4aa3-8566-9f71d5b2bcf7', 'Sousa', 'Lima', '02114446987', '017187299331', 'sousa@ep.de', 'Am Stadtweiher 4', '40699', 'Erkrath', '03.11.1992' , 'j', 'n', 'Haan', '', 'Deutschland', 'Deutsch', 'Spanisch',  0, 'Deutschland', 'Spanien', 'w', 'rk', 'Anwendungsentwickler', '1', '01.08.2015', '01.08.2016', 'Gymnasium', 'FHR', 'Spanisch-Leistungskurs', 0, '6b5cf080-5911-4654-810f-c6360e3cea90', '9db37532-2ed2-44e6-b822-3432c9df3c80');
INSERT INTO Azubi VALUES ('a2e5b71b-c209-425e-8ff3-4c2033e3578f', 'Jeryczek', 'Jessica', '02104565880', '017122235331', 'jeryczek@randomcomputer.de', 'Bahnstraße 11', '40699', 'Erkrath', '02.02.1996', 'j', 'n', 'Warschau', 'Evangelista', 'Polen', 'Polnisch', 'Deutsch',  1999, 'Polen', 'Polen', 'w',  'rk', 'Anwendungsentwicklerin', '1', '01.08.2015', '01.08.2017', 'Realschule', 'FOR Q', 'Polnisch ist Muttersprache', 0, '6b5cf080-5911-4654-810f-c6360e3cea90', 'ce00a126-0fed-4d91-a4b7-ae8a5d7faf73');
INSERT INTO Azubi VALUES ('2628d636-adec-41b8-96ff-74e9d002b641', 'Bonn', 'Julia', '02119785647', '017187245631', 'julia.bonn@provinzial.com', 'Goldgasse 4', '40667', 'Meerbusch', '09.11.1988', 'j', 'j', 'Duesseldorf', '', 'Deutschland', 'Deutsch', '',  0, 'Deutschland', 'Niederlande',  'w',  'rk', 'Anwendungsentwicklerin', '1', '01.08.2015', '01.08.2017', 'Realschule', 'FOR Q', 'Ist halbseitig gelaehmt', 0, '6b5cf080-5911-4654-810f-c6360e3cea90', 'da2b0f99-2dbe-4735-bf0c-8b5cdde0059e');
INSERT INTO Azubi VALUES ('e6985eb6-0c9e-45e1-bcc1-5d472c12642a', 'Siegen', 'Gerit', '02119783366', '017181115321', 'gerit.siegen@provinzial.com', 'Am Goldteich 2', '40822', 'Mettmann', '23.12.1991', 'j', 'n', 'Mettmann', '', 'Deutschland', 'Deutsch', '',  0, 'Deutschland', 'Deutschland',  'm',  'ev', 'Anwendungsentwickler', '1', '01.08.2015', '01.08.2017', 'Gymnasium', 'FHR', '', 0, '6b5cf080-5911-4654-810f-c6360e3cea90', 'da2b0f99-2dbe-4735-bf0c-8b5cdde0059e');
INSERT INTO Azubi VALUES ('a2e35b39-6562-4457-906e-1605355493c4', 'Neuss', 'Sascha', '02119785522', '017187886331', 'sascha.neuss@provinzial.com', 'Duesseldorfer Straße 54', '41564', 'Kaarst', '19.09.1989', 'j', 'n', 'Dresden', '', 'Deutschland', 'Deutsch', '',  0, 'Deutschland', 'Deutschland',  'm',   'juedisch', 'Anwendungsentwickler', '1', '01.08.2015', '01.08.2017', 'Realschule', 'FOR', '', 0,'6b5cf080-5911-4654-810f-c6360e3cea90', 'c4579e85-23a4-4960-9260-b12cd35836f8');

INSERT INTO Login_Daten VALUES ('588b2bde-812b-40e7-bf72-eda754d92f9c', 'germ', 'A1234', 0);
INSERT INTO Login_Daten VALUES ('96db4cc7-9f02-4470-8c13-15cededba5d7', 'klim', 'B5678', 1);
INSERT INTO Login_Daten VALUES ('49e36777-1ef3-4f4e-8e26-ec5b591e823c', 'mard', 'C1337', 2);

INSERT INTO Lehrer VALUES ('abc39ab0-d438-46d5-a4f4-fa1458fe4ad7', 'Germani', 'Irena', '02114326665', '588b2bde-812b-40e7-bf72-eda754d92f9c');
INSERT INTO Lehrer VALUES ('171a930a-d827-4063-9229-b1769cec839f', 'Klimpera', 'Else', '02114327879', '96db4cc7-9f02-4470-8c13-15cededba5d7');
INSERT INTO Lehrer VALUES ('d8e3aa04-1767-4670-9c7b-ec2e33a53509', 'Marder', 'Gustav', '02114325583', '49e36777-1ef3-4f4e-8e26-ec5b591e823c');

INSERT INTO Klasse VALUES ('6b5cf080-5911-4654-810f-c6360e3cea90', 'FA2_2', 2014, 'd8e3aa04-1767-4670-9c7b-ec2e33a53509');
INSERT INTO Klasse VALUES ('29387a50-bd96-4903-8b08-d5165f4fdfab', 'FS3_6', 2013, '171a930a-d827-4063-9229-b1769cec839f');

INSERT INTO Zeugnis VALUES ('fc12f7ff-589e-45cd-84b8-5dc013dee8a8','2015','13.12.2015','a7f74819-3503-46f6-bac8-455af7aa9d7c');
INSERT INTO Zeugnis VALUES ('26e6daf6-7461-4a62-8a34-1926ba28c745','2015','13.12.2015','541c56e5-d4a6-439e-9b55-67ae327310fd');
INSERT INTO Zeugnis VALUES ('4276f6bc-9fbc-496a-863a-5d1c2547c1f5','2015','13.12.2015','0ae7bf33-5013-4aa3-8566-9f71d5b2bcf7');
INSERT INTO Zeugnis VALUES ('005b8b8a-e3cd-4cf3-a52c-fc830c96b42f','2015','13.12.2015','a2e5b71b-c209-425e-8ff3-4c2033e3578f');
INSERT INTO Zeugnis VALUES ('edfa9964-5a6e-4772-a26f-882bcca39020','2015','13.12.2015','2628d636-adec-41b8-96ff-74e9d002b641');
INSERT INTO Zeugnis VALUES ('3bd82796-1b63-485b-a9b1-3ba4802b784c','2015','13.12.2015','e6985eb6-0c9e-45e1-bcc1-5d472c12642a');
INSERT INTO Zeugnis VALUES ('40d0fcff-2404-47d3-90e9-6b473096b3c1','2015','06.01.2016','a2e35b39-6562-4457-906e-1605355493c4');

-- Zeugnisposition 
INSERT INTO Zeugnisposition VALUES ('d8b78761-dc34-4da3-9db7-31e6b612d602', 'fc12f7ff-589e-45cd-84b8-5dc013dee8a8', '1', '79c6c8de-63d8-462c-ba6e-0471499e90a6');
INSERT INTO Zeugnisposition VALUES ('7c9eae04-d63b-4e01-9756-a7b00e2bef1c', 'fc12f7ff-589e-45cd-84b8-5dc013dee8a8', '2', 'eb810b59-4d2c-48b3-b2bd-599d418952e0');
INSERT INTO Zeugnisposition VALUES ('03213c6a-3b54-4bdb-81ce-295371daa4f4', 'fc12f7ff-589e-45cd-84b8-5dc013dee8a8', '3', 'f56023c8-2a78-455a-a854-bb62493b641d');
INSERT INTO Zeugnisposition VALUES ('549fcafe-3d0d-4c64-82f6-ff0e291b2979', 'fc12f7ff-589e-45cd-84b8-5dc013dee8a8', '1', 'b575279b-5c46-4613-8519-856575ea73b7');
INSERT INTO Zeugnisposition VALUES ('e5ed086a-b316-4052-9f29-e8c134a43253', 'fc12f7ff-589e-45cd-84b8-5dc013dee8a8', '2', '2d3b9342-ba36-4320-9470-91b10bb92e65');
INSERT INTO Zeugnisposition VALUES ('32378ab6-9455-477c-88a4-d52cbf6cc406', 'fc12f7ff-589e-45cd-84b8-5dc013dee8a8', '2', '11fad0e6-86b4-4d77-ac37-9343dfdad468');
INSERT INTO Zeugnisposition VALUES ('03f4422d-ec22-45ee-9b3b-67dc1df99955', 'fc12f7ff-589e-45cd-84b8-5dc013dee8a8', '1', '20228e27-31bc-4dce-879e-85d2403e1d77');
INSERT INTO Zeugnisposition VALUES ('4f3377f7-90fb-4ff9-bf19-335b26c4ddfe', 'fc12f7ff-589e-45cd-84b8-5dc013dee8a8', '3', '4135c274-b7f2-4f31-994a-943834469307');

INSERT INTO Zeugnisposition VALUES ('574549c6-b03f-11e4-ab7d-12e3f512a338', '26e6daf6-7461-4a62-8a34-1926ba28c745', '2', '79c6c8de-63d8-462c-ba6e-0471499e90a6');
INSERT INTO Zeugnisposition VALUES ('c8fc93bb-1f3d-428c-ba72-8d3eda5c61a3', '26e6daf6-7461-4a62-8a34-1926ba28c745', '2', 'eb810b59-4d2c-48b3-b2bd-599d418952e0');
INSERT INTO Zeugnisposition VALUES ('b6e24d91-141c-4f0e-95a8-8e9736c85e3e', '26e6daf6-7461-4a62-8a34-1926ba28c745', '2', 'f56023c8-2a78-455a-a854-bb62493b641d');
INSERT INTO Zeugnisposition VALUES ('d832a6b2-055f-4eef-80c8-e9928b6584dd', '26e6daf6-7461-4a62-8a34-1926ba28c745', '4', 'b575279b-5c46-4613-8519-856575ea73b7');
INSERT INTO Zeugnisposition VALUES ('6d23c6ed-b9a0-46bb-905c-46a48caf104b', '26e6daf6-7461-4a62-8a34-1926ba28c745', '4', '2d3b9342-ba36-4320-9470-91b10bb92e65');
INSERT INTO Zeugnisposition VALUES ('f683ec99-16e2-4ecd-b629-da4bf9698ac0', '26e6daf6-7461-4a62-8a34-1926ba28c745', '2', '11fad0e6-86b4-4d77-ac37-9343dfdad468');
INSERT INTO Zeugnisposition VALUES ('78ffeff3-543b-4bc4-8c73-266212d43196', '26e6daf6-7461-4a62-8a34-1926ba28c745', '1', '20228e27-31bc-4dce-879e-85d2403e1d77');
INSERT INTO Zeugnisposition VALUES ('d6acea46-6a1f-4259-b76f-f4f415a81cca', '26e6daf6-7461-4a62-8a34-1926ba28c745', '3', '4135c274-b7f2-4f31-994a-943834469307');

INSERT INTO Zeugnisposition VALUES ('9aa9b4d6-9eb1-4441-907a-b34cd5a9881c', '4276f6bc-9fbc-496a-863a-5d1c2547c1f5', '4', '79c6c8de-63d8-462c-ba6e-0471499e90a6');
INSERT INTO Zeugnisposition VALUES ('54e53760-238a-4a60-a082-3da5071a10a3', '4276f6bc-9fbc-496a-863a-5d1c2547c1f5', '2', 'eb810b59-4d2c-48b3-b2bd-599d418952e0');
INSERT INTO Zeugnisposition VALUES ('279850cb-1cd2-43b3-bc4b-042f637c714a', '4276f6bc-9fbc-496a-863a-5d1c2547c1f5', '1', 'f56023c8-2a78-455a-a854-bb62493b641d');
INSERT INTO Zeugnisposition VALUES ('9d434395-acac-4265-8c8d-eb8df97050b3', '4276f6bc-9fbc-496a-863a-5d1c2547c1f5', '1', 'b575279b-5c46-4613-8519-856575ea73b7');
INSERT INTO Zeugnisposition VALUES ('a18aaac8-39c9-4719-a8bc-7bb660f3068e', '4276f6bc-9fbc-496a-863a-5d1c2547c1f5', '1', '2d3b9342-ba36-4320-9470-91b10bb92e65');
INSERT INTO Zeugnisposition VALUES ('983dadce-ad58-4021-abb1-3db9b6d66c0a', '4276f6bc-9fbc-496a-863a-5d1c2547c1f5', '1', '11fad0e6-86b4-4d77-ac37-9343dfdad468');
INSERT INTO Zeugnisposition VALUES ('41f6d948-887b-4d0a-ba8c-5cea5c7f1955', '4276f6bc-9fbc-496a-863a-5d1c2547c1f5', '1', '20228e27-31bc-4dce-879e-85d2403e1d77');
INSERT INTO Zeugnisposition VALUES ('c8672af2-1353-4e49-9263-5ca3f25ad288', '4276f6bc-9fbc-496a-863a-5d1c2547c1f5', '3', '4135c274-b7f2-4f31-994a-943834469307');

INSERT INTO Zeugnisposition VALUES ('e30c0ad9-5c4c-4bce-ac98-1d9e74695390', '005b8b8a-e3cd-4cf3-a52c-fc830c96b42f', '3', '79c6c8de-63d8-462c-ba6e-0471499e90a6');
INSERT INTO Zeugnisposition VALUES ('87cb9b8e-441d-4a59-b110-e7d91ea86179', '005b8b8a-e3cd-4cf3-a52c-fc830c96b42f', '2', 'eb810b59-4d2c-48b3-b2bd-599d418952e0');
INSERT INTO Zeugnisposition VALUES ('20a8b17e-2f23-4a7a-89df-86e94e3377b4', '005b8b8a-e3cd-4cf3-a52c-fc830c96b42f', '2', 'f56023c8-2a78-455a-a854-bb62493b641d');
INSERT INTO Zeugnisposition VALUES ('d9544ca9-37d4-4518-94af-8e4ac2ab5691', '005b8b8a-e3cd-4cf3-a52c-fc830c96b42f', '1', 'b575279b-5c46-4613-8519-856575ea73b7');
INSERT INTO Zeugnisposition VALUES ('4f3123e1-1a5d-4d41-8c76-36644c7c123f', '005b8b8a-e3cd-4cf3-a52c-fc830c96b42f', '4', '2d3b9342-ba36-4320-9470-91b10bb92e65');
INSERT INTO Zeugnisposition VALUES ('8d6adeef-cf30-4247-b378-eb57d7d7dc15', '005b8b8a-e3cd-4cf3-a52c-fc830c96b42f', '4', '11fad0e6-86b4-4d77-ac37-9343dfdad468');
INSERT INTO Zeugnisposition VALUES ('e798959e-d875-409a-a696-0eabcb7cb878', '005b8b8a-e3cd-4cf3-a52c-fc830c96b42f', '3', '20228e27-31bc-4dce-879e-85d2403e1d77');
INSERT INTO Zeugnisposition VALUES ('5fdff34b-1d7e-4b61-ad23-0cdaca93ee9b', '005b8b8a-e3cd-4cf3-a52c-fc830c96b42f', '1', '4135c274-b7f2-4f31-994a-943834469307');

INSERT INTO Zeugnisposition VALUES ('5957355d-6a50-44f8-8283-36d12be2569f', 'edfa9964-5a6e-4772-a26f-882bcca39020', '4', 'eb810b59-4d2c-48b3-b2bd-599d418952e0');
INSERT INTO Zeugnisposition VALUES ('11b7b90c-ebae-48af-8e35-79291b5397e2', 'edfa9964-5a6e-4772-a26f-882bcca39020', '2', 'f56023c8-2a78-455a-a854-bb62493b641d');
INSERT INTO Zeugnisposition VALUES ('def21f3e-d142-4bdd-becb-f2a021316f78', 'edfa9964-5a6e-4772-a26f-882bcca39020', '3', 'b575279b-5c46-4613-8519-856575ea73b7');
INSERT INTO Zeugnisposition VALUES ('5bc08ab4-52b1-48cd-b0a6-28b2b009c04d', 'edfa9964-5a6e-4772-a26f-882bcca39020', '2', '2d3b9342-ba36-4320-9470-91b10bb92e65');
INSERT INTO Zeugnisposition VALUES ('eecd2b85-b501-4f5e-9fc4-314d7f9c82e6', 'edfa9964-5a6e-4772-a26f-882bcca39020', '2', '11fad0e6-86b4-4d77-ac37-9343dfdad468');
INSERT INTO Zeugnisposition VALUES ('bbc17200-5b03-45ae-8f5e-20845af53db9', 'edfa9964-5a6e-4772-a26f-882bcca39020', '3', '20228e27-31bc-4dce-879e-85d2403e1d77');
INSERT INTO Zeugnisposition VALUES ('1863f978-48f0-48d5-a9e1-f5d040ec535d', 'edfa9964-5a6e-4772-a26f-882bcca39020', '2', '4135c274-b7f2-4f31-994a-943834469307');

INSERT INTO Zeugnisposition VALUES ('1885ab57-93ce-4223-9e95-8a1c5237655d', '3bd82796-1b63-485b-a9b1-3ba4802b784c', '4', 'eb810b59-4d2c-48b3-b2bd-599d418952e0');
INSERT INTO Zeugnisposition VALUES ('5fccd9fc-be62-4583-ae6e-5da6901f74dc', '3bd82796-1b63-485b-a9b1-3ba4802b784c', '2', 'f56023c8-2a78-455a-a854-bb62493b641d');
INSERT INTO Zeugnisposition VALUES ('1f194fb4-ea95-4875-a0e0-70c8ca95ec28', '3bd82796-1b63-485b-a9b1-3ba4802b784c', '3', 'b575279b-5c46-4613-8519-856575ea73b7');
INSERT INTO Zeugnisposition VALUES ('2bfb2736-fc66-4283-8ebb-eb3106547b01', '3bd82796-1b63-485b-a9b1-3ba4802b784c', '2', '2d3b9342-ba36-4320-9470-91b10bb92e65');
INSERT INTO Zeugnisposition VALUES ('0fe8b7a1-5d07-4943-813e-42b16bfdef07', '3bd82796-1b63-485b-a9b1-3ba4802b784c', '2', '11fad0e6-86b4-4d77-ac37-9343dfdad468');
INSERT INTO Zeugnisposition VALUES ('006d51a6-8b6e-4cc6-a9f7-fd14ff26f26a', '3bd82796-1b63-485b-a9b1-3ba4802b784c', '3', '20228e27-31bc-4dce-879e-85d2403e1d77');
INSERT INTO Zeugnisposition VALUES ('ee8f8aea-9ac9-4710-9552-d30b6bce2524', '3bd82796-1b63-485b-a9b1-3ba4802b784c', '2', '4135c274-b7f2-4f31-994a-943834469307');

INSERT INTO Zeugnisposition VALUES ('988b3469-5470-4ca2-b3e0-bd126f319447', '40d0fcff-2404-47d3-90e9-6b473096b3c1', '4', 'eb810b59-4d2c-48b3-b2bd-599d418952e0');
INSERT INTO Zeugnisposition VALUES ('428fb67c-473a-49c8-addd-ce8f85d11c28', '40d0fcff-2404-47d3-90e9-6b473096b3c1', '3', 'f56023c8-2a78-455a-a854-bb62493b641d');
INSERT INTO Zeugnisposition VALUES ('60e29cb0-f26f-4cf1-b720-6fba7f156143', '40d0fcff-2404-47d3-90e9-6b473096b3c1', '1', 'b575279b-5c46-4613-8519-856575ea73b7');
INSERT INTO Zeugnisposition VALUES ('fcb79816-e740-4792-a94a-124653c48b75', '40d0fcff-2404-47d3-90e9-6b473096b3c1', '2', '2d3b9342-ba36-4320-9470-91b10bb92e65');
INSERT INTO Zeugnisposition VALUES ('5d6a6031-34fa-414f-9a9d-160dfc6a48a2', '40d0fcff-2404-47d3-90e9-6b473096b3c1', '1', '11fad0e6-86b4-4d77-ac37-9343dfdad468');
INSERT INTO Zeugnisposition VALUES ('243b5305-24ef-46f7-b39a-7e5f8f14d7c3', '40d0fcff-2404-47d3-90e9-6b473096b3c1', '3', '20228e27-31bc-4dce-879e-85d2403e1d77');
INSERT INTO Zeugnisposition VALUES ('215c1f2d-ea4b-49bc-894b-495f726b40aa', '40d0fcff-2404-47d3-90e9-6b473096b3c1', '2', '4135c274-b7f2-4f31-994a-943834469307');


