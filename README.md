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

-> Then run the application in your mobile or emulator having internet connection enabled.




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

Now coming to android. In android I have created 3 activities they are :- MainActivity, LoginActivity and HomeActivity.

-> MainActivity is just for showing the splash screen and taking the user to login activity. 

-> In login activity using TwitterLoginButton and user session I am authenticating the user and if the user has a twitter 
app then he will just need to press authenticate and if the user dont have twiiter app in his mobile then the user will be
re directed to a webview asking for the twitter credentials for login in.

-> The HomeActivity is for displaying the details fetched from users home timeline like friends userid, Timestamp of the tweet
and the urls shared.

For fetching the details of user's home timeline I have created three classes they are :- 
BackgroundTask which is extending Asynchronous Tasks and running in background wherein I have created a json to send the 
loggedin users userid and tokens fetched from session and then passing these all with post to php code 
Now in PostExecute method I am fetching the json returned from the execution of the php and saving in a ArrayList of classtype
MyDataObject which contains the Class variables like timestamp, userid and arraylist of type string containing the urls.

Now In the main adapter class I am filling the details of the user home timeline in the card view using recycler view for displaying in the homeactivity.




