package org.example.Controller;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;
import org.example.DAO.RatingsDAO;
import org.example.DAO.SCHEDULESDAO;
import org.example.DTO.SCHEDULESDTO;
import org.example.filter.DoctorFilter;
import org.example.filter.MEDICALFilter;
import org.example.model.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
@Path("SCHEDULES")
public class SCHEDULESController {
    private final SCHEDULESDAO schtDao = new SCHEDULESDAO();

    @Context
    UriInfo uriInfo;
    @Context HttpHeaders headers;

    @GET
    @Path("/doctorsditils/{doctor_id}")
    public Response getConsultationsBySSCHEDULES (@PathParam("doctor_id") int doctor_id){
        try {
            List<SCHEDULESDTO> consultations = schtDao.selectConsultationsBySCHEDULESsss(doctor_id);
            return Response.ok(consultations).build();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return Response.serverError().entity("Failed to retrieve SCHEDULES by doctor_id.").build();
        }
    }



    @GET
    @Path("/selectAll_SCHEDULES")
    public Response getAllSCHEDULES () {
        try {
            List<SCHEDULESDTO> s=schtDao.selectSCHEDULES();
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
    @Path("/insert_into_SCHEDULES")
    @Consumes(MediaType.APPLICATION_JSON)
    public void insertSCHEDULES ( SCHEDULESDTO p){
        try {
            schtDao.insertAvailabilitySCHEDULES(p);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

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

    @DELETE
    @Path("/delete_SCHEDULES/{id}")
    public void deleteSCHEDULES(@PathParam("id") int id) {

        try {
            schtDao.DeleteSCHEDULESS(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
