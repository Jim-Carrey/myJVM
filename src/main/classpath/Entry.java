package classpath;

import java.io.IOException;

public interface Entry {

     String pathListSeparator = System.getProperty("os.name").contains("Windows")?";":":";


    /*负责寻找和加载class文件
      @param className class文件的相对路径，路径之间用斜线 / 分隔，文件名有.class后缀
    */

    byte[] readClass(String className) throws IOException;


    /*
    @return 返回className的字符串表示
     */
    String printClassName();


     static Entry createEntry(String path){
        if (path != null){
            if (path.contains(pathListSeparator)){
                return new CompositeEntry(path,pathListSeparator);
            }else if (path.contains("*")){
                return new WildcardEntry("");
            }else if (path.contains(",jar")
                    || path.contains(",JAR")
                    || path.contains(".zip")
                    || path.contains(".ZIP")
            ){
                return new ZipJarEntry(path);
            }
            return new DirEntry(path);
        }
        return null;
    }





}
