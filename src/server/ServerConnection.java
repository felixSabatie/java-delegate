package server;

import common.Connection;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.nio.charset.Charset;

public class ServerConnection extends Connection {

  public ServerConnection(Socket socket) {
    this.socket = socket;
  }

  @Override
  public void run() {
    initStreams();
    basicFileReceiver();
  }

  private void basicFileReceiver() {
    File receivingFile = new File("./received-files/test.txt");
    try {
      FileOutputStream fileOutputStream = new FileOutputStream(receivingFile);

      byte[] buffer = new byte[4096];
      int count;
      try {
        while((count = in.read(buffer)) > 0) {
          fileOutputStream.write(buffer, 0, count);
        }
      } catch (Exception e) {
        System.out.println("Error while receiving and writing file");
        closeConnection();
      }
    } catch (FileNotFoundException e) {
      System.out.println("Couldn't open file stream");
      closeConnection();
    }
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
