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
public class FileInfo {

//    private static StringBuilder output = new StringBuilder();
    private static Map<Long, File> allFiles = new TreeMap(Collections.reverseOrder());

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
        for (File f : curLevelFiles) {
            FileInfo fi = new FileInfo();
            fi.getChildren(f);
        }
        
        ArrayList<File> biggestFiles = new ArrayList(allFiles.values());
        for (int i = 0; i < 100; i++) {
            System.out.println(biggestFiles.get(i).getCanonicalPath() + "\t" + biggestFiles.get(i).length());
        }

//        For file output
//        FileOutputStream fos = new FileOutputStream(new File(System.getProperty("user.home") + "/allFiles.txt"));
//        fos.write(output.toString().getBytes());
//        fos.close();
    }

    public void getChildren(File f) {
        if (f.isDirectory() && !f.isHidden() && f.listFiles().length != 0 && f.listFiles() != null) {
            for (File fc : f.listFiles()) {
                try {
                    getChildren(fc);
                } catch (NullPointerException ex) {
                    // Print to System.out 
                    // System.out.println(fc.getAbsolutePath());

                    // Add to StringBuilder for file output
//                    output.append(fc.getAbsolutePath());
//                    output.append("\t");
//                    output.append(fc.length());
//                    output.append("\r\n");
                    allFiles.put(fc.length(), fc.getAbsoluteFile());
                }
            }
        } else if (f.isFile() && !f.isHidden()) {

            // Print to System.out 
            // System.out.println(f.getAbsolutePath());
            // Add to StringBuilder for file output
            // output.append(f.getAbsolutePath());
//            output.append("\t");
//            output.append(f.length());
//            output.append("\r\n");
            allFiles.put(f.length(), f.getAbsoluteFile());

        }
    }

}
