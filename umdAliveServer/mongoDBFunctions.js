//loads the MongoDB package
var mongojs = require("mongojs");

var url = 'mongodb://127.0.0.1:27017/umdAliveDatabase';

//array of collections we will use
var collections = ['allClubs', 'users', 'posts'];

var assert = require('assert');

var mongoDBRef = mongojs(url, collections);
console.log("MongoDB is active.")

//the following are anonymous functions that wil be used in index.js

/**
* Inserts json info of a new club into the allClubs collection.
* If allClubs collection doesn't exist, it is created.
* @param - clubData JSON data of the club
*/
module.exports.insertClub = function(clubData) {
    mongoDBRef.collection('allClubs').save({club: clubData.clubName, clubData}, function(err, result){
        if(err || !result) console.log("Club failed to save in database.");
        else console.log("Club inserted into allClubs collection in MongoDB.");
    });
};

/**
*
*/
module.exports.findClub = function(clubName, callback) {
    mongoDBRef.collection('allClubs').find({club: clubName}).toArray(function(err, docs) {
	    if(!err){
	        console.log("Found the following records");
	        console.log(docs);
	        callback(docs);
	    }
	    else console.log("Club not found.")
    });
};