package com.dant.entity.distribution;

import com.dant.entity.ResultSet;
import com.dant.filter.GsonProvider;
import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;
import org.jboss.resteasy.spi.ResteasyProviderFactory;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

public class ServiceClient {
    private static final ExecutorService executors = Executors.newFixedThreadPool(2);
    private static final List<String> nodes_Name = new ArrayList<>();
    private static final ResteasyClient client ;

    // Code execute a la compilation
    static {
        ResteasyProviderFactory providerFactory = ResteasyProviderFactory.newInstance();
        providerFactory.registerProvider(GsonProvider.class);
        client = new ResteasyClientBuilder().withConfig(providerFactory).build();

        nodes_Name.add("http://192.168.1.56:8081");
        //nodes_Name.add("ip3");
    }

    public static int getNbNode(){
        return nodes_Name.size();
    }
    public static List<String> getNodes_Name(){
        return nodes_Name;
    }
    public static List<Response> multiPostRequests(String apiEndpoint, String mediaType, Object obj, MultivaluedMap<String, Object> queryParameters) {
        if (obj==null) {
            return null ;
        }

        List<Callable<Response>> callable = new ArrayList<>();
        nodes_Name.forEach(address ->
            callable.add(() -> {
                ResteasyWebTarget target = ServiceClient.client.target(address + apiEndpoint).queryParams(queryParameters);
                return target.request().post(Entity.entity(obj, mediaType));}
            )
        );

        List<Response> listRes = null;
        try{
            listRes = executors.invokeAll(callable).stream().map(
                respAsFuture -> {
                    try {
                        Response res = respAsFuture.get();
                        return res;
                    } catch (Exception e) {
                        e.printStackTrace();
                        return null;
                    }
                }
            ).collect(Collectors.toList());
        } catch (Exception e){
            e.printStackTrace();
        }
        return listRes;
    }
    public static Response singlePostRequest(String fullEndpoint, Object obj, MultivaluedMap<String, Object> queryParameters){
        ResteasyWebTarget target = ServiceClient.client.target(fullEndpoint);
        return target.queryParams(queryParameters).request().post(Entity.entity(obj, "text/plain");
    }
    public static List<Response> multiGetRequests(String apiEndpoint,MultivaluedMap<String, Object> queryParameters) {
        List<Callable<Response>> callable = new ArrayList<>();

        ServiceClient.nodes_Name.forEach(address ->
            callable.add(
                () -> {
                    ResteasyWebTarget target = ServiceClient.client.target(address + apiEndpoint).queryParams(queryParameters);
                    return target.request().get();
                }
            )
        );
        List<Response> listRes = null;
        try{
            listRes = executors.invokeAll(callable).stream().map(
                respAsFuture -> {
                    try {
                        return respAsFuture.get();
                    } catch (Exception e) {
                        e.printStackTrace();
                        return null;
                    }
                }
            ).collect(Collectors.toList());
        } catch (Exception e){
            e.printStackTrace();
        }
        return listRes;
    }


    public static Object readRawResponse(Response response, Class<?> genericClass) throws Exception {
        if(response.getStatus() != 200){
            System.out.println("error with get request " + response.getStatus());
            throw new Exception("Problem with request get") ;
        }
        else{
            response.bufferEntity();
            return response.readEntity(genericClass);
        }
    }
    public static List<Object> readRawResponses(List<Response> responses, Class<?> genericClass) throws Exception {
        if (responses==null) {return null ;}
        List<Object> genericList = new ArrayList<>();

        for (Response response : responses) {
            genericList.add(readRawResponse(response,genericClass)) ;
        }
        return genericList ;
    }
}
