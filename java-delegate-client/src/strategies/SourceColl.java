package strategies;

import exceptions.BadRequestException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

public class SourceColl extends Strategy {

  public SourceColl(OutputStream out, BufferedReader reader, PrintWriter writer,
      String request) {
    super(out, reader, writer, request);
  }

  @Override
  public void execute() throws IOException, BadRequestException {
    sendRequest("SOURCEColl Calculator " + request);
    sendFile("./src/delegated/Calculator.java");
    result = reader.readLine();
  }
}
