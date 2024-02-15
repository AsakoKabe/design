package com.example.printer;

import com.example.provider.model.transfer.Transfer;
import com.example.provider.model.transfer.TransferService;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void printRate(){
        TransferService transferService = new TransferService();
        Transfer transfer = transferService.getLastByTimestamp();

        System.out.println(transfer);
    }

    public static void main(String[] args) {

        ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
        executor.scheduleAtFixedRate(() -> {
            try {
                printRate();
            } catch (Exception ignored) {
            }
        }, 0, 30, TimeUnit.SECONDS);
    }
}
