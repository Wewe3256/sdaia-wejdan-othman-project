package org.example.helpers;

import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerResponseContext;
import jakarta.ws.rs.container.ContainerResponseFilter;
import jakarta.ws.rs.core.MultivaluedMap;

import java.io.IOException;

public class CORSFilter implements ContainerResponseFilter {
    @Override
    public void filter(ContainerRequestContext containerRequestContext, ContainerResponseContext containerResponseContext) throws IOException {

        MultivaluedMap<String, Object> headers = containerResponseContext.getHeaders();
        headers.add("Access-Control-Allow-Origin", "*");
        headers.add("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE");
        headers.add("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
    }
}
