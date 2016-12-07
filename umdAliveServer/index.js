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
            var dummyClub4 = {
                    clubname: "Air Guitar Club",
                    username: "umdAlive1",
                    keywords: "Music",
                    description: "We jam to songs and play air instruments",
                    post: "Send email to umd.alive@gmail.com for questions about playlist"
                };
            var dummyUser1 = {
                    name: "Billy Joe",
                    email: "umdAlive1@gmail.com",
                    password: "123abc",
                    graduationDate: "2018",
                    major: "computer science",
                    users_clubs: []
                };
            var dummyUser2 = {
                    name: "Seemore Buts",
                    email: "Seemore.Buts@gmail.com",
                    password: "password",
                    graduationDate: "2019",
                    major: "mechanical engineering",
                    users_clubs: []
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
var countPosts = 0;
var countDummy1SubscribedClubs = 0;


countClubs = clubs.items.push(dummyClub1);
countClubs = clubs.items.push(dummyClub2);
countClubs = clubs.items.push(dummyClub3);
countClubs = clubs.items.push(dummyClub4);

countUsers = users.items.push(dummyUser1);
countUsers = users.items.push(dummyUser2);
dummyUser1.users_clubs.push(dummyClub1);
dummyUser1.users_clubs.push(dummyClub2);



countDummy1SubscribedClubs = dummyUser1.users_clubs.push(dummyClub3);

console.log("Dummy 1 is subscribed to  : " + countDummy1SubscribedClubs + " Clubs");
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
            countPosts = mostRecentPosts.items.push(req.body.clubname + ": " + req.body.post);

            //print post to server for testing
            console.log(req.body.post);
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
        console.log("total posts saved on server: " + countPosts);
        });

        app.put('/newPost', function(req, res) {

                    // If for some reason the JSON isn't parsed, return HTTP error 400
                    if (!req.body) return res.sendStatus(400);

                    var dataObject = {
                                    clubToPost: req.body.clubToPost,
                                    postToDisplay: req.body.postToDisplay
                                };
                    // Adds dataObject items to array
                    countPosts = mostRecentPosts.items.push(req.body.clubToPost + ": " + req.body.postToDisplay);

                    //print post to server for testing
                    console.log(req.body.clubToPost);
                    console.log(req.body.postToDisplay);
                    var jsonResponse = {
                    id: '123', status: 'updated'
                };
                res.json(jsonResponse);
                });

        app.put('/getClubObject', function(req, res) {

                            // If for some reason the JSON isn't parsed, return HTTP error 400
                            if (!req.body) return res.sendStatus(400);
                            var position = getClubPosition(req.body.club);
                            console.log(position);

                            var clubname = clubs.items[position].clubname;
                            var post = clubs.items[position].post;
                            var description = clubs.items[position].description;
                            var username = clubs.items[position].username;
                            var keywords = clubs.items[position].keywords;
                            var jsonResponse = {
                            //id: '123', status: 'updated'
                        };
                        console.log(clubname);
                        console.log(post);
                        console.log(description);
                        console.log(username);
                        console.log(keywords);

                        var jsonResponse = {
                          club: clubname,
                          post: post,
                          description: description,
                          username: username,
                          keywords: keywords
                           };
                        res.json(jsonResponse);
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


        function getClubPosition(clubname_temp){
        //loop through array
        //console.log(clubname_temp);

            for(var x = 0; x < clubs.items.length; x++)
            {//search for club name and return once found
          //  console.log(clubs.items[x].clubname);
                if (clubs.items[x].clubname === clubname_temp)
                return x;
            }
            return -1;
        }

        function getUserPosition(username_temp){
            for(var x = 0; x < users.length; x++)
            {
                if (users.items[x].body.username === username_temp)
                return x;
            }
            return -1;
         }
        function getClubName(position) {
                      return clubs.items[position].clubname;
         }

        app.put('/userInformation', function(req, res) {

            // If for some reason the JSON isn't parsed, return HTTP error 400
            if (!req.body) return res.sendStatus(400);



            // Takes data from request and makes a new object
            var dataObject = {
                name: req.body.name,
                email: req.body.emailAddress,
                password: req.body.user_password,
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
            //funtion to send array of all the clubs created
            app.get('/getAllClubs', function(req,res){
             //array to which each club will be stored
               var club_names = {
                     items: []
               };
               for(var x = 0; x < clubs.items.length; x++){
                     club_names.items[x] = getClubName(x);
               }

                var stringArray = JSON.stringify(club_names);
                console.log( "clubs being sent to client: " + stringArray);
                res.send(stringArray);

             });

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

            app.get('/mostRecentPosts', function(req,res){

                        var mostRecentPostsTemp = {
                                           items: []
                                     };
                        for(var x = 0; x < mostRecentPosts.items.length; x++){
                        mostRecentPostsTemp.items[x] = mostRecentPosts.items[x];
                         }

                          var stringArray = JSON.stringify(mostRecentPostsTemp);
                          console.log( "posts being sent to client: " + stringArray);
                          res.send(stringArray);
                        });


app.listen(app.get("port"), function(){
	console.log('CS4531 UMDAlive app listening on port: ', app.get("port"));

});