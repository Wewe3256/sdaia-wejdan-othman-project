package org.example.Controller;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;
import org.example.DAO.MEDICAL_REPORTSDAO;
import org.example.DAO.RatingsDAO;
import org.example.DTO.RatingsDTO;
import org.example.model.CONSULTATIONS;
import org.example.model.Ratings;

import java.sql.SQLException;
import java.util.ArrayList;
@Path("RATINGS")
public class RatingsController {
    private final RatingsDAO ratDao = new RatingsDAO();
    @Context
    UriInfo uriInfo;
    @Context
    HttpHeaders headers;

    @GET
    @Path("/selectAll_RATINGS")
    public Response getAllRatings () {
        try {
            ArrayList<RatingsDTO> s= ratDao.selectRatings();
            return Response.ok(s)
                    .type(MediaType.APPLICATION_JSON)
                    .header("Access-Control-Allow-Origin","*")
                    .header("Access-Control-Allow-Methods","GET")
                    .build();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return Response.serverError().entity("Failed to retrieve SCHEDULES info.").build();
        }
    }

    @POST
    @Path("/insert_into_Ratings")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response insertRatings (RatingsDTO p){
        try {
            ratDao.insertRatings(p);
            return Response
                    .ok()
                    .type(MediaType.APPLICATION_JSON)
                    .header("Access-Control-Allow-Origin","*")
                    .header("Access-Control-Allow-Methods","POST, OPTIONS")
                    .build();
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




}
