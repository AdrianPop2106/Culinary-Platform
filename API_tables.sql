CREATE DATABASE IF NOT EXISTS culinary;
use culinary;

CREATE TABLE IF NOT EXISTS Users (
	Id int primary key auto_increment not null,
    UserName varchar(20),
	Email varchar(25),
    Pass varchar(20)
);

CREATE TABLE IF NOT EXISTS Chefs (
	Id int primary key auto_increment not null,
    UserId int,
    Ordering bool,
    constraint foreign key (userId) references Users(Id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS Admins (
	Id int primary key auto_increment not null,
    UserId int,
    constraint foreign key (userId) references Users(Id) ON DELETE CASCADE
);

DELIMITER //
CREATE procedure login(email varchar(20),pass varchar(20))
begin
	select id
    from Users as u
    where email=u.email AND pass=u.pass;
end; //

DELIMITER //
CREATE procedure getUserById(UserId int)
begin
	select username,email,pass
    from Users
    where UserId=Id;
end; //

DELIMITER //
CREATE procedure getRecipeById(recipeId int)
begin
	select chefId,title,description
    from recipe
    where recipeId=Id;
end; //

DELIMITER //
CREATE procedure postUser(userName varchar(20),pass varchar(20),email varchar(25))
begin
	IF (select count(u.Id) from users as u where u.email=email)=0 then
		INSERT INTO `culinary`.`Users` (`UserName`, `Email`, `Pass`) VALUES (userName,email,pass);
	END IF;
end; //

DELIMITER //
CREATE procedure postChef(chefName varchar(20),pass varchar(20),email varchar(25),ordering bool)
begin
	IF (select count(u.Id) from users as u where u.email=email)=0 then
		call postUser(chefName,pass,email);
		SET @userId = (select id from users where chefName=users.UserName AND pass=users.Pass); 
		INSERT INTO `culinary`.`chefs` (`userId`, `ordering`) VALUES (@userId,ordering);
    end if;
end; //

DELIMITER //
CREATE procedure addAdmins(adminName varchar(20),pass varchar(20),email varchar(25))
begin
	call postUser(adminName,pass,email);
    SET @userId = (select id from users where adminName=users.UserName AND pass=users.Pass); 
	INSERT INTO `culinary`.`admins` (`userId`) VALUES (@userId);
end; //

CREATE TABLE IF NOT EXISTS creditCards (
	Id int primary key auto_increment not null,
    UserId int,
    cardNumber varchar(16),
    cardOwner varchar(20),
    CVW int,
    constraint foreign key (userId) references Users(Id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS recipe (
	Id int primary key auto_increment not null,
    chefId int,
    title varchar(20),
    description varchar(1000),
    constraint foreign key (chefId) references chefs(Id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS ingredient (
	Id int primary key auto_increment not null,
    title varchar(20),
    description varchar(100),
    custom bool
);

CREATE TABLE IF NOT EXISTS comments (
	Id int primary key auto_increment not null,
    UserId varchar(30),
    commentDate date,
    message varchar(200),
    constraint foreign key (userId) references Users(Id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS review (
	Id int primary key auto_increment not null,
    commentId int,
    rating int,
    constraint foreign key (commentId) references comments(Id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS orders (
	Id int primary key auto_increment not null,
    UserId int,
    chefId int,
    address varchar(30),
    price int,
    constraint foreign key (userId) references Users(Id) ON DELETE CASCADE,
    constraint foreign key (chefId) references Chefs(Id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS menus (
	Id int primary key auto_increment not null,
    chefId int,
    description varchar(100),
    price int,
    constraint foreign key (chefId) references Chefs(Id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS ingredientRecipe (
	Id int primary key auto_increment not null,
    ingredientId int,
    recipeId int,
    quantity int,
    measure VARCHAR(10),
    constraint foreign key (ingredientId) references ingredient(Id) ON DELETE CASCADE,
    constraint foreign key (recipeId) references Recipe(Id) ON DELETE CASCADE
);


CREATE TABLE IF NOT EXISTS menuRecipe (
	Id int primary key auto_increment not null,
    menuId int,
    recipeId int,
	quantity int,
    constraint foreign key (menuId) references menus(Id) ON DELETE CASCADE,
    constraint foreign key (recipeId) references recipe(Id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS userRecipe (
	Id int primary key auto_increment not null,
    UserId int,
    recipeId int,
    constraint foreign key (userId) references Users(Id) ON DELETE CASCADE,
    constraint foreign key (recipeId) references recipe(Id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS commentRecipe (
	Id int primary key auto_increment not null,
    commentId int,
    recipeId int,
    constraint foreign key (commentId) references comments(Id) ON DELETE CASCADE,
    constraint foreign key (recipeId) references recipe(Id) ON DELETE CASCADE
);

DELIMITER //
CREATE procedure postRecipe(chefId int,title varchar(20),description varchar(1000))
begin
	INSERT INTO `culinary`.`recipe` (`chefId`, `title`, `description`) VALUES (chefId,title,description);
end; //

DELIMITER //
CREATE procedure postIngredientRecipe(recipeId int,ingredientId int,quantity int)
begin
	INSERT INTO `culinary`.`ingredientrecipe` (`recipeId`, `ingredientId`, `quantity`) VALUES (recipeId ,ingredientId ,quantity );
end; //

DELIMITER //
CREATE procedure getUsers()
begin
	select *
    from users;
end; //

DELIMITER //
CREATE procedure putUser(userId int,userName varchar(20),email varchar(25),pass varchar(20))
begin
    UPDATE users as u 
    SET u.userName=CASE 
		WHEN username!="" then userName else u.username end,
	u.email=CASE
		WHEN email!="" then email else u.email end,
	u.pass=CASE
		WHEN pass!="" then pass else u.pass end
	where id=userId;
end; //

DELIMITER //
CREATE procedure deleteUser(userId int)
begin
    DELETE from users where Id=userId;
    
end; //

DELIMITER //
CREATE procedure postIngredient(ingredientTitle varchar(25),description varchar(100),custom boolean)
begin
	INSERT INTO `culinary`.`ingredient` (`title`, `description`, `custom`) VALUES (ingredientTitle,description,custom);
end; //

DELIMITER //
CREATE procedure getIngredients()
begin
	select *
    from ingredient;
end; //

DELIMITER //
CREATE procedure getIngredientRecipe(recipeId int)
begin
	select i.*,ir.quantity,ir.measure
    from ingredient as i,ingredientrecipe as ir
    where ir.recipeid=recipeId and ir.ingredientid=i.id;
end; //

DELIMITER //
CREATE procedure postComment(userId int,recipeId int,message varchar(200))
begin
	SET @currDate=(SELECT CURDATE() AS Today); 
	INSERT INTO `culinary`.`comments` (`userId`, `commentDate`, `message`) VALUES (userId,@currDate,message);
    SET @commentId=(select id from comments as c where c.userId=userId and c.commentDate=@currDate and c.message=message);
    INSERT INTO `culinary`.`commentrecipe` (`commentId`, `recipeId`) VALUES (@commentId,recipeId);
end; //

DELIMITER //
CREATE procedure putIngredient(ingredientId int,title varchar(20),description varchar(100),custom boolean)
begin
    UPDATE ingredient as i SET i.title=title,i.description=description,i.custom=custom where Id=ingredientId;
end; //

DELIMITER //
CREATE procedure deleteIngredient(ingredientId int)
begin
    DELETE from ingredient where Id=ingredientId;
end; //

DELIMITER //
CREATE procedure getChefs()
begin
	select u.*,c.ordering
    from users as u,chefs as c
    where u.id=c.userId;
end; //

DELIMITER //
CREATE procedure putChef(userId int,userName varchar(20),email varchar(25),pass varchar(20),ordering boolean)
begin
	UPDATE users as u SET u.userName=userName,u.email=email,u.pass=pass where Id=userId;
    UPDATE chefs as c SET c.ordering=ordering where c.userId=userId;
end; //

DELIMITER //
CREATE procedure getRecipeComments(recipeId int)
begin
	select comments.id,users.UserName,comments.commentDate,comments.message
    from comments
    inner join users where users.id=comments.userId
    order by comments.id desc;
end; //

DELIMITER //
CREATE procedure getChefByRecipeId(recipeId int)
begin
	select username
    from users
    inner join chefs,recipe where users.id=chefs.userId and recipe.chefId=chefs.id and recipeId=recipe.id;
end; //

DELIMITER //
CREATE procedure getSavedRecipes(userId int)
begin
	select recipe.*
    from recipe
    inner join userrecipe where recipe.id=userrecipe.recipeId and userrecipe.UserId=userId;
end; //

DELIMITER //
CREATE procedure saveRecipe(userId int,recipeId int)
begin
	INSERT INTO `culinary`.`userrecipe` (`userId`, `recipeId`) VALUES (userId,recipeId);
end; //