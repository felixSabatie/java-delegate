package strategies;

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
  public void execute() throws IOException {
    sendRequest("BYTEColl Calculator add 16 24");
    sendFile("./out/production/java-delegate-client/delegated/Calculator.class");
    result = reader.readLine();
  }
}
