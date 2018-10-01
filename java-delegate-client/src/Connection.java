import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public abstract class Connection implements Runnable {

  protected Socket socket;
  protected BufferedReader reader;
  protected PrintWriter writer;
  protected InputStream in;
  protected OutputStream out;

  protected void initStreams() {
    try {
      in = socket.getInputStream();
      out = socket.getOutputStream();
      reader = new BufferedReader(new InputStreamReader(in));
      writer = new PrintWriter(out);
    } catch (IOException e) {
      System.out.println("Error during streams initialization");
      closeConnection();
      System.exit(2);
    }
  }

  protected void closeConnection() {
    try {
      reader.close();
      in.close();
      out.close();
      socket.close();
    } catch (IOException e) {
      System.out.println("Error while closing connections");
      System.exit(5);
    }
  }
}
