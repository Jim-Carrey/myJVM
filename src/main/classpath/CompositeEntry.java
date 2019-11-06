package classpath;

import java.io.IOException;
import java.util.ArrayList;

/*这种使用场景是包含多个路径的情况,(eg:a1/b1/c1;a2/b2/c2;a3/b3/c3),
那么遇到这种情况,需要将字符串分割成不同的子串,
注意分割符在不同的系统下是不同的,这里仅仅实现windows,其它情况下视为unix*/
public class CompositeEntry implements Entry {

    ArrayList<Entry> compositeEntries;
    private String pathList;

    public CompositeEntry(){

    }

    public CompositeEntry(String pathList, String pathListSeparator) {
        this.pathList = pathList;
        String[] paths = pathList.split(pathListSeparator); //以/拆分path参数
        compositeEntries = new ArrayList<Entry>(paths.length);
        for (int i = 0; i < paths.length; i++) {
            compositeEntries.add(new DirEntry(paths[i]));
        }
    }

    @Override
    public byte[] readClass(String className) throws IOException {
        byte[] data;
        for (int i = 0; i < compositeEntries.size(); i++) {
            try {
                data = compositeEntries.get(i).readClass(className);
                if (data != null){
                    return data;
                }
            }catch (IOException e){
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public String printClassName() {
        return pathList;
    }
}
