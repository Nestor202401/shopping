package com.users.service;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private static final String API_KEY = "";

    public void sendVerificationEmail(String userEmail, String verificationCode) {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpPost request = new HttpPost("https://api.sendinblue.com/v3/smtp/email");
            request.addHeader("api-key", API_KEY);
            request.addHeader("Content-Type", "application/json");

            String payload = "{"
                    + "\"sender\":{\"name\":\"Your Name\",\"email\":\"aasneen@gmail.com\"},"
                    + "\"to\":[{\"email\":\"" + userEmail + "\"}],"
                    + "\"subject\":\"Email Verification\","
                    + "\"htmlContent\":\"Please verify your email using the following link: "
                    + "<a href='localhost:8080/users/verify?code=" + verificationCode + "'>Verify</a>\""
                    + "}";

            request.setEntity(new StringEntity(payload));
            CloseableHttpResponse response = httpClient.execute(request);

            String responseBody = EntityUtils.toString(response.getEntity());
            System.out.println("Response: " + responseBody);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error while sending email: " + e.getMessage());
        }
    }
}
