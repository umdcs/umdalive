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

/*//////////////////////////
*   Dummy clubs/users for testing
*//////////////////////////
            var dummyClub1= {
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
            var dummyUser1 = {
                    name: "Billy Joe",
                    email: "umdAlive1@gmail.com",
                    password: "123abc",
                    graduationDate: "2018",
                    major: "computer science"
                };

/*///////////////////////////
*   End of dummy users/clubs
*////////////////////////////


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
var countClubs = 0;
var countUsers = 0;

countClubs = clubs.items.push(dummyClub1);
countClubs = clubs.items.push(dummyClub2);
countClubs = clubs.items.push(dummyClub3);
countUsers = users.items.push(dummyUser1);

/*
************************
* PUT ROUTE SECTION
************************
*/

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



        app.get('/getAllClubs', function(req,res){

                    var club_names = {
                        items: []
                    };
                    for(var x = 0; x < clubs.length; x++)
                    {
                        club_names[0] = getClubName(x);
                    }

                    var stringArray = json.stringify(club_names);
                    res.send(stringArray);

                    });

        app.put('/subscribeUser', function(req, res) {

                    // If for some reason the JSON isn't parsed, return HTTP error 400
                    if (!req.body) return res.sendStatus(400);
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


        function getClubPosition(var clubname_temp){
            for(var x = 0; x < clubs.length; x++)
            {
                if (clubs[x].body.clubname === clubname_temp)
                                return x;
            }
            return -1;
        }

        function getUserPosition(var username_temp){
            for(var x = 0; x < users.length; x++)
            {
                if (users[x].body.username === username_temp)
                return x;
            }
            return -1;
         }
        function getClubName(var position) {
                      return clubs[position].clubname;              /
                 })

        app.put('/userInformation', function(req, res) {

            // If for some reason the JSON isn't parsed, return HTTP error 400
            if (!req.body) return res.sendStatus(400);


            var users_clubs = {
            	items: []
            };

            // Takes data from request and makes a new object
            var dataObject = {
                name: req.body.name,
                email: req.body.emailAddress,
                password: req.body.user_password,
                graduation_date: req.body.graduation_date,
                major: req.body.major,
                clubs: users_clubs,
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
            app.get('/userDataGet', function(req,res){

            //console log for testing what data is being sent
            console.log("User data being returned: \n" + "Name: " + dummyUser1.name
                                                    + "\n Email: " + dummyUser1.email
                                                    + "\n Password: " + dummyUser1.password
                                                    + "\n Graduation Date: " + dummyUser1.graduationDate
                                                    + "\n Major: " + dummyUser1.major)
            //respose message from server
            res.send(JSON.stringify(dummyUser1));

            });

app.listen(app.get("port"), function(){
	console.log('CS4531 UMDAlive app listening on port: ', app.get("port"));

});