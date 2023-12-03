package task1.dto;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;


public class Main {

    public static void main(String[] args) throws IOException {

        Root user = new Root(99, "Sara Conor", "sara.conor", "sara.conor1234@gmail.com"
                , new Address("Some Street", "Suite 100", "Chesterfild", "1024"
                ,(new Geo("25.4544", "26.2323")))
                , "034-213-234", "www.json.org"
                ,(new Company("BigCorp", "Super Good","enable innovation")));


        //Gson json = new GsonBuilder().setPrettyPrinting().create();
        //String jsonJson = json.toJson(user);
        //System.out.println("jsonJson = " + jsonJson);
        String json = new Gson().toJson(user);

        HttpHandler handler = new HttpHandler(json);
        //handler.httpPost();

        //handler.httpGet();
        handler.sendPOST();
        handler.sendPUT(5, "PUT");
        handler.sendPUT(5, "DELETE");
        handler.sendGEToneUSer(1);
        handler.sendGETallUsers();
        handler.sendGETUserByName("Kamren");


    }



}
