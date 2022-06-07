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

        nodes_Name.add("ip2");
        nodes_Name.add("ip3");
    }

    public static int getNbNode(){
        return nodes_Name.size();
    }
    public static List<String> getNodes_Name(){
        return nodes_Name;
    }
    public static List<Response> multiPostRequests(String apiEndpoint, String mediaType, Object body, MultivaluedMap<String, Object> queryParameters) {
        if (body==null) {
            return null ;
        }

        List<Callable<Response>> callable = new ArrayList<>();
        nodes_Name.forEach(address ->
            callable.add(
                () -> {
                    ResteasyWebTarget target = ServiceClient.client.target(address + apiEndpoint).queryParams(queryParameters);
                    return target.request(mediaType).post(Entity.json(body));
                }
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
    public static Response singlePostRequest(String fullEndpoint, Object body, MultivaluedMap<String, Object> queryParameters){
        ResteasyWebTarget target = ServiceClient.client.target(fullEndpoint);
        return target.queryParams(queryParameters).request().post(Entity.entity(body, "application/json"));
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


    /*
    private Client c1;
    private Client c2;

    private final List<Node> nodes = new CopyOnWriteArrayList<>();

    public ServiceClient(){
        c1 = ClientBuilder.newClient();
        c2 = ClientBuilder.newClient();
    }
    @Override
    public String toString() {
        return "ServiceClient{" +
                "c1=" + c1 +
                ", c2=" + c2 +
                '}';
    }
    public Client getC1() {return c1;}
    public Client getC2() {return c2;}

    public String clientGET(String ip1, String ip2, String uri){
        String res = c1.target("https://"+ip1+":"+"8080"+uri).request(MediaType.APPLICATION_JSON).get(String.class);
        res += c2.target("https://"+ip2+":"+"8080"+uri).request(MediaType.APPLICATION_JSON).get(String.class);
        return res;
    }
    public void clientPUT(String ip1, String ip2, String uri, Object obj){
        c1.target("https://"+ip1+":"+"8080"+uri).request(MediaType.APPLICATION_JSON).put(Entity.json(obj));
        c2.target("https://"+ip2+":"+"8080"+uri).request(MediaType.APPLICATION_JSON).put(Entity.json(obj));
    }
    public void clientPOST(String ip1, String ip2, String uri, Object obj){
        c1.target("https://"+ip1+":"+"8080"+uri).request(MediaType.APPLICATION_JSON).post(Entity.json(obj));
        c2.target("https://"+ip2+":"+"8080"+uri).request(MediaType.APPLICATION_JSON).post(Entity.json(obj));
    }
    public void closeClients(){
        c1.close();
        c2.close();
    }
    */
}
