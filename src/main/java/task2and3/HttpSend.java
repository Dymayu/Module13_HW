package task2and3;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class HttpSend {

    public String establishConnection(String url) throws IOException {

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
        return response.toString();
    }


    public void allUserCommentsToTheLastPost(int userid) throws IOException {

        String url = "https://jsonplaceholder.typicode.com/users/" + userid + "/posts";
        String response = establishConnection(url);
        int getLastPostId = parseLastPostId(response.toString());
        String allComments = getAllComments(getLastPostId);
        writeCommentsToJsonFile(allComments,userid,getLastPostId);

    }

    private int parseLastPostId(String postsJson) {
        JsonArray postsArray = JsonParser.parseString(postsJson).getAsJsonArray();
        int lastPostId = 0;

        for (JsonElement postElement : postsArray) {
            JsonObject postObject = postElement.getAsJsonObject();
            int postId = postObject.get("id").getAsInt();
            if (postId > lastPostId) {
                lastPostId = postId;
            }
        }
        return lastPostId;
    }

    public String getAllComments(int getLastPostId) throws IOException {
        String url = "https://jsonplaceholder.typicode.com/posts/" + getLastPostId + "/comments";
        return establishConnection(url);
    }

    public void writeCommentsToJsonFile(String allComments, int userid, int getLastPostId) throws IOException {

        JsonElement jsonElement = JsonParser.parseString(allComments);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        FileWriter writer = new FileWriter(makeFileName(userid,getLastPostId));
        gson.toJson(jsonElement,writer);
        writer.close();

    }

    public String makeFileName(int userId, int postId){

        String FORMAT = "user-%s-post-%s-comments.json";
        return String.format(FORMAT, userId, postId);

    }

    public void allOpenUserTasks(int userid) throws IOException {
        String url = "https://jsonplaceholder.typicode.com/users/"+userid+"/todos";
        String response = establishConnection(url);
        JsonArray tasksArray = JsonParser.parseString(response).getAsJsonArray();
        for (JsonElement element:tasksArray){
            JsonObject jsonObject = element.getAsJsonObject();
            boolean completed = jsonObject.get("completed").getAsBoolean();
            if (completed){
                System.out.println("element = " + element);
            }
        }
    }
}
