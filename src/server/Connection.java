package server;

import java.net.Socket;

public class Connection implements Runnable {
  Socket socket;

  public Connection(Socket socket) {
    this.socket = socket;
  }

  @Override
  public void run() {
    System.out.println("Executing...");
  }
}
