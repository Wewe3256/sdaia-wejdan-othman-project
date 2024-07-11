package org.example.DAO;

import org.example.DTO.MEDICAL_REPORTSDTO;
import org.example.filter.DoctorFilter;
import org.example.filter.MEDICALFilter;
import org.example.model.DOCTORS;
import org.example.model.MEDICAL_REPORTS;

import java.io.Serializable;
import java.sql.*;
import java.util.ArrayList;

public class MEDICAL_REPORTSDAO implements Serializable {
    private static final String URL = "jdbc:sqlite:C:\\Users\\dev\\IdeaProjects\\JAVAPROJECT\\javabd.db";
    private static final String select_MEDICAL_REPORTS = "select * from MEDICAL_REPORTS";
    private static final String insert_MEDICAL_REPORTS = "INSERT INTO MEDICAL_REPORTS (patient_id, details, report_date ,rating_id) VALUES (?, ?, ?, ?)";
    private static final String UPDATE_MEDICAL_REPORTS = "UPDATE MEDICAL_REPORTS SET details = ?, report_date = ?, rating_id = ? WHERE patient_id = ?";
    private static final String SELECT_MEDICAL_REPORTSd = "select * from MEDICAL_REPORTS where details = ?";
    private static final String SELECT_MEDICAL_REPORTSp = "select * from MEDICAL_REPORTS where patient_id = ?";
    static final String SELECT_MEDICAL_REPORTS_BY_PATIENTSid = "select * from MEDICAL_REPORTS where patient_id = ?";




    public ArrayList<MEDICAL_REPORTSDTO> selectMEDICAL_REPORTS(int patient_id) throws SQLException, ClassNotFoundException {
        // int  يعني القيمه الابتدائية صفر
        // معناها يبدا من صفر
        Class.forName("org.sqlite.JDBC");
        Connection conn = DriverManager.getConnection(URL);
        PreparedStatement st = null;
        if(patient_id != -1 ) {
            //  بمعنى لازم يكون محدد لي كل القيم
            st = conn.prepareStatement(SELECT_MEDICAL_REPORTS_BY_PATIENTSid);
            st.setObject(1, patient_id);
        }
        ResultSet rs = st.executeQuery();
        ArrayList<MEDICAL_REPORTSDTO> jobs = new ArrayList<>();
        while (rs.next()) {
            jobs.add(new MEDICAL_REPORTSDTO(rs));
        }
        return jobs;
    }








    public ArrayList<MEDICAL_REPORTSDTO> selectDoctorBydetailz(MEDICALFilter filter) throws SQLException, ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        Connection conn = DriverManager.getConnection(URL);
        PreparedStatement st ;
        if(filter.getDetails() != null ) {
            st = conn.prepareStatement(SELECT_MEDICAL_REPORTSd);
            st.setObject(1,filter.getDetails());
        }
        else if(filter.getPatient_id() != null) {
            st = conn.prepareStatement(SELECT_MEDICAL_REPORTSp);
            st.setObject(1,filter.getPatient_id());
        }
        else {
            st = conn.prepareStatement(select_MEDICAL_REPORTS);
        }
        ResultSet rs = st.executeQuery();
        ArrayList<MEDICAL_REPORTSDTO> jobs = new ArrayList<>();
        while (rs.next()) {
            jobs.add(new MEDICAL_REPORTSDTO(rs));
        }
        return jobs;
    }


    public ArrayList<MEDICAL_REPORTS> selectAllMEDICAL_REPORTS() throws SQLException, ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        Connection conn = DriverManager.getConnection(URL);
        PreparedStatement st = conn.prepareStatement(select_MEDICAL_REPORTS);
        ResultSet rs = st.executeQuery();
        java.util.ArrayList<org.example.model.MEDICAL_REPORTS> depts = new ArrayList<>();
        while (rs.next()) {
            depts.add(new org.example.model.MEDICAL_REPORTS(rs));
        }

        return depts;
    }

    public void insertAvailabilityMEDICAL_REPORTS(MEDICAL_REPORTSDTO d) throws SQLException, ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        Connection conn = DriverManager.getConnection(URL);
        PreparedStatement stmt = conn.prepareStatement(insert_MEDICAL_REPORTS); {
            stmt.setInt(1,d.getPatient_id());
            stmt.setObject(2,d.getDetails());
            stmt.setObject(3,d.getReport_date());
            stmt.setObject(4,d.getRating_id());
            stmt.executeUpdate();
        }
    }


    public void update_the_MEDICAL_RECORDS(int patient_id,String details,String report_date,int rating_id) throws SQLException, ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        Connection conn = DriverManager.getConnection(URL);
        try (PreparedStatement st = conn.prepareStatement(UPDATE_MEDICAL_REPORTS)) {
            st.setObject(1,details);
            st.setObject(2,report_date);
            st.setObject(3, rating_id);
            st.setObject(4, patient_id);
            st.executeUpdate();
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }


