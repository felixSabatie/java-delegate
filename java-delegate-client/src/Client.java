import strategies.SourceColl;
import strategies.Strategy;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Client extends Connection {

  Scanner keyboardScanner;
  Strategy strategy;

//  https://stackoverflow.com/questions/6219829/method-to-dynamically-load-java-class-files
//  https://stackoverflow.com/questions/2946338/how-do-i-programmatically-compile-and-instantiate-a-java-class

  @Override
  public void run() {
    initSocket();
    initStreams();
    keyboardScanner = new Scanner(System.in);
    int choice = chooseProtocol();
    switch (choice) {
      case 1:
        strategy = new SourceColl(out, reader, writer);
        break;
      case 2:
        //TODO
        System.out.println("Not implemented");
        break;
      case 3:
        //TODO
        System.out.println("Not implemented");
        break;
    }
    try {
      System.out.println("Executing...\n");
      strategy.execute();
      strategy.printResult();
    } catch (IOException e) {
      System.out.println("An error occured :");
      e.printStackTrace();
    }

    System.out.println("\nDone");
  }

  private void initSocket() {
    int serverPort = 9000;

    try {
      socket = new Socket("127.0.0.1", serverPort);
      System.out.println("Connection established to server\n");
    } catch (IOException e) {
      System.out.println("Could not connect to server on port " + serverPort);
      closeConnection();
      System.exit(1);
    }
  }

  private int chooseProtocol() {
    System.out.println("Choose the protocol you want to use for the delegation :");
    System.out.println("1: SOURCEColl");
    System.out.println("2: BYTEColl");
    System.out.println("3: OBJECTColl");
    int choice;
    boolean choiceIsValid;
    do {
      choice = getNumberFromUser();
      choiceIsValid = choice >= 1 && choice <= 3;
      if (!choiceIsValid) {
        System.out.println("Please enter a valid choice");
      }
    } while (!choiceIsValid);

    return choice;
  }

  private int getNumberFromUser() {
    while (!keyboardScanner.hasNextInt()) {
      System.out.println("Please enter a number");
      keyboardScanner.nextLine();
    }
    return keyboardScanner.nextInt();
  }

}