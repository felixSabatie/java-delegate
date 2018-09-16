package strategies;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;

public class ObjectColl extends Strategy {

  public ObjectColl(InputStream in, BufferedReader reader, PrintWriter writer,
      String[] args) {
    super(in, reader, writer, args);
  }

  @Override
  public void execute() throws IOException {
    try {
      Object object = receiveObject();
      writer.println(getResult(object));
    } catch (ClassNotFoundException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
      writer.println("error");
      e.printStackTrace();
    } finally {
      writer.flush();
    }
  }

  private Object receiveObject() throws IOException, ClassNotFoundException {
    ObjectInputStream objectInputStream = new ObjectInputStream(in);
    return objectInputStream.readObject();
  }

}
