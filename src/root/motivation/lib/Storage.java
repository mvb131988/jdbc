package root.motivation.lib;

public interface Storage {

  void save(String s);
  
  Descriptor getDescriptor();
  
}
