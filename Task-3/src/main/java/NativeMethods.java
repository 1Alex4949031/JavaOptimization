public class NativeMethods {
  static {
    System.loadLibrary("NativeMethods");
  }
  public native void crashMemory();

  public native long allocateMemory();

  public native void crashWithStackTrace();

  public native int getStringLength(String str);

  public native void callObjectMethod(Object object);

  public native void changeObjectField(Object object);

  public native long allocateStructure();

  public native long getStructureValue(long pointer);

  public native void freeStructure(long pointer);
}
