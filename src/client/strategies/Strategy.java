package client.strategies;

import client.Client;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public abstract class Strategy {
  protected InputStream in;
  protected OutputStream out;
  protected BufferedReader reader;
  protected String result;

  public Strategy(InputStream in, OutputStream out, BufferedReader reader) {
    this.in = in;
    this.out = out;
    this.reader = reader;
  }

  public abstract void execute() throws IOException;

  public void printResult() {
    System.out.println("Result : " + result);
  }

  protected void sendFile(String fileName) throws IOException {
    InputStream fileStream = Client.class.getResourceAsStream(fileName);
    byte[] buffer = new byte[4096];
    int count;

    while((count = fileStream.read(buffer)) > 0) {
      out.write(buffer, 0, count);
    }
    out.flush();
  }
}
