public class StructureMethods {
  public static void main(String[] args) {
    NativeMethods nativeMethods = new NativeMethods();
    long pointer = nativeMethods.allocateStructure();
    nativeMethods.getStructureValue(pointer);
    nativeMethods.freeStructure(pointer);
  }
}
