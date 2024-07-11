package org.example.filter;

import jakarta.ws.rs.QueryParam;

public class MEDICALFilter {
    @QueryParam("details") String details;
    @QueryParam("patient_id") Integer patient_id;

    public String getDetails() {
        return details;
    }

    public Integer getPatient_id() {
        return patient_id;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public void setPatient_id(Integer patient_id) {
        this.patient_id = patient_id;
    }
}

