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

/*
************************
* ROUTE SECTION
************************
*/

app.put('/newClub', function(req, res) {
	console.log("At start of newClub ");

	// If for some reason the JSON isn't parsed, return HTTP error 400
	if (!req.body) return res.sendStatus(400);

	// Takes data from request and makes a new object
	var dataObject = {
		clubname: req.body.clubname,
		admin: req.body.admin,
		keywords: req.body.keywords,
		description: req.body.description,
		post: req.body.post
	};

	// Adds dataObject items to array
	clubs.items.push(dataObject);

	/* ??????????
	var jsonResponse = {
	//id: '123',
	//status: 'updated'
	id: '123', status: 'updated'
};
res.json(jsonResponse);
*/

res.sendStatus(200);

console.log("New club has been created: " + req.body.clubname);
});

app.listen(app.get("port"), function(){
	console.log('CS4531 UMDAlive app listening on port: ', app.get("port"));

});
