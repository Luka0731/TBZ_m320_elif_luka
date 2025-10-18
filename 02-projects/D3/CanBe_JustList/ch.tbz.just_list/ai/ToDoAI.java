package ai;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;
import exception.AIPromptingException;
import exception.DataSaveException;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ToDoAI {

    private static final String API_URL = "http://localhost:11434/api/chat";

    public static String interpret(String userInput) throws Exception {
        String body = """
                {
                  "model": "llama3",
                  "messages": [{"role": "user", "content": "%s"}]
                }
                """.formatted(userInput);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(API_URL))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(body))
                .build();

        HttpClient client = HttpClient.newHttpClient();
        HttpResponse<java.io.InputStream> response = client.send(request, HttpResponse.BodyHandlers.ofInputStream());

        BufferedReader reader = new BufferedReader(new InputStreamReader(response.body()));
        String line;
        StringBuilder fullResponse = new StringBuilder();

        while ((line = reader.readLine()) != null) {
            line = line.trim();
            if (line.isEmpty()) continue;

            // JSON lenient parsen (akzeptiert einzelne Objekte)
            JsonReader jsonReader = new JsonReader(new StringReader(line));
            jsonReader.setLenient(true);
            JsonObject obj = JsonParser.parseReader(jsonReader).getAsJsonObject();

            if (obj.has("message")) {
                JsonObject msg = obj.getAsJsonObject("message");
                if (msg.has("content")) {
                    fullResponse.append(msg.get("content").getAsString());
                }
            }
        }

        return fullResponse.toString();
    }

    public void AskAI(String user_input) {
        try {
            final String ai_answer = interpret(user_input);
            System.out.println(ai_answer);
        } catch (Exception e) {
          throw new AIPromptingException();
        }
    }

}
