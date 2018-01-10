package domain;

import java.io.File;
import java.text.DecimalFormat;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Charlie Hard
 */
public class CustomFile extends File {
    
    

    public CustomFile(String path) {
        super(path);
    }

    public String toString() {
        StringBuilder strBuild = new StringBuilder();
        strBuild.append(super.toString());
        strBuild.append("    ");

        DecimalFormat df = new DecimalFormat("#####0.00");
        String formattedLength = df.format((double) super.getAbsoluteFile().length() / 1000000000L);

        strBuild.append(formattedLength);
        strBuild.append("gb");
        return strBuild.toString();
    }
}
