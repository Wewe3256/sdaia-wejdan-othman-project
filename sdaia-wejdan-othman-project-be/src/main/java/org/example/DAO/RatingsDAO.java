package org.example.DAO;

import org.example.DTO.DOCTORSDTO;
import org.example.DTO.RatingsDTO;
import org.example.model.DOCTORS;
import org.example.model.Ratings;

import java.io.Serializable;
import java.sql.*;
import java.util.ArrayList;

public class RatingsDAO implements Serializable {
    private static final String URL = "jdbc:sqlite:C:\\Users\\dev\\IdeaProjects\\JAVAPROJECT\\javabd.db";
    private static final String select_Ratings = "select * from Ratings";
    private static final String insert_Ratings = "INSERT INTO Ratings (patient_id,doctor_id,rating) VALUES (?,?,?)";
    private static final String UPDATE_Ratings = "UPDATE Ratings SET patient_id = ?, doctor_id = ?, rating = ? WHERE id = ?";
    private static final String SELECT_rating_BY_doctorid = "select * from Ratings where doctor_id = ?";
    private static final String SELECT_doctor_BY_rating = "select * from Ratings where rating = ?";
    private static final String selectt_doctorbyrating = "select * from DOCTORS where id in(select distinct(doctor_id) from Ratings where rating = ?)";

    public ArrayList<DOCTORSDTO> selectDoctorByavairating(int rating) throws SQLException, ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        Connection conn = DriverManager.getConnection(URL);
        PreparedStatement st = null;
       // if(rating>=5) {
            st = conn.prepareStatement(selectt_doctorbyrating);
            st.setObject(1,rating);
       // }
        ResultSet rs = st.executeQuery();
        ArrayList<DOCTORSDTO> jobs = new ArrayList<>();
        while (rs.next()) {
            jobs.add(new DOCTORSDTO(rs));
        }
        return jobs;
    }



    public ArrayList<RatingsDTO> selectDoctorByRating(int rating) throws SQLException, ClassNotFoundException {
        // int  يعني القيمه الابتدائية صفر
        // معناها يبدا من صفر
        Class.forName("org.sqlite.JDBC");
        Connection conn = DriverManager.getConnection(URL);
        PreparedStatement st = null;
        if(rating > 0 && rating <= 5 ) {
            //  بمعنى لازم يكون محدد لي كل القيم
            st = conn.prepareStatement(SELECT_doctor_BY_rating);
            st.setObject(1,rating);
        }
        ResultSet rs = st.executeQuery();
        ArrayList<RatingsDTO> jobs = new ArrayList<>();
        while (rs.next()) {
            jobs.add(new RatingsDTO(rs));
        }
        return jobs;
    }


    public ArrayList<RatingsDTO> SELECT_rating_BY_doctoridd(int doctor_id) throws SQLException, ClassNotFoundException {
        // int  يعني القيمه الابتدائية صفر
        // معناها يبدا من صفر
        Class.forName("org.sqlite.JDBC");
        Connection conn = DriverManager.getConnection(URL);
        PreparedStatement st = null;
        if(doctor_id != -1 ) {
            //  بمعنى لازم يكون محدد لي كل القيم
            st = conn.prepareStatement(SELECT_rating_BY_doctorid);
            st.setObject(1,doctor_id);
        }
        ResultSet rs = st.executeQuery();
        ArrayList<RatingsDTO> jobs = new ArrayList<>();
        while (rs.next()) {
            jobs.add(new RatingsDTO(rs));
        }
        return jobs;
    }






    public ArrayList<RatingsDTO> selectRatings() throws SQLException, ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        Connection conn = DriverManager.getConnection(URL);
        PreparedStatement st = conn.prepareStatement(select_Ratings);
        ResultSet rs = st.executeQuery();
        ArrayList<RatingsDTO> depts = new ArrayList<>();
        while (rs.next()) {
            depts.add(new RatingsDTO(rs));
        }

        return depts;
    }

    public void insertRatings(RatingsDTO d) throws SQLException, ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        Connection conn = DriverManager.getConnection(URL);
        PreparedStatement stmt = conn.prepareStatement(insert_Ratings); {
            stmt.setObject(1,d.getPatient_id());
            stmt.setObject(2,d.getDoctor_id());
            stmt.setObject(3,d.getRating());
            stmt.executeUpdate();
        }
    }


    public void updateRatings(int patient_id,int doctor_id,int rating,int id ) throws SQLException, ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        Connection conn = DriverManager.getConnection(URL);
        try (PreparedStatement st = conn.prepareStatement(UPDATE_Ratings)) {
            st.setObject(1,patient_id);
            st.setObject(2,doctor_id);
            st.setObject(3, rating);
            st.setObject(4,id);
            st.executeUpdate();
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }




}
