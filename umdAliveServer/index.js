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
//loads the mongo functions in this file
var mongodb = require('./mongoDBFunctions.js');
console.log(mongodb);

var dummyUser1 = {
    name: "Billy Joe",
    email: "umdAlive1@gmail.com",
    graduationDate: "2018",
    major: "computer science",
    clubs: []

};

/*///////////////////////////
 *   End of dummy users/clubs
 *////////////////////////////

/*
 ************************
 * PUT ROUTE SECTION
 ************************
 */
app.put('/clubs', function (req, res) {

    // If for some reason the JSON isn't parsed, return HTTP error 400
    if (!req.body)
        return res.sendStatus(400);
    // Takes data from request and makes a new object
    var clubData = {
        clubName: req.body.clubName,
        username: req.body.username,
        keywords: req.body.keywords,
        description: req.body.description,
    };

    mongodb.insertClub(clubData);

    var jsonResponse = {
        id: '123', status: 'updated'
    };
    res.json(jsonResponse);

    console.log("New club has been created: " + req.body.clubName);
});

app.put('/posts', function (req, res) {
    // If for some reason the JSON isn't parsed, return HTTP error 400
    if (!req.body) return res.sendStatus(400);

    var postData = {
        clubName: req.body.clubName,
        title: req.body.title,
        time: req.body.time,
        date: req.body.date,
        location: req.body.location,
        description: req.body.description,
        image: req.body.image
    };

    mongodb.insertPost(postData);

    console.log("Club posting: " + req.body.clubName);
    console.log("Title of post: " + req.body.title);
    var jsonResponse = {
        id: '123', status: 'updated'
    };
    res.json(jsonResponse);
});

app.put('/userData', function (req, res) {
    // If for some reason the JSON isn't parsed, return HTTP error 400
    if (!req.body) return res.sendStatus(400);

    var userData = {
        name: req.body.name,
        email: req.body.emailAddress,
        graduation_date: req.body.graduationDate,
        major: req.body.major,
        users_clubs: [],
    };

    mongodb.insertUser(userData);

    var jsonResponse = {
        id: '123', status: 'updated'
    };
    res.json(jsonResponse);
});

/*
 ************************
 * GET ROUTE SECTION
 ************************
 */

app.get('/clubs', function (req, res) {
    //array to which each club will be stored
    var clubNames = {
        items: []
    };
    mongodb.getCollection('clubs', function(result){
            var clubsData = {
                jsonArray: []
            };

            result.forEach(function(clubs){
                clubsData.jsonArray.push(clubs);
            });

            for(var i = 0; i < clubsData.jsonArray.length; i++){
                var curClub = clubsData.jsonArray[i];
                clubNames.items[i] = curClub.clubData.clubName;
            }

            var stringArray = JSON.stringify(clubNames);
            console.log("clubs being sent to client: " + stringArray);
            res.send(stringArray);
    });

});

app.get('/clubs/:clubName', function (req,res) {
    var club;
    console.log("Looking for " + req.params.clubName);

    mongodb.findClub(req.params.clubName, function(result){
        var club = result[0];
        console.log("Found club.");
        res.body = JSON.stringify(club.clubData);
        res.send(res.body);
    });
});

app.get('/clubSearch/:keyword', function (req,res) {
    //array to which each club will be stored
    var clubNames = {
        items: []
    };

    mongodb.getCollection('clubs', function(result){
            var clubsData = {
                jsonArray: []
            };

            result.forEach(function(clubs){
                clubsData.jsonArray.push(clubs);
            });

            var x = 0;
            for(var i = 0; i < clubsData.jsonArray.length; i++){
                var curClub = clubsData.jsonArray[i];
                if(req.params.keyword === curClub.clubData.keywords) {
                    clubNames.items[x] = curClub.clubData.clubName;
                    x++;
                    }
            }

            var stringArray = JSON.stringify(clubNames);
            console.log("clubs being sent to client: " + stringArray);
            res.send(stringArray);
    });
});

//Only returns dummy
app.get('/userData/:user', function (req, res) {
    res.send(JSON.stringify(dummyUser1));
});

//Only returns dummy
app.get('/userData/', function (req, res) {
    res.send(JSON.stringify(dummyUser1));
});

app.get('/posts', function (req, res) {
    var mostRecentPosts = {
        items: []
    };

    mongodb.getCollection('posts', function(result){
                var postsData = {
                    jsonArray: []
                };

                result.forEach(function(posts){
                    postsData.jsonArray.push(posts);
                });

                for(var i = 0; i < postsData.jsonArray.length; i++){
                    var curPost = postsData.jsonArray[i];
                    mostRecentPosts.items[i] = curPost.postData;
                }

                var stringArray = JSON.stringify(mostRecentPosts);
                res.send(stringArray);
    });
});

function getClubKeyword(position) {
    return clubs.items[position].keywords;
}

app.listen(app.get("port"), function () {
    console.log('CS4531 UMDAlive app listening on port: ', app.get("port"));

});


