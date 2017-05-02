package rs.stefandjokic.mokranjatzz365bet.models;

public class User {

    private int id;
    private String username;
    private String email;
    private String password;
    private String full_name;

    public User(String name, String email, String password, String full_name) {
        this.username = name;
        this.email = email;
        this.password = password;
        this.full_name = full_name;
    }

    public User(int id, String username, String email, String full_name){
        this.id = id;
        this.username = username;
        this.email = email;
        this.full_name = full_name;
    }

    public User(int id, String username, String email, String password, String full_name) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.full_name = full_name;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword(){
        return password;
    }

    public String getFullname() {
        return full_name;
    }

    public String toString(){

        return id + " " + username + " " + email + " " + password + " " + full_name + "\n";

    }

}
