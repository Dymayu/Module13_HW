package task1.dto;
import com.google.gson.Gson;
import org.jsoup.Connection;
import org.jsoup.Jsoup;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.*;
import java.net.http.HttpClient;

public class HttpHandler {

    private final String json;

    public HttpHandler(String json) {
        this.json = json;
    }

    public String getJson() {
        return json;
    }

    public void sendPOST() throws IOException {
        String url = "https://jsonplaceholder.typicode.com/users";

        HttpURLConnection connection = (HttpURLConnection)new URL(url).openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setDoOutput(true);

        OutputStream os = connection.getOutputStream();
        byte[] input = getJson().getBytes();
        os.write(input);
        os.flush();
        os.close();

        int responseCode = connection.getResponseCode();
        System.out.println("POST responseCode: " + responseCode);
        if (responseCode == HttpURLConnection.HTTP_CREATED) {
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuffer response = new StringBuffer();
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            System.out.println(response.toString());
        } else {
            System.out.println("POST request not worked");
        }
    }
    public void httpGet() throws IOException {
        URL url = new URL("http://example.com");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        //con.setRequestMethod("GET");

    }
    public void httpPost() throws IOException {

        String url = "https://jsonplaceholder.typicode.com/users";

        Connection.Response execute = Jsoup.connect(url)
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .ignoreContentType(true)
                .method(Connection.Method.POST)
                .requestBody(getJson())
                .execute();
    }

    public void sendPUT(int id, String method) throws IOException {

        String url = "https://jsonplaceholder.typicode.com/users/"+id;

        HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
        connection.setRequestMethod(method);
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setDoOutput(true);

        OutputStream os = connection.getOutputStream();
        byte[] input = getJson().getBytes();
        os.write(input);
        os.flush();
        os.close();

        int responseCode = connection.getResponseCode();
        System.out.println("POST responseCode: " + responseCode);

            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuffer response = new StringBuffer();
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            System.out.println(response.toString());

    }

    public void sendGEToneUSer(int id) throws IOException {

        String url = "https://jsonplaceholder.typicode.com/users/"+id;

        HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Content-Type", "application/json");

        int responseCode = connection.getResponseCode();
        System.out.println("GET responseCode: " + responseCode);

        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        System.out.println(response.toString());

    }

    public void sendGETallUsers() throws IOException {

        String url = "https://jsonplaceholder.typicode.com/users";

        HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Content-Type", "application/json");

        int responseCode = connection.getResponseCode();
        System.out.println("GET responseCode: " + responseCode);

        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        System.out.println(response.toString());

    }

    public void sendGETUserByName(String name) throws IOException {

        String url = "https://jsonplaceholder.typicode.com/users?username="+name;

        HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Content-Type", "application/json");

        int responseCode = connection.getResponseCode();
        System.out.println("GET responseCode: " + responseCode);

        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        System.out.println(response.toString());

    }





}
