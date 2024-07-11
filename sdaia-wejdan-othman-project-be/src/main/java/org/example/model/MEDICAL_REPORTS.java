package org.example.model;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class MEDICAL_REPORTS {
    private int id;
    private int patient_id;
    private String details;
    private String report_date;
    private int rating_id;


    public MEDICAL_REPORTS() {
    }

    public MEDICAL_REPORTS(int id, int patient_id, String details, String report_date, int rating_id) {
        this.id = id;
        this.patient_id = patient_id;
        this.details = details;
        this.report_date = report_date;
        this.rating_id = rating_id;
    }
    public MEDICAL_REPORTS (ResultSet rs) throws SQLException {
        id =rs.getInt("id");
        patient_id =rs.getInt("patient_id");
        details =rs.getString("details");
        report_date = rs.getString("report_date");
        rating_id =rs.getInt("rating_id");
    }




    public int getId() {
        return id;
    }

    public int getPatient_id() {
        return patient_id;
    }

    public String getDetails() {
        return details;
    }

    public String getReport_date() {
        return report_date;
    }

    public int getRating_id() {
        return rating_id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPatient_id(int patient_id) {
        this.patient_id = patient_id;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public void setReport_date(String report_date) {
        this.report_date = report_date;
    }

    public void setRating_id(int rating_id) {
        this.rating_id = rating_id;
    }

    @Override
    public String toString() {
        return "MEDICAL_REPORTS{" +
                "id=" + id +
                ", patient_id=" + patient_id +
                ", details='" + details + '\'' +
                ", report_date=" + report_date +
                ", rating_id=" + rating_id +
                '}';
    }
}
