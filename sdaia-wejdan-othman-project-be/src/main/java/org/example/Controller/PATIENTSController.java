package org.example.Controller;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;
import org.example.DAO.*;
import org.example.DTO.*;
import org.example.filter.DoctorFilter;
import org.example.filter.MEDICALFilter;
import org.example.model.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Path("PATIENT")
public class PATIENTSController {

    PATIENTSDAO patDao = new PATIENTSDAO();
    ConsultationsDAO conDAO = new ConsultationsDAO();
    SCHEDULESDAO schtDao = new SCHEDULESDAO();
    MEDICAL_REPORTSDAO MedicalDao = new MEDICAL_REPORTSDAO();
    RatingsDAO ratDao = new RatingsDAO();
    @Context UriInfo uriInfo;
    @Context HttpHeaders headers;




    @GET
    @Path("/selectAll_RATINGS")
    public ArrayList<RatingsDTO> getAllRatings () {
        try {
            return ratDao.selectRatings();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }







    @PUT
    @Path("/update_Ratings/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void updateAVAILABrating(@PathParam("id") int id, Ratings d) {
        try {
            d.setId(id); // تعيين معرف الاستشارة من المعامل المسار
            ratDao.updateRatings(d.getPatient_id(),d.getDoctor_id(),d.getRating(),d.getId()); // تحديث التشخيص باستخدام الـ DAO
        } catch (Exception e) {
            throw new RuntimeException(e); // حزم وإلقاء استثناء الوقت الفعلي في حالة الخطأ
        }
    }




// cheack CONSULTATIONSDTO resalt
    @GET
    @Path("/Consultationsditils/{patient_id}")
    public Response getConsultationsBypatiusid (@PathParam("patient_id") int patient_id){
        try {
            List<CONSULTATIONSDTO> consultations = conDAO.selectConsultationsBypatints(patient_id);
            return Response.ok(consultations).build();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return Response.serverError().entity("Failed to retrieve consultations by patient_id.").build();
        }
    }


// select doctor by rating
    @GET
    @Path("/SELECT_doctor_rating/{rating}")
    public Response getratt (@PathParam("rating")int rating){
        try {
            ArrayList<DOCTORSDTO> consultations;
            consultations = ratDao.selectDoctorByavairating(rating);
            return Response.ok(consultations).build();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return Response.serverError().entity("Failed to retrieve doctor rating.").build();
        }
    }

    @GET
    @Path("/SELECT_ALL_doctor_avillabil/{is_available}")
    public Response getdoctoravdet (@PathParam("is_available")boolean is_available){
        try {
            ArrayList<DOCTORSDTO> consultations = schtDao.selectDoctorByavailable(is_available);
            return Response.ok(consultations).build();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return Response.serverError().entity("Failed to retrieve doctor avillabil.").build();
        }
    }

    @GET
    @Path("/selectAll_PATIENT_bynameid")
    public ArrayList<PATIENTSDTO> SELECT_PATIENTSvvb () {
        try {
            return patDao.selectAllPATIENTSnameid();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }








// select doctor avilabel
    @GET
    @Path("/SELECT_Specifice_Doctor_Available/{doctor_id}/{is_available}")
    public Response getdoctorme (@PathParam("doctor_id") int doctor_id,@PathParam("is_available")boolean is_available){
        try {
            ArrayList<SCHEDULESDTO> consultations = schtDao.Select_specifice_doctor(doctor_id,is_available);
            return Response.ok(consultations).build();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return Response.serverError().entity("Failed to retrieve Specifice Doctor Available.").build();
        }
    }

















    @PUT
    @Path("/updatePATIENTTT/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void updateAVAILABPATIENTS(@PathParam("id") int id, PATIENTS d) {
        try {
            d.setId(id);
            patDao.updateAVAIPATIENTS(d.getName(),d.getEmail(),d.getPassword(),d.getPhone(),d.getBirth_date(),d.getId()); // تحديث التشخيص باستخدام الـ DAO
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }



    @GET
    @Path("/SELECT_PATIENT_by_ name/{ name}")
    public Response getdoctorByname (@PathParam("name") String name){
        try {
            ArrayList<PATIENTS> consultations = patDao.SQL_SELECT_PATIENTS_by_name(name);
            return Response.ok(consultations).build();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return Response.serverError().entity("Failed to retrieve PATIENT info.").build();
        }
    }








// select  for  doctor name id or specialties
    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response getAlldoc(
            @BeanParam DoctorFilter filter
    ) {
        try {
            GenericEntity<ArrayList<DOCTORSDTO>> d = new GenericEntity<ArrayList<DOCTORSDTO>>(patDao.selectDoctorBydetailz(filter)) {};
            if(headers.getAcceptableMediaTypes().contains(MediaType.valueOf(MediaType.APPLICATION_XML))) {
                return Response
                        .ok(d)
                        .type(MediaType.APPLICATION_XML)
                        .build();
            }
            return Response
                    .ok()
                    .entity(d)
                    .type(MediaType.APPLICATION_JSON)
                    .build();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }






// select doctor by rating
   // @GET
   // @Path("/SELECT_doctor_byRating/{rating}")
    //public Response getdoctorByrating (@PathParam("rating") int rating){
        //try {
            //ArrayList<RatingsDTO> consultations = ratDao.selectDoctorByRating(rating);
           // return Response.ok(consultations).build();
       // } catch (SQLException | ClassNotFoundException e) {
           // e.printStackTrace();
          //  return Response.serverError().entity("Failed to retrieve doctor with Rating.").build();
      //  }
   // }
// rate doctor
    @POST
    @Path("/rate_the_doctors")
    @Consumes(MediaType.APPLICATION_JSON)
    public void insertRatings (RatingsDTO p){
        try {
            ratDao.insertRatings(p);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


// search aboat MEDICAL_REPORTS
    @GET
    @Path("/SELECT_MEDICAL_REPORTS_by_patient_id/{patient_id}")
    public Response getMEDICAL_REPORTSBypatient_id (@PathParam("patient_id") int patient_id){
        try {
            ArrayList<MEDICAL_REPORTSDTO> s = MedicalDao.selectMEDICAL_REPORTS(patient_id);
            return Response.ok(s).build();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return Response.serverError().entity("Failed to retrieve MEDICAL_REPORTS for patient.").build();
        }
    }






// insert request CONSULTATIONS
    @POST
    @Path("/insert_request_CONSULTATIONS")
    public Response requestAvailabilityCONSULTATIONS(CONSULTATIONSDTO cosltuion) {
        try {
            conDAO.insertrequestCONSULTATIONS(cosltuion);
            return Response.status(Response.Status.CREATED).build();
        } catch (SQLException | ClassNotFoundException e) {
            throw new InternalServerErrorException("Failed to insert request CONSULTATIONS", e);
        }
    }


   /// @GET
   // @Path("/selectAll_PATIENT")
   // public ArrayList<PATIENTS> SELECT_PATIENTS () {
       // try {
           // return patDao.selectAllPATIENTS();
        //} catch (Exception e) {
            //throw new RuntimeException(e);
       // }
    //}

 /// register
    @POST
    @Path("/insert_into_PATIENT")
    @Consumes(MediaType.APPLICATION_JSON)
    public void insertPATIENT (PATIENTS p){
        try {
            patDao.registerPATIENTS(p);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
