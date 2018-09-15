package client.strategies;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;

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
    char fileSeparator = File.separator.charAt(0);
    String path = ("./src/client/" + fileName).replace('/', fileSeparator);
    InputStream fileStream = new FileInputStream(new File(path));
    byte[] buffer = new byte[4096];
    int count;

    while((count = fileStream.read(buffer)) > 0) {
      out.write(buffer, 0, count);
    }
    out.flush();
  }

  protected void sendRequest(String request) throws IOException {
    System.out.println("Sending request...");

    out.write((request + "\n\r").getBytes(Charset.forName("UTF-8")));
    out.flush();

    String response = reader.readLine();
    System.out.println("Message received from server :");
    System.out.println(response);
  }
}
