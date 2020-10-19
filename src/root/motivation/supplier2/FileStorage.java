package root.motivation.supplier2;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import root.motivation.lib.Descriptor;
import root.motivation.lib.Storage;

public class FileStorage implements Storage {

  @Override
  public void save(String s) {
    BufferedWriter writer;
    try {
      writer = new BufferedWriter(new FileWriter("file_storage.txt"));
      writer.write(s);
      writer.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public Descriptor getDescriptor() {
    return new FileDescriptor();
  }

}
