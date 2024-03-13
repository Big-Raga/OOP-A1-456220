//Muhammad Umair Shakoor, 456220, BSDS1-A, Assignment 1
//FILE NAME: Library.java

package org.example;

import java.util.ArrayList;

public class Library {

    //array list to store the book objects.
    public ArrayList <Book> book_list = new ArrayList<>();

    //array list to store the user objects.
    ArrayList <User> user_list = new ArrayList<>();


    //creating a method to add books
    public void addBook(String title, String author, String genre) {

        //variable to store the ID number
        int ID = this.book_list.size() + 1;

        //create an object of book
        Book newbook=new Book(title,author,genre,"",ID);

        //adding the new book to the books array.
        this.book_list.add(newbook);
    }


    //creating a method to add a new user
    public void addUser(String name, int number){

        //variable to store the ID number
        int ID = this.user_list.size() + 1;

        //creating a new user.
        User newUser = new User(name,"",number,ID);

        //adding the user to the users array
        this.user_list.add(newUser);
    }


    //creating a method to update data after a book is borrowed
    public void borrowBook(int U_ID, int B_ID){

        // Adjusting indices for 0-based indexing
        int b_index = B_ID - 1;
        int u_index = U_ID - 1;

        //checking if the book is available
        if (this.book_list.get(b_index).getStatus().equals("")) {

            // Adding the book ID to the list of borrowed books for the user
            this.user_list.get(u_index).setBorrowedBooks(this.user_list.get(u_index).getBorrowedBooks()+" "+B_ID);

            // Updating the status of the borrowed book
            this.book_list.get(b_index).setStatus(Integer.toString(U_ID));

            // Displaying a success message
            System.out.println("Successfully checked out " + this.book_list.get(b_index).getTitle() + "(ID: " + B_ID + ").");

        }

       else {

            // Displaying an error message if the book is not available
            System.out.println("Sorry,you cannot borrow " + this.book_list.get(b_index).getTitle() + "(ID: " + B_ID + ") as it has already been borrowed.");
        }
    }


    //creating a method to update data after a book is returned
    public void returnBook(int U_ID, int B_ID){

        //variable to monitor if the book has been returned or not
        boolean return_book = false;

        // Adjusting indices for 0-based indexing
        int b_index = B_ID - 1;
        int u_index = U_ID - 1;

        // splitting the string of borrowed books into individual IDs based on whitespaces
        String[] ids = this.user_list.get(u_index).getBorrowedBooks().split("\\s+");

        //for loop runs for each book borrowed
        for (int i = 0; i < ids.length; i++) {

            //checking if the book id has a match in the borrowed book array.
            if (this.user_list.get(u_index).getBorrowedBooks().contains(Integer.toString(B_ID))){
                //initialising and empty string
                String newBorrowedBooks = "";

                //iterating through each book ID stored in the ids array
                for(String id:ids){
                    // checking if the current ID is not the book ID to be returned
                    if(!id.equals(Integer.toString(B_ID)))
                        //adding the books not to be returned into a string
                        newBorrowedBooks = newBorrowedBooks + " " + id;
                }

                //remove the book from the borrowed book lists
                this.user_list.get(u_index).setBorrowedBooks(newBorrowedBooks);

                //update the return book variable to true
                return_book = true;

                break;
            }
        }

        if (return_book) {

            // Updating the status of the borrowed book
            this.book_list.get(b_index).setStatus("");

            // Displaying a success message
            System.out.println("Successfully returned " + this.book_list.get(b_index).getTitle() + "(ID: " + B_ID + ").");
        }

        else {
            //printing a message if no match has been found
            System.out.println("Sorry,you cannot return " + this.book_list.get(b_index).getTitle() + "(ID: " + B_ID + ") as it hasn't been borrowed by the specified user.");
        }
    }


    //creating a method to search for books by their title or author
    public boolean searchBook(String author, String title){

        //variable to track if a book has been found
        boolean match = false;

        //for loop iterates through each book
        for (int i = 0; i < this.book_list.size(); i++) {

            //checking if the title or author has a match
            if (this.book_list.get(i).getTitle().equals(title) || this.book_list.get(i).getAuthor().equals(author) ){

                //printing the details of the book along with the status
                System.out.printf("\n%s\t%d\n%s\t%s\n%s\t%s\n%s\t%s\n%s\t%s\n\n", "ID:",     this.book_list.get(i).getBookId(),
                                                                                  "Title:",  this.book_list.get(i).getTitle(),
                                                                                  "Author:", this.book_list.get(i).getAuthor(),
                                                                                  "Genre:",  this.book_list.get(i).getGenre(),
                                                                                  "Status:",(this.book_list.get(i).getStatus()).equals("")?"Available":"Not Available");//" " means that nobody has borrowed the book

                //set the variable to true
                match = true;
            }
        }

        //returning the variable
        return match;
    }


    //creating a method to check if the mentioned user exists
    public boolean validUser(int userID){

        //variable to track if a user has been found
        boolean match = false;

        //for loop iterates through each user
        for (int i = 0; i < this.user_list.size(); i++) {

            //checking if the user is registered
            if (this.user_list.get(i).getUserId() == userID){

                //setting the match to true
                match = true;

                //returning the variable
                return match;
            }
        }

        //returning the variable
        return match;
    }


    //creating a method to check if the mentioned book exists
    public boolean validBook(int bookID){

        //variable to track if a book has been found
        boolean match = false;

        //for loop iterates through each book
        for (int i = 0; i < this.book_list.size(); i++) {

            //checking if the book is in the system
            if (this.book_list.get(i).getBookId() == bookID){

                //setting the match to true
                match = true;

                return match;
            }
        }

        //returning the variable
        return match;
    }


    //creating a method to display books borrowed by a user
    public void userBooks(int userID){

        // Get the list of book IDs borrowed by the user
        String[] borrowedBooks = user_list.get(userID - 1).getBorrowedBooks().split("\\s+");

        //length of the new array after removing the first element
        int n = borrowedBooks.length-1;

        //array to hold the borrowed books excluding the first element
        String[] newArray = new String[n];

        //copy elements from borrowedBooks starting from index 1 to newArray
        System.arraycopy(borrowedBooks,1, newArray,0, n);

        // Check if the user has borrowed any books
        if (!(newArray.length==0)) {

            System.out.println("\nThe books borrowed by the mentioned user:");

            // Iterate through each book ID
            for (String bookId : newArray) {

                // Find the book in the book list using its ID
                for (Book book : book_list) {

                    // Compare book ID with borrowed book ID
                    if (book.getBookId() == Integer.parseInt(bookId)) {

                        // Print the details of the book
                        System.out.println("ID: " + book.getBookId());
                        System.out.println("Title: " + book.getTitle());
                        System.out.println("Author: " + book.getAuthor());
                        System.out.println("Genre: " + book.getGenre());
                        System.out.println();

                        break;
                    }
                }
            }
        }
        else {
            // The user hasn't borrowed any books
            System.out.println("The specified user hasn't borrowed any books.");
        }
    }


    //method to display all books
    public void displayAllbooks(){
        //iterates through each book
        for(Book book:book_list){
            //printing book details
            System.out.printf("\n%s\t%d\n%s\t%s\n%s\t%s\n%s\t%s\n%s\t%s\n\n", "ID:",     book.getBookId(),
                    "Title:",  book.getTitle(),
                    "Author:", book.getAuthor(),
                    "Genre:",  book.getGenre(),
                    "Status:",(book.getStatus()).equals("")?"Available":"Not Available");

        }
    }


    //method to display available books
    public void availableBooks(){
        //iterates through each book
        for(Book book:book_list){
            //checks if book is available
            if (book.getStatus().equals("")) {
                System.out.printf("\n%s\t%d\n%s\t%s\n%s\t%s\n%s\t%s\n\n",         "ID:", book.getBookId(),
                                                                                  "Title:", book.getTitle(),
                                                                                  "Author:", book.getAuthor(),
                                                                                  "Genre:", book.getGenre());
            }
        }
    }


    //method to display all the users
    public void displayAllUsers(){
        //iterates through each user
        for(User user: user_list){
            //printing the details of each user
            System.out.printf("\n%s\t%d\n%s\t%s\n%s\t%d\n%s\t%s\n\n", "ID:", user.getUserId(),
                    "Name:",  user.getName(),
                    "Contact Info:", user.getContactInfo(),
                    "Borrowed Books:",(user.getBorrowedBooks()));

        }
    }

}















