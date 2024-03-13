//Muhammad Umair Shakoor, 456220, BSDS1-A, Assignment 1
//FILE NAME: Book.java

package org.example;

//creating a class for books.
public class Book {
    private Integer book_id;
    private String title;
    private String author;
    private String genre;
    private String status; // presetting the availability to true.


    //constructor
    public Book(String _title, String _author, String _genre, String _status,int id){
        //setting attributes
        title = _title;
        author = _author;
        genre = _genre;
        status = _status;
        book_id=id;
    }


    // getter for book_id
    public Integer getBookId() {
        return book_id;
    }

    // setter for book_id
    public void setBookId(Integer book_id) {
        this.book_id = book_id;
    }

    // getter for title
    public String getTitle() {
        return title;
    }

    // setter for title
    public void setTitle(String title) {
        this.title = title;
    }

    // getter for author
    public String getAuthor() {
        return author;
    }

    // setter for author
    public void setAuthor(String author) {
        this.author = author;
    }

    // getter for genre
    public String getGenre() {
        return genre;
    }

    // setter for genre
    public void setGenre(String genre) {
        this.genre = genre;
    }

    // getter for status
    public String getStatus() {
        return status;
    }

    // setter for status
    public void setStatus(String status) {
        this.status = status;
    }
}