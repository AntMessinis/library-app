$(document).ready(function (){
    getCountryChoices()
});

function getCountryChoices(){
    let xhr = new XMLHttpRequest();
    xhr.open('GET', "/library-app/countries");
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
                output += `<option selected="selected">${country.name}</option>`
            } else {
                output += `<option>${country.name}</option>`
            }
            
        }
        $("#countrySelect").append(output);
    }
}

function APIError(){
    $("#feedback").html('<p class="text-danger">Something went wrong the page might not work as intended</p>');
}