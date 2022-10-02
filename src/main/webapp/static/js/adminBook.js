$(document).ready(function(){
    $('#adminAddBook').on('click', function(){
        showAddBookForm();
        getCategoriesFromDB();
        getAuthorsFromDB();
        getLanguagesFromDB();
    });

    $('#adminUpdateBook').on('click', function(){
        showSearchBookForm();
    });

    $('#adminDeleteBook').on('click', function(){
        //showDeleteBookForm();
    });
});

//####### ADD BOOK FUNCTIONS #######

function showAddBookForm(){
    showBookForm();

	$('#bookForm').on('submit', function (e){
        e.preventDefault();
    });
    
    $('#bookFormSubmitButton').on('click', function(){
        addBookToDB();
    });
}


function getAuthorsFromDB(){
    let xhr = new XMLHttpRequest();
    xhr.open('GET', `/library-app/authors`,true)
    xhr.timeout = 10000;
    xhr.ontimeout = (e) => APIError();

    xhr.onreadystatechange = function(){
        if(xhr.readyState === 4){
            if(xhr.status === 200){
                getAuthorList(JSON.parse(xhr.responseText));
            }else{
                APIError();
            }
        }
    }

    xhr.send();
}


function getCategoriesFromDB(){
    let xhr = new XMLHttpRequest();
    xhr.open('GET', `/library-app/categories`,true)
    xhr.timeout = 10000;
    xhr.ontimeout = (e) => APIError();

    xhr.onreadystatechange = function(){
        if(xhr.readyState === 4){
            if(xhr.status === 200){
                getCategoryList(JSON.parse(xhr.responseText));
            }else{
                APIError();
            }
        }
    }

    xhr.send();
}

function getLanguagesFromDB(){
    let xhr = new XMLHttpRequest();

    xhr.open('GET', `/library-app/languages`, true);
    xhr.timeout = 10000;
    xhr.ontimeout = (e) => APIError();

    xhr.onreadystatechange = function(){
        if(xhr.readyState === 4){
            if(xhr.status === 200){
                getLanguageList(JSON.parse(xhr.responseText));
            } else {
                APIError();
            }
        }
    }
    xhr.send();
}

function getAuthorList(authors){
    if($.isEmptyObject(authors)){
        $('#feedback').html('<p>There are no authors in the database.</p>')
    } else {
        let output = "";
        for(let author of authors){
            if(author.lastname){
                output +=  `<option value="${author.lastname}, ${author.firstname}, ${author.countryOfOrigin.name}">${author.lastname}, ${author.firstname}</option>`
            } else {
                output +=  `<option value="${author.firstname}">${author.firstname}</option>`
            }
            
        }
        $('#authorSelect').append(output);
    }
}

function getCategoryList(categories){
    if($.isEmptyObject(categories)){
        $('#feedback').html('<p>There are no categories in the database.</p>')
    } else {
        let output = "";
        for(let category of categories){
            output +=  `<option value="${category.categoryName}, ${category.subcategoryName}">${category.subcategoryName}</option>`
        }
        $('#categorySelect').append(output);
    }
}

function getLanguageList(languages){
    if($.isEmptyObject(languages)){
        $('#feedback').html('<p>There are no languages in the database</p>');
    } else {
        let output = "";
        for(let language of languages){
            output += `<option value="${language.id}">${language.languageName}</option>`
        }
        $('#languageSelect').append(output);
    }
}

function addBookToDB(){
    let title = $('#bookTitle').val().trim();
    let isbn = $('#bookIsbn').val().trim();
    let description = $('#bookDescription').val().trim();
    let category = $('#categorySelect option:selected').val();
    let categoryName = category.substring(0, category.indexOf(',')).trim();
    let subcategoryName = category.substring(category.indexOf(',') + 1).trim();
    let author = $('#authorSelect option:selected').val().trim();

    let firstCommaIndex = author.indexOf(",");
    let secondCommaIndex =author.lastIndexOf(",");

    let lastname = author.substring(0,firstCommaIndex).trim();
    let firstname = author.substring(firstCommaIndex + 1, secondCommaIndex).trim();
    let countryName = author.substring(secondCommaIndex + 1).trim();

    let languageName = $("#languageSelect option:selected").text();
    let copiesInLibrary = $('#addCopiesInLibrary').val();

    let xhr = new XMLHttpRequest();
    xhr.open('POST', 'add-book', true);
    xhr.timeout = 10000;
    xhr.ontimeout = (e) => APIError();
    xhr.setRequestHeader('Content-Type','application/json;charset=UTF-8');

    xhr.onreadystatechange = function(){
        if(xhr.readyState === 4){
            if(xhr.status === 200){
                console.log("status OK")
                
                $("#feedback").html(`<p class="text-success">${title} was added successfully to the library!</p>`);
                resetFields($('#bookForm :input'));
            }else{
                console.log("status Not OK");
                APIError();
            }
        }
    }
    var data = JSON.stringify({
        "title":title,
        "isbn": isbn,
        "author": {"firstname": firstname, "lastname": lastname, "countryOfOrigin":{"countryName": countryName}},
        "category": {"categoryName": categoryName, "subcategoryName": subcategoryName},
        "description": description,
        "language": {"languageName": languageName},
        "copiesInLibrary": copiesInLibrary,
        "currentlyBorrowed": 0                 
    });
  xhr.send(data);
}


// ####### UPDATE BOOK #######


function showSearchBookForm(){
    let mainContainer = $('#mainContainer');

    mainContainer.html(`
        <form id="bookUpdateForm">
            <div class="row">
                <div class=col-6>
                    <label for="searchBookTitle" class="form-label mt-3">Title</label>
                    <input id="searchBookTitle" class="form-control me-sm-2" type="text" placeholder="">
                </div>
                <div class=col-6>
                <label for="searchBookIsbn" class="form-label mt-3">ISBN</label>
                <input id="searchBookIsbn" class="form-control me-sm-2" type="text" placeholder="">
                </div>
            </div>
            <div class=form-group row mt-3>
                <div class="col-md-4">
                    <button type="submit" id="bookSearchButton" class="btn btn-primary">Submit</button>
                    <a role="button" class="btn btn-outline-primary" href="/library-app/">Cancel</a>
                </div>
            </div>
        </form>
    `);

    $('#bookUpdateForm').on('submit', function (e){
        e.preventDefault();
    });
    
    $('#bookSearchButton').on('click', function(){
        searchForBookByTitleOrIsbn();
    });
}

function searchForBookByTitleOrIsbn(){
    let bookTitle = $('#searchBookTitle').val().trim();
    let bookIsbn = $('#searchBookIsbn').val().trim();

    let jsonKey = `${bookTitle.length > 0 ? 'title' : 'isbn'}`;
    let value = `${bookTitle.length > 0 ? bookTitle : bookIsbn}`;

    let xhr = new XMLHttpRequest();
    xhr.open('POST', `getbook`, true);
    xhr.timeout = 10000;
    xhr.ontimeout = (e) => APIError();
    xhr.setRequestHeader('Content-Type','application/json;charset=UTF-8');
    xhr.onreadystatechange = function(){
        if(xhr.readyState === 4){
            if(xhr.status === 200){
                console.log("status OK");
                getBookData(JSON.parse(xhr.responseText));
            }else {
                console.log("status Not OK");
            }
        }
    }
    var data = JSON.stringify({
        "title": bookTitle,
        "isbn": bookIsbn
    });
    console.log(data);
    xhr.send(data);
}

function showUpdateBookForm(bookData){
        showBookForm();
        getCategoriesFromDB();
        getAuthorsFromDB();
        getLanguagesFromDB();
        $('#bookForm').on('submit', function (e){
            e.preventDefault();
        });
        $('#bookFormSubmitButton').on('click', function(){
            updateBook();
        });

        $('#bookTitle').val(`${bookData.title}`);
        $('#bookIsbn').val(`${bookData.isbn}`);
        $('#bookDescription').val(`${bookData.description}`);
        $(`#authorSelect option[value="${bookData.author.lastname}, ${bookData.author.firstname}, ${bookData.author.countryOfOrigin.countryName}"]`).prop('selected', true);
        $(`#categorySelect option[value="${bookData.category.categoryName},${bookData.category.subcategoryName}"]`).prop('selected', true);
        $(`#authorSelect option[value="${bookData.language.languageName}"]`).prop('selected', true);
        $('#copiesInLibrary').val(`${bookData.copiesInLibrary}`);
}

function getBookData(bookData){
    if($.isEmptyObject(bookData)){
        APIError();
    }else {
        showUpdateBookForm(bookData);
    }
}

function updateBook(){
    let title = $('#bookTitle').val().trim();
    let isbn = $('#bookIsbn').val().trim();
    let description = $('#bookDescription').val().trim();
    let category = $('#categorySelect option:selected').val();
    let categoryName = category.substring(0, category.indexOf(',')).trim();
    let subcategoryName = category.substring(category.indexOf(',') + 1).trim();
    let author = $('#authorSelect option:selected').val().trim();

    let firstCommaIndex = author.indexOf(",");
    let secondCommaIndex =author.lastIndexOf(",");

    let lastname = author.substring(0,firstCommaIndex).trim();
    let firstname = author.substring(firstCommaIndex + 1, secondCommaIndex).trim();
    let countryName = author.substring(secondCommaIndex + 1).trim();

    let languageName = $("#languageSelect option:selected").text();
    let copiesInLibrary = $('#addCopiesInLibrary').val();

    let xhr = new XMLHttpRequest();
    xhr.open('POST', 'update-book', true);
    xhr.timeout = 10000;
    xhr.ontimeout = (e) => APIError();
    xhr.setRequestHeader('Content-Type','application/json;charset=UTF-8');

    xhr.onreadystatechange = function(){
        if(xhr.readyState === 4){
            if(xhr.status === 200){
                console.log("status OK")
                
                $("#feedback").html(`<p class="text-success">${title} was added successfully to the library!</p>`);
                resetFields($('#bookForm :input'));
            }else{
                console.log("status Not OK");
                APIError();
            }
        }
    }
    var data = JSON.stringify({
        "title":title,
        "isbn": isbn,
        "author": {"firstname": firstname, "lastname": lastname, "countryOfOrigin":{"countryName": countryName}},
        "category": {"categoryName": categoryName, "subcategoryName": subcategoryName},
        "description": description,
        "language": {"languageName": languageName},
        "copiesInLibrary": copiesInLibrary,
        "currentlyBorrowed": 0                 
    });
  xhr.send(data);
}


//###### UTILITY FUNCTIONS #######

function showBookForm(){
    $('#mainContainer').html(`
    <h3>Add New Title to Library</h3>
    <form id="bookForm">
        <div class="row">
        <div class="col-4">  
        <label for="bookTitle" class="form-label mt-3">Title</label>
        <input id="bookTitle" class="form-control me-sm-2" type="text" placeholder="Title"></div>
        <div class="col-4">  
        <label for="bookIsbn" class="form-label mt-3">ISBN</label>
        <input id="bookIsbn" class="form-control me-sm-2" type="text" placeholder="ISBN"></div>
        <div class="col-4">  
        <label for="authorSelect" class="form-label mt-3">Author</label>
        <select class="form-select" id="authorSelect" placeholder="Choose Author">        
        </select></div>
        </div>
        <div class="row">
        <div class="col-4">  
        <label for="categorySelect" class="form-label mt-3">Category</label>
        <select class="form-select" id="categorySelect" placeholder="Choose Category">
        </select></div>
        <div class="col-4">  
        <label for="languageSelect" class="form-label mt-3">Language</label>
        <select class="form-select" id="languageSelect" placeholder="Choose Language">        
        </select></div>
        <div class="col-4">  
        <label for="copiesInLibrary" class="form-label mt-3">Copies in Library</label>
        <input id="copiesInLibrary" class="form-control me-sm-2" type="number" min="1" max="20 style="-moz-appearance: textfield; -webkit-appearance: none;">
        </div>
        </div>
        <div class="row"><label for="bookDescription" class="form-label mt-3">Description</label>
        <textarea id="bookDescription" class="form-control me-sm-2" placeholder="Description" rows="10"></textarea></div>
        <div class="form-group row mt-3">
            <div class="col-md-3">
                <button type="submit" id="bookFormSubmitButton" class="btn btn-primary">Submit</button>
                <a role="button" class="btn btn-outline-primary" href="/library-app/">Cancel</a>
            </div>
        </div>          
    </form>`);
}

function resetFields(jQueryFormSelector){
    jQueryFormSelector.val('');
}

function APIError(){
    $("#feedback").html('<p class="text-danger">Something went wrong, the page might not work as intended</p>');
}