# Twitter_URL_Dashboard

This application is to facilitate the user who miss 
there important URLs from their twitter Home Timeline due to their 
busy schedules,
So, this app provides a dashboard to get all the 
URLs shared by their friends and followings at one place.


# Procedure to be followed to run this app.

-> Download the files from my repository and unzip it.

-> For running the php script and database switch on to xampp server and start Apache and MySQl in it.

-> For making my localhost global i have used a software named ngrok (using command :-  ngrok http 80).

-> Now copy the entire include folder and index.php file inside the htdocs of your computer.

-> After this switch on to crome and write the url that is generated during ngrok execution.

-> Move to android studio and open the android file provided. 

-> Inside the android project thier is a class named BackgroundTasks in that their is a variable url (paste the ngrok url til the index.php).

-> Then run the application to your mobile or emulator having internet connection enabled.




# Implementation

Firstly, I have written a php script to access to users home feed by using 
twitter api.
Link :- https://developer.twitter.com/en/docs/tweets/timelines/api-reference/get-statuses-home_timeline
Api Resource Url :- https://developer.twitter.com/en/docs/tweets/timelines/api-reference/get-statuses-home_timeline

Anyone cannot have access to the url directly for this first the user has to create an app in https://apps.twitter.com/ where in
user need to provide the basic details for the app like callback url and all.

After the creation of app in https://apps.twitter.com/ user will get Consumer_access_token and Consumer_access_token_secret from there.

Now comming to the php script here the user need to include three php file OAuth.php,TwitterAPIExchange.php,twitteroauth.php the files 
I have already providedin the include folder.

After accessing their home timeline I am treaming the text field to get only the url which their friends twitted and saving into the database.

Note: Here I have used local database to store the data. The fields that the database contains are followers userid,urls they shared,your userid and timestamp.



