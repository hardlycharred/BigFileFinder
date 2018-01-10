/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fileinfo;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

/**
 *
 * @author Charlie Hard
 */
public class BigFileFinder {

//    private static StringBuilder output = new StringBuilder();
    static Map<Long, File> allFiles = new TreeMap(Collections.reverseOrder());
    static Integer topLevelCount = 0;
    static Boolean[] greenLights;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        Path path = Paths.get(System.getProperty("user.home"));
        while (path.getParent() != null) {
            path = path.getParent();
        }
        File file = path.toFile();
        File[] curLevelFiles = file.listFiles();
        

        
        topLevelCount = curLevelFiles.length;
        greenLights = new Boolean[topLevelCount];
        for (int i = 0; i < greenLights.length; i++) {
            greenLights[i] = false;
        }
        
        
        int i = 0;
        
        for (File f : curLevelFiles) {
            Thread t = new Thread(new FolderBrowserTask(f, i));
            i++;
            t.start();
        }

    }
    
    public static void threadCallback() throws IOException {
        
        for (boolean b : greenLights) {
            if (b == false) {
                return;
            }
        }
        
        ArrayList<File> listFiles = new ArrayList(allFiles.values());
        ArrayList<File> biggestFiles = new ArrayList<>(listFiles.subList(0, 101));
        
        for (File f : biggestFiles) {
            System.out.println(f.getCanonicalPath() + "\t" + f.length());
        }
    }

}
