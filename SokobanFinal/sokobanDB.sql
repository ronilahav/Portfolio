
use SokobanDB
create table Levels (
   levelName NVARCHAR(20) NOT NULL,
   levelString  NVARCHAR(2000) default NULL,
   difficulty INT  default NULL,
   PRIMARY KEY (levelName)
);

create table Players (
   userName VARCHAR(30) NOT NULL,
   PRIMARY KEY (userName)
);


use SokobanDB
create table scoreList (
   scoreID INT IDENTITY(1,1),
   levelName NVARCHAR(20) NOT NULL,
   userName VARCHAR(30) NOT NULL,
   levelSteps INT NOT NULL,
   levelTime INT NOT NULL,
   PRIMARY KEY (scoreID),
   foreign key (levelName) REFERENCES Levels(levelName),
   foreign key (userName) REFERENCES Players(userName)
);