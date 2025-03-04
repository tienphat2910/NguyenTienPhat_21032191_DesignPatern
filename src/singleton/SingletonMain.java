package singleton;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.lang.reflect.Constructor;
import java.io.FileNotFoundException;

public class SingletonMain {
    public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {
        
        // Test for EagerInitializedSingleton
        System.out.println("Testing EagerInitializedSingleton:");
        EagerInitializedSingleton eagerInstanceOne = EagerInitializedSingleton.getInstance();
        EagerInitializedSingleton eagerInstanceTwo = EagerInitializedSingleton.getInstance();
        System.out.println("EagerInitializedSingleton instanceOne hashCode=" + eagerInstanceOne.hashCode());
        System.out.println("EagerInitializedSingleton instanceTwo hashCode=" + eagerInstanceTwo.hashCode());
        System.out.println("Both instances are the same: " + (eagerInstanceOne == eagerInstanceTwo));

        // Test for BillPughSingleton
        System.out.println("\nTesting BillPughSingleton:");
        BillPughSingleton billPughInstanceOne = BillPughSingleton.getInstance();
        BillPughSingleton billPughInstanceTwo = BillPughSingleton.getInstance();
        System.out.println("BillPughSingleton instanceOne hashCode=" + billPughInstanceOne.hashCode());
        System.out.println("BillPughSingleton instanceTwo hashCode=" + billPughInstanceTwo.hashCode());
        System.out.println("Both instances are the same: " + (billPughInstanceOne == billPughInstanceTwo));

        // Test for EnumSingleton
        System.out.println("\nTesting EnumSingleton:");
        EnumSingleton enumInstanceOne = EnumSingleton.INSTANCE;
        EnumSingleton enumInstanceTwo = EnumSingleton.INSTANCE;
        System.out.println("EnumSingleton instanceOne hashCode=" + enumInstanceOne.hashCode());
        System.out.println("EnumSingleton instanceTwo hashCode=" + enumInstanceTwo.hashCode());
        System.out.println("Both instances are the same: " + (enumInstanceOne == enumInstanceTwo));

        // Test for LazyInitializedSingleton
        System.out.println("\nTesting LazyInitializedSingleton:");
        LazyInitializedSingleton lazyInstanceOne = LazyInitializedSingleton.getInstance();
        LazyInitializedSingleton lazyInstanceTwo = LazyInitializedSingleton.getInstance();
        System.out.println("LazyInitializedSingleton instanceOne hashCode=" + lazyInstanceOne.hashCode());
        System.out.println("LazyInitializedSingleton instanceTwo hashCode=" + lazyInstanceTwo.hashCode());
        System.out.println("Both instances are the same: " + (lazyInstanceOne == lazyInstanceTwo));

        // Test for StaticBlockSingleton
        System.out.println("\nTesting StaticBlockSingleton:");
        StaticBlockSingleton staticBlockInstanceOne = StaticBlockSingleton.getInstance();
        StaticBlockSingleton staticBlockInstanceTwo = StaticBlockSingleton.getInstance();
        System.out.println("StaticBlockSingleton instanceOne hashCode=" + staticBlockInstanceOne.hashCode());
        System.out.println("StaticBlockSingleton instanceTwo hashCode=" + staticBlockInstanceTwo.hashCode());
        System.out.println("Both instances are the same: " + (staticBlockInstanceOne == staticBlockInstanceTwo));

        // Test for ThreadSafeSingleton
        System.out.println("\nTesting ThreadSafeSingleton (synchronized):");
        ThreadSafeSingleton threadSafeInstanceOne = ThreadSafeSingleton.getInstance();
        ThreadSafeSingleton threadSafeInstanceTwo = ThreadSafeSingleton.getInstance();
        System.out.println("ThreadSafeSingleton instanceOne hashCode=" + threadSafeInstanceOne.hashCode());
        System.out.println("ThreadSafeSingleton instanceTwo hashCode=" + threadSafeInstanceTwo.hashCode());
        System.out.println("Both instances are the same: " + (threadSafeInstanceOne == threadSafeInstanceTwo));

        // Test for ThreadSafeSingleton with Double-Checked Locking
        System.out.println("\nTesting ThreadSafeSingleton (Double-Checked Locking):");
        ThreadSafeSingleton threadSafeInstanceOneDC = ThreadSafeSingleton.getInstanceUsingDoubleLocking();
        ThreadSafeSingleton threadSafeInstanceTwoDC = ThreadSafeSingleton.getInstanceUsingDoubleLocking();
        System.out.println("ThreadSafeSingleton (Double-Checked Locking) instanceOne hashCode=" + threadSafeInstanceOneDC.hashCode());
        System.out.println("ThreadSafeSingleton (Double-Checked Locking) instanceTwo hashCode=" + threadSafeInstanceTwoDC.hashCode());
        System.out.println("Both instances are the same: " + (threadSafeInstanceOneDC == threadSafeInstanceTwoDC));

        // Test for SerializedSingleton with object serialization
        System.out.println("\nTesting SerializedSingleton:");
        SerializedSingleton serializedInstanceOne = SerializedSingleton.getInstance();

        // Serialize the instance
        ObjectOutput out = new ObjectOutputStream(new FileOutputStream("filename.ser"));
        out.writeObject(serializedInstanceOne);
        out.close();

        // Deserialize the instance
        ObjectInput in = new ObjectInputStream(new FileInputStream("filename.ser"));
        SerializedSingleton serializedInstanceTwo = (SerializedSingleton) in.readObject();
        in.close();

        System.out.println("SerializedSingleton instanceOne hashCode=" + serializedInstanceOne.hashCode());
        System.out.println("SerializedSingleton instanceTwo hashCode=" + serializedInstanceTwo.hashCode());
        System.out.println("Both instances are the same: " + (serializedInstanceOne == serializedInstanceTwo));

        // Test for ReflectionSingletonTest
        System.out.println("\nTesting ReflectionSingletonTest:");
        EagerInitializedSingleton reflectionInstanceOne = EagerInitializedSingleton.getInstance();
        EagerInitializedSingleton reflectionInstanceTwo = null;

        try {
            Constructor[] constructors = EagerInitializedSingleton.class.getDeclaredConstructors();
            for (Constructor constructor : constructors) {
                constructor.setAccessible(true);
                reflectionInstanceTwo = (EagerInitializedSingleton) constructor.newInstance();
                break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("ReflectionSingletonTest instanceOne hashCode=" + reflectionInstanceOne.hashCode());
        System.out.println("ReflectionSingletonTest instanceTwo hashCode=" + reflectionInstanceTwo.hashCode());
    }
}
