package org.example.DAO;

import org.example.DTO.CONSULTATIONSDTO;
import org.example.DTO.RatingsDTO;
import org.example.DTO.SCHEDULESDTO;
import org.example.model.CONSULTATIONS;

import java.io.Serializable;
import java.sql.*;
import java.util.ArrayList;

public class ConsultationsDAO implements Serializable {
    private static final String URL = "jdbc:sqlite:C:\\Users\\dev\\IdeaProjects\\JAVAPROJECT\\javabd.db";
    private static final String select_CONSULTATIONS = "select * from CONSULTATIONS";
    private static final String SQL_DEFINE_CONSULTATIONS = "INSERT INTO CONSULTATIONS (doctor_id, patient_id, request_time, consultation_time ,status ,diagnosis) VALUES (?, ?, ?, ? ,? ,?)";
    private static final String UPDATE_CONSULTATIONS = "UPDATE CONSULTATIONS SET doctor_id = ?, patient_id = ?, request_time = ? , consultation_time = ? , status = ? , diagnosis = ? WHERE id = ?";
    private static final String SELECT_ALL_CONSULTATIONS_patint = "select * from CONSULTATIONS where patient_id = ?";
    private static final String SQL_RECORD_DIAGNOSIS = "UPDATE CONSULTATIONS SET diagnosis = ? WHERE patient_id = ?";
    static final String SELECT_ALL_CONSULTATIONS_BY_STATAS = "select * from CONSULTATIONS where status = ?";
    private static final String SQL_update_status = "UPDATE CONSULTATIONS SET status = ? WHERE patient_id = ?";
    private static final String select__CONSULTATIONS_doctor = "select * from CONSULTATIONS where doctor_id =?";
    private static final String select__CONSULTATIONS_STATAS = "select * from CONSULTATIONS where doctor_id =? AND status = ?";

    public ArrayList<CONSULTATIONSDTO> Select_STATAS_doctor(int doctor_id, String status) throws SQLException, ClassNotFoundException {

        Class.forName("org.sqlite.JDBC");
        Connection conn = DriverManager.getConnection(URL);
        PreparedStatement st = null;
        //if( doctor_id > 0 && status != null ) {
            //  بمعنى لازم يكون محدد لي كل القيم
            st = conn.prepareStatement(select__CONSULTATIONS_STATAS);
            st.setObject(1, doctor_id);
            st.setObject(2, status);
        //}
        ResultSet rs = st.executeQuery();
        ArrayList<CONSULTATIONSDTO> jobs = new ArrayList<>();
        while (rs.next()) {
            jobs.add(new CONSULTATIONSDTO(rs));
        }
        return jobs;
    }


    public ArrayList<CONSULTATIONSDTO> selectDoctorCONSULTATIONS(int doctor_id) throws SQLException, ClassNotFoundException {
        // int  يعني القيمه الابتدائية صفر
        // معناها يبدا من صفر
        Class.forName("org.sqlite.JDBC");
        Connection conn = DriverManager.getConnection(URL);
        PreparedStatement st = null;
        if(doctor_id != -1) {
            //  بمعنى لازم يكون محدد لي كل القيم
            st = conn.prepareStatement(select__CONSULTATIONS_doctor);
            st.setObject(1,doctor_id);
        }
        ResultSet rs = st.executeQuery();
        ArrayList<CONSULTATIONSDTO> jobs = new ArrayList<>();
        while (rs.next()) {
            jobs.add(new CONSULTATIONSDTO(rs));
        }
        return jobs;
    }



    public void updatestatus(int patient_id, String status) throws SQLException, ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        Connection conn = DriverManager.getConnection(URL);
        try (PreparedStatement st = conn.prepareStatement(SQL_update_status)) {
            st.setString(1, status);
            st.setInt(2, patient_id);
            st.executeUpdate();
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }





    public ArrayList<CONSULTATIONSDTO> selectConsultationsByStatus(String status) throws SQLException, ClassNotFoundException {
        // int  يعني القيمه الابتدائية صفر
        // معناها يبدا من صفر
        Class.forName("org.sqlite.JDBC");
        Connection conn = DriverManager.getConnection(URL);
        PreparedStatement st = null;
        if(status != null ) {
            //  بمعنى لازم يكون محدد لي كل القيم
            st = conn.prepareStatement(SELECT_ALL_CONSULTATIONS_BY_STATAS);
            st.setString(1, status);
        }
        ResultSet rs = st.executeQuery();
        ArrayList<CONSULTATIONSDTO> CONSUL = new ArrayList<>();
        while (rs.next()) {
            CONSUL.add(new CONSULTATIONSDTO(rs));
        }

        return CONSUL;
    }


    public void updateDiagnosis(int patient_id, String diagnosis) throws SQLException, ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        Connection conn = DriverManager.getConnection(URL);
        try (PreparedStatement st = conn.prepareStatement(SQL_RECORD_DIAGNOSIS)) {
            st.setString(1, diagnosis);
            st.setInt(2, patient_id);
            st.executeUpdate();
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }






    public ArrayList<CONSULTATIONSDTO> selectConsultationsBypatints(int patient_id) throws SQLException, ClassNotFoundException {
        // int  يعني القيمه الابتدائية صفر
        // معناها يبدا من صفر
        Class.forName("org.sqlite.JDBC");
        Connection conn = DriverManager.getConnection(URL);
        PreparedStatement st = null;
        if(patient_id >= 0 ) {
            //  بمعنى لازم يكون محدد لي كل القيم
            st = conn.prepareStatement(SELECT_ALL_CONSULTATIONS_patint);
            st.setObject(1, patient_id);
        }
        ResultSet rs = st.executeQuery();
        ArrayList<CONSULTATIONSDTO> jobs = new ArrayList<>();
        while (rs.next()) {
            jobs.add(new CONSULTATIONSDTO(rs));
        }

        return jobs;
    }












    public ArrayList<CONSULTATIONSDTO> selectAllofconsltuion() throws SQLException, ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        Connection conn = DriverManager.getConnection(URL);
        PreparedStatement st = conn.prepareStatement(select_CONSULTATIONS);
        ResultSet rs = st.executeQuery();
        ArrayList<CONSULTATIONSDTO> depts = new ArrayList<>();
        while (rs.next()) {
            depts.add(new CONSULTATIONSDTO(rs));
        }

        return depts;
    }





    public void insertAvailabilityCONSULTATIONS(CONSULTATIONSDTO d) throws SQLException, ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        Connection conn = DriverManager.getConnection(URL);
        PreparedStatement stmt = conn.prepareStatement(SQL_DEFINE_CONSULTATIONS); {
            stmt.setInt(1,d.getDoctor_id());
            stmt.setInt(2,d.getPatient_id());
            stmt.setObject(3,d.getRequest_time());
            stmt.setObject(4,d.getConsultation_time());
            stmt.setObject(5,d.getStatus());
            stmt.setString(6,d.getDiagnosis());
            stmt.executeUpdate();
        }
    }



    public void insertrequestCONSULTATIONS(CONSULTATIONSDTO d) throws SQLException, ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        Connection conn = DriverManager.getConnection(URL);
        PreparedStatement stmt = conn.prepareStatement(SQL_DEFINE_CONSULTATIONS); {
            stmt.setInt(1,d.getDoctor_id());
            stmt.setInt(2,d.getPatient_id());
            stmt.setObject(3,d.getRequest_time());
            stmt.setObject(4,d.getConsultation_time());
            stmt.setObject(5,d.getStatus().equals(null));
            stmt.setString(6, String.valueOf(d.getDiagnosis().equals(null)));
            stmt.executeUpdate();
        }
    }







    public void updateAVAILABILITYCONSULTATIONS(int doctor_id,int Patient_id,Date request_time,Date consultation_time,String status,String diagnosis,int id) throws SQLException, ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        Connection conn = DriverManager.getConnection(URL);
        try (PreparedStatement st = conn.prepareStatement(UPDATE_CONSULTATIONS)){
            st.setObject(1,doctor_id);
            st.setObject(2,Patient_id);
            st.setObject(3, request_time);
            st.setObject(4,consultation_time);
            st.setObject(5,status);
            st.setObject(6,diagnosis);
            st.setInt(7,id);
            st.executeUpdate();
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

}
