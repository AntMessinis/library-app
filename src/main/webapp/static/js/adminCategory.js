$(document).ready(function(){
    $('#adminAddCategory').on('click',function(){
        clearFeedback();
        showAddCategoryForm();
    });

    $('#adminShowCategoryList').on('click', function(){
        clearFeedback();
        getCategories();
    })
});

function showAddCategoryForm(){
    $('#mainContainer').html(`
        <h3>Add a new Category</h3>
        <form id="categoryForm">
            <div class="row">
                <div class="col-4">
                    <label for="categoryName" class="form-label mt-3">Category</label>
                    <input id="categoryName" class="form-control me-sm-2" type="text">
                </div>
                <div class="col-4">
                    <label for="basicCategorySelect" class="form-label mt-3">Basic Category</label>
                    <select class="form-select" id="basicCategorySelect">
                        <option value="1">Fiction</option>
                        <option value="2">Other</option>
                    </select>
                </div>
            </div>
            <div id="categoryFormButtons" class="form-group row mt-3">
                <div class="col-md-3">
                    <button type="submit" id="categoryFormSubmitButton" class="btn btn-primary">Submit</button>
                    <a role="button" class="btn btn-outline-primary" href="/library-app/">Cancel</a>
                </div>
            </div>
        </form>
    `);
    $("#categoryForm").on('submit', function(e){
        e.preventDefault();
    });
    
    $('#categoryFormSubmitButton').on('click', function(){
        addCategory();
    });
}

function addCategory(){
    let category = $('#categoryName').val().trim();
    let basicCategory = $('#basicCategorySelect :selected').text()

    let xhr = new XMLHttpRequest();
    xhr.open('POST', 'add-category', true);
    xhr.timeout = 10000;
    xhr.ontimeout = APIError();
    xhr.onreadystatechange = function(){
        if(xhr.readyState === 4){
            if(xhr.status === 200){
                console.log('Status OK');
            }else {
                console.log('Status not OK')
            }
        }
    }
    let data = JSON.stringify({
        'categoryName': basicCategory,
        'subcategoryName': category
    });

    xhr.send(data);
}

function getCategories(){
    let xhr = new XMLHttpRequest();
    xhr.open('GET', `/library-app/categories`,true)
    xhr.timeout = 10000;
    xhr.ontimeout = (e) => APIError();

    xhr.onreadystatechange = function(){
        if(xhr.readyState === 4){
            if(xhr.status === 200){
                showCategoryList(JSON.parse(xhr.responseText));
            }else{
                APIError();
            }
        }
    }

    xhr.send();
}

function showCategoryList(categories){
    if($.isEmptyObject(categories)){
        $('#feedback').html('Something went wrong. Please try again.');
    }else {
        let output = `
        <table class="table table-hover">
            <thead>
            <tr>
            <th scope="col">ID</th>
            <th scope="col">Category</th>
            <th scope="col">Base Category</th>
            </tr>
        </thead>
        <tbody>
    `;
        for (let category of categories){
            output += `<tr id="tableRow${category.id}" class="table-light">
            <th scope="row">${category.id}</th>
            <td class="category-name">${category.subcategoryName}</td>
            <td class="base-category-name">${category.categoryName}</td>
            <td><a id="categoryBooks${category.id}" class="btn btn-primary">Show Books</a></td>
            <td><a id="categoryDelete${category.id}" class="btn btn-danger">Delete</a></td>
          </tr>`;
        }
        output += '</tbody></table>';
        $('#mainContainer').html(output);

        for (let category of categories){
            $(`#categoryDelete${category.id}`).on('click', function(){
                $(`#tableRow${category.id}`).remove();
                deleteCategory(category.id, category.subcategoryName, category.categoryName);
                });

            $(`#categoryBooks${category.id}`).on('click', function(){
                getBooksFromDB(category.subcategoryName)
            });
        }
    }
}

function getBooksFromDB(category){
    let xhr = new XMLHttpRequest()
    xhr.open('GET', `/library-app/searchByCategory?category=${category}`, true)

    xhr.timeout = 10000;
    xhr.ontimeout = (e) => APIError();


    xhr.onreadystatechange = function(){
        if(xhr.readyState === 4){
            if(xhr.status === 200){
                showBookList(JSON.parse(xhr.responseText), category);
            } else{
                APIError();
            }
        }
    }
    xhr.send();
}

function showBookList(response, category){
    let books = response;
    if($.isEmptyObject(response)){
        $("#searchResults").html('<p class="text-danger">No books was found</p>');
    } else {
        let output = `<div class="row mb-5 align-items-center text-center">`;
        for (let book of books){
            output += `<div class="col-4 h-75 d-inline-block mt-5 align-items-center ">
            <h4>${book.title}</h4>` 
            +'<img src="/library-app/static/imgs/tumbnail.jpg" alt="bookPic">'
            + ((book.copiesInLibrary > 0) ? '<p>Availiable for borrowing</p><div class="mt-3"><button class="btn btn-primary">Details</button></div></div>' : '<p>Not Availiable for borrowing</p><div class="mt-3"><button class="btn btn-primary" disabled>Details</button></div></div>');
        }
        output += "</div>";
        $("#mainContainer").html(`<h3>All books for category ${category}</h3>`);
        $("#mainContainer").append(output);
    }
}