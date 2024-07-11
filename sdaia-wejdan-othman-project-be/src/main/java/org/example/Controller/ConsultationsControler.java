package org.example.Controller;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;
import org.example.DAO.ConsultationsDAO;
import org.example.DAO.DoctorDAO;
import org.example.DTO.CONSULTATIONSDTO;
import org.example.model.CONSULTATIONS;
import org.example.model.DOCTORS;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Path("CONSULTATIONS")
public class ConsultationsControler {
    private final ConsultationsDAO conDAO = new ConsultationsDAO();

    @Context
    UriInfo uriInfo;
    @Context
    HttpHeaders headers;



    @GET
    @Path("/selectAll_CONSULTATIONS")
    public Response getAllconsultations() {
        try {
            ArrayList<CONSULTATIONSDTO> b =conDAO.selectAllofconsltuion();
            return Response.ok(b)
                    .type(MediaType.APPLICATION_JSON)
                    .header("Access-Control-Allow-Origin","*")
                    .header("Access-Control-Allow-Methods","GET")
                    .build();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return Response.serverError().entity("Failed to retrieve CONSULTATIONS info.").build();
        }
    }






      @POST
      @Path("/insert_into_CONSULTATIONS")
      public Response insertAvailabilityCONSULTATIONS(CONSULTATIONSDTO cosltuion) {
          try {
              conDAO.insertAvailabilityCONSULTATIONS(cosltuion);
              return Response.status(Response.Status.CREATED).build();
          } catch (SQLException | ClassNotFoundException e) {
              throw new InternalServerErrorException("Failed to insert CONSULTATIONS", e);
          }
      }


      @PUT
      @Path("/update_CONSULTATIONS/{id}")
      @Consumes(MediaType.APPLICATION_JSON)
      public void updateCONSULTATIONS(@PathParam("id") int id, CONSULTATIONS d) {
          try {
              d.setId(id); // تعيين معرف الاستشارة من المعامل المسار
              conDAO.updateAVAILABILITYCONSULTATIONS(d.getDoctor_id(),d.getPatient_id(),d.getRequest_time(),d.getConsultation_time(),d.getStatus(),d.getDiagnosis(),d.getId()); // تحديث التشخيص باستخدام الـ DAO
          } catch (Exception e) {
              throw new RuntimeException(e); // حزم وإلقاء استثناء الوقت الفعلي في حالة الخطأ
          }
      }



}
