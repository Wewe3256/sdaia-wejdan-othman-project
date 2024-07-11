package org.example.DAO;

import org.example.DTO.DOCTORSDTO;
import org.example.DTO.SCHEDULESDTO;
import org.example.model.CONSULTATIONS;
import org.example.model.SCHEDULES;

import java.io.Serializable;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SCHEDULESDAO implements Serializable {
    private static final String URL = "jdbc:sqlite:C:\\Users\\dev\\IdeaProjects\\JAVAPROJECT\\javabd.db";
    private static final String select_SCHEDULES = "select * from SCHEDULES";
    private static final String insert_SCHEDULES = "INSERT INTO SCHEDULES (doctor_id, start_time, end_time, is_available) VALUES (?, ?, ?, ?)";
    private static final String UPDATE_SCHEDULES = "UPDATE SCHEDULES SET start_time = ?, end_time = ?, is_available = ? WHERE doctor_id = ?";
    private static final String delete_SCHEDULES = "DELETE FROM SCHEDULES WHERE id = ?";
    private static final String selecttt_SCHEDULESsss = "select * from SCHEDULES where doctor_id =?";
    private static final String select_specifice_doctor = "select * from SCHEDULES where doctor_id =? AND is_available = ?";
    private static final String select_ALL_available_doctors = "select * from DOCTORS where id in(select distinct(doctor_id) from SCHEDULES where is_available = ?)";






    public ArrayList<DOCTORSDTO> selectDoctorByavailable(Boolean is_available) throws SQLException, ClassNotFoundException {
        // int  يعني القيمه الابتدائية صفر
        // معناها يبدا من صفر
        Class.forName("org.sqlite.JDBC");
        Connection conn = DriverManager.getConnection(URL);
        PreparedStatement st = null;
        if(is_available != null ) {
            //  بمعنى لازم يكون محدد لي كل القيم
            st = conn.prepareStatement(select_ALL_available_doctors);
            st.setObject(1,is_available);
        }
        ResultSet rs = st.executeQuery();
        ArrayList<DOCTORSDTO> jobs = new ArrayList<>();
        while (rs.next()) {
            jobs.add(new DOCTORSDTO(rs));
        }
        return jobs;
    }


    public ArrayList<SCHEDULESDTO> Select_specifice_doctor(int doctor_id,boolean is_available) throws SQLException, ClassNotFoundException {

        Class.forName("org.sqlite.JDBC");
        Connection conn = DriverManager.getConnection(URL);
        PreparedStatement st = null;
        if( doctor_id > 0 && is_available == true ) {
            //  بمعنى لازم يكون محدد لي كل القيم
            st = conn.prepareStatement(select_specifice_doctor);
            st.setObject(1, doctor_id);
            st.setObject(2, is_available);
        }
        ResultSet rs = st.executeQuery();
        ArrayList<SCHEDULESDTO> jobs = new ArrayList<>();
        while (rs.next()) {
            jobs.add(new SCHEDULESDTO(rs));
        }
        return jobs;
    }








    public ArrayList<SCHEDULESDTO> selectConsultationsBySCHEDULESsss(int doctor_id) throws SQLException, ClassNotFoundException {
        // int  يعني القيمه الابتدائية صفر
        // معناها يبدا من صفر
        Class.forName("org.sqlite.JDBC");
        Connection conn = DriverManager.getConnection(URL);
        PreparedStatement st = null;
        if(doctor_id >= 0 ) {
            //  بمعنى لازم يكون محدد لي كل القيم
            st = conn.prepareStatement(selecttt_SCHEDULESsss);
            st.setObject(1, doctor_id);
        }
        ResultSet rs = st.executeQuery();
        ArrayList<SCHEDULESDTO> jobs = new ArrayList<>();
        while (rs.next()) {
            jobs.add(new  SCHEDULESDTO(rs));
        }

        return jobs;
    }




    public List< SCHEDULESDTO> selectSCHEDULES() throws SQLException, ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        Connection conn = DriverManager.getConnection(URL);
        PreparedStatement st = conn.prepareStatement(select_SCHEDULES);
        ResultSet rs = st.executeQuery();
        ArrayList< SCHEDULESDTO> depts = new ArrayList<>();
        while (rs.next()) {
            depts.add(new  SCHEDULESDTO(rs));
        }
        return depts;
    }

    public void insertAvailabilitySCHEDULES( SCHEDULESDTO d) throws SQLException, ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        Connection conn = DriverManager.getConnection(URL);
        PreparedStatement stmt = conn.prepareStatement(insert_SCHEDULES); {
            stmt.setInt(1,d.getDoctor_id());
            stmt.setObject(2,d.getStart_end());
            stmt.setObject(3,d.getEnd_time());
            stmt.setBoolean(4,d.isIs_available());
            stmt.executeUpdate();
        }
    }

    public void updateAVAILABILITYSCHEDULESS(int doctor_id,String start_time,String end_time,Boolean is_available) throws SQLException, ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        Connection conn = DriverManager.getConnection(URL);
        try (PreparedStatement st = conn.prepareStatement(UPDATE_SCHEDULES)) {
            st.setString(1,start_time);
            st.setString(2,end_time);
            st.setBoolean(3, is_available);
            st.setInt(4, doctor_id);
            st.executeUpdate();
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    public void DeleteSCHEDULESS(int id) throws SQLException, ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        Connection conn = DriverManager.getConnection(URL);
        PreparedStatement st = conn.prepareStatement(delete_SCHEDULES);
        st.setObject(1,id);
        st.executeUpdate();
    }

}
