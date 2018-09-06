package server;

import common.Connection;
import java.io.IOException;
import java.net.Socket;
import java.nio.charset.Charset;

public class ServerConnection extends Connection {

  public ServerConnection(Socket socket) {
    this.socket = socket;
  }

  @Override
  public void run() {
    initStreams();
    basicCommunication();
  }

  private void basicCommunication() {
    System.out.println("Waiting for request...");
    try {
      String request = reader.readLine();
      System.out.println("Message received from client :");
      System.out.println(request);
    } catch (IOException e) {
      System.out.println("Could not read from input stream");
      closeConnection();
      System.exit(4);
    }

    try {
      out.write("Hello client\n\r".getBytes(Charset.forName("UTF-8")));
      out.flush();
    } catch (IOException e) {
      System.out.println("Could not write to output stream");
      closeConnection();
      System.exit(3);
    }
  }
}
