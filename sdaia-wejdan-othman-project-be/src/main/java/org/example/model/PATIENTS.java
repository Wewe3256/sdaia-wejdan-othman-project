package org.example.model;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PATIENTS {
    private int id;
    private String name;
    private String email;
    private String password;
    private String phone;
    private String birth_date;

    public PATIENTS() {
    }

    public PATIENTS(int id, String name, String email, String password, String phone, String birth_date) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.birth_date = birth_date;
    }

    public PATIENTS (ResultSet rs) throws SQLException {
        id =rs.getInt("id");
        name =rs.getString("name");
        email =rs.getString("email");
        password =rs.getString("password");
        phone =rs.getString("phone");
        birth_date = rs.getString("birth_date");
    }

    //public PATIENTS(int id, String name, String email, String phone, Date birthDate) {
   // }

   // public PATIENTS(String email, String password) {
   // }


    public int getId() {
        return id;
    }

    public String getName() {
        return name;
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

    public String getBirth_date() {
        return birth_date;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
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

    public void setBirth_date(String birth_date) {
        this.birth_date = birth_date;
    }

    @Override
    public String toString() {
        return "PATIENTS{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", phone='" + phone + '\'' +
                ", birth_date=" + birth_date +
                '}';
    }
}
