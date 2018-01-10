/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fileinfo;

import gui.BigFileFinder;
import java.io.File;
import java.io.IOException;

/**
 *
 * @author Charlie Hard
 */
public class FolderBrowserTask implements Runnable {

    private File file;
    private int pos;

    public FolderBrowserTask(File f, int pos) {
        this.file = f;
        this.pos = pos;
        
    }

    @Override
    public void run() {
        getChildren(file);
        BigFileFinder.greenLights[pos] = true;
        try {
            BigFileFinder.threadCallback();
        } catch (IOException ex) {
        }
    }

    public void getChildren(File f) {
        if (f.isDirectory() && !f.isHidden() && f.listFiles().length != 0 && f.listFiles() != null) {
            for (File fc : f.listFiles()) {
                try {
                    getChildren(fc);
                } catch (NullPointerException ex) {
                    BigFileFinder.allFiles.put(fc.length(), fc.getAbsoluteFile());
//                    synchronized (System.out) {
//                        System.out.println(Thread.currentThread().getId() + " added file");
//                    }
                }
            }
        } else if (f.isFile() && !f.isHidden()) {
            BigFileFinder.allFiles.put(f.length(), f.getAbsoluteFile());
        }
    }
}
