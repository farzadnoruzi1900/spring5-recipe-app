-- writting the proper query to create tables and insert some value inside them . for initializing
-- database .
create table catagory (id bigint not null auto_increment, description varchar(255), primary key (id)) engine=InnoDB
create table ingrediant (id bigint not null auto_increment, amount decimal(19,2), description longtext, recipe_id bigint, unit_of_measure_id bigint, primary key (id)) engine=InnoDB
create table notes (id bigint not null auto_increment, direction longtext, recipe_id bigint, primary key (id)) engine=InnoDB
create table recipe (id bigint not null auto_increment, cook_time integer, description varchar(255), difficulity varchar(255), directions longtext, image longblob, prep_time integer, servings integer, source varchar(255), url varchar(255), notes_id bigint, primary key (id)) engine=InnoDB
create table recipe_catagory (recipe_id bigint not null, catagory_id bigint not null, primary key (recipe_id, catagory_id)) engine=InnoDB
create table unit_of_measure (id bigint not null auto_increment, description longtext, primary key (id)) engine=InnoDB
alter table ingrediant add constraint FKdysmrch45vbtvm8sldst9jyw1 foreign key (recipe_id) references recipe (id)
alter table ingrediant add constraint FKssvcrhbhrq448bbuo6metdj2q foreign key (unit_of_measure_id) references unit_of_measure (id)
alter table notes add constraint FKdbfsiv21ocsbt63sd6fg0t3c8 foreign key (recipe_id) references recipe (id)
alter table recipe add constraint FK37al6kcbdasgfnut9xokktie9 foreign key (notes_id) references notes (id)
alter table recipe_catagory add constraint FK919jqvxqqbdr548orcmj29d93 foreign key (catagory_id) references catagory (id)
alter table recipe_catagory add constraint FKf0v523s7yteuf1l22y4xn7cj1 foreign key (recipe_id) references recipe (id)
