UMD Alive
=========

A two-team effort, UMD Alive is an Android Application meant to connect students on campus with information and events from clubs around campus. The application is meant to be an informational hub and, in a way, digitizes the informational posters a student may find around campus.

One team was responsible for business aspects and idea creation, while the other team was responsible for implementing the ideas in the form of an application.

Cultural entrepreneurship (CUE) team: Anastasia Kratzner, Holly Pudwill, and Ryan Danielson.

Computer science (CS) team: Brandon Harshe, Kevin Schauer, Luke Anderson, and Ray Cerney.
Andy Miller, Wesley Osumo, Ryan Gebhart, and Jenna Anderson. 

----------

Preparing to Install
-------------
The application should work without trouble for over 97% of Android users, but the tested software version details are outlined below.

> **Software information:**

> - Android Studio version last verified to work with `master`: 2.2.2.
> - Minimum Android SDK: 15+ (minimum Ice Cream Sandwich 4.0.3).
> - Target SDK: 25.

Current State of Project
-------------

There is currently no options for deleting anything. This should be one of the first things to proceed with.

Currently the whole application signs in a “dummy user.” Moving forward it would be essential to implement a way to sign up which creates a new user and then when they login it would go and grab the clubs they are subscribed.

Overview of Activities
-------------

**AllCubs**

Currently displays all the club names in alphabetical order. Each club can be selected and its information will be displayed in a new activity.


**Club**

This class will make a new club and send JSON data to be stored on server. This class still needs to attach more keywords to each club. It also is not functional at if any of the data is messed up (can't be edited or deleted easily)


**LoginActivity**

Uses Google OAuth to let user log in using their UMD ID and password.


**Main Activity**

Is the main page of the app where the posts from clubs should be shown. As of now it shows posts associated with the user's clubs that they follow after selecting the refresh button. Plan to make the posts a container type object vs just text. The menu on the page contains access to all clubs and posting for each club. The menu has the users email and name from the server. It pulles a dummy user not a real one from the array of users.



/////////////////////////////////////////////
Additions by Spring Semester Team
/////////////////////////////////////////////

************************************************************************************************************
TESTING

-Ryan-
PostInformationModel - Test checks proper initialization of Posts. It compares different posts checking for inequality.
    JSONStringify() is not tested due to this error: java.lang.RuntimeException: Method put in org.json.JSONObject not mocked.

--Wesley OSumo--
NodeServer: The test checks several Get and Put Rquests. an initial querry is done to the server to get the current stack, then several put requests are sent to the server. The server then is requested to return the current stack once more.  Current club and the position methods are not tested due to incompletes in the project infrauscture.

-Andy-
Presenter: Created a thorough unit test for all functionality.
    There are issues with using AsyncTask in unit tests so it makes many of the tests worthless. I will look into it more to see if there's a good way to work around that.

Unit Testing Inlcuded
---------------------

Jenna: ClubInformationUnitTest; creates a test club and checks to see that all the data is created and stored properly. 

Licensing
----------------
The licensing can be found in the Java Folder. 
