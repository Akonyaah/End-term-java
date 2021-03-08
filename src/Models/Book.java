package Models;

public class Book {
    //to save data from database we have some models such as Book, Library and User
    private int isbn;
    private String name;
    private String author;
    private int count;

    public Book(int isbn, String name, String author, int count){
        this.isbn=isbn;
        this.name=name;
        this.author=author;
        this.count=count;
    }

    public Book(){

    }
    //Getter and Setters for Book class

    public String getName() {
        return name;
    }

    public int getCount() {
        return count;
    }

    public int getIsbn() {
        return isbn;
    }

    public String getAuthor() {
        return author;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void setIsbn(int isbn) {
        this.isbn = isbn;
    }

}
