package com.github.jimcarrey.classpath;

import java.io.File;
import java.io.IOException;

public class ClassPath {

    Entry bootClasspath;
    Entry extClasspath;
    Entry userClasspath;


    public ClassPath(String jreOption,String cpOption){
        parseBootAndExtClasspath(jreOption);
        parseUserClasspath(cpOption);

    }
    void parseUserClasspath(String cpOption) {
        userClasspath = Entry.createEntry(cpOption);
    }

    //@param JAVA_HOME\jre
    void parseBootAndExtClasspath(String jreOption) {
        String jreDir = getJreDir(jreOption);
    }

    String getJreDir(String jreOption) {
        File jreFile;
        if (jreOption != null && jreOption != ""){
            jreFile = new File(jreOption);
            if (jreFile.exists()){
                return jreOption;
            }
        }

        jreFile = new File("jre");
        if (jreFile.exists()){
            return jreFile.getAbsolutePath();
        }

        String JAVA_HOME = System.getenv("JAVA_HOME");
        if (JAVA_HOME != null){
            return JAVA_HOME + File.separator + "jre";
        }

        throw new RuntimeException("Can not find jre folder! Please check your local environment variables setting!");
    }

    public byte[] readClass(String className){
        className = className + ".class";
        byte[] data;
        try {
            data = bootClasspath.readClass(className);
            if (data != null){
                return data;
            }

            data = extClasspath.readClass(className);
            if (data != null){
                return data;
            }

            data = userClasspath.readClass(className);
            if (data != null){
                return data;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        throw new RuntimeException("can not find class! ");
    }

    @Override
    public String toString() {
        return userClasspath.printClassName();
    }
}
