package app.dao;

import app.dao.util.ConnectionManager;
import app.entities.Book;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
public class BookDao {


    public List<Book> getAll(Connection con) {
        List<Book> books = new ArrayList<>();
        Statement stmt;
        ResultSet rs = null;
        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT * FROM my_shop.books");
            while (rs.next()) {
                books.add(extract(rs));
            }
            return books;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } finally {
            ConnectionManager.close(rs);
        }
    }
    private Book extract(ResultSet rs) {
        Book book = new Book();
        try {
            book.setId(rs.getInt("id"));
            book.setName(rs.getString("name"));
            book.setPrice(rs.getInt("price"));
        } catch (SQLException ex) {
           throw new RuntimeException(ex);
        }
        return book;
    }}

/*
    private static String url = "jdbc:postgresql://127.0.0.1:5432/vertex";
    private static String username = "postgres";
    private static String password = "4321";

    private static ArrayList<Book> getAll(){

        ArrayList<Book> books = new ArrayList<>();

        try{
            Class.forName("org.postgresql.Driver").getDeclaredConstructor().newInstance();
            try( Connection connection = DriverManager.getConnection(url, username, password)){

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM products");

            while (resultSet.next()){
                Integer id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                String author = resultSet.getString(3);
                Integer price = resultSet.getInt(4);
                String category = resultSet.getString(5);

                Book book = new Book(id, name, price);
                books.add(book);
            }

            }
        } catch (SQLException | ClassNotFoundException | NoSuchMethodException | InvocationTargetException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return books;*/






