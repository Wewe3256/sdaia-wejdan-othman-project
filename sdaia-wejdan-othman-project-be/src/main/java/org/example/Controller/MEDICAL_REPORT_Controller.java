package org.example.Controller;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;
import org.example.DAO.DoctorDAO;
import org.example.DAO.MEDICAL_REPORTSDAO;
import org.example.DTO.MEDICAL_REPORTSDTO;
import org.example.filter.MEDICALFilter;
import org.example.model.DOCTORS;
import org.example.model.MEDICAL_REPORTS;

import java.sql.SQLException;
import java.util.ArrayList;
@Path("MEDICAL_REPORTS")
public class MEDICAL_REPORT_Controller {
    private final MEDICAL_REPORTSDAO MedicalDao = new MEDICAL_REPORTSDAO();
    @Context
    UriInfo uriInfo;
    @Context HttpHeaders headers;


    @GET
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
    @GET
    @Path("/all_MEDICAL_REPORTS")
    public Response getAll_MEDICAL_REPORTSs () {
        try {
            ArrayList<MEDICAL_REPORTS> s=MedicalDao.selectAllMEDICAL_REPORTS();
            return Response.ok(s)
                    .type(MediaType.APPLICATION_JSON)
                    .header("Access-Control-Allow-Origin","*")
                    .header("Access-Control-Allow-Methods","GET")
                    .build();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return Response.serverError().entity("Failed to retrieve MEDICAL info.").build();
        }
    }

    @POST
    @Path("/insert_into_MEDICAL_REPORTS")
    public Response insertAvailabilityMEDICAL_REPORTS(MEDICAL_REPORTSDTO medicalReport) {
        try {
            MedicalDao.insertAvailabilityMEDICAL_REPORTS(medicalReport);
            return Response.status(Response.Status.CREATED).build();
        } catch (SQLException | ClassNotFoundException e) {
            throw new InternalServerErrorException("Failed to insert MEDICAL_REPORTS", e);
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


}
