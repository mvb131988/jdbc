package root.motivation.supplier1;

import root.motivation.lib.Descriptor;
import root.motivation.lib.Storage;

public class ConsoleStorage implements Storage {

  @Override
  public void save(String s) {
    System.out.println(s);
  }

  @Override
  public Descriptor getDescriptor() {
    return new ConsoleDescriptor();
  }

}
