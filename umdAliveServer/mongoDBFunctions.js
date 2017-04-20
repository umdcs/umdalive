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
* @param clubData - JSON data of the club
*/
module.exports.insertClub = function(clubData) {
    mongoDBRef.collection('allClubs').save({club: clubData.clubName, clubData}, function(err, result){
        if(err || !result) console.log("Club failed to save in database.");
        else console.log("Club inserted into allClubs collection in MongoDB.");
    });
};

/**
* Inserts json info of a new post into the posts collection.
* If posts collection doesn't exist, it is created.
* @param postData - JSON data of the post
*/
module.exports.insertPost = function(postData) {
    console.log(postData);
    mongoDBRef.collection('posts').save({post: postData.clubName, postData}, function(err, result){
        if(err || !result) console.log("Post failed to save in database.");
        else console.log("Post inserted into posts collection in MongoDB.");
    });
};

/**
* Finds a specific club
* @param clubName - string of club's name
* @param callback - the function that the result is sent to
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

module.exports.getCollection = function(collectionName, callback) {
    var cursor = mongoDBRef.collection(collectionName).find(function(err, docs) {
            if(err || !docs) {
    	    console.log("Error in retrieving collection\n");
    	}
            else {
    	    callback(docs);
    	}
        });
};