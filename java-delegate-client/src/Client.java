import exceptions.BadRequestException;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;
import strategies.ByteColl;
import strategies.ObjectColl;
import strategies.SourceColl;
import strategies.Strategy;

public class Client extends Connection {

  Scanner keyboardScanner;
  Strategy strategy;

  @Override
  public void run() {
    initSocket();
    initStreams();
    keyboardScanner = new Scanner(System.in);
    int choice = chooseProtocol();
    System.out.println("Enter your request. Usage : add|substract number1 number2. Example : add 10 25");
    String request = keyboardScanner.nextLine();
    switch (choice) {
      case 1:
        strategy = new SourceColl(out, reader, writer, request);
        break;
      case 2:
        strategy = new ByteColl(out, reader, writer, request);
        break;
      case 3:
        strategy = new ObjectColl(out, reader, writer, request);
        break;
    }
    try {
      System.out.println("Executing...\n");
      strategy.execute();
      strategy.printResult();
    } catch (IOException e) {
      System.out.println("An error occured :");
      e.printStackTrace();
    } catch (BadRequestException e) {
      System.out.println("Bad request");
    } finally {
      System.out.println("\nDone");
      closeConnection();
    }
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
    keyboardScanner.nextLine();

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
