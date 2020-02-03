package com.github.jimcarrey.classpath;

import java.io.*;

public class DirEntry implements Entry {
    String absDir;

    public DirEntry(String path){
        File dir = new File(path);
        if (dir.exists()){
            absDir = dir.getAbsolutePath();
        }
    }

    @Override
    public byte[] readClass(String className) throws IOException {
        File file = new File(absDir,className);
        byte[] tmp = new byte[1024*1024];
        BufferedInputStream in;
        ByteArrayOutputStream out;

        in = new BufferedInputStream(new FileInputStream(file));
        out = new ByteArrayOutputStream(1024*1024);
        int size = 0;
        while ((size = in.read(tmp)) != -1){
            out.write(tmp,0,size);
        }
        in.close();
        out.close();

        return out.toByteArray();
    }

    @Override
    public String printClassName() {
        return absDir;
    }
}
