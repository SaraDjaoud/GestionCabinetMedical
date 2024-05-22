
CREATE TABLE users (
    id INT PRIMARY KEY NOT NULL,
    username varchar2(30) NOT NULL unique,
    mdp varchar2(30) NOT NULL
);

desc users;
insert into users Values (1,'admin','admin');
commit;


CREATE SEQUENCE patient_idseq
    START WITH 1
    INCREMENT BY 1
    NOCACHE
    NOCYCLE;

CREATE TABLE Patient (
    ID INT PRIMARY KEY,
    FirstName VARCHAR2(30) NOT NULL,
    FamilyName VARCHAR2(30) NOT NULL,
    bDay VARCHAR2(30) NOT NULL,
    Phone VARCHAR2(30) NOT NULL,
    Sex VARCHAR2(30) NOT NULL,
    Adress VARCHAR2(30) NOT NULL,
    Allergy VARCHAR2(30) NOT NULL
);

CREATE TRIGGER patient_id_trigger
    BEFORE INSERT ON Patient
    FOR EACH ROW
BEGIN
    SELECT patient_idseq.NEXTVAL INTO :new.ID FROM dual;
END;
/

CREATE SEQUENCE appointment_seq START WITH 1 INCREMENT BY 1;

CREATE TABLE Appointment (
   ID INT PRIMARY KEY,
    FirstName VARCHAR2(30) NOT NULL,
    FamilyName VARCHAR2(30) NOT NULL,
    Allergy VARCHAR2(30) NOT NULL,
    Heur VARCHAR2(30) NOT NULL,
    Dat VARCHAR2(30) NOT NULL
);

CREATE SEQUENCE Medicalfile_seq START WITH 1 INCREMENT BY 1;

CREATE TABLE Medicalfile (
   ID INT PRIMARY KEY,
    FirstName VARCHAR2(30) NOT NULL,
    FamilyName VARCHAR2(30) NOT NULL,
    Allergy VARCHAR2(30) NOT NULL,
    Traitement VARCHAR2(30) NOT NULL,
    Medicine VARCHAR2(30) NOT NULL,
    Price VARCHAR2(30) NOT NULL,
    Dose VARCHAR2(30) NOT NULL,
    Duration VARCHAR2(30) NOT NULL
);

CREATE SEQUENCE Prescription_seq START WITH 1 INCREMENT BY 1;

CREATE TABLE Prescription (
   ID INT PRIMARY KEY,
    FirstName VARCHAR2(30) NOT NULL,
    FamilyName VARCHAR2(30) NOT NULL,
    Traitement VARCHAR2(30) NOT NULL,
    Medicine VARCHAR2(30) NOT NULL,
    Price VARCHAR2(30) NOT NULL,
    Dose VARCHAR2(30) NOT NULL,
    Duration VARCHAR2(30) NOT NULL
);