
var express = reqiure('express');

var app = express();

var bodyParser = require('body-parser');

app.set("port",5000);

app.use(bodyParser.urlencoded({
	extended: true;
}));

app.use(bodyParser.json());

var clubs = new Array();//new empty array for clubs to reside


//-----------------------------
//PUT paths
//-----------------------------

app.put('newClub',function(req,res){


        // If for some reason, the JSON isn't parsed, return a HTTP ERROR
        // 400
        if (!req.body) return res.sendStatus(400);

        //takes data from request and makes into new 'club' object
        //contains name, keywords, description and its posts
        var club = {clubname: req.body.clubname
                    admin: req.body.admin
                    keywords: req.body.keywords
                    description: req.body.description
                    post: req.body.post};

        clubs[clubs.length] = club; //adds new club to array of clubs



        console.log("new club has been created: " + req.body.clubName);
});

app.listen(app.get("port"), function(){
    console.log('CS4531 UMDAlive app listening on port: ', app.get("port"));
});

