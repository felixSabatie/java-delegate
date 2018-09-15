package strategies;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

public class SourceColl extends Strategy {

  public SourceColl(InputStream in, BufferedReader reader, PrintWriter writer,
      String[] args) {
    super(in, reader, writer, args);
  }

  @Override
  public void execute() throws IOException {
    receiveFile("Calculator.java");
    try {
      Class receivedClass = loadClass();
      System.out.println(receivedClass);
      writer.println("result");
      writer.flush();
    } catch (ClassNotFoundException e) {
      System.out.println("The class was not found");
      writer.println("error");
      writer.flush();
    }
  }

  private Class loadClass() throws MalformedURLException, ClassNotFoundException {
    char fileSeparator = File.separator.charAt(0);
    String path = ("./src/delegated").replace('/', fileSeparator);
    File file = new File(path);

    URL url = file.toURL();
    ClassLoader classLoader = new URLClassLoader(new URL[]{url});
    return classLoader.loadClass("delegated.Calculator");
  }
}
