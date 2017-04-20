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

<<<<<<< HEAD

/*//////////////////////////
 *   Dummy clubs/users for testing
 *//////////////////////////
var dummyClub1 = {
    clubname: "BBQ",
    username: "umdAlive1",
    keywords: "Food",
    description: "we enjoy BBQ's",
    post: "BBQ this sunday, 2 pm, bring your own food. Be there or your kicked out of the club."
};
var dummyClub2 = {
    clubname: "ACM",
    username: "umdAlive2",
    keywords: "Academic",
    description: "we enjoy vast amounts of code not working",
    post: "rewriting facebook this friday, meet at edu 115 3pm "
};
var dummyClub3 = {
    clubname: "UnderWaterBasketWeaving",
    username: "umdAlive1",
    keywords: "Athletic",
    description: "We are the best under water basket weaving club in the northland",
    post: "dryland this sunday, 2 pm, bring your own weights. Be there or no more basket weaving with us."
};
var dummyClub4 = {
    clubname: "Air Guitar Club",
    username: "umdAlive1",
    keywords: "Music",
    description: "We jam to songs and play air instruments",
    post: "Send email to umd.alive@gmail.com for questions about playlist"
};

// Empty array for clubs to reside in
var clubs = {
    items: []
};

var currentClub;

var currentKeyword;

var countClubs = 0;
countClubs = clubs.items.push(dummyClub1);
countClubs = clubs.items.push(dummyClub2);
countClubs = clubs.items.push(dummyClub3);
countClubs = clubs.items.push(dummyClub4);


var stringArray = JSON.stringify(clubs);

=======
//loads the mongo functions in this file
var mongodb = require('./mongoDBFunctions.js');
console.log(mongodb);
>>>>>>> mongo

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

<<<<<<< HEAD
app.put('/currentClub', function (req, res) {
    if (!req.body)
        return res.sendStatus(400);

    currentClub = req.body.clubname;

    console.log("Club selected: " + currentClub);

    var jsonResponse = {
            id: '123', status: 'updated'
        };
    res.json(jsonResponse);
});

app.put('/keyword', function (req, res) {
    if (!req.body)
        return res.sendStatus(400);

    currentKeyword = req.body.keyword;

    console.log("keyword: " + currentKeyword);

    var jsonResponse = {
            id: '123', status: 'updated'
        };
    res.json(jsonResponse);
});

=======
>>>>>>> mongo
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


app.get('/currentClub/:clubName', function (req,res) {
    var club;
    console.log("Looking for " + req.params.clubName);

    mongodb.findClub(req.params.clubName, function(result){
        var club = result[0];
        console.log("Found club.");
        res.body = JSON.stringify(club.clubData);
        res.send(res.body);
    });
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

<<<<<<< HEAD
app.get('/getSearchAllClubs', function (req, res) {
    //array to which each club will be stored
    var clubNames = {
        items: []
    };

    var i = 0;

    for (var x = 0; x < clubs.items.length; x++) {
        if(currentKeyword === getClubKeyword(x)) {
            clubNames.items[i] = getClubName(x);
            ++i;
            }
    }
    var stringArray = JSON.stringify(clubNames);
    console.log("clubs being sent to client: " + stringArray);
    res.send(stringArray);
});


app.get('/currentClub', function (req,res) {
    var success = false;
    var club;
    console.log("Looking for " + currentClub);
=======
            result.forEach(function(clubs){
                clubsData.jsonArray.push(clubs);
            });
>>>>>>> mongo

            for(var i = 0; i < clubsData.jsonArray.length; i++){
                var curClub = clubsData.jsonArray[i];
                clubNames.items[i] = curClub.club;
            }

            var stringArray = JSON.stringify(clubNames);
            console.log("clubs being sent to client: " + stringArray);
            res.send(stringArray);
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

function getClubKeyword(position) {
    return clubs.items[position].keywords;
}

app.listen(app.get("port"), function () {
    console.log('CS4531 UMDAlive app listening on port: ', app.get("port"));

});
