package strategies;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

public abstract class Strategy {

  protected InputStream in;
  protected BufferedReader reader;
  protected PrintWriter writer;
  protected String[] args;

  public Strategy(InputStream in, BufferedReader reader, PrintWriter writer, String[] args) {
    this.in = in;
    this.reader = reader;
    this.writer = writer;
    this.args = args;
  }

  public abstract void execute() throws IOException;

  protected void receiveFile(String fileName) throws IOException {
    System.out.println("Receiving file...");

    char fileSeparator = File.separator.charAt(0);
    String path = ("./src/delegated/" + fileName).replace('/', fileSeparator);
    File receivingFile = new File(path);
    FileOutputStream fileOutputStream = new FileOutputStream(receivingFile);

    byte[] buffer = new byte[4096];
    int count;
    do {
      count = in.read(buffer);
      fileOutputStream.write(buffer, 0, count);
    } while (count == 4096);
    fileOutputStream.close();

    System.out.println("File received");
  }

}
