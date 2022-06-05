package com.dant.entity.distribution;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class ServiceClient {
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
}
