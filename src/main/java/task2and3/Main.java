package task2and3;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {

        HttpSend hp = new HttpSend();
        hp.allUserCommentsToTheLastPost(5);
        hp.allOpenUserTasks(5);

    }


}
