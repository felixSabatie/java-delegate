package strategies;

import exceptions.BadRequestException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

public class ByteColl extends Strategy {

  public ByteColl(OutputStream out, BufferedReader reader, PrintWriter writer,
      String request) {
    super(out, reader, writer, request);
  }

  @Override
  public void execute() throws IOException, BadRequestException {
    sendRequest("BYTEColl Calculator " + request);
    sendFile("./to-server/Calculator.class");
    result = reader.readLine();
  }
}
