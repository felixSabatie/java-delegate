package strategies;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

public abstract class Strategy {

  protected InputStream in;
  protected BufferedReader reader;
  protected PrintWriter writer;
  protected String className;
  protected String methodName;
  protected int firstParam;
  protected int secondParam;

  public Strategy(InputStream in, BufferedReader reader, PrintWriter writer, String[] args) {
    this.in = in;
    this.reader = reader;
    this.writer = writer;
    this.className = args[1];
    this.methodName = args[2];
    this.firstParam = Integer.valueOf(args[3]);
    this.secondParam = Integer.valueOf(args[4]);
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

  protected Class loadClass() throws MalformedURLException, ClassNotFoundException {
    String folderPath = ("./src/delegated/").replace('/', File.separator.charAt(0));
    File folderFile = new File(folderPath);

    URLClassLoader classLoader = URLClassLoader.newInstance(new URL[]{folderFile.toURL()});
    return Class.forName("delegated." + className, true, classLoader);
  }

  protected int getResult()
      throws MalformedURLException, ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
    Class cls = loadClass();
    Object instance = cls.newInstance();

    return (int) cls.getDeclaredMethod(methodName, new Class[]{int.class, int.class})
        .invoke(instance, firstParam, secondParam);
  }

}
