package server.strategies;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class SourceColl extends Strategy {

  public SourceColl(InputStream in, OutputStream out, BufferedReader reader, String[] args) {
    super(in, out, reader, args);
  }

  @Override
  public void execute() throws IOException {
    receiveFile("test.txt");
  }
}
