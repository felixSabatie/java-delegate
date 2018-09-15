package server.strategies;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public abstract class Strategy {
  protected InputStream in;
  protected OutputStream out;
  protected BufferedReader reader;
  protected String[] args;

  public Strategy(InputStream in, OutputStream out, BufferedReader reader, String[] args) {
    this.in = in;
    this.out = out;
    this.reader = reader;
    this.args = args;
  }

  public abstract void execute() throws IOException;

  protected void receiveFile(String fileName) throws IOException {
    File receivingFile = new File("./src/server/client/" + fileName);
    FileOutputStream fileOutputStream = new FileOutputStream(receivingFile);

    byte[] buffer = new byte[4096];
    int count;
    while((count = in.read(buffer)) > 0) {
      fileOutputStream.write(buffer, 0, count);
    }
  }

}
