# Dispromed Clientes

## Demo
To see the demo you can go to https://disclitest.herokuapp.com/ and log with the account type you can to try
- user: jefe password: testdispro  to access with a boss type account
- user: vendedor password: testdispro to access with a seller type account

## Tools
This web app was built using Spring Boot with:
- Spring Security to build the accounts system
- Data JPA to access the database
- Postgres SQL to store the data
- Thymeleaf to implement functionality in the front end
- Bootstrap to apply css to the pages

## The problem
The problem I try to solve with this project is from a company where they have a group of sellers which have different clients
assigned which they have to visit regularly to show new products, to reach new people or to acomplish goals about their
relationship with the client.

To do that they need something to keep track o what every seller does with every client to know which subjects they already covered,
who talked with who about what, what responses they got about different topics...

To solve the problem I wanted to make a simple list of the clients with the possibility to add a log of comments where every
seller can go and say what happened or did with that client.

## Pages and Details
**Accounts:** We have 2 types of accounts or roles, JEFE(boss) and VENDEDOR(seller), i'll describe what they can do in each
page description

**Login page:** this is a custom login page to handle the accounts, to access every page you need to be logged.
The form show an error message when you try to access with invalid data and other message when you log out.

**Clients page:** This page have the list of clients with the option to add a new client only visible and accesible to a boss account,
each client have a button to access to it's detail page.

**Details page:** Here each client have a list of it's own comments, every one can add new comments, which will be saved with a timestamp
and the name of the user adding the comment. Only a boss can delete a comment.

The messages are shown in order, newer on top.

**New client:** this page can only be accessed with a boss account and is used to add a new client to the database, here you
have to name the client a set a reference number, the idea of this number is to use it to match with the already existing data base
used in other program for the company.

**Access denied:** this page is shown when someone try to access a page without the role needed (to create a client as a seller for example)
