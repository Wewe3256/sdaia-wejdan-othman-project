package org.example.helpers;

import jakarta.inject.Inject;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.Provider;
import org.example.DAO.DoctorDAO;
import org.example.DAO.PATIENTSDAO;
import org.example.Exption.ErrorMessage;
import java.io.IOException;
//import java.util.Base64;
//import java.util.List;
//import java.util.StringTokenizer;
import java.util.*;

@Provider
public class AuthFilter implements ContainerRequestFilter {
    PATIENTSDAO pattDao = new PATIENTSDAO();
    DoctorDAO doctorDAO = new DoctorDAO();
    //انجكت تسوي اوبجكت من الكلاس
  //  @Inject
   // DoctorDAO doctorDAO;
   // @Inject
   // PATIENTSDAO pattDao;

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        if(!requestContext.getUriInfo().getPath().contains("secures")) return;

        List<String> authHeaders = requestContext.getHeaders().get("Authorization");//تاخذ الاوثرايزيشن ك ليست اوف سترينق

        if (authHeaders != null && authHeaders.size() != 0) {
            String authHeader = authHeaders.get(0);
            authHeader = authHeader.replace("Basic ", "");
            authHeader = new String(Base64.getDecoder().decode(authHeader));//ديكود ترجع بايت عسان كذا حطينا سترينق
            StringTokenizer tokenizer = new StringTokenizer(authHeader, ":");
            String user_email = tokenizer.nextToken();
            String user_password = tokenizer.nextToken();

            try {
                if ( doctorDAO.DoctorLogin(user_email,user_password) != null){
                    return;
                } else if (pattDao.PatientsLogin(user_email,user_password)!= null) {
                    return;
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        ErrorMessage err = new ErrorMessage();
        err.setErrorContent("Please login");
        err.setErrorCode(Response.Status.UNAUTHORIZED.getStatusCode());
        err.setDocumentationUrl("https://google.com");

        Response res = Response.status(Response.Status.UNAUTHORIZED)
                .entity(err)
                .build();

        requestContext.abortWith(res);
    }
}