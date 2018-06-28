<?php
				$db="twitter";
                $user="root";
                $pass="";
                $server="localhost";
				$link=mysqli_connect($server,$user,$pass,$db);
				

require_once('include/OAuth.php');
require_once('include/TwitterAPIExchange.php');
require_once('include/twitteroauth.php');

$settings = array(
	'oauth_access_token' => "841199184-xxYO5ipP7hze7AQY57Vm9cPIMiFgNUDgzFmtjkVD",
	'oauth_access_token_secret' => "JSX6NEGr8Ty8A9ldbEwYe92AoOZNhOOdandJBSZAy3T3O",
	'consumer_key' => "SyG1u65zIKBtljkxnSnuM0NpF",
	'consumer_secret' => "dR5VMvCCA81p6zsge8yLWSryy4KjdkOYI9fh0INxR39iZ1iv55",
);

$requestMethod = 'GET';
$url = 'https://api.twitter.com/1.1/statuses/home_timeline.json';

$getField ='?screen_name=$_POST["UserID"]&count=20';
$name = $_POST["UserID"];

$twitter = new TwitterAPIExchange($settings);
$response = json_decode($twitter -> setGetfield($getField)
					 -> buildOauth($url,$requestMethod)
					 ->performRequest(),$assoc=TRUE);
					 
					 

foreach($response as $key)
{
	preg_match_all('#\bhttps?://[^,\s()<>]+(?:\([\w\d]+\)|([^,[:punct:]\s]|/))#',$key['text'], $match);
	if(!$match[0] == NULL){
	//$profilepic = $key['user']['profile_image_url'];
//echo '<img src='.$profilepic.'></img>';
	
	//echo "Time and Date : ".$key['created_at']."</br>";
	
	//echo "Screen Name : ".$key['user']['screen_name']."</br>";
	//echo "Followers : ".$key['user']['followers_count']."</br>";
	//echo "Friends : ".$key['user']['friends_count']."</br>";
	 $serialized_array = serialize($match); 
	 $time = $key['created_at'];
	 
	
	$query="Insert into dashboard(time,uid,curr_user,url) values('".$time."','".$key['user']['screen_name']."','".$name."','".$serialized_array."')";
                    mysqli_query($link,$query);
					
	
					
	$match = unserialize( $serialized_array );
	//echo"<hr>";
	
	
	
	}
}

	$sql = "SELECT * FROM dashboard where curr_user='". $name. "'";
$result = mysqli_query($link,$sql);

if($result->num_rows > 0){
    echo '{"testData":[';

    $first = true;
    $row=$result->fetch_assoc();
    while($row=mysqli_fetch_row($result)){
        

        if($first) {
            $first = false;
        } else {
            echo ',';
        }
        echo json_encode($row);
    }
    echo ']}';
} else
	echo '[]';
	





?>