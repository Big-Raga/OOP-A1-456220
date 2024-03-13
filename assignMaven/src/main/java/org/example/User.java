//Muhammad Umair Shakoor, 456220, BSDS1-A, Assignment 1
//FILE NAME: User.java

package org.example;

import java.util.ArrayList;

public class User {
    private int user_id;
    private String name;
    private int contact_info;
    private String BorrowedBooks;

    //constructor
    public User(String _name, String _BorrowedBooks, int _contact_info, int id){
        //setting attributes
        name=_name;
        BorrowedBooks=_BorrowedBooks;
        contact_info=_contact_info;
        user_id=id;
    }


    // Getter for user_id
    public int getUserId() {
        return user_id;
    }

    // Setter for user_id
    public void setUserId(int user_id) {
        this.user_id = user_id;
    }

    // Getter for name
    public String getName() {
        return name;
    }

    // Setter for name
    public void setName(String name) {
        this.name = name;
    }

    // Getter for contact_info
    public int getContactInfo() {
        return contact_info;
    }

    // Setter for contact_info
    public void setContactInfo(int contact_info) {
        this.contact_info = contact_info;
    }

    // Getter for BorrowedBooks
    public String getBorrowedBooks() {
        return BorrowedBooks;
    }

    // Setter for BorrowedBooks
    public void setBorrowedBooks(String borrowedBooks) {
        BorrowedBooks = borrowedBooks;
    }
}



