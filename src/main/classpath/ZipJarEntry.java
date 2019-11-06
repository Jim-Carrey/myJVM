package classpath;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

/*这种使用场景是指定了一个zip文件或者jar包的情况,
那么需要解决的问题是如何拿到压缩文件中的文件,
 这里主要使用了java.util.zip包下的类来拿到文件,
使用zipFile来读取文件和读取普通的文件夹类似,
这里只管把zip文件当成文件夹就好*/
public class ZipJarEntry implements Entry {
    String absPath;  //C：\zip\test.zip 全路径
    String zipName;  //test 压缩包名,不带包名.zip或jar


    public ZipJarEntry(String path) {
        File dir = new File(path);
        if (dir.exists()){
            absPath = dir.getParentFile().getAbsolutePath();
            zipName = dir.getName().substring(0,zipName.length() - 4);

        }
    }


    public ZipJarEntry(String path,String zipName){
        File dir = new File(path,zipName);
        if (dir.exists()){
            absPath = dir.getAbsolutePath();
            //去掉结尾的.zip或者.jar 碰到其它情况直接报异常
           this.zipName = zipName.substring(0,zipName.length() -4);
        }
    }
    /*
    * 从zip或者jar文件中提取class文件
    * */

    @Override
    public byte[] readClass(String className) throws IOException {
        File file = new File(absPath);

        ZipInputStream zis = null;
        BufferedInputStream in;
        ByteArrayOutputStream out = null;

        ZipFile zf = new ZipFile(file);
        //如果是zip文件，获取ZipEntry的时候，直接用zipName+"/"+className
        //ZipEntry ze = zf.getEntry(zipName + "/" + className);
        //如果是jar包，获取ZipEntry的时候，直接用className
        ZipEntry ze = zf.getEntry(className);
        if (ze == null){
            return null;
        }
        in = new BufferedInputStream(zf.getInputStream(ze));
        out = new ByteArrayOutputStream(1024*1024);
        int size = 0;
        byte[] tmp = new byte[1024*1024];
        while ((size = in.read(tmp)) != -1){
            out.write(tmp,0,size);
        }

        if (zis != null){
            zis.closeEntry();
        }

        if (in != null){
            in.close();
        }

        if (out != null){
            out.close();
        }
        return out.toByteArray();
    }


    @Override
    public String printClassName() {
        return absPath;
    }
}
