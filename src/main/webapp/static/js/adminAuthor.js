$(document).ready(function(){
    $('#adminAddAuthor').on('click', function(){
        showAuthorForm();
    });
});

function showAuthorForm(){
    $('#mainContainer').html(`
        <h3>Add new Author</h3>
        <form id="authorForm">
            <div class="row">
                <div class="col-4">
                    <label for="authorFirstname" class="form-label mt-3">Firstname</label>
                    <input id="authorFirstname" class="form-control me-sm-2" type="text">
                </div>
                <div class="col-4">
                    <label for="authorLastname" class="form-label mt-3">Firstname</label>
                    <input id="authorLastname" class="form-control me-sm-2" type="text">
                </div>
            </div>
            <div class="row">
                <div class="col-4">
                    <label for="authorCountrySelect" class="form-label mt-3">Country of Origin</label>
                    <select class="form-select" id="authorCountrySelect" placeholder="Choose Country of Origin"></select>
                </div>
            </div>
            <div id="authorFormButtons" class="form-group row mt-3">
                <div class="col-md-3">
                    <button type="submit" id="authorFormSubmitButton" class="btn btn-primary">Submit</button>
                    <a role="button" class="btn btn-outline-primary" href="/library-app/">Cancel</a>
                </div>
            </div>
        </form>
    `);
    $("#authorForm").on('submit', function(e){
        e.preventDefault();
    });
    getCountriesFromDB();
    $('#authorFormSubmitButton').on('click', function(){
        addAuthor();
    });
}

function getCountriesFromDB(){
    let xhr = new XMLHttpRequest();
    xhr.open('GET', "/library-app/countries", true);
    xhr.timeout = 10000;
    xhr.ontimeout = (e) => APIError();

    xhr.onreadystatechange = function(){
        if(xhr.readyState === 4){
            if(xhr.status === 200){
                addCountryChoicesToForm(JSON.parse(xhr.responseText))
            } else{
               APIError()
            }
        }
    }
    xhr.send();
}

function addCountryChoicesToForm(response){
    if($.isEmptyObject(response)){
        $('#feedback').html('Something went wrong. Please try again.');
    } else {
        let output = '';
        for (let country of response){
            if(country.name === "Greece"){
                output += `<option value="${country.name}" selected="selected">${country.name}</option>`
            } else {
                output += `<option value="${country.name}">${country.name}</option>`
            }
        }
        $('#authorCountrySelect').append(output);
    }
}

function addAuthor(){
    let firstname = $('#authorFirstname').val().trim();
    let lastname = $('#authorLastname').val().trim();
    let countryOfOrigin = $('#authorCountrySelect option:selected').text();

    let xhr = new XMLHttpRequest();
    xhr.open('POST', 'add-author', true);
    xhr. timeout = 10000;
    xhr.ontimeout = (e)=> $('#feedback').html('Something went wrong. Please try again.');

    xhr.setRequestHeader('Content-Type', 'application/json;charset=UTF-8');
    xhr.onreadystatechange = function(){
        if(xhr.readyState === 4){
            if(xhr.status === 200){
                console.log('Status OK');
                clearForm('#authorForm');
                $('#feedback').html(`<p class=text-success>Author ${firstname} ${lastname} was added successfully to the database</p>`);
            } else {
                console.log('Status not OK');
                $('#feedback').html(`<p class=text-danger>Something went wrong. Author wasn't saved to Database</p>`);
            }
        }
    }
    let data = JSON.stringify({
        "firstname":firstname,
        "lastname": lastname,
        "countryOfOrigin": {"countryName":countryOfOrigin}
    });

    xhr.send(data);
}

function clearForm(selector){
    selector.val('');
}