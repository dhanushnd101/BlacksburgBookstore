DELETE from book;
ALTER table book AUTO_INCREMENT =1001;

DELETE From category;
ALTER table category AUTO_INCREMENT =1001;

INSERT INTO `category` (`name`) VALUE ('Best Selling'),('Mystery'),('Fiction'),('Novels'),('Romance'),('Fantasy'),('Horror'),('KIDS');

INSERT INTO `book` (title,author, description, price, rating, is_public, is_featured, category_id) VALUES ('Ugly Love','Colleen Hoover','',1049,4,1,1,1001);
INSERT INTO `book` (title,author, description, price, rating, is_public, is_featured, category_id) VALUES ('Beach Read','Emily Henry','',599,3,1,0,1001);
INSERT INTO `book` (title,author, description, price, rating, is_public, is_featured, category_id) VALUES ('The Silent Patient','Alex Michaelides','',1235,4,0,0,1001);
INSERT INTO `book` (title,author, description, price, rating, is_public, is_featured, category_id) VALUES ('This we never got over','Lucy Score','',627,3,1,0,1001);
INSERT INTO `book` (title,author, description, price, rating, is_public, is_featured, category_id) VALUES ('The Testaments','Margaret Atwood','',645,5,1,1,1001);
INSERT INTO `book` (title,author, description, price, rating, is_public, is_featured, category_id) VALUES ('Of Mice and Men','John Steinbeck','',100,3,0,0,1001);

INSERT INTO `book` (title,author, description, price, rating, is_public, is_featured, category_id) VALUES ('Death on the Nile','Agatha Christie','',2149,5,1,1,1002);
INSERT INTO `book` (title,author, description, price, rating, is_public, is_featured, category_id) VALUES ('The Maid','Nita Prose','',732,4,1,0,1002);
INSERT INTO `book` (title,author, description, price, rating, is_public, is_featured, category_id) VALUES ('Suspect','Scott Turow','',1645,5,1,0,1002);
INSERT INTO `book` (title,author, description, price, rating, is_public, is_featured, category_id) VALUES ('Redeeming Love','Francine Rivers','',1600,4,0,0,1002);

INSERT INTO `book` (title,author, description, price, rating, is_public, is_featured, category_id) VALUES ('The Perfect Marriage','Jeneva Rose','',1978,4,1,1,1003);
INSERT INTO `book` (title,author, description, price, rating, is_public, is_featured, category_id) VALUES ('Abduction','Robin Cook','',1300,5,1,0,1003);
INSERT INTO `book` (title,author, description, price, rating, is_public, is_featured, category_id) VALUES ('Terminal World','Alastair Reynolds','',650,4,0,1,1003);
INSERT INTO `book` (title,author, description, price, rating, is_public, is_featured, category_id) VALUES ('All the Light We Cannot See','Anthony Doerr','',627,3,1,1,1003);

INSERT INTO `book` (title,author, description, price, rating, is_public, is_featured, category_id) VALUES ('Harry Potter','J K Rowling','',499,4,1,1,1004); #Novels
INSERT INTO `book` (title,author, description, price, rating, is_public, is_featured, category_id) VALUES ('The Secret of the Nagas',' Amish Tripathi','',1152,5,1,0,1004);
INSERT INTO `book` (title,author, description, price, rating, is_public, is_featured, category_id) VALUES ('ULYSSES','James Joyce','',403,5,0,0,1004);
INSERT INTO `book` (title,author, description, price, rating, is_public, is_featured, category_id) VALUES ('LOLITA','Vladimir Nabokov','',9.72,3,0,0,1004);

INSERT INTO `book` (title,author, description, price, rating, is_public, is_featured, category_id) VALUES ('The Great Alone','Kristin Hannah','',239,3,0,0,1005); #romance
INSERT INTO `book` (title,author, description, price, rating, is_public, is_featured, category_id) VALUES ('The Score','Elle Kennedy','',599,3,1,0,1005);
INSERT INTO `book` (title,author, description, price, rating, is_public, is_featured, category_id) VALUES ('The Goal','Elle Kennedy','',1235,4,0,1,1005);
INSERT INTO `book` (title,author, description, price, rating, is_public, is_featured, category_id) VALUES ('Verity','Colleen Hoover','',627,3,1,1,1005);

INSERT INTO `book` (title,author, description, price, rating, is_public, is_featured, category_id) VALUES ('A Court of Mist and Fury','Sarah J Maas','',1175,4,0,0,1006); #Fantasy
INSERT INTO `book` (title,author, description, price, rating, is_public, is_featured, category_id) VALUES ('A Court of Thorns and Roses','Sarah J Maas','',1159,3,1,0,1006);
INSERT INTO `book` (title,author, description, price, rating, is_public, is_featured, category_id) VALUES ('A Game of Thrones','George R.R. Martin','',650,5,1,1,1006);
INSERT INTO `book` (title,author, description, price, rating, is_public, is_featured, category_id) VALUES ('Circe','Madeline Miller','',863,3,0,0,1006);

INSERT INTO `book` (title,author, description, price, rating, is_public, is_featured, category_id) VALUES ('IT','Stephen King','',225,5,1,1,1007); #Horror
INSERT INTO `book` (title,author, description, price, rating, is_public, is_featured, category_id) VALUES ('Home before Dark','Riley Sager','',577,4,1,0,1007);
INSERT INTO `book` (title,author, description, price, rating, is_public, is_featured, category_id) VALUES ('Woom','Duncan Ralston','',595,4,1,0,1007);
INSERT INTO `book` (title,author, description, price, rating, is_public, is_featured, category_id) VALUES ('Dracula','Bram Stoker','',99,2,0,0,1007);

INSERT INTO `book` (title,author, description, price, rating, is_public, is_featured, category_id) VALUES ('The Adventures of Tintin','Herge','',1842,5,1,1,1008); #KIDS
INSERT INTO `book` (title,author, description, price, rating, is_public, is_featured, category_id) VALUES ('Matilda','Dahl','',1499,4,0,0,1008);
INSERT INTO `book` (title,author, description, price, rating, is_public, is_featured, category_id) VALUES ('Wish','Barbara O-Connor','',100,3,1,0,1008);
INSERT INTO `book` (title,author, description, price, rating, is_public, is_featured, category_id) VALUES ('Diary of a Wimpy kid','Jeff Kinney','',1055,4,1,0,1008);


