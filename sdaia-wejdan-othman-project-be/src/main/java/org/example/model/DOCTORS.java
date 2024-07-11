package org.example.model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DOCTORS {
    private int id;
    private String name;
    private String specialty;
    private String email;
    private String password;
    private String phone;


    public DOCTORS() {
    }

    public DOCTORS(int id, String name, String specialty, String email, String password, String phone) {
        this.id = id;
        this.name = name;
        this.specialty = specialty;
        this.email = email;
        this.password = password;
        this.phone = phone;
    }

    public DOCTORS (ResultSet rs) throws SQLException{
        id =rs.getInt("id");
        name =rs.getString("name");
        specialty =rs.getString("specialty");
        email =rs.getString("email");
        password =rs.getString("password");
        phone =rs.getString("phone");
    }

    public DOCTORS(String email, String password) {
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSpecialty() {
        return specialty;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getPhone() {
        return phone;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "DOCTORS{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", specialty='" + specialty + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
