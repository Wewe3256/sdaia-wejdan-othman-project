package org.example.DAO;

import org.example.DTO.DOCTORSDTO;
import org.example.DTO.PATIENTSDTO;
import org.example.filter.DoctorFilter;
import org.example.model.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

public class PATIENTSDAO  {
    private static final String URL = "jdbc:sqlite:C:\\Users\\dev\\IdeaProjects\\JAVAPROJECT\\javabd.db";
    private static final String SQL_REGISTER_PATIENTS = "INSERT INTO PATIENTS (name, email, password, phone, birth_date) VALUES (?, ?, ?, ?, ?)";
    private static final String SQL_SELECT_PATIENTS = "SELECT * FROM PATIENTS";
    private static final String SELECT_ALL_DOCTORs = "select * from DOCTORS";
    private static final String SELECT_name_DOCTOR = "select * from DOCTORS where name = ?";
    private static final String SELECT_specialty_DOCTOR = "select * from DOCTORS where specialty = ?";
    private static final String SELECT_id_DOCTOR = "select * from DOCTORS where id = ?";
    private static final String SQL_SELECT_PATIENTS_byid = "SELECT * FROM PATIENTS where lower(name) = ?";
    private static final String SQL_UPDATE_PATIENTS = "UPDATE PATIENTS SET name = ?, email = ?, password = ? , phone = ?, birth_date = ? WHERE id = ?";
    private static final String LOGIN_PAT = "select * from PATIENTS where email = ? AND password = ?";
    private static final String SELECT_one_PATIENT = "select * from PATIENTS where id = ?";





    
    public ArrayList<PATIENTSDTO> SELECT_one_PATIENT(int id) throws SQLException, ClassNotFoundException {
        // int  يعني القيمه الابتدائية صفر
        // معناها يبدا من صفر
        Class.forName("org.sqlite.JDBC");
        Connection conn = DriverManager.getConnection(URL);
        PreparedStatement st = null;
        if(id != -1 ) {
            //  بمعنى لازم يكون محدد لي كل القيم
            st = conn.prepareStatement(SELECT_one_PATIENT);
            st.setObject(1,id);
        }
        ResultSet rs = st.executeQuery();
        ArrayList<PATIENTSDTO> jobs = new ArrayList<>();
        while (rs.next()) {
            jobs.add(new PATIENTSDTO(rs));
        }
        return jobs;
    }





    public ArrayList<PATIENTSDTO> selectAllPATIENTSnameid() throws SQLException, ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        Connection conn = DriverManager.getConnection(URL);
        PreparedStatement st = conn.prepareStatement(SQL_SELECT_PATIENTS);
        ResultSet rs = st.executeQuery();
        ArrayList<PATIENTSDTO> depts = new ArrayList<>();
        while (rs.next()) {
            depts.add(new PATIENTSDTO(rs));
        }
        return depts;
    }







    public PATIENTS PatientsLogin(String email, String password) throws SQLException, ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        Connection conn = DriverManager.getConnection(URL);
        PreparedStatement st = conn.prepareStatement(LOGIN_PAT);
        st.setString(1, email);
        st.setString(2, password);
        ResultSet rs = st.executeQuery();
        if(rs.next()) {
            return new PATIENTS(rs);
        }
            return null;
    }

    public void updateAVAIPATIENTS(String name,String email,String password,String phone,String birth_date,int id) throws SQLException, ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        Connection conn = DriverManager.getConnection(URL); {
            try (PreparedStatement st = conn.prepareStatement(SQL_UPDATE_PATIENTS)) {
                st.setObject(1, name);
                st.setObject(2, email);
                st.setObject(3, password);
                st.setObject(4, phone);
                st.setObject(5, birth_date);
                st.setInt(6,id);
                st.executeUpdate();
            } finally {
                if (conn != null) {
                    conn.close();
                }
            }
        }
    }




    public ArrayList<PATIENTS> SQL_SELECT_PATIENTS_by_name(String  name ) throws SQLException, ClassNotFoundException {

        Class.forName("org.sqlite.JDBC");
        Connection conn = DriverManager.getConnection(URL);
        PreparedStatement st = null;
        if( name != null) {
            //  بمعنى لازم يكون محدد لي كل القيم
            st = conn.prepareStatement(SQL_SELECT_PATIENTS_byid);
            st.setObject(1, name.toLowerCase());
        }
        ResultSet rs = st.executeQuery();
        ArrayList<PATIENTS> jobs = new ArrayList<>();
        while (rs.next()) {
            jobs.add(new PATIENTS(rs));
        }
        return jobs;
    }



    public ArrayList<DOCTORSDTO> selectDoctorBydetailz(DoctorFilter filter) throws SQLException, ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        Connection conn = DriverManager.getConnection(URL);
        PreparedStatement st ;
        if(filter.getSpecialty() != null ) {
            st = conn.prepareStatement(SELECT_specialty_DOCTOR);
            st.setObject(1,filter.getSpecialty());
        }
        else if(filter.getId() != null) {
            st = conn.prepareStatement(SELECT_id_DOCTOR);
            st.setObject(1,filter.getId());
        }
        else if(filter.getName() != null) {
            st = conn.prepareStatement(SELECT_name_DOCTOR);
            st.setObject(1,filter.getName());
        }
        else {
            st = conn.prepareStatement(SELECT_ALL_DOCTORs);
        }
        ResultSet rs = st.executeQuery();
        ArrayList<DOCTORSDTO> jobs = new ArrayList<>();
        while (rs.next()) {
            jobs.add(new DOCTORSDTO(rs));
        }
        return jobs;
    }







//////////////////////////////////////////// nooooooooooooooooooooo












    public void registerPATIENTS(PATIENTS d) throws SQLException, ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        Connection conn = DriverManager.getConnection(URL);
        PreparedStatement stmt = conn.prepareStatement(SQL_REGISTER_PATIENTS); {
            stmt.setString(1,d.getName());
            stmt.setString(2,d.getEmail());
            stmt.setString(3,d.getPassword());
            stmt.setString(4,d.getPhone());
            stmt.setString(5,d.getBirth_date());
            stmt.executeUpdate();
        }
    }

   // public ArrayList<PATIENTS> selectAllPATIENTS() throws SQLException, ClassNotFoundException {
    //    Class.forName("org.sqlite.JDBC");
       // Connection conn = DriverManager.getConnection(URL);
        //PreparedStatement st = conn.prepareStatement(SQL_SELECT_PATIENTS);
        //ResultSet rs = st.executeQuery();
       // ArrayList<PATIENTS> depts = new ArrayList<>();
        //while (rs.next()) {
           // depts.add(new PATIENTS(rs));
       // }

       // return depts;
   // }







}
