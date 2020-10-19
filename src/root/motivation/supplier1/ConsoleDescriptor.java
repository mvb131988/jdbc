package root.motivation.supplier1;

import root.motivation.lib.Descriptor;

public class ConsoleDescriptor implements Descriptor {

  @Override
  public String get() {
    return "input message is sent to console output";
  }

}
