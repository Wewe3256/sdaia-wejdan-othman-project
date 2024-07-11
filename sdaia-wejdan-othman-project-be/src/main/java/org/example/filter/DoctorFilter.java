package org.example.filter;

import jakarta.ws.rs.QueryParam;

public class DoctorFilter {
    @QueryParam("name") String name;
    @QueryParam("id") Integer id;
    @QueryParam("specialty") String specialty;

    public String getName() {
        return name;
    }

    public Integer getId() {
        return id;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }
}
