DROP DATABASE IF EXISTS retoMyTube;
CREATE DATABASE IF NOT EXISTS retoMyTube;

use retoMyTube;

CREATE TABLE song(
id int primary key auto_increment,
title varchar(50) not null,
author varchar(50) not null,
url varchar(500) not null
);

INSERT INTO song VALUES(1,"Bohemian Rhapsody", "Queen", "https://youtu.be/fJ9rUzIMcZQ?feature=shared");
INSERT INTO song VALUES(2,"Imagine", "John Lennon", "https://youtu.be/VOgFZfRVaww?feature=shared");
INSERT INTO song VALUES(3,"Shape of You", "Ed Sheeran", "https://youtu.be/JGwWNGJdvx8?feature=shared");

CREATE TABLE user(
id int primary key auto_increment,
name varchar(25) not null,
lastName varchar(25) not null,
email varchar(30) not null unique,
password varchar(24) not null
);

INSERT INTO user VALUES(1, "Amancio", "Ortega", "amanciortega@gmail.com", "1234");
INSERT INTO user VALUES(2, "Lionel", "Messi", "lionelmessi@gmail.com", "1234");
INSERT INTO user VALUES(3, "Elon", "Musk", "elonmusk@gmail.com", "1234");

CREATE TABLE favorite(
id_user int not null,
id_song int not null,


PRIMARY KEY (id_song,id_user),

constraint fk_id_song_Song
	FOREIGN KEY (id_song)
	REFERENCES song(id)
    ON UPDATE CASCADE
	ON DELETE CASCADE,
 
constraint fk_id_user_User
	FOREIGN KEY (id_user) 
	REFERENCES user(id)
    ON UPDATE CASCADE
	ON DELETE CASCADE
);

INSERT INTO favorite VALUES(1,1);
INSERT INTO favorite VALUES(1,2);
INSERT INTO favorite VALUES(2,1);
INSERT INTO favorite VALUES(3,3);

DROP USER IF EXISTS retoMyTube;
CREATE USER IF NOT EXISTS retoMyTube
IDENTIFIED BY 'retoMyTube';
GRANT ALL PRIVILEGES on retoMyTube.* to retoMyTube;