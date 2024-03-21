package com.example.printer;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;


@Service
public class PrinterService {

    public static void getRequest(String uri) throws Exception {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(uri))
                .build();

        HttpResponse<String> response =
                client.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println(response.body());
    }

    @Scheduled(fixedRate = 5000)
    public void print() throws Exception {
        String uri = "http://127.0.0.1:8000/rate/USD/RUB";
        getRequest(uri);
    }

//    public static void main(String[] args) {
//        String uri = "http://127.0.0.1:8080/rate/USD/RUB";
//
//        ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
//        executor.scheduleAtFixedRate(() -> {
//            try {
//                getRequest(uri);
//            } catch (Exception ignored) {
//            }
//        }, 0, 5, TimeUnit.SECONDS);

//    }

}
