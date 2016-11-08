var express = require('express');
var bodyParser = require('body-parser');

// Initialize main instanced class
var app = express();

// Set the port
app.set("port", 5000);

// Support encoded bodies
app.use(bodyParser.urlencoded({
	extended: true
}));

// Support JSON-encoded bodies
app.use(bodyParser.json());

// Empty array for clubs to reside in
var clubs = {
	items: []
};
var users = {
    items: []
};
var mostRecentPosts = {
    items: []
};


/*
************************
* ROUTE SECTION
************************
*/
var countClubs = 0;
var countUsers = 0;
app.put('/newClub', function(req, res) {

	// If for some reason the JSON isn't parsed, return HTTP error 400
	if (!req.body) return res.sendStatus(400);

	// Takes data from request and makes a new object
	var dataObject = {
		clubname: req.body.clubname,
		username: req.body.username,
		keywords: req.body.keywords,
		description: req.body.description,
		post: req.body.post
	};

	// Adds dataObject items to array
    countClubs = clubs.items.push(dataObject);

	var jsonResponse = {
	id: '123', status: 'updated'
};
res.json(jsonResponse);

console.log("New club has been created: " + req.body.clubname);
console.log("Name of username/admin : " + req.body.username);
console.log("Name of keyword/catagory : " + req.body.keywords);
console.log("Name of description : " + req.body.description);
console.log("Name of new post : " + req.body.post);
console.log("total items in array : " + countClubs);
});

app.put('/userInformation', function(req, res) {

    // If for some reason the JSON isn't parsed, return HTTP error 400
    if (!req.body) return res.sendStatus(400);

    // Takes data from request and makes a new object
    var dataObject = {
        email: req.body.emailAddress,
        password: req.body.user_password,
        graduation_date: req.body.graduation_date,
        major: req.body.major,
    };

    // Adds dataObject items to array
    countUsers = users.items.push(dataObject);

    var jsonResponse = {
    id: '123', status: 'updated'
};
res.json(jsonResponse);

console.log("Number of Users: " + countUsers);
console.log("Email of user created: " + req.body.email);
console.log("GraduationDate of user created: " + req.body.graduation_date);
console.log("Major of new user: " + req.body.major);
});

app.listen(app.get("port"), function(){
	console.log('CS4531 UMDAlive app listening on port: ', app.get("port"));

});