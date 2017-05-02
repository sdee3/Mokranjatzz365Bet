package rs.stefandjokic.mokranjatzz365bet.activities.models;

public class User {

    private int id;
    private String username;
    private String email;
    private String password;
    private String full_name;

    public User(String name, String email, String password, String gender) {
        this.username = name;
        this.email = email;
        this.password = password;
        this.full_name = gender;
    }

    public User(int id, String name, String email, String gender){
        this.id = id;
        this.username = name;
        this.email = email;
        this.full_name = gender;
    }

    public User(int id, String name, String email, String password, String gender) {
        this.id = id;
        this.username = name;
        this.email = email;
        this.password = password;
        this.full_name = gender;
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

}
