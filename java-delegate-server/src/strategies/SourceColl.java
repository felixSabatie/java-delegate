package strategies;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;

public class SourceColl extends Strategy {

  public SourceColl(InputStream in, BufferedReader reader, PrintWriter writer,
      String[] args) {
    super(in, reader, writer, args);
  }

  @Override
  public void execute() throws IOException {
    receiveFile("Calculator.java");
    try {
      compileClass();
      writer.println("result");
    } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
      e.printStackTrace();
      writer.println("error");
    } finally {
      writer.flush();
    }
  }

  private void compileClass()
      throws MalformedURLException, ClassNotFoundException, IllegalAccessException, InstantiationException {
    char fileSeparator = File.separator.charAt(0);
    String path = ("./src/delegated/Calculator.java").replace('/', fileSeparator);
    File classFile = new File(path);

    String folderPath = ("./src/delegated").replace('/', fileSeparator);
    File folder = new File(folderPath);

    // Compile source file.
    JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
    compiler.run(null, null, null, classFile.getPath());

    // Load and instantiate compiled class.
    URLClassLoader classLoader = URLClassLoader.newInstance(new URL[] { folder.toURL()});
    Class<?> cls = Class.forName("delegated.Calculator", true, classLoader);
    Object instance = cls.newInstance();
    System.out.println(instance);
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
