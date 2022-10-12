$(document).ready(function(){
    $('#adminAddCategory').on('click',function(){
        clearFeedback();
        showAddCategoryForm();
    });

    $('#adminShowCategoryList').on('click', function(){
        clearFeedback();
        showCategoryList();
    })
});

function showAddCategoryForm(){
    $('#mainContainer').html(`
        <h3>Add new Author</h3>
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