package bytecodefile;

public class ConstantPool {

    ConstantPool[] infos;

    public ConstantPool[] getInfos(){
        return infos;
    }

    //class文件中常量池中的常量数量，注意返回的这个数量是包含0的，但是0是空的
    private int constantPoolCount;
    private int realConstantPoolCount;
    public ConstantPool(ClassReader reader){

        constantPoolCount = reader.readUint16();
        infos = new ConstantInfo[constantPoolCount];
        for (int i = 0; i < constantPoolCount ; i++) {
            infos[i]  =  ConstantInfo.readConstantInfo(read,this);
            realConstantPoolCount++;

        }

    }
}
