package com.vilin.atomic;

import sun.misc.Unsafe;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.util.HashSet;
import java.util.Set;

public class UnsafeFooTest {

    public static void main(String[] args) throws NoSuchFieldException {
//        Simple simple = new Simple();
//        System.out.println(simple.get());

//        try {
////            Class.forName("com.vilin.atomic.UnsafeFooTest");
////        } catch (ClassNotFoundException e) {
////            e.printStackTrace();
////        }

        Unsafe unsafe = getUnsafe();
//        Simple simple = null;
//        try {
//            simple = (Simple)unsafe.allocateInstance(Simple.class);
//        } catch (InstantiationException e) {
//            e.printStackTrace();
//        }
//
//        System.out.println(simple.get());
//        System.out.println(simple.getClass());
//        System.out.println(simple.getClass().getClassLoader());

//        Guard guard = new Guard();
//        guard.work();
//
//        Field f = guard.getClass().getDeclaredField("ACCESS_ALLOW");
//        unsafe.putInt(guard, unsafe.objectFieldOffset(f), 42);
//        guard.work();


//        byte[] bytes;
//        try {
//            bytes = loadClassContent();
//            Class<?> aClass = unsafe.defineClass(null, bytes, 0, bytes.length, null, null);
//            int i = (int)aClass.getMethod("get").invoke(aClass.newInstance(), null);
//            System.out.println(i);
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        } catch (InvocationTargetException e) {
//            e.printStackTrace();
//        } catch (InstantiationException e) {
//            e.printStackTrace();
//        } catch (NoSuchMethodException e) {
//            e.printStackTrace();
//        }

        System.out.println(sizeOf(new Simple()));


    }

    private static long sizeOf(Object object){
        Unsafe unsafe = getUnsafe();
        Set<Field> fieldSet = new HashSet<>();
        Class c = object.getClass();
        while(c != Object.class){
            Field[] fields = c.getDeclaredFields();
            for(Field f : fields){
                if((f.getModifiers() & Modifier.STATIC) == 0){
                    fieldSet.add(f);
                }
            }
            c = c.getSuperclass();
        }

        long maxOffSet = 0;
        for(Field f : fieldSet){
            long offSet = unsafe.objectFieldOffset(f);
            if(offSet > maxOffSet){
                maxOffSet = offSet;
            }
        }

        return ((maxOffSet / 8) + 1) * 8;
    }

    private static byte[] loadClassContent() throws IOException {
        File file = new File("/Users/luowei/IdeaProjects/A.class");
        FileInputStream fis = new FileInputStream(file);
        byte[] bytes = new byte[(int)file.length()];
        fis.read(bytes);
        return bytes;
    }

    static class Guard{
        private int ACCESS_ALLOW = 1;

        private boolean allow(){
            return 42 == ACCESS_ALLOW;
        }

        public void work(){
            if(allow()){
                System.out.println("I am working by allowed.");
            }
        }

    }

    static class Simple{
        private long l = 0;

        public Simple(){
            this.l = 1;
            System.out.println("=========");
        }

        public long get(){
            return this.l;
        }
    }

    private static Unsafe getUnsafe(){
        try {
            Field f = Unsafe.class.getDeclaredField("theUnsafe");
            f.setAccessible(true);
            return (Unsafe)f.get(null);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

}
