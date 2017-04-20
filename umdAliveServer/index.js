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

var currentClub;

var countClubs = 0;

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

// Empty array for clubs to reside in
var users = {
    items: []
};
var mostRecentPosts = {
    items: []
};

var countUsers = 0;
var countPosts = 0;
var countDummy1SubscribedClubs = 0;

countUsers = users.items.push(dummyUser1);

console.log("Dummy 1 is subscribed to  : " + countDummy1SubscribedClubs + " Clubs");

/*
 ************************
 * PUT ROUTE SECTION
 ************************
 */
app.put('/newClub', function (req, res) {

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

app.put('/newPost', function (req, res) {
    // If for some reason the JSON isn't parsed, return HTTP error 400
    if (!req.body) return res.sendStatus(400);

    var postData = {
        clubName: req.body.clubName,
        title: req.body.title,
        time: req.body.time,
        date: req.body.date,
        location: req.body.location,
        description: req.body.description
    };

    mongodb.insertPost(postData);
    console.log(req.body.club);
    console.log(req.body.title);
    var jsonResponse = {
        id: '123', status: 'updated'
    };
    res.json(jsonResponse);
});

app.put('/subscribeUser', function (req, res) {

    // If for some reason the JSON isn't parsed, return HTTP error 400
    if (!req.body)
        return res.sendStatus(400);
    var tempUser = req.body.username;
    var tempClub = req.body.clubname;

    var user_position = getUserPosition(tempUser);
    var club_position = getClubPosition(tempClub);


    users[user_position].users_clubs.items.push(clubs[club_position])
    countClubs = clubs.items.push(dataObject);

    var jsonResponse = {
        id: '123', status: 'updated'
    };
    res.json(jsonResponse);
});

app.put('/currentClub', function (req, res) {
    if (!req.body)
        return res.sendStatus(400);

    currentClub = req.body.clubName;

    console.log("Club selected: " + currentClub);

    var jsonResponse = {
            id: '123', status: 'updated'
        };
    res.json(jsonResponse);
});

app.put('/userInformation', function (req, res) {
    // If for some reason the JSON isn't parsed, return HTTP error 400
    if (!req.body)
        return res.sendStatus(400);

    // Takes data from request and makes a new object
    var dataObject = {
        name: req.body.name,
        email: req.body.emailAddress,
        graduation_date: req.body.graduation_date,
        major: req.body.major,
        users_clubs: [],
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


/*
 ************************
 * GET ROUTE SECTION
 ************************
 */

app.get('/getAllClubs', function (req, res) {
    //array to which each club will be stored
    var clubNames = {
        items: []
    };
    mongodb.getCollection('allClubs', function(result){
            var clubsData = {
                jsonArray: []
            };

            result.forEach(function(clubs){
                clubsData.jsonArray.push(clubs);
            });

            for(var i = 0; i < clubsData.jsonArray.length; i++){
                var curClub = clubsData.jsonArray[i];
                clubNames.items[i] = curClub.club;
            }

            var stringArray = JSON.stringify(clubNames);
            console.log("clubs being sent to client: " + stringArray);
            res.send(stringArray);
    });

});

app.get('/currentClub', function (req,res) {
    var club;
    console.log("Looking for " + currentClub);

    mongodb.findClub(currentClub, function(result){
        var club = result[0];
        console.log("Found club.");
        res.send(JSON.stringify(club));
    });
});

app.get('/userDataGet', function (req, res) {
    res.send(JSON.stringify(dummyUser1));
});

app.get('/mostRecentPosts', function (req, res) {
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
                    console.log(mostRecentPosts[i]);
                }

                var stringArray = JSON.stringify(mostRecentPosts);
                console.log(stringArray);
                res.send(stringArray);
    });
});

function getClubPosition(clubname_temp) {
    //loop through array
    //console.log(clubname_temp);

    for (var x = 0; x < clubs.items.length; x++)
    {//search for club name and return once found
        //  console.log(clubs.items[x].clubname);
        if (clubs.items[x].clubName === clubname_temp)
            return x;
    }
    return -1;
}

function getUserPosition(username_temp) {
    for (var x = 0; x < users.length; x++)
    {
        if (users.items[x].body.username === username_temp)
            return x;
    }
    return -1;
}

function getClubName(position) {
    return clubs.items[position].clubName;
}

app.listen(app.get("port"), function () {
    console.log('CS4531 UMDAlive app listening on port: ', app.get("port"));

});
