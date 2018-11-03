package com.github.http;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;

public class App {

    public static void main(String[] args) throws Exception {


        HttpResponse<JsonNode> httpResponse = Unirest.post("http://httpbin.org/post")
                .queryString("name", "Mark")
                .field("last", "Polo")
                .asJson();


        String json = httpResponse.getBody().toString();

        System.out.println(json);
    }
}
