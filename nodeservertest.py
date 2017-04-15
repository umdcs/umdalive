# importing the requests library
import requests
 
# getRequest URLS
getAllClubsURL = "http://127.0.0.1:5000/getAllClubs"


getmostrecentpostsURL="http://127.0.0.1:5000/mostRecentPosts"

getUserdata="http://127.0.0.1:5000/userDataGet"


#PUT request URLS

putUserinformation="http://127.0.0.1:5000/userInformation"
putnewPost="http://127.0.0.1:5000/newPost"
putnewClub="http://127.0.0.1:5000/newClub"

#-------- lsit of clubs to put into the server
club1={
     'clubname': 'baby',
        'username':'Justin bieber' ,
        'keywords': 'teenage-stuff',
        'description': 'oh yeah'
}


club2={
     'clubname': 'baby111',
        'username':'Just11111in bieber' ,
        'keywords': 'teenage-111111stuff',
        'description': 'oh yea2323123213213213h'
}

club3={
     'clubname': 'baddqwdwudehceiju3bry38cfn2mnr7f3rifnir3fh8xnwehcofuoicwlxvueyr4bv8r3bviur3niunvuirw3niufwevfwudwbfdwby',
        'username':'Justin defeiufnu3vnu3bfu3hqeipuuwduwdbieber' ,
        'keywords': 'teenae3fici3nfio3nf2wge-ewidewdwdewdwestuff',
        'description': 'odwqdu2ciru39hu48 g5h7r8v46dg7bc3rfurhfruenxfur bvyreojciwqdqxbcuvyoijx2efcph gyrcbyrufcrfcrydewdyewdbuewh yeah'
}


#------ new post to put into server

newpost={

        'club': "duby2ef24738fh01u48fgh58",
        'title': "duby2ef24738fh01u48fgh58",
        'time': "duby2ef24738fh01u48fgh58",
        'date': 'Justin defeiufnu3vnu3bfu3hqeipuuwduwdbieber' ,
        'location':'Justin defeiufnu3vnu3bfu3hqeipuuwduwdbieber' ,
        'description': 'Justinefeiufnu3vnu3bfu3hqeipuuwduwdbieber' 

}

#-- new user info to enter into the server 
newuser={

        'name': "fehrybfyr3bfeuybf3ryfbrgy3fb475ebfyebfuwb",
        'email': "fehrybfyr3bfeuybf3ryfbrgy3fb475ebfyebfuwb",
        'password': "fehrybfyr3bfeuybf3ryfbrgy3fb475ebfyebfuwb",
        'graduation_date': "fehrybfyr3bfeuybf3ryfbrgy3fb475ebfyebfuwb",
        'major': "fehrybfyr3bfeuybf3ryfbrgy3fb475ebfyebfuwb"
        

}
#method takes in the URL parameters for get_request and prints out data from the server stack

def get_requests(URL1,URL2,URL3):
 
    r = requests.get(URL1)
    data = r.json() #data from the server needs to be converted to JSON Strings 
   
    r_recentPosts=requests.get(URL2)
    data_recentPosts=r_recentPosts.json()#data from the server needs to be converted to JSON Strings

    r_getUserData=requests.get(URL3)
    data_userdata=r_getUserData.json()#data from the server needs to be converted to JSON Strings

#printing data 
    print("Club Listings:%s \n"%(data))
    print("Recent Post:%s \n"%(data_recentPosts))
    print("User data:%s \n"%(data_userdata))


#method puts data into the server. takes in the put urls and stacks the server suing the value and keys

def put_request(URL1,URL2,URL3): 
    
    r_putUser = requests.put(URL1, newuser)
    r_putPost=requests.put(URL2, newpost)
  
    r_putCLub=requests.get(URL3, club1)
  
    r_putCLub2=requests.get(URL3, club1)

    r_putCLub3=requests.get(URL3, club1)


get_requests(getAllClubsURL,getmostrecentpostsURL,getUserdata)
put_request(putUserinformation,putnewPost,putnewClub)

print"new basket ---------------------------------------------------------"

get_requests(getAllClubsURL,getmostrecentpostsURL,getUserdata)






