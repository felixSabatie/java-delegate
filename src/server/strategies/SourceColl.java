package server.strategies;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

public class SourceColl extends Strategy {

  public SourceColl(InputStream in, BufferedReader reader, PrintWriter writer,
      String[] args) {
    super(in, reader, writer, args);
  }

  @Override
  public void execute() throws IOException {
    receiveFile("Calculator.java");
    writer.println("12");
    writer.flush();
  }
}
