package com.dant.entity.distribution;

import lombok.EqualsAndHashCode;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;

public class Node {
    private Client node;
    private String name;

    public Node(String name){
        this.name = name;
        this.node = ClientBuilder.newClient();
    }

}
