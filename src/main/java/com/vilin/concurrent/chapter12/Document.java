package com.vilin.concurrent.chapter12;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class Document {

    private List<String> content;

    private final Writer writer;

    private boolean changed;

    private static AutoSaveThread autoSaveThread;

    public Document(String fileName) throws IOException {
        this.content = new ArrayList<>();
        this.changed = false;
        this.writer = new FileWriter(fileName, true);
    }

    public static Document create(String fileName) throws IOException {
        Document document = new Document(fileName);
        autoSaveThread = new AutoSaveThread(document);
        autoSaveThread.start();
        return document;
    }

    public synchronized void edit(String newContent){
        this.content.add(newContent);
        this.changed = true;
    }

    public synchronized void save(){
        if(!changed){
            return;
        }
        doSave();
        this.changed = false;
    }

    public void close() throws IOException {
        autoSaveThread.interrupt();
        writer.close();
    }

    private void doSave(){
        System.out.println(Thread.currentThread().getName() + " calls do save, content = " + content);

        try {
            for(String cacheLine: content) {
                writer.write(cacheLine);
                writer.write("\n");
            }
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
