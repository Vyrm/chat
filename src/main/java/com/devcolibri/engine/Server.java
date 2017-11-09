package com.devcolibri.engine;

import com.devcolibri.handler.MessageHandler;
import com.devcolibri.handler.PrintWriterHandler;
import com.devcolibri.handler.UserHandler;
import com.devcolibri.service.AuthorizationService;
import com.devcolibri.service.MessageService;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;

public class Server {

    public void run() throws IOException, SQLException {
        System.out.println("Server is working!");
        ServerSocket serverSocket = new ServerSocket(8081);
        UserHandler userHandler = new UserHandler();
        PrintWriterHandler printWriterHandler = new PrintWriterHandler();
        MessageHandler messageHandler = new MessageHandler();
        AuthorizationService authorizationService = new AuthorizationService(userHandler, printWriterHandler);
        MessageService messageService = new MessageService(printWriterHandler, messageHandler);

        while (true) {
            Socket socket = serverSocket.accept();
            Worker worker = new Worker(socket, authorizationService, messageService);
            new Thread(worker).start();
        }
    }
}
