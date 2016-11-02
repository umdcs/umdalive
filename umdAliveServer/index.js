
var express = require('express');
var bodyParser = require('body-parser');

// Initialize main instanced class
var app = express();

// Set the port
app.set("port",5000);

// Support encoded bodies
app.use(bodyParser.urlencoded({
	extended: true
}));

// Support JSON-encoded bodies
app.use(bodyParser.json());

// Empty array for clubs to reside in
var clubs = new Array();

/*
 ************************
 * ROUTE SECTION
 ************************
 */

app.put('/newClub', function(req, res) {
        console.log("At start of newClub ");

        // If for some reason the JSON isn't parsed, return HTTP error 400
        if (!req.body) return res.sendStatus(400);

        //takes data from request and makes into new 'club' object
        //contains name, keywords, description and its posts
        var club = {clubname: req.body.clubname,
                    admin: req.body.admin,
                    keywords: req.body.keywords,
                    description: req.body.description,
                    post: req.body.post};


				var club

        clubs[clubs.length] = club; //adds new club to array of clubs

        var jsonResponse = {
    	//id: '123',
    	//status: 'updated'
        id: '123', status: 'updated'
        };
        res.json(jsonResponse);


        console.log("new club has been created: " + req.body.clubname);
});

app.listen(app.get("port"), function(){
    console.log('CS4531 UMDAlive app listening on port: ', app.get("port"));

});
