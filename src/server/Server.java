package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server implements Runnable {

  private ServerSocket serverSocket;

  public Server() {
    try {
      serverSocket = new ServerSocket(9000);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void run() {
    System.out.println("Listening incoming requests on port: " + serverSocket.getLocalPort());
    while (!Thread.currentThread().isInterrupted()) {
      try {
        Socket socket = serverSocket.accept();
        ServerConnection serverConnection = new ServerConnection(socket);
        new Thread(serverConnection).start();
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }

}
