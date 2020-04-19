

window.onload = function() //function to make the api post request... for now just doing it when the page is loaded ... implement this where you want and how you want later
{

    //done using ajax

    var html = new XMLHttpRequest();
    var url = "https://[PROJECT_ID].firebaseio.com/users/jack/name.json";// the url you wanna connect too
                //I DONT KNOW YOUR PROJECT ID , USRERNAME OR THE FILE YOU WANNA SEND STUFF TO 
                //KEEP THE .JSON AT THE END OF URL TO DO REQUESTS

    html.open("POST",url);


    //create the json data to be sent
    //HOW YOUR DATA IS STORED IN THE FIREBASE FOR SENDING THE NOTIFICATION
    //THIS IS JUST A DEMO ... NOT SURE HOW YOU IMPLEMENTED YOURS
    const data ={

        "data":{
            "title":"Hey",
            "content":"Cos Omega Notifications sent this notification",
        },
        "to":"/topics/all"  //THE to INDICATES WHO THE NOTIFICATION IS TO BE SEND TO0 , IN THIS CASE IT WILL SEND IT TO EVERYONE

    } ;

    $.post(url, data,function (data , status){
        console.log('${data} and status is ${status}');
    });

    html.close();
}