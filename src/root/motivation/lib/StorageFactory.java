package root.motivation.lib;

import root.motivation.supplier2.FileStorage;

public class StorageFactory {

  public static Storage storage() {
    Storage s = loadInternally();
    return s;
  }
  
  private static Storage loadInternally() {
    return new FileStorage();
  }
  
}
