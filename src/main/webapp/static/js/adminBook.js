$(document).ready(function(){
    $('#adminAddBook').on('click', function(){
        showAddBookForm();
        getCategoriesFromDB();
        getAuthorsFromDB();
        
    });

    $('#adminUpdateBook').on('click', function(){
        showUpdateBookForm();
    });

    $('#adminAddBook').on('click', function(){
        showDeleteBookForm();
    });
});

function showAddBookForm(){
    $('#mainContainer').html(`<form>
        <label for="addBookTitle" class="form-label">Title</label>
        <input id="addBookTitle" class="form-control me-sm-2" type="text" placeholder="Book Title">
        <label for="addBookIsbn" class="form-label">ISBN</label>
        <input id="addBookIsbn" class="form-control me-sm-2" type="text" placeholder="Book Title">
        <label for="categorySelect" class="form-label">Category</label>
        <select class="form-select" id="categorySelect" placeholder="Choose Category">
        </select>
        <label for="authorSelect" class="form-label">Author</label>
        <select class="form-select" id="authorSelect" placeholder="Choose Author">        
        </select>     
        <label for="addCopiesInLibrary" class="form-label">Copies in Library</label>
        <input id="addCopiesInLibrary" class="form-control me-sm-2" type="number">
        <div class="form-group row mt-3">
            <div class="col-md-3">
                <button type="submit" id="registrationButton" class="btn btn-primary">Submit</button>
                <a role="button" class="btn btn-outline-primary" href="/library-app/">Cancel</a>
            </div>
        </div>          
    </form>`)
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

function getAuthorList(authors){
    if($.isEmptyObject(authors)){
        $('#feedback').html('<p>There are no authors in the database.</p>')
    } else {
        let output = "";
        for(let author of authors){
            if(author.lastname){
                output +=  `<option value="${author.lastname}, ${author.firstname}">${author.lastname}, ${author.firstname}</option>`
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
            output +=  `<option value="${category.subcategoryName}">${category.subcategoryName}</option>`
        }
        $('#authorSelect').append(output);
    }
}