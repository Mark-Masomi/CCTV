package org.example;

import org.json.JSONObject;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Main {
    public static void main(String[] args) {

        try{

            // Create an HttpClient instance
            HttpClient client = HttpClient.newHttpClient();

            // Create the API request to ipinfo.io
            HttpRequest requeast= HttpRequest.newBuilder()
                    .uri(new URI("http://ipinfo.io/json")).build();

            //send request and get response
            HttpResponse<String> response =client.send(requeast,HttpResponse.BodyHandlers.ofString());


            JSONObject json=new JSONObject(response.body());

            String ip=json.getString("ip");
            String city=json.getString("city");
            String region=json.getString("region");
            String country=json.getString("country");
            String location=json.getString("loc");

            System.out.println("IP Address: " + ip);
            System.out.println("City: " + city);
            System.out.println("Region: " + region);
            System.out.println("Country: " + country);
            System.out.println("Location (lat, long): " + location);

        }catch (Exception e){
            System.out.println("Error: " + e.getMessage());
        }

        }

}