package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.Charset;

public class Connection implements Runnable {
  Socket socket;
  InputStream in;
  OutputStream out;
  BufferedReader reader;

  public Connection(Socket socket) {
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

  private void initStreams() {
    try {
      in = socket.getInputStream();
      out = socket.getOutputStream();
      reader = new BufferedReader(new InputStreamReader(in));
    } catch (IOException e) {
      System.out.println("Error during streams initialization");
      closeConnection();
      System.exit(2);
    }
  }

  private void closeConnection() {
    try {
      reader.close();
      in.close();
      out.close();
      socket.close();
    } catch (IOException e) {
      System.out.println("Error while closing connections");
      System.exit(5);
    }
  }
}
