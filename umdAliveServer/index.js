
var express = require('express');

var app = express();


var express = require('express');
var bodyParser = require('body-parser');

var app = express();
app.set("port",5000);

app.use(bodyParser.urlencoded({
	extended: true
}));

app.use(bodyParser.json());

var clubs = new Array();//new empty array for clubs to reside


//-----------------------------
//PUT paths
//-----------------------------

app.put('/newClub',function(req,res){
        console.log("At start of newClub ");

        // If for some reason, the JSON isn't parsed, return a HTTP ERROR
        // 400
        if (!req.body) return res.sendStatus(400);

        //takes data from request and makes into new 'club' object
        //contains name, keywords, description and its posts
        var club = {clubname: req.body.clubname,
                    username: req.body.username,
                    keywords: req.body.keywords,
                    description: req.body.description,
                    post: req.body.post};

        clubs[clubs.length] = club; //adds new club to array of clubs

        var jsonResponse = {
    	//id: '123',
    	//status: 'updated'
        id: '123', status: 'updated'
        };
        res.json(jsonResponse);


        console.log("new club has been created: " + req.body.clubname);
        console.log("administrator is: " + req.body.username);
        console.log("keyword is: " + req.body.keywords);
        console.log("description of club is: " + req.body.description);
        console.log("initial post of club is: " + req.body.post);
});

app.listen(app.get("port"), function(){
    console.log('CS4531 UMDAlive app listening on port: ', app.get("port"));

});

