# OOP A1(456220)
 LibraryManagemetSystem

## Description
This project is a simple Library Management System implemented in Java. It allows users to add books, add users, search for books, display available books, borrow and return books, search books borrowed by a user, and display all books and users. The system stores data in an SQLite database.

## Instructions

### Running the Project Locally
To run this project locally, you need to have the following software installed:

- Java Development Kit (JDK)
- SQLite Database Browser (optional for database management)

### Prerequisites
Make sure you have the following software installed before running the project:
- Java Development Kit (JDK) (includign the api and simple)
- SQLite Database Browser 

### Setting Up
1. Clone this repository to your local machine.
2. Open the project in your preferred Java IDE.
3. Make sure you have the JDBC driver for SQLite added to your project's dependencies.
4. Ensure you have the SQLite database file (`lbms1.db`) in the project directory.

### Running the Project
1. Compile and run the `LibraryManagement.java` file.
2. Follow the on-screen instructions to interact with the Library Management System.
3. To exit the program, choose option 10 from the menu.

## Documentation

### Features and Functionalities
- **Add Book**: Allows adding a new book to the library.
- **Add User**: Allows adding a new user to the library.
- **Search Book**: Allows searching for books by title and author.
- **Display Available Books**: Displays all available books in the library.
- **Borrow Book**: Allows users to borrow a book.
- **Return Book**: Allows users to return a borrowed book.
- **Search Books Borrowed by a User**: Displays all books borrowed by a specific user.
- **Display All Books**: Displays details of all books in the library.
- **Display All Users**: Displays details of all users in the library.
- **Data Saving**: Option to save all library data to the database.

### Database Management
The `Database.java` class provides methods for database management, including:
- `connect()`: Establishes a connection to the SQLite database.
- `getAllBooks()`: Retrieves all books from the database.
- `getAllUsers()`: Retrieves all users from the database.
- `dumpAll(ArrayList<Book> books, ArrayList<User> users)`: Dumps all data (books and users) back into the database.

### Exiting the Program
To exit the program, choose option 10 from the menu, dont turn off the program in any other way as the data wont be saved

