package delegated;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import strategies.Strategy;

public class ObjectColl extends Strategy {

  public ObjectColl(OutputStream out, BufferedReader reader,
      PrintWriter writer) {
    super(out, reader, writer);
  }

  @Override
  public void execute() throws IOException {
    sendRequest("OBJECTColl Calculator add 16 24");
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
