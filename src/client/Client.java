package client;

import common.Connection;
import java.io.IOException;
import java.net.Socket;
import java.nio.charset.Charset;
import java.util.Scanner;

public class Client extends Connection {

//  https://stackoverflow.com/questions/6219829/method-to-dynamically-load-java-class-files
//  https://stackoverflow.com/questions/2946338/how-do-i-programmatically-compile-and-instantiate-a-java-class

  @Override
  public void run() {
    initSocket();
    initStreams();
    sendBasicRequest();
  }

  private void sendBasicRequest() {
    System.out.println("Sending basic request...");
    
    try {
      out.write("test\n\r".getBytes(Charset.forName("UTF-8")));
      out.flush();
    } catch (IOException e) {
      System.out.println("Could not write to output stream");
      closeConnection();
      System.exit(3);
    }

    try {
      String response = reader.readLine();
      System.out.println("Message received from server :");
      System.out.println(response);
    } catch (IOException e) {
      System.out.println("Could not read from input stream");
      closeConnection();
      System.exit(4);
    }
  }

  private void initSocket() {
    Scanner keyboardScanner = new Scanner(System.in);
    int serverPort = getPortFromUser(keyboardScanner);

    try {
      socket = new Socket("127.0.0.1", serverPort);
      System.out.println("Connection established to server\n");
    } catch (IOException e) {
      System.out.println("Could not connect to server on port " + serverPort);
      closeConnection();
      System.exit(1);
    }
  }

  private int getPortFromUser(Scanner keyboardScanner) {
    int port;
    boolean portIsValid;

    System.out.println("Enter the server port");
    do {
      port = getNumberFromUser(keyboardScanner);
      portIsValid = port >= 0 && port <= 65535;
      if(!portIsValid) {
        System.out.println("Please enter a valid port");
      }
    } while(!portIsValid);

    return port;
  }

  private int getNumberFromUser(Scanner keyboardScanner) {
    while(!keyboardScanner.hasNextInt()) {
      System.out.println("Please enter a number");
      keyboardScanner.nextLine();
    }
    return keyboardScanner.nextInt();
  }

}
