$(document).ready(function (){
    getCountryChoices();
    
    $('#registrationForm').on('submit', function (e){
        e.preventDefault();
    });

    $('#registrationButton').on('click', function (){
        postJSON();
    });
});


function getCountryChoices(){
    let xhr = new XMLHttpRequest();
    xhr.open('GET', "/library-app/countries", true);
    xhr.timeout = 10000;
    xhr.ontimeout = (e) => APIError();

    xhr.onreadystatechange = function(){
        if(xhr.readyState === 4){
            if(xhr.status === 200){
                getCountryList(JSON.parse(xhr.responseText))
            } else{
               APIError()
            }
        }
    }
    xhr.send();
}


function getCountryList(response){
    let countries = response;
    if($.isEmptyObject(response)){
        $("#countrySelect").append("<option>No Countries</option>");
    } else{
        let output = "";
        for (let country of countries){
            if(country.name === "Greece"){
                output += `<option value="${country.name}" selected="selected">${country.name}</option>`
            } else {
                output += `<option value="${country.name}">${country.name}</option>`
            }
            
        }
        $("#countrySelect").append(output);
    }
}

function postJSON(){
    // Get data from registration form
    let username = $('#newUserUsername').val().trim();
    let password = $('#newUserPassword').val().trim();
    let email = $('#email').val().trim();
    let firstname = $('#firstname').val().trim();
    let lastname = $('#lastname').val().trim();
    let address = $('#address').val().trim();
    let postalCode = $('#postalCode').val().trim();
    let city = $('#city').val().trim();
    let country = $('#countrySelect option:selected').text();
    let phoneNumber = $('#phoneNumber').val().trim();
    let birthdate = $('#birthDate').val();

    // Create an XHR object
    let xhr = new XMLHttpRequest();

    // Open Connection
    xhr.open('POST', 'registration', true);

    // Set timeout
    xhr.timeout = 10000;
    xhr.ontimeout = (e) => APIError();

    // Set Content type to JSON
    xhr.setRequestHeader("Content-Type", "application/json;charset=UTF-8");

    // Check connection
    xhr.onreadystatechange = function (){
        if(xhr.readyState === 4){
            if(xhr.status === 200){
                console.log("status OK")
                $("#feedback").html('<p class="text-success">User registered</p>');
                resetFields();
        };
            }else {
				console.log("status not OK")
                APIError();  
            }
        }
    // Create JSON string with data
    var data = JSON.stringify({
        "username": username,
        "password": password,
        "email": email,
        "firstname": firstname,
        "lastname": lastname,
        "address": {
            "addressName": address,
            "postalCode": postalCode,
            "city": city,
            "country":{
                'countryName':country
            } 
        },
        "phoneNumber": phoneNumber,
        "birthdate": birthdate,
        "isadmin": false
     });
    
     /*$.ajax({
        url: 'register',
        dataType: 'json',
        contentType: 'application/json',
        type: 'POST',
        async: true,
        data: data,
        failure: APIError()
    });*/

    // Send JSON string
    xhr.send(data);    
} 

function resetFields(){
    $('#registrationForm :input').val('');
}

function APIError(){
    $("#feedback").html('<p class="text-danger">Something went wrong, the page might not work as intended</p>');
}