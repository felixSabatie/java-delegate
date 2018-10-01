package strategies;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;

public class SourceColl extends Strategy {


  public SourceColl(InputStream in, BufferedReader reader, PrintWriter writer,
      String[] args) {
    super(in, reader, writer, args);
  }

  @Override
  public void execute() throws IOException {
    receiveFile(className + ".java");
    try {
      compileClass();
      writer.println(getResult());
    } catch (ClassNotFoundException | IllegalAccessException | InstantiationException
        | NoSuchMethodException | InvocationTargetException e) {
      e.printStackTrace();
      writer.println("error");
    } finally {
      writer.flush();
    }
  }

  private void compileClass() {
    char fileSeparator = File.separator.charAt(0);
    String filePath = ("./from-client/delegated/" + className + ".java")
        .replace('/', fileSeparator);
    File classFile = new File(filePath);

    JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
    int returnCode = compiler.run(null, null, null, classFile.getPath());
    System.out.println("Class compiled with code " + returnCode);
  }
}
