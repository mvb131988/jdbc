package root.motivation.supplier2;

import root.motivation.lib.Descriptor;

public class FileDescriptor implements Descriptor {

  @Override
  public String get() {
    return "input message is stored in a file";
  }

}
