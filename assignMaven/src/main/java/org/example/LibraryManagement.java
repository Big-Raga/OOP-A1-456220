//Muhammad Umair Shakoor, 456220, BSDS1-A, Assignment 1
//FILE NAME: LibraryManagement.java

package org.example;

import java.util.InputMismatchException;
import java.util.Scanner;


public class LibraryManagement {

    public static void main(String[] args) {

        //creating a variable to store users input
        int choice;

        //creating an instance to get the input
        Scanner inp = new Scanner(System.in);

        //creating an instance of the library
        Library lib1 = new Library();

        //getting library data from databases
        lib1.book_list=Database.getAllBooks();
        lib1.user_list=Database.getAllUsers();

        //program loop
        do {
            //displaying menu
            System.out.println("\n\n===Library Management System===");
            System.out.println("1. Add Book");
            System.out.println("2. Add User");
            System.out.println("3. Search Book");
            System.out.println("4. Display available Books");
            System.out.println("5. Borrow Book");
            System.out.println("6. Return Book");
            System.out.println("7. Search Books borrowed by a User");
            System.out.println("8. Display all Books");
            System.out.println("9. Display all Users");
            System.out.println("10. Exit");
            System.out.println("Enter your choice (1-10): ");

            //storing the user choice
            choice = intGetter(inp);

            //processing the user input
            switch (choice) {
                case 1:
                    inp.nextLine(); // Clear the buffer

                    //asking the user to enter the details of the book
                    System.out.println("\nPlease enter the title of the book:");
                    String b_title = inp.nextLine();

                    System.out.println("Please enter the name of the Author:");
                    String b_auth = inp.nextLine();

                    System.out.println("Please enter the genre of the book:");
                    String b_genre = inp.nextLine();

                    //calling the method to add the book
                    lib1.addBook(b_title, b_auth, b_genre);

                    //displaying success message
                    System.out.println("\nBook added successfully!\n");

                    break;
                case 2:
                    inp.nextLine(); // Clear the buffer

                    //asking the librarian to enter details of the new user
                    System.out.println("Please enter the name of the new user:");
                    String u_name = inp.nextLine();

                    //getting the users number
                    System.out.println("Please enter the user's contact number(8 digits):");
                    int u_num = getContactinfo(inp);

                    //calling the method to add the user
                    lib1.addUser(u_name, u_num);

                    //displaying success message
                    System.out.println("\nUser added successfully!\n");

                    break;
                case 3:
                    inp.nextLine(); // Clear the buffer

                    //asking the user to enter the details of the book
                    System.out.println("\nPlease enter the details of the book you want to search for:\n");

                    System.out.println("Title (Enter N if N\\A):");
                    String search_name = inp.nextLine();

                    System.out.println("Author(enter N if N\\A):");
                    String search_author = inp.nextLine();

                    //calling the method to print the relevant books
                    if (!lib1.searchBook(search_author, search_name)) {

                        //if no match is found
                        System.out.println("Sorry, no match found.\n");
                    }

                    break;
                case 4:
                    inp.nextLine(); // Clear the buffer

                    System.out.println("\nAll available books:\n");
                    //calling method to display all available books
                    lib1.availableBooks();

                    break;
                case 5:
                    inp.nextLine(); // Clear the buffer

                    //calling the method which checks out a book to a user
                    borrowingABook(inp, lib1);

                    break;
                case 6:
                    inp.nextLine(); // Clear the buffer

                    //calling the method which checks out a book to a user
                    returningABook(inp, lib1);

                    break;
                case 7:
                    //variable to store the user's input
                    int temp_uid;

                    //asking the librarian to enter user's ID
                    System.out.println("Please enter the user ID:");

                    //while loop runs until the user enters a valid user id
                    while (true){

                        temp_uid = intGetter(inp);

                        //for negative entries
                        if (temp_uid > 0){
                            break;
                        }
                        else {
                            //error message
                            System.out.println("Invalid input, Please enter a valid ID(numbers greater than 0):");
                        }
                    }

                    //checking if the user exists or not
                    if (lib1.validUser(temp_uid)){

                        //calling the method to display all books borrowed by a user
                        lib1.userBooks(temp_uid);
                    }
                    else{
                        System.out.println("No such user exists.");
                    }

                    break;
                case 8:
                    //method to display all books
                    lib1.displayAllbooks();

                    break;
                case 9:
                    //method to display all books
                    lib1.displayAllUsers();

                    break;
                case 10:
                    // attempt to save all library data
                    boolean flag=Database.dumpAll(lib1.book_list,lib1.user_list);

                    //check if data was successfully saved
                    if (flag) {
                        System.out.println("Data Saved");
                    }
                    else {
                        System.out.println("Error Occurred: Data Could not be saved in the DataBase. Congrats your data is lost");
                    }

                    System.out.println("\nExiting...");

                    break;
                default:
                    // clear the invalid input
                    inp.nextLine();

                    //display error message
                    System.out.println("\nInvalid choice. Please enter an option from the menu:");
            }

        } while (choice != 10);

        //closing the scanner
        inp.close();
    }



    // method to get an integer input
    private static int intGetter(Scanner scanner) {

        // variable to store the user input
        int userInput;

        // loop runs until an integer has been entered.
        while (true) {
            try {

                // getting user input
                userInput = scanner.nextInt();

                // Break out of the loop if the input is a valid integer
                break;

            } catch (InputMismatchException e ) {
                // catch the InputMismatchException if the input is not an integer
                System.out.println("\nInvalid input. Please enter a valid integer:");

                // clear the invalid input
                scanner.next();
            }
        }

        // return the valid integer input
        return userInput;
    }


    //method to get contact info
    public static int getContactinfo(Scanner scan){

        int num;

        //loop runs until we get a valid input
        while (true) {

            try {
                //getting user input
                num = scan.nextInt();

                //checking if the number is valid or not
                if (num >= 30000000 && num <= 39999999 ) {

                    break;
                }
                else {

                    //error message
                    System.out.println("Please enter a 8 digit number starting with 3:");
                }

            } catch (InputMismatchException e ) {
                // clear the invalid input
                scan.next();

                // catch the InputMismatchException if the input is not an integer
                System.out.println("\nInvalid input. Please enter a 8 digit number starting with 3:");
            }
        }

        //returning the validated input
        return num;
    }


    //method to borrow a book
    public static void borrowingABook(Scanner inp, Library L1){

        //variables to store the users input
        int temp_uid, temp_bid;

        //asking the librarian to enter users ID
        System.out.println("Please enter the user ID:");

        //while loop runs until the user enters a valid user id
        while (true){

            temp_uid = intGetter(inp);

            //for negative entries
            if (temp_uid > 0){
                break;
            }
            else {
                //error message
                System.out.println("Invalid input, Please enter a valid ID(numbers greater than 0):");
            }
        }

        //checking if the user exists or not
        if (L1.validUser(temp_uid)){
            //asking the librarian to enter book ID
            System.out.println("Please enter the book ID:");

            //while loop runs until the user enters a valid book id
            while (true){

                temp_bid = intGetter(inp);

                //for negative entries
                if (temp_bid > 0){
                    break;
                }
                else {
                    //error message
                    System.out.println("Invalid input, Please enter a valid ID(numbers greater than 0):");
                }
            }

            //checking if the book exists
            if (L1.validBook(temp_bid)){

                //calling method to check out the book on the users id
                L1.borrowBook(temp_uid, temp_bid);

            }
            else{
                System.out.println("No such book exists.");
            }
        }
        else{
            System.out.println("No such user exists.");
        }
    }


    //method to return a book
    public static void returningABook(Scanner inp, Library L1){

        //variables to store the users input
        int temp_uid, temp_bid;

        //asking the librarian to enter users ID
        System.out.println("Please enter the user ID:");

        //while loop runs until the user enters a valid user id
        while (true){

            temp_uid = intGetter(inp);

            //for negative entries
            if (temp_uid > 0){
                break;
            }
            else {
                //error message
                System.out.println("Invalid input, Please enter a valid ID(numbers greater than 0):");
            }
        }

        //checking if the user exists or not
        if (L1.validUser(temp_uid)){
            //asking the librarian to enter book ID
            System.out.println("Please enter the book ID:");

            //while loop runs until the user enters a valid book id
            while (true){

                temp_bid = intGetter(inp);

                //for negative entries
                if (temp_bid > 0){
                    break;
                }
                else {
                    //error message
                    System.out.println("Invalid input, Please enter a valid ID(numbers greater than 0):");
                }
            }

            //checking if the book exists
            if (L1.validBook(temp_bid)){

                //calling method to check out the book on the users id
                L1.returnBook(temp_uid, temp_bid);

            }
            else{
                System.out.println("No such book exists.");
            }
        }
        else{
            System.out.println("No such user exists.");
        }
    }

}



