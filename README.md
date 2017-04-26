UMD Alive
=========

A two-team effort, UMD Alive is an Android Application meant to connect students on campus with information and events from clubs around campus. The application is meant to be an informational hub and, in a way, digitizes the informational posters a student may find around campus.

One team was responsible for business aspects and idea creation, while the other team was responsible for implementing the ideas in the form of an application.

Cultural entrepreneurship (CUE) 
team: Anastasia Kratzner, Holly Pudwill, and Ryan Danielson.

Computer Science (CS) 
Andy Miller, Wesley Osumo, Ryan Gebhart, and Jenna Anderson. 

------------
Preparing to Install
-------------
The application should work without trouble for over 97% of Android users, but the tested software version details are outlined below.

 **Software Information:**

> - Android Studio Version last verified to work with `master`: 2.2.3
> - Minimum Android SDK: 15+ (minimum Ice Cream Sandwich 4.0.3).
> - Target SDK: 25.

To run the App you need to connect to the server and a MongoDB. The IP address is set to the emulator tunnel by default but can be changed in RESTModel. 

**Node will need these modules installed**
- express
- body-parser
- mongodb
- mongo.js

node version 1.0.0

**MongoDB**
Have an instance of MongoDB running for the server to connect to.
 
Current State of Project
-------------

**Streamline** 

 The streamline was implemented using a RecyclerView holding CardViews. The RecyclerView acts as a flexible list of all events that have been posted(in the form of cards). It implements a view holder which contains a card containing the event title, posting club’s name, event date and time, location, description, and an image. The RecyclerView works nicely in this context because it allows for new posts to be easily added to the the streamline. 

**Post for Club**

Directly related to the streamline feature, a key feature for the app is the ability to create a post about an event for your club. Posts appear on the streamline of recent/upcoming events. Each post has an image header selected by the poster,  event name, date, time, and location. These all appear on every event posted in the streamline. If the user wishes to see additional information or an extended description, they can tap the post to expand the information.

**User Profile**

Generated from Google Login. A profile picture, name, and email address is accessed from Google. An option to add your Major and Graduation Date is also available. 

**Create A Club**

User can make a new club with a club name, admin user, description, and a keyword tag.

**Search For Club**

After an interest profile was collected by the CUE group, they gave us a list of key terms that students had suggested as possible interest groupings for clubs. Some of the keywords suggested were academic, food, free, religion, sports, outdoors, and music. A spinner is used to select which keyword they would like to search for clubs. The list of clubs with their accompanying key terms are stored as an array of strings. 

**Login**

In order to login to the app using a UMD ID/password, we implemented the Google OAuth API. By implementing this, it uses Google authentication and authorization to log in. After obtaining credentials from the Google API console, an access token is granted from the Google Authorization Server and then the token can be refreshed if necessary. Since the UMD login is hosted through Google, the Google OAuth login can be used for student accounts. The API also has features to return dname, email, and user picture.

There is currently no options for deleting anything. This should be one of the first things to proceed with.

**Data Management**

All data is stored on a MongoDB which is managed by a node.js server. File names are index.js and mongoDBFunctions.js found in umdAliveServer folder.

Unit Testing Inlcuded
---------------------

Jenna Anderson: ClubInformationUnitTest; creates a test club and checks to see that all the data is created and stored properly. 

Ryan Gebhart: PostInformationModel; Test checks proper initialization of Posts. It compares different posts checking for inequality.
    JSONStringify() is not tested due to this error: java.lang.RuntimeException: Method put in org.json.JSONObject not mocked.

Wesley Osumo: NodeServer; The test checks several Get and Put Rquests. an initial querry is done to the server to get the current stack, then several put requests are sent to the server. The server then is requested to return the current stack once more.  Current club and the position methods are not tested due to incompletes in the project infrauscture.

Andy Miller: Presenter; Created a thorough unit test for all functionality.
    There are issues with using AsyncTask in unit tests so it makes many of the tests worthless. I will look into it more to see if there's a good way to work around that.
