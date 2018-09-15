package client.strategies;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class SourceColl extends Strategy {

  public SourceColl(InputStream in, OutputStream out, BufferedReader reader) {
    super(in, out, reader);
  }

  @Override
  public void execute() throws IOException {
    sendRequest("SOURCEColl");
    sendFile("Calculator.java");
  }
}
