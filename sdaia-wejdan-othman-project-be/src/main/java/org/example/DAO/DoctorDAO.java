package org.example.DAO;

import org.example.DTO.DOCTORSDTO;
import org.example.DTO.PATIENTSDTO;
import org.example.model.*;

import java.io.Serializable;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DoctorDAO implements Serializable {
    private static final String URL = "jdbc:sqlite:C:\\Users\\dev\\IdeaProjects\\JAVAPROJECT\\javabd.db";
    private static final String SQL_REGISTER_DOCTOR = "INSERT INTO DOCTORS (name, specialty, email, password, phone) VALUES (?, ?, ?, ?, ?)";
    private static final String SQL_SEARCH_MEDICAL_RECORDS = "SELECT * FROM MEDICAL_REPORTS WHERE patient_id IN (SELECT id FROM PATIENTS WHERE lower(name)=? OR lower(email)=?)";
    private static final String SELECT_ALL_DOCTOR = "select * from DOCTORS";
    private static final String SELECT_one_DOCTOR = "select * from DOCTORS where id = ?";
    private static final String SQL_update_DOCTOR = "UPDATE DOCTORS SET name = ?, specialty = ?, email = ?, password = ?,phone = ?  WHERE id = ?";;
    private static final String DoctorLogin = "select * from DOCTORS where email = ? AND password = ?";


    public ArrayList<DOCTORS> selectAlldociioo() throws SQLException, ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        Connection conn = DriverManager.getConnection(URL);
        PreparedStatement st = conn.prepareStatement(SELECT_ALL_DOCTOR);
        ResultSet rs = st.executeQuery();
        ArrayList<DOCTORS> depts = new ArrayList<>();
        while (rs.next()) {
            depts.add(new DOCTORS(rs));
        }
        return depts;
    }




    public ArrayList<DOCTORSDTO> SELECT_one_doctor_id(int id) throws SQLException, ClassNotFoundException {
        // int  يعني القيمه الابتدائية صفر
        // معناها يبدا من صفر
        Class.forName("org.sqlite.JDBC");
        Connection conn = DriverManager.getConnection(URL);
        PreparedStatement st = null;
        if(id != -1 ) {
            //  بمعنى لازم يكون محدد لي كل القيم
            st = conn.prepareStatement(SELECT_one_DOCTOR);
            st.setObject(1,id);
        }
        ResultSet rs = st.executeQuery();
        ArrayList<DOCTORSDTO> jobs = new ArrayList<>();
        while (rs.next()) {
            jobs.add(new DOCTORSDTO(rs));
        }
        return jobs;
    }









    public ArrayList<DOCTORSDTO> selectAlldociio() throws SQLException, ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        Connection conn = DriverManager.getConnection(URL);
        PreparedStatement st = conn.prepareStatement(SELECT_ALL_DOCTOR);
        ResultSet rs = st.executeQuery();
        ArrayList<DOCTORSDTO> depts = new ArrayList<>();
        while (rs.next()) {
            depts.add(new DOCTORSDTO(rs));
        }
        return depts;
    }










    public DOCTORS DoctorLogin(String email, String password) throws SQLException, ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        Connection conn = DriverManager.getConnection(URL);
        PreparedStatement st = conn.prepareStatement(DoctorLogin);
        st.setString(1, email);
        st.setString(2, password);
        ResultSet rs = st.executeQuery();
        if(rs.next()) {
            return new DOCTORS(rs);
        }
        return null;
    }




















    public void updateAVAILABILITYDOCTOR(String name,String specialty,String email,String password,String phone,int id ) throws SQLException, ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        Connection conn = DriverManager.getConnection(URL);
        try (PreparedStatement st = conn.prepareStatement(SQL_update_DOCTOR)) {
            st.setObject(1,name);
            st.setObject(2,specialty);
            st.setObject(3, email);
            st.setObject(4, password);
            st.setObject(5, phone);
            st.setObject(6,id);
            st.executeUpdate();
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }
















    // public DoctorDAO(DOCTORS doctor) {
   // }

   //// public DoctorDAO() {

    //}

   // public ArrayList<CONSULTATIONS> selectAllConsultationsByDoctor(int doctorId) throws SQLException, ClassNotFoundException {
      //  Class.forName("org.sqlite.JDBC");
       // Connection conn = DriverManager.getConnection(URL);
        //PreparedStatement st = conn.prepareStatement(SELECT_ALL_CONSULTATIONS_BY_DOCTOR);
        //st.setInt(1, doctorId);
       // ResultSet rs = st.executeQuery();
       // ArrayList<CONSULTATIONS> consultations = new ArrayList<>();
       // while (rs.next()) {
       //     CONSULTATIONS consultation = new CONSULTATIONS(
                  //  rs.getInt("id"),
                 //   rs.getInt("doctor_id"),
                //    rs.getInt("patient_id"),
                //    rs.getTimestamp("request_time"),
                //    rs.getTimestamp("consultation_time"),
               //     rs.getString("status"),
                //    rs.getString("diagnosis")
          //  );
         //   consultations.add(consultation);
       // }
      //  return consultations;
   // }


  // public ArrayList<SCHEDULES> getAllAvailableSchedules() throws SQLException, ClassNotFoundException {
     //  Class.forName("org.sqlite.JDBC");
      // Connection conn = DriverManager.getConnection(URL);
      // PreparedStatement st = conn.prepareStatement(SELECT_AVAILABLE_SCHEDULES);
       //ResultSet rs = st.executeQuery();
      // ArrayList<SCHEDULES> depts = new ArrayList<>();
      // while (rs.next()) {
          // depts.add(new SCHEDULES(rs));
      // }

      // return depts;
  // }



        public void registerDoctor(DOCTORS d) throws SQLException, ClassNotFoundException {
           Class.forName("org.sqlite.JDBC");
           Connection conn = DriverManager.getConnection(URL);
           PreparedStatement stmt = conn.prepareStatement(SQL_REGISTER_DOCTOR); {
              stmt.setString(1,d.getName());
              stmt.setString(2,d.getSpecialty());
              stmt.setString(3,d.getEmail());
              stmt.setString(4,d.getPassword());
              stmt.setString(5,d.getPhone());
              stmt.executeUpdate();
         }
     }
//(SQL_REGISTER_DOCTOR,Statement.RETURN_GENERATED_KEYS)
   // public boolean loginDoctor(DOCTORS d) throws SQLException, ClassNotFoundException {
      //  Class.forName("org.sqlite.JDBC");
       // Connection conn = DriverManager.getConnection(URL);
           //  PreparedStatement stmt = conn.prepareStatement(SQL_LOGIN_DOCTOR);{
           // stmt.setString(1,d.getEmail());
           // stmt.setString(2,d.getPassword());
          //  ResultSet rs = stmt.executeQuery();
          //  return rs.next();
       // }
   // }


//Statement.RETURN_GENERATED_KEYS
    /// mmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmm
      //public void updateAvailabilitySchedule(SCHEDULES d) throws SQLException, ClassNotFoundException {
        //  Class.forName("org.sqlite.JDBC");
         // Connection conn = DriverManager.getConnection(URL);
          // PreparedStatement stmt = conn.prepareStatement(SQL_UPDATE_AVAILABILITY); {
           // stmt.setInt(1,d.getDoctor_id());
           // stmt.setObject(2,d.getStart_end());
           // stmt.setObject(3,d.getEnd_time());
           // stmt.setBoolean(4,d.isIs_available());
           // stmt.executeUpdate();
     //   }
    //}

   // public List<CONSULTATIONS> getPendingConsultations(CONSULTATIONS d) throws SQLException, ClassNotFoundException {
       // Class.forName("org.sqlite.JDBC");
       // try (Connection conn = DriverManager.getConnection(URL);
          //   PreparedStatement stmt = conn.prepareStatement(SQL_GET_PENDING_CONSULTATIONS)) {
           // stmt.setInt(1, d.getDoctor_id());
           // ResultSet rs = stmt.executeQuery();
           // List<CONSULTATIONS> consultations = new ArrayList<>();
            //while (rs.next()) {
               // consultations.add(new CONSULTATIONS(
                       // rs.getInt("id"),
                       // rs.getInt("doctor_id"),
                       // rs.getInt("patient_id"),
                      //  rs.getTimestamp("request_time"),
                     //   rs.getTimestamp("consultation_time"),
                     //   rs.getString("status"),
                     //   rs.getString("diagnosis")
               // ));
           // }
          //  return consultations;
       // }
   // }

    //public void giveConsultation(CONSULTATIONS d) throws SQLException, ClassNotFoundException {
        //Class.forName("org.sqlite.JDBC");
        //Connection conn = DriverManager.getConnection(URL);
            // PreparedStatement stmt = conn.prepareStatement(SQL_GIVE_CONSULTATION); {
            //stmt.setTimestamp(1, Timestamp.valueOf(d.getConsultation_time()));
           // stmt.setInt(2,d.getId());
            //stmt.executeUpdate();
       // }
   // }

    //public void recordDiagnosis(CONSULTATIONS d) throws SQLException, ClassNotFoundException {
      //  Class.forName("org.sqlite.JDBC");
       // Connection conn = DriverManager.getConnection(URL);
         //    PreparedStatement stmt = conn.prepareStatement(SQL_RECORD_DIAGNOSIS); {
          //  stmt.setString(1,d.getDiagnosis());
            //stmt.setInt(2,d.getId());
            //stmt.executeUpdate();
//}
   // }

         // public List<MEDICAL_REPORTS> searchMedicalRecords(String keyword,int patient_id ) throws SQLException, ClassNotFoundException {
            //Class.forName("org.sqlite.JDBC");
            //Connection conn = DriverManager.getConnection(URL);
            //PreparedStatement stmt = conn.prepareStatement(SQL_SEARCH_MEDICAL_RECORDS);{
             // stmt.setString(1, "%" + keyword + "%");
              //stmt.setInt(2,patient_id);
             // ResultSet rs = stmt.executeQuery();
              // List<MEDICAL_REPORTS> reports = new ArrayList<>();
             //  while (rs.next()) {
                 // MEDICAL_REPORTS report = new MEDICAL_REPORTS(
                         // rs.getInt("id"),
                      // rs.getInt("patient_id"),
                      // rs.getString("details"),
                      // rs.getString("report_date"),
                     //  rs.getInt("rating_id")
                //  );
              //  reports.add(report);
        // }
          //  return reports;
      //  }
    //}


    public List<MEDICAL_REPORTS> searchMedicalRecords(String keyword) throws SQLException, ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        Connection conn = DriverManager.getConnection(URL);
        PreparedStatement stmt = conn.prepareStatement(SQL_SEARCH_MEDICAL_RECORDS);
        stmt.setString(1, keyword.toLowerCase());
        stmt.setString(2, keyword.toLowerCase());
        ResultSet rs = stmt.executeQuery();
        List<MEDICAL_REPORTS> reports = new ArrayList<>();
        while (rs.next()) {
            MEDICAL_REPORTS report = new MEDICAL_REPORTS(
                    rs.getInt("id"),
                    rs.getInt("patient_id"),
                    rs.getString("details"),
                    rs.getString("report_date"),
                    rs.getInt("rating_id")
            );
            reports.add(report);
        }
        rs.close();
        stmt.close();
        conn.close();
        return reports;
    }
}

