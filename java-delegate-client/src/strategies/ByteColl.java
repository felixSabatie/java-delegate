package strategies;

import exceptions.BadRequestException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

public class ByteColl extends Strategy {

  public ByteColl(OutputStream out, BufferedReader reader,
      PrintWriter writer) {
    super(out, reader, writer);
  }

  @Override
  public void execute() throws IOException, BadRequestException {
    sendRequest("BYTEColl Calculator add 10 24");
    sendFile("./to-server/Calculator.class");
    result = reader.readLine();
  }
}
