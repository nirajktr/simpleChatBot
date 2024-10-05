package com.example;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class ChatbotGPT {
    private static final String API_KEY = "API_KEY"; // Replace with your OpenAI API key
    private static final String API_URL = "API_URL"; // Replace with the OpenAI API URL

    public static String getGPTResponse(String prompt) throws Exception {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost post = new HttpPost(API_URL);
        post.setHeader("Content-Type", "application/json");
        post.setHeader("Authorization", "Bearer " + API_KEY);

        // Enhanced parameters for better responses
        String json = "{\"model\": \"text-davinci-003\", " +
                      "\"prompt\": \"" + prompt + "\", " +
                      "\"max_tokens\": 150, " +
                      "\"temperature\": 0.7, " +
                      "\"top_p\": 0.9, " +
                      "\"frequency_penalty\": 0.2, " +
                      "\"presence_penalty\": 0.3}";

        StringEntity entity = new StringEntity(json);
        post.setEntity(entity);

        HttpResponse response = httpClient.execute(post);
        String result = EntityUtils.toString(response.getEntity());

        // Parsing response (Optional: You could extract only the text part)
        return result;
    }

    public static void main(String[] args) throws Exception {
        String prompt = "Hello, how can I assist you today?";
        String response = getGPTResponse(prompt);
        System.out.println(response);
    }
}
