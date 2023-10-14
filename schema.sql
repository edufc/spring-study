CREATE TABLE USER (
  ID CHAR(36) DEFAULT UUID() PRIMARY KEY,
  NAME varchar(100) NOT NULL
)

CREATE TABLE CATEGORY (
  ID int NOT NULL AUTO_INCREMENT,
  NAME varchar(50) NOT NULL,

  PRIMARY KEY (ID)
)

CREATE TABLE BOOK (
  ID CHAR(36) DEFAULT UUID() PRIMARY KEY,
  NAME varchar(100) NOT NULL,
  DESCRIPTION varchar(255) NOT NULL,
  ID_CATEGORY int NOT NULL,  
  
  FOREIGN KEY (ID_CATEGORY) REFERENCES CATEGORY(ID)
)

CREATE TABLE REVIEW (
    ID int NOT NULL AUTO_INCREMENT PRIMARY KEY,
    DESCRIPTION varchar(255) NOT NULL,
    ID_BOOK CHAR(36) DEFAULT UUID(),
    ID_USER CHAR(36) DEFAULT UUID(),
    
    FOREIGN KEY (ID_BOOK) REFERENCES BOOK(ID),
    FOREIGN KEY (ID_USER) REFERENCES USER(ID)  
);
