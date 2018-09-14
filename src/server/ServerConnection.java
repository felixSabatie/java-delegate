package server;

import common.Connection;
import java.io.IOException;
import java.net.Socket;
import java.nio.charset.Charset;
import server.strategies.SourceColl;
import server.strategies.Strategy;

public class ServerConnection extends Connection {

  Strategy strategy;

  public ServerConnection(Socket socket) {
    this.socket = socket;
  }

  @Override
  public void run() {
    initStreams();
    startCommunication();
  }

  private void startCommunication() {
    System.out.println("Waiting for request...");
    try {
      String request = reader.readLine();
      System.out.println("Message received from client :");
      System.out.println(request);

      String[] splittedRequest = request.split(" ");

      switch (splittedRequest[0]) {
        case "SOURCEColl":
          strategy = new SourceColl(in, out, reader, splittedRequest);
          break;
        case "BYTEColl":
          //TODO
          System.out.println("Not implemented");
          break;
        case "OBJECTColl":
          //TODO
          System.out.println("Not implemented");
          break;
      }

      out.write("ACK\n\r".getBytes(Charset.forName("UTF-8")));
      out.flush();

      System.out.println("Executing request...\n");
      strategy.execute();
      System.out.println("Done\n");
    } catch (IOException e) {
      System.out.println("Could not read from input stream");
      closeConnection();
      System.exit(4);
    }
  }
}
