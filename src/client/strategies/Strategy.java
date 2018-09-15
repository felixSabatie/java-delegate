package client.strategies;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;

public abstract class Strategy {

  protected BufferedReader reader;
  protected PrintWriter writer;
  protected OutputStream out;
  protected String result;

  public Strategy(OutputStream out, BufferedReader reader, PrintWriter writer) {
    this.out = out;
    this.reader = reader;
    this.writer = writer;
  }

  public abstract void execute() throws IOException;

  public void printResult() {
    System.out.println("Result : " + result);
  }

  protected void sendFile(String fileName) throws IOException {
    System.out.println("Sending file " + fileName + "...");

    char fileSeparator = File.separator.charAt(0);
    String path = ("./src/client/" + fileName).replace('/', fileSeparator);
    InputStream fileStream = new FileInputStream(new File(path));
    byte[] buffer = new byte[4096];
    int count;

    while ((count = fileStream.read(buffer)) > 0) {
      out.write(buffer, 0, count);
    }
    out.flush();

    System.out.println("File sent");
  }

  protected void sendRequest(String request) throws IOException {
    System.out.println("Sending request...");

    writer.println(request);
    writer.flush();

    String response = reader.readLine();
    System.out.println("Message received from server :");
    System.out.println(response);
  }
}
