package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server implements Runnable {

  private ServerSocket serverSocket;

  public Server() {
    try {
      serverSocket = new ServerSocket(0);
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
        Connection connection = new Connection(socket);
        new Thread(connection).start();
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }

}
