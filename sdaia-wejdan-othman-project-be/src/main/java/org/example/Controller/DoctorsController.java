package org.example.Controller;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;
import org.example.DAO.*;
import org.example.DTO.*;
import org.example.filter.MEDICALFilter;
import org.example.model.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

  @Path("doctors")
//  @Produces(MediaType.APPLICATION_JSON)
//  @Consumes(MediaType.APPLICATION_JSON)

public class DoctorsController {
//      private final
      DoctorDAO doctorDAO = new DoctorDAO();
      ConsultationsDAO conDAO = new ConsultationsDAO();
      SCHEDULESDAO schtDao = new SCHEDULESDAO();
      MEDICAL_REPORTSDAO MedicalDao = new MEDICAL_REPORTSDAO();
      RatingsDAO ratDao = new RatingsDAO();
      PATIENTSDAO patDao = new PATIENTSDAO();

      @Context
      UriInfo uriInfo;
      @Context HttpHeaders headers;


      @GET
      @Path("/search_for_MEDICALINFO/")
      @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
      public Response getAllwithdit(
              @BeanParam MEDICALFilter filter
      ) {
          try {
              GenericEntity<ArrayList<MEDICAL_REPORTSDTO>> d = new GenericEntity<ArrayList<MEDICAL_REPORTSDTO>>(MedicalDao.selectDoctorBydetailz(filter)) {};
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





      // just use in front to dsplay some info
      @GET
      @Path("SELECT_doctor_infoall")
      public Response getAllDoctoridnn() {

          try {
              ArrayList<DOCTORS> s= doctorDAO.selectAlldociioo();
              return Response.ok(s)
                      .type(MediaType.APPLICATION_JSON)
                      .header("Access-Control-Allow-Origin","*")
                      .header("Access-Control-Allow-Methods","GET")
                      .build();
          } catch (SQLException | ClassNotFoundException e) {
              e.printStackTrace();
              return Response.serverError().entity("Failed to retrieve doctor info.").build();
          }
      }







      @GET
      @Path("/SELECT_doctorby_id/{doctor_id}")
      public Response get_onedoc_id (@PathParam("doctor_id") int doctor_id){
          try {
              ArrayList<DOCTORSDTO> s = doctorDAO.SELECT_one_doctor_id(doctor_id);
              return Response.ok(s)
                      .type(MediaType.APPLICATION_JSON)
                      .header("Access-Control-Allow-Origin","*")
                      .header("Access-Control-Allow-Methods","GET")
                      .build();
          } catch (SQLException | ClassNotFoundException e) {
              e.printStackTrace();
              return Response.serverError().entity("Failed to retrieve doctor info.").build();
          }
      }











      @GET
      @Path("/SELECT_rating_by_doctor_id/{doctor_id}")
      public Response getratingbyid (@PathParam("doctor_id") int doctor_id){
          try {
              ArrayList<RatingsDTO> s = ratDao.SELECT_rating_BY_doctoridd(doctor_id);
              return Response.ok(s).build();
          } catch (SQLException | ClassNotFoundException e) {
              e.printStackTrace();
              return Response.serverError().entity("Failed to retrieve doctors by rating.").build();
          }
      }





      @PUT
      @Path("/update_CONSULTATIONS/{id}")
      @Consumes(MediaType.APPLICATION_JSON)
      public void updateCONSULTATIONS(@PathParam("id") int id, CONSULTATIONS d) {
          try {
              d.setId(id);
              conDAO.updateAVAILABILITYCONSULTATIONS(d.getDoctor_id(),d.getPatient_id(),d.getRequest_time(),d.getConsultation_time(),d.getStatus(),d.getDiagnosis(),d.getId()); // تحديث التشخيص باستخدام الـ DAO
          } catch (Exception e) {
              throw new RuntimeException(e);
          }
      }




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




      @GET
      @Path("/alldoctorsnameid")
      public ArrayList<DOCTORSDTO> getAllDoctoridn() {

          try {
              return doctorDAO.selectAlldociio();
          } catch (Exception e) {
              throw new RuntimeException(e);
          }
      }





      @GET
      @Path("/all_consultations/{doctor_id}")
      public Response getdoctorconsultations (@PathParam("doctor_id") int doctor_id){
          try {
              ArrayList<CONSULTATIONSDTO> consultations = conDAO.selectDoctorCONSULTATIONS(doctor_id);
              return Response.ok(consultations).build();
          } catch (SQLException | ClassNotFoundException e) {
              e.printStackTrace();
              return Response.serverError().entity("Failed to retrieve doctor with Rating.").build();
          }
      }




      @PUT
      @Path("/update_statas_OF_CONSULTATIONS/{patient_id}")
      @Consumes(MediaType.APPLICATION_JSON)
      public void updatestatas(@PathParam("patient_id") int patient_id, CONSULTATIONS consultation) {
          try {
              consultation.setPatient_id(patient_id); // تعيين معرف الاستشارة من المعامل المسار
              conDAO.updatestatus(consultation.getPatient_id(), consultation.getStatus()); // تحديث التشخيص باستخدام الـ DAO
          } catch (Exception e) {
              throw new RuntimeException(e); // حزم وإلقاء استثناء الوقت الفعلي في حالة الخطأ
          }
      }

      @PUT
      @Path("/updatedoctorinformation/{id}")
      @Consumes(MediaType.APPLICATION_JSON)
      public void updatdocto(@PathParam("id") int id, DOCTORS d) {
          try {
              d.setId(id); // تعيين معرف الاستشارة من المعامل المسار
              doctorDAO.updateAVAILABILITYDOCTOR(d.getName(),d.getSpecialty(),d.getEmail(),d.getPassword(),d.getPhone(),d.getId()); // تحديث التشخيص باستخدام الـ DAO
          } catch (Exception e) {
              throw new RuntimeException(e); // حزم وإلقاء استثناء الوقت الفعلي في حالة الخطأ
          }
      }

      @PUT
      @Path("/update_MEDICAL_REPORTS/{patient_id}")
      @Consumes(MediaType.APPLICATION_JSON)
      public void updateMEDICAL_REPORTS(@PathParam("patient_id") int patient_id, MEDICAL_REPORTS d) {
          try {
              d.setId(patient_id); // تعيين معرف الاستشارة من المعامل المسار
              MedicalDao.update_the_MEDICAL_RECORDS(d.getPatient_id(), d.getDetails(),d.getReport_date(),d.getRating_id()); // تحديث التشخيص باستخدام الـ DAO
          } catch (Exception e) {
              throw new RuntimeException(e); // حزم وإلقاء استثناء الوقت الفعلي في حالة الخطأ
          }
      }




     /// record patients diagonsis
      @PUT
      @Path("/updatediagosis/{patient_id}")
      @Consumes(MediaType.APPLICATION_JSON)
      public void updateDiagnosis(@PathParam("patient_id") int patient_id, CONSULTATIONS consultation) {
          try {
              consultation.setId(patient_id); // تعيين معرف الاستشارة من المعامل المسار
              conDAO.updateDiagnosis(consultation.getPatient_id(), consultation.getDiagnosis()); // تحديث التشخيص باستخدام الـ DAO
          } catch (Exception e) {
              throw new RuntimeException(e); // حزم وإلقاء استثناء الوقت الفعلي في حالة الخطأ
          }
      }

    // manage Schedule
      @PUT
      @Path("/updateschadel/{doctor_id}")
      @Consumes(MediaType.APPLICATION_JSON)
      public void updateAVAILABSchedal(@PathParam("doctor_id") int doctor_id, SCHEDULES d) {
          try {
              d.setId(doctor_id); // تعيين معرف الاستشارة من المعامل المسار
              schtDao.updateAVAILABILITYSCHEDULESS(d.getDoctor_id(),d.getStart_end(),d.getEnd_time(),d.isIs_available()); // تحديث التشخيص باستخدام الـ DAO
          } catch (Exception e) {
              throw new RuntimeException(e); // حزم وإلقاء استثناء الوقت الفعلي في حالة الخطأ
          }
      }


          @GET
          @Path("/allPATIENTS")
          public Response SELECT_PATIENTSvvb () {
              try {
                  ArrayList<PATIENTSDTO> s=patDao.selectAllPATIENTSnameid();
                  return Response.ok(s)
                          .type(MediaType.APPLICATION_JSON)
                          .header("Access-Control-Allow-Origin","*")
                          .header("Access-Control-Allow-Methods","GET")
                          .build();
              } catch (SQLException | ClassNotFoundException e) {
                  e.printStackTrace();
                  return Response.serverError().entity("Failed to retrieve PATIENT info.").build();
              }
          }






/////////// search medical record
      @GET
      @Path("/all_MEDICAL_REPORTS")
      public Response getAll_MEDICAL_REPORTS () {
          try {
              ArrayList<MEDICAL_REPORTS> s=MedicalDao.selectAllMEDICAL_REPORTS();
              return Response.ok(s)
                      .type(MediaType.APPLICATION_JSON)
                      .header("Access-Control-Allow-Origin","*")
                      .header("Access-Control-Allow-Methods","GET")
                      .build();
          } catch (SQLException | ClassNotFoundException e) {
              e.printStackTrace();
              return Response.serverError().entity("Failed to retrieve PATIENT info.").build();
          }
      }

/////////// search medical record to one patient

      @GET
      @Path("/MEDICAL_REPORTS_for_onepatient/{patient_id}")
      public Response getMEDICAL_REPORTSpatient_id (@PathParam("patient_id") int patient_id){
          try {
              ArrayList<MEDICAL_REPORTSDTO> s = MedicalDao.selectMEDICAL_REPORTS(patient_id);
              return Response.ok(s).build();
          } catch (SQLException | ClassNotFoundException e) {
              e.printStackTrace();
              return Response.serverError().entity("Failed to retrieve MEDICAL_REPORTS for patient.").build();
          }
      }




       //check all status consultations requests
          //@GET
          //@Path("/selectby_status_of_Consultations/{status}")
         // public Response getConsultationsByStatus (@PathParam("status") String status){
              //try {
                 // List<CONSULTATIONSDTO> consultations = conDAO.selectConsultationsByStatus(status);
                 // return Response.ok(consultations).build();
             // } catch (SQLException | ClassNotFoundException e) {
               //   e.printStackTrace();
              //    return Response.serverError().entity("Failed to retrieve consultations by status.").build();
            //  }
          //}

      //check all status consultations requests
      @GET
      @Path("/selec_status/{doctor_id}/{status}")
      public Response getdoctormen (@PathParam("doctor_id") int doctor_id,@PathParam("status")String status){
          try {
              ArrayList<CONSULTATIONSDTO> consultations = conDAO.Select_STATAS_doctor(doctor_id,status);
              return Response.ok(consultations).build();
          } catch (SQLException | ClassNotFoundException e) {
              e.printStackTrace();
              return Response.serverError().entity("Failed to retrieve consultations by status.").build();
          }
      }






          // get all available-schedules
          @GET
          @Path("/available-schedules")
          public List<SCHEDULESDTO> getAllsch () {
              try {
                  return schtDao.selectSCHEDULES();
              } catch (Exception e) {
                  throw new RuntimeException(e);
              }
          }



          // get all doctor



          // Register a new doctor
//          @POST
//          @Path("/register")
//          @Consumes(MediaType.APPLICATION_JSON)
//          public void registerDoctor (DOCTORS doctor){
//              try {
//                  doctorDAO.registerDoctor(doctor);
//              } catch (Exception e) {
//                  throw new RuntimeException(e);
//              }
//          }

           @POST
          @Path("/register")
          @Consumes(MediaType.APPLICATION_JSON)
          public Response registerDoctor (DOCTORS doctor){
              try {
                  doctorDAO.registerDoctor(doctor);
                  return Response.ok()
                          .type(MediaType.APPLICATION_JSON)
                          .header("Access-Control-Allow-Origin","*")
                          .header("Access-Control-Allow-Methods","POST")
                          .build();
              } catch (SQLException | ClassNotFoundException e) {
                  throw new InternalServerErrorException("Failed to define availability schedule", e);
              }
          }

           //Define availability schedule for a doctor
            @POST
            @Path("/availabilitySchedalupdate")
             public Response defineAvailabilitySchedule(SCHEDULESDTO schedule) {
              try {
                  schtDao.insertAvailabilitySCHEDULES(schedule);
               return Response.status(Response.Status.CREATED).build();
              } catch (SQLException | ClassNotFoundException e) {
               throw new InternalServerErrorException("Failed to define availability schedule", e);
             }
             }
//////////give CONSULTATIONS to pending request
           @POST
          @Path("/insert_CONSULTATIONS")
          public Response defineAvailabilitycosltution(CONSULTATIONSDTO cosltuion) {
              try {
                  conDAO.insertAvailabilityCONSULTATIONS(cosltuion);
              return Response.status(Response.Status.CREATED).build();
                  } catch (SQLException | ClassNotFoundException e) {
              throw new InternalServerErrorException("Failed to insert CONSULTATIONS", e);
          }
      }

      //defineAvailabilityMEDICAL_RECORDS
      @POST
      @Path("/insert_into_MEDICAL_REPORTS")
      public Response insertAvailabilityMEDICAL_REPORTS(MEDICAL_REPORTSDTO medicalReport) {
          try {
              MedicalDao.insertAvailabilityMEDICAL_REPORTS(medicalReport);
              return Response.status(Response.Status.CREATED).build();
          } catch (SQLException | ClassNotFoundException e) {
              throw new InternalServerErrorException("Failed to insert into MEDICAL_REPORTS", e);
          }
      }


          // Search medical records by keyword
          @GET
          @Path("/searsh/{keyword}")
          @Produces(MediaType.APPLICATION_JSON)
          public Response searchMedicalRecords(@PathParam("keyword") String keyword) {
              try {
                  List<MEDICAL_REPORTS> reports = doctorDAO.searchMedicalRecords(keyword);
                  return Response.ok(reports).build();
              } catch (SQLException | ClassNotFoundException e) {
                  e.printStackTrace();
                  return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
              }
          }

      }
