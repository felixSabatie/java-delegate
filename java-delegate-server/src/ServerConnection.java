import java.io.IOException;
import java.net.Socket;
import strategies.ByteColl;
import strategies.ObjectColl;
import strategies.SourceColl;
import strategies.Strategy;

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

      try {
        switch (splittedRequest[0]) {
          case "SOURCEColl":
            strategy = new SourceColl(in, reader, writer, splittedRequest);
            break;
          case "BYTEColl":
            strategy = new ByteColl(in, reader, writer, splittedRequest);
            break;
          case "OBJECTColl":
            strategy = new ObjectColl(in, reader, writer, splittedRequest);
            break;
        }

        writer.println("ACK");
        writer.flush();

        System.out.println("Executing request...\n");
        strategy.execute();
      } catch (NumberFormatException e) {
        writer.println("Wrong number format");
        writer.flush();
      } catch (ArrayIndexOutOfBoundsException e) {
        writer.println("Not enough parameters");
        writer.flush();
      } finally {
        System.out.println("Done\n");
        closeConnection();
      }
    } catch (IOException e) {
      System.out.println("Could not read from input stream");
      e.printStackTrace();
      closeConnection();
      System.exit(4);
    }
  }
}
