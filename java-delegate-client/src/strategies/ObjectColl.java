package strategies;

import delegated.Calculator;
import exceptions.BadRequestException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;

public class ObjectColl extends Strategy {

  public ObjectColl(OutputStream out, BufferedReader reader, PrintWriter writer,
      String request) {
    super(out, reader, writer, request);
  }

  @Override
  public void execute() throws IOException, BadRequestException {
    sendRequest("OBJECTColl Calculator " + request);
    sendObject(new Calculator());
    result = reader.readLine();
  }

  private void sendObject(Object object) throws IOException {
    System.out.println("Sending object...");

    ObjectOutputStream objectOutputStream = new ObjectOutputStream(out);
    objectOutputStream.writeObject(object);
    objectOutputStream.flush();

    System.out.println("File sent");
  }
}
