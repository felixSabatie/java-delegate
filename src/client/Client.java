package client;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Client {

  Socket socket;

  public void run() {
    initSocket();
  }

  private void initSocket() {
    Scanner keyboardScanner = new Scanner(System.in);
    int serverPort = getPortFromUser(keyboardScanner);

    try {
      socket = new Socket("127.0.0.1", serverPort);
      System.out.println("Connection established to server");
    } catch (IOException e) {
      System.out.println("Could not connect to server on port " + serverPort);
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
