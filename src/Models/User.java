package Models;

public class User extends Library{
    public User(){
    }

    public User(int id, String name, String surname, String username, String password){
        super(id, name, surname, username, password);
    }

    @Override
    public int getId() {
        return super.getId();
    }

    @Override
    public String getName() {
        return super.getName();
    }

    @Override
    public String getPassword() {
        return super.getPassword();
    }

    @Override
    public String getSurname() {
        return super.getSurname();
    }

    @Override
    public String getUsername() {
        return super.getUsername();
    }

    @Override
    public void setId(int id) {
        super.setId(id);
    }

    @Override
    public void setName(String name) {
        super.setName(name);
    }

    @Override
    public void setPassword(String password) {
        super.setPassword(password);
    }

    @Override
    public void setSurname(String surname) {
        super.setSurname(surname);
    }

    @Override
    public void setUsername(String username) {
        super.setUsername(username);
    }


}
