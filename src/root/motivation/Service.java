package root.motivation;

import root.motivation.lib.Descriptor;
import root.motivation.lib.Storage;
import root.motivation.lib.StorageFactory;

public class Service {

  public void save(String str) {
    Storage s = StorageFactory.storage();
    s.save(str);
    
    Descriptor d = s.getDescriptor();
    System.out.println(d.get());
  }
  
}
