package com.trung;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;
import request.Handler;
import request.Util;

public class HttpServer {
    private static List<Integer> threadRegistry;

    private int getCurrentNewThreadId() {
        return threadRegistry.size();
    }

    public static void main(String[] args) throws IOException {
        final String PORT = System.getenv().getOrDefault("SERVER_PORT", "8080");

        ServerSocket serverSocket = new ServerSocket(Integer.valueOf(PORT));

        threadRegistry = new ArrayList<>();

        //hashMap with the files stored in the server
        HashMap<String, String> contentDirectory = Util.getContentDirectory("directory");

        while (true)
        {
            Handler taskHandler = new Handler(
                serverSocket.accept(),
                id, contentDirectory, idRegistry
            );

            ++id;

            taskSolver.start();
        }
    }

}