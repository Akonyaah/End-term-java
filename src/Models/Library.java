package Models;

public class Library {
    private int id;
    private String name;
    private String surname;
    private String username;
    private String password;

    public Library(){}

    public Library(int id, String name, String surname, String username, String password){
        this.id=id;
        this.name=name;
        this.password=password;
        this.surname=surname;
        this.username=username;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getPassword() {
        return password;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}
