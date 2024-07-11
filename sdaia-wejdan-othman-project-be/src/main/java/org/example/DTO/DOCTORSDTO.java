package org.example.DTO;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DOCTORSDTO {

    private int id;
    private String name;
    private String specialty;


    public DOCTORSDTO() {
    }

    public DOCTORSDTO(int id, String name, String specialty, String email, String password, String phone) {
        this.id = id;
        this.name = name;
        this.specialty = specialty;
    }

    public DOCTORSDTO (ResultSet rs) throws SQLException {
        id =rs.getInt("id");
        name =rs.getString("name");
        specialty =rs.getString("specialty");

    }

    public DOCTORSDTO(String email, String password) {
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


    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }


    @Override
    public String toString() {
        return "DOCTORS{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", specialty='" + specialty + '\'' +
                '}';
    }


}
