# library-app (This project is still Under Development)
This project is made as the final project for AUEB's Coding Factory Java course. 
I'm building it with JavaEE - Maven - JDBC - JSP - JavaScript - JQuery - Jackson Databind - Bootstrap with Lux theme - MySQL - Service Oriented Architecture.

Library App is a web application where you can register, log in as a member or an Admin, browse the book collection and if a book is availible, the user can request to borrow it. 

The goal for this project is to function mostly as a Single Page Application. Most of the functions implemented work by sending or requesting data as json to/from rest servlets.  

I'm also using JavaScript with jQuery to prossess the data but I'm planning to remake the whole project in .NET with Blazor, WEBApi and Entity Framework.

This is the Home page with no logged user.
![1](https://user-images.githubusercontent.com/84563107/196004652-a1551ca4-50b1-4e0a-8804-633f0a1abc44.png)

This is the Home page with a logged user who is a simple member and not an Admin. This type of user can only search for books and borrow if availiable
![2](https://user-images.githubusercontent.com/84563107/196004701-0d734e68-54d2-488f-827b-1c21fee594c6.png)


If an Admin logs in there is an extra panel with various CRUD fanctionalities.
![3](https://user-images.githubusercontent.com/84563107/196004765-48935a90-fba3-44f6-a2bb-639402607fe2.png)

You can search for a book (or for a collection of books) using title, isbn, author's name or bokks's category

![4](https://user-images.githubusercontent.com/84563107/196004839-4793fbd0-cdf9-4cb1-afd1-c9823e0a08fe.png)
![5](https://user-images.githubusercontent.com/84563107/196004843-0f460d73-2c5b-4a79-a7d9-01128df21850.png)
