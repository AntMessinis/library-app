//import {handleBookAuthorSearchResults} from './authorSearch';
$(document).ready(function(){
    $('#adminAddAuthor').on('click', function(){
        clearFeedback();
        showNewAuthorForm();
    });

    $('#adminSearchForAuthor').on('click', function(){
        clearFeedback();
        getAuthorListFromDB();
    });
});

function showNewAuthorForm(){
    $('#mainContainer').html(`
        <h3>Add new Author</h3>
        <form id="authorForm">
            <div class="row">
                <div class="col-4">
                    <label for="authorFirstname" class="form-label mt-3">Firstname</label>
                    <input id="authorFirstname" class="form-control me-sm-2" type="text">
                </div>
                <div class="col-4">
                    <label for="authorLastname" class="form-label mt-3">Lastname</label>
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
                clearForm($('#authorForm :input'));
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

function getAuthorListFromDB(){
    let xhr = new XMLHttpRequest();
    xhr.open('GET', `/library-app/authors`,true)
    xhr.timeout = 10000;
    xhr.ontimeout = (e) => APIError();

    xhr.onreadystatechange = function(){
        if(xhr.readyState === 4){
            if(xhr.status === 200){
                showAuthorList(JSON.parse(xhr.responseText));
            }else{
                APIError();
            }
        }
    }

    xhr.send();
}

function showAuthorList(authors){
    if($.isEmptyObject(authors)){
        $('#feedback').html('Something went wrong. Please try again.');
    }else {
        let output = `
        <table class="table table-hover">
            <thead>
            <tr>
            <th scope="col">ID</th>
            <th scope="col">Firstname</th>
            <th scope="col">Lastname</th>
            <th scope="col">Country</th>
            </tr>
        </thead>
        <tbody>
    `;
        for (let author of authors){
            output += `<tr id="tableRow${author.id}" class="table-light">
            <th scope="row">${author.id}</th>
            <td class="author-firstname">${author.firstname}</td>
            <td class="author-lastname">${author.lastname}</td>
            <td class="author-country">${author.countryOfOrigin.name}</td>
            <td><a id="authorBooks${author.id}" class="btn btn-primary">Show Books</a></td>
            <td><a id="authorDelete${author.id}" class="btn btn-danger">Delete</a></td>
          </tr>`;
        }
        output += '</tbody></table>';
        $('#mainContainer').html(output);

        for (let author of authors){
            $(`#authorDelete${author.id}`).on('click', function(){
                $(`#tableRow${author.id}`).remove();
                deleteAuthor(author.id, author.firstname, author.lastname, author.countryOfOrigin.name);
                });

            $(`#authorBooks${author.id}`).on('click', function(){
                getAuthorsBooksFromDB(author.lastname)
            });
        }

        
    }
}

function deleteAuthor(id, firstname, lastname, country){
    let xhr = new XMLHttpRequest();
    xhr.open('POST', 'delete-author', true);
    xhr.timeout = 10000;
    xhr.ontimeout = (e)=> $('#feedback').html("<p class=text-danger>Something went wrong.</p>");
    xhr.setRequestHeader('Content-Type', 'application/json;charset=UTF-8');
    xhr.onreadystatechange = function(){
        if(xhr.readyState === 4){
            if(xhr.status === 200){
                console.log('Status OK');
            }
        }else {
            console.log('Status not OK');
        }
    }

    let data = JSON.stringify({
        "id":id,
        "firstname":firstname,
        "lastname": lastname,
        "countryOfOrigin":{"countryName":country}
    });
    
    xhr.send(data);
}


function getAuthorsBooksFromDB(lastname){
    let xhr = new XMLHttpRequest();
    xhr.open('GET', `/library-app/searchByAuthor?author=${lastname}`, true);
    xhr.timeout = 10000;
    xhr.ontimeout = (e)=> $('#feedback').html("<p class=text-danger>Something went wrong.</p>");
    xhr.onreadystatechange = function(){
        if(xhr.readyState === 4){
            if(xhr.status === 200){
                console.log('Status OK');
                showAuthorBookList(JSON.parse(xhr.responseText));
            }
        }else {
            console.log('Status not OK');
        }
    }
    
    xhr.send();
}

function showAuthorBookList(bookList){
    if ($.isEmptyObject(bookList)){
        $("#feedback").html("<p>No book was found</p>")
    }else {
        let output = `<div class="row mb-5 align-items-center text-center">`;
        for (let book of bookList){
            output += `<div class="col-4 h-75 d-inline-block mt-5 align-items-center ">
            <h4>${book.title}</h4>` 
            +'<img src="/library-app/static/imgs/tumbnail.jpg" alt="bookPic">'
            + `<p>In Library: ${book.copiesInLibrary}</p>
            <p>Currently Borrowed: ${book.copiesCurrentlyBorrowed}</p></div>
            `
        }
        output += "</div>";
        $("#mainContainer").html(output);
    }
}

function clearForm(jQueryFormSelector){
        jQueryFormSelector.val('');
}

function clearFeedback(){
    $('#feedback').html('');
}