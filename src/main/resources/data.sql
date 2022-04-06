CREATE TABLE Inhabitant
(
   id VARCHAR(255) PRIMARY KEY NOT NULL,
   first_name VARCHAR(255),
   last_name VARCHAR(255),
   email VARCHAR(255),
   birth_day DATE,
   arrival_date DATE,
   address VARCHAR(255)
);
INSERT INTO Inhabitant(id, first_name, last_name, email, birth_day, arrival_date, address) VALUES ('5e18367a-1eb3-4b91-b87a-44cd210ef7ba', 'Carin', 'Marie', 'marie.carin@example.fr', '1980-10-08', '2016-12-01', '12 rue des Lilas');
INSERT INTO Inhabitant(id, first_name, last_name, email, birth_day, arrival_date, address) VALUES ('939c0a28-c407-4ce3-b661-d96a412a3d29', 'Robin', 'Patrick', 'patrick.robin@example.fr', '2000-06-12', CURRENT_DATE(), '28 rue des Pivoines');
INSERT INTO Inhabitant(id, first_name, last_name, email, birth_day, arrival_date, address) VALUES ('aebb21fa-b981-4baa-9668-52be5ea3ce90', 'Moulin', 'Camille', 'camille.moulin@example.fr', '2018-02-05', DATEADD('YEAR', -1, CURRENT_DATE), '1 rue des Acacias');
