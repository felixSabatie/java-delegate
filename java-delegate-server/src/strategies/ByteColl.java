package strategies;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;

public class ByteColl extends Strategy {

  public ByteColl(InputStream in, BufferedReader reader, PrintWriter writer,
      String[] args) {
    super(in, reader, writer, args);
  }

  @Override
  public void execute() throws IOException {
    receiveFile(className + ".class");
    try {
      writer.println(getResult());
    } catch (ClassNotFoundException | IllegalAccessException | InstantiationException
        | InvocationTargetException | NoSuchMethodException e) {
      e.printStackTrace();
      writer.println("error");
    } finally {
      writer.flush();
    }
  }
}
