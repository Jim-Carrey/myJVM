package classpath;

import java.io.IOException;

/*这种使用场景是指定了一个形如aa/bb的路径,
这种路径表明我们的class文件在aa/bb/路径下的jar包中,
所以我们只要遍历该路径下的所有以.jar结尾的文件,
然后调用ZipJarEntry的实现方法,即可以获得字节码.*/
public class WildcardEntry implements Entry {


    public WildcardEntry(String s) {
    }

    @Override
    public byte[] readClass(String className) throws IOException {
        return new byte[0];
    }

    @Override
    public String printClassName() {
        return null;
    }
}
