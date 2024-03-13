//Muhammad Umair Shakoor, 456220, BSDS1-A, Assignment 1
//FILE NAME: Database.java

package org.example;

import javax.xml.crypto.Data;
import java.sql.*;
import java.util.ArrayList;

public class Database {
    // Establishes connection to the database
    public static Connection connect() {
        Connection con = null;
        try {
            // Register the JDBC driver
            Class.forName("org.sqlite.JDBC");

            // Database URL
            String url = "jdbc:sqlite:lbms1.db";

            // Create a connection to the database
            con = DriverManager.getConnection("jdbc:sqlite:lbms1.db");
            System.out.println("Connected to the database");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            // You may want to handle the exception more gracefully
        }
        return con;
    }

    // Retrieves all books from the database
    public static ArrayList<Book> getAllBooks(){
        ArrayList<Book> books=new ArrayList<>();
        Connection con=null;
        PreparedStatement ps=null;
        ResultSet rs=null;

        try{
            con= Database.connect();
            String sql="SELECT * from books";
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery();
            String title="";
            String author="";
            String genre="";
            String status="";
            int id=0;

            while(rs.next()){
                // Retrieving book details
                title=rs.getString("title");
                author=rs.getString("author");
                genre=rs.getString("genre");
                status=rs.getString("availability");
                id=rs.getInt("id");
                books.add(new Book(title, author,genre,status,id));
            }

        }catch (SQLException e){
            System.out.println(e+"");
        }
        return  books;
    }

    // Retrieves all users from the database
    public static ArrayList<User> getAllUsers(){
        ArrayList<User> users=new ArrayList<>();
        Connection con=null;
        PreparedStatement ps=null;
        ResultSet rs=null;

        try{
            con= Database.connect();
            String sql="SELECT * from users";
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery();
            String name="";
            String borrowedBooks="";
            int contactInfo=0;
            int id=0;

            while(rs.next()){
                // Retrieving user details
                name=rs.getString("name");
                borrowedBooks=rs.getString("borrowedBooks");
                contactInfo=rs.getInt("contactInfo");
                id=rs.getInt("id");
                users.add(new User(name,borrowedBooks,contactInfo,id));
            }

        }catch (SQLException e){
            System.out.println(e+"");
        }
        return  users;
    }

    // Dump all data to the database
    public static boolean dumpAll(ArrayList<Book> books, ArrayList<User> users){
        Connection con=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        try{
            // Deleting existing data from tables
            con=Database.connect();
            String sql="delete from users";
            ps=con.prepareStatement(sql);
            ps.executeUpdate();

            // Closing resources
            con.close();
            ps.close();

            // Deleting existing data from tables
            con=Database.connect();
            sql="delete from books";
            ps=con.prepareStatement(sql);
            ps.executeUpdate();

            // Closing resources
            con.close();
            ps.close();

            // Inserting books into the database
            con= Database.connect();
            sql="insert into books(title,author,genre,availability) values(?,?,?,?)";
            ps=con.prepareStatement(sql);
            for(Book book:books){
                ps.setString(1,book.getTitle());
                ps.setString(2,book.getGenre());
                ps.setString(3,book.getAuthor());
                ps.setString(4,book.getStatus());
                ps.execute();
            }

            // Closing resources
            con.close();
            ps.close();

            // Inserting users into the database
            con=Database.connect();
            sql="insert into users(name,contactInfo,borrowedBooks) values(?,?,?)";
            ps=con.prepareStatement(sql);
            for(User user:users){
                ps.setString(1,user.getName());
                ps.setInt(2,user.getContactInfo());
                ps.setString(3,user.getBorrowedBooks());
                ps.execute();
            }

            // Closing resources
            con.close();
            ps.close();

            return true;
        }catch (SQLException e){
            System.out.println(e+"");
            return false;
        }
    }
}
