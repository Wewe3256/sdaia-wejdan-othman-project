package org.example.DTO;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PATIENTSDTO {
    private int id;
    private String name;
    private String phone;
    private String birth_date;

    public PATIENTSDTO() {
    }

    public PATIENTSDTO(int id, String name, String phone, String birth_date) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.birth_date = birth_date;
    }

    public PATIENTSDTO (ResultSet rs) throws SQLException {
        id =rs.getInt("id");
        name =rs.getString("name");
        phone =rs.getString("phone");
        birth_date = rs.getString("birth_date");
    }



    public int getId() {
        return id;
    }

    public String getName() {
        return name;
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
                ", phone='" + phone + '\'' +
                ", birth_date=" + birth_date +
                '}';
    }
    }


