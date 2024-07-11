package org.example.model;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class CONSULTATIONS {
    private int id;
    private int doctor_id;
    private int patient_id;
    private Date request_time;
    private Date consultation_time;
    private String status;
    private String diagnosis;

    public CONSULTATIONS() {
    }

    public CONSULTATIONS(int id, int doctor_id, int patient_id, Date request_time, Date consultation_time, String status, String diagnosis) {
        this.id = id;
        this.doctor_id = doctor_id;
        this.patient_id = patient_id;
        this.request_time = request_time;
        this.consultation_time = consultation_time;
        this.status = status;
        this.diagnosis = diagnosis;
    }
    public CONSULTATIONS (ResultSet rs) throws SQLException {
        id =rs.getInt("id");
        doctor_id =rs.getInt("doctor_id");
        patient_id =rs.getInt("patient_id");
        request_time = rs.getDate("request_time");
        consultation_time = rs.getDate("consultation_time");
        status=rs.getString("status");
        diagnosis =rs.getString("diagnosis");
    }

    //public CONSULTATIONS(int id,int doctorId, int patientId, Timestamp requestTime, Timestamp consultationTime, String status, String diagnosis) {
   // }

    //public CONSULTATIONS(int doctorId, Timestamp requestTime, Timestamp consultationTime, String status, String diagnosis) {
    //}

    //public CONSULTATIONS(int doctorId) {
    //}

    public int getId() {
        return id;
    }

    public int getDoctor_id() {
        return doctor_id;
    }

    public int getPatient_id() {
        return patient_id;
    }

    public Date getRequest_time() {
        return request_time;
    }

    public Date getConsultation_time() {
        return consultation_time;
    }

    public String getStatus() {
        return status;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDoctor_id(int doctor_id) {
        this.doctor_id = doctor_id;
    }

    public void setPatient_id(int patient_id) {
        this.patient_id = patient_id;
    }

    public void setRequest_time(Date request_time) {
        this.request_time = request_time;
    }

    public void setConsultation_time(Date consultation_time) {
        this.consultation_time = consultation_time;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    @Override
    public String toString() {
        return "CONSULTATIONS{" +
                "id=" + id +
                ", doctor_id=" + doctor_id +
                ", patient_id=" + patient_id +
                ", request_time=" + request_time +
                ", consultation_time=" + consultation_time +
                ", status='" + status + '\'' +
                ", diagnosis='" + diagnosis + '\'' +
                '}';
    }
}
