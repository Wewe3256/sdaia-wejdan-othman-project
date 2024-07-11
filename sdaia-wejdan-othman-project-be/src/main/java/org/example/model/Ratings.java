package org.example.model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Ratings {
    private int id;
    private int patient_id;
    private int doctor_id;
    private int rating;

    public Ratings() {
    }

    public Ratings(int id, int patient_id, int doctor_id, int rating) {
        this.id = id;
        this.patient_id = patient_id;
        this.doctor_id = doctor_id;
        this.rating = rating;
    }

    public Ratings (ResultSet rs) throws SQLException {
        id =rs.getInt("id");
        patient_id =rs.getInt("patient_id");
        doctor_id =rs.getInt("doctor_id");
        rating =rs.getInt("rating");
    }

    public int getId() {
        return id;
    }

    public int getPatient_id() {
        return patient_id;
    }

    public int getDoctor_id() {
        return doctor_id;
    }

    public int getRating() {
        return rating;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPatient_id(int patient_id) {
        this.patient_id = patient_id;
    }

    public void setDoctor_id(int doctor_id) {
        this.doctor_id = doctor_id;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "Ratings{" +
                "id=" + id +
                ", patient_id=" + patient_id +
                ", doctor_id=" + doctor_id +
                ", rating=" + rating +
                '}';
    }
}
