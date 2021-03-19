package com.vilin.classloader.chapter04;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public final class EncryptUtils {

    public static final byte ENCRYPT_FACTOR = (byte)0xff;

    private EncryptUtils(){}

    public static void doEncrypt(String source, String target){
        try(FileInputStream fis = new FileInputStream(source);
            FileOutputStream fos = new FileOutputStream(target)){
            int data;
            while ((data = fis.read()) != -1){
                fos.write(data ^ ENCRYPT_FACTOR);
            }
            fos.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        doEncrypt("/Users/luowei/Documents/classLoader3/com/vilin/classloader/chapter03/MyObject.class", "/Users/luowei/Documents/classLoader3/com/vilin/classloader/chapter03/MyObject1.class");
    }
}
