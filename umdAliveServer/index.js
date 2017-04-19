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

// Empty array for clubs to reside in
var clubs = {
    items: []
};

var currentClub;

var countClubs = 0;

var stringArray = JSON.stringify(clubs);

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
    var dataObject = {
        clubName: req.body.clubName,
        username: req.body.username,
        keywords: req.body.keywords,
        description: req.body.description,
    };

    mongodb.insertClub(dataObject);

    countClubs = clubs.items.push(dataObject);
    console.log(req.body.post);
    var jsonResponse = {
        id: '123', status: 'updated'
    };
    res.json(jsonResponse);

    console.log("New club has been created: " + req.body.clubname);
    console.log("Name of username/admin : " + req.body.username);
    console.log("Name of keyword/catagory : " + req.body.keywords);
    console.log("Name of description : " + req.body.description);
    console.log("total items in array : " + countClubs);
    console.log("total posts saved on server: " + countPosts);
});

app.put('/newPost', function (req, res) {
    // If for some reason the JSON isn't parsed, return HTTP error 400
    if (!req.body) return res.sendStatus(400);

    var dataObject = {
        club: req.body.clubName,
        title: req.body.title,
        time: req.body.time,
        date: req.body.date,
        location: req.body.location,
        description: req.body.description
    };
    // Adds dataObject items to array
    // this would have to be altered to handle new parameters event title, time, date, and location. - Ryan
    countPosts = mostRecentPosts.items.push(dataObject);
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

/*
 * Function to get a specific users data, will eventually return a user info based on the name of them
 * for now it returns a fake user for testing purposes -Kevin
 */
app.get('/getAllClubs', function (req, res) {
    //array to which each club will be stored
    var clubNames = {
        items: []
    };
    for (var x = 0; x < clubs.items.length; x++) {
        clubNames.items[x] = getClubName(x);
    }
    var stringArray = JSON.stringify(clubNames);
    console.log("clubs being sent to client: " + stringArray);
    res.send(stringArray);

});

app.get('/currentClub', function (req,res) {
    var success = false;
    var club;
    console.log("Looking for " + currentClub);

    var pos = getClubPosition(currentClub);

    mongodb.findClub(currentClub, function(result){
        res.send(JSON.stringify(result))
    });

//    if(pos !== -1){
//        console.log("SUCCESS: " + currentClub + " WAS FOUND");
//        club = clubs.items[pos];
//        var stringArray = JSON.stringify(club);
//        res.send(stringArray);
//    }
//
//    else{
//        console.log("ERROR: " + currentClub + " NOT FOUND.");
//    }
});

app.get('/userDataGet', function (req, res) {

    //console log for testing what data is being sent
    console.log("User data being returned: \n" + "Name: " + dummyUser1.name
            + "\n Email: " + dummyUser1.email
            + "\n Password: " + dummyUser1.password
            + "\n Graduation Date: " + dummyUser1.graduationDate
            + "\n Major: " + dummyUser1.major
            + "\n UserClubs:" + dummyUser1.clubs)
    //response message from server
    res.send(JSON.stringify(dummyUser1));
});

app.get('/mostRecentPosts', function (req, res) {
    var mostRecentPostsTemp = {
        items: []
    };
    for (var x = 0; x < mostRecentPosts.items.length; x++) {
        mostRecentPostsTemp.items[x] = mostRecentPosts.items[x];
    }
    var stringArray = JSON.stringify(mostRecentPostsTemp);
    console.log("posts being sent to client: " + stringArray);
    res.send(stringArray);
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
