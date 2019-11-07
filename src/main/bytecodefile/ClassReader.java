package bytecodefile;

import utils.ByteUtils;

//封装要读取class字节码文件的reader，里面包含一个index值，表示当前要读取的字节数组索引
public class ClassReader {

    private byte[] data;
    private int index = 0;

    public ClassReader(byte[] classData){
        this.data = classData;
    }

    //u1
    public byte readUint8(){
        return data[index++];
    }

    //u2 这里是读取一个无符号的16位整，java中并没有，只能用int代替
    public int readUint16(){
        byte[] result = new byte[2];
        result[0] = data[index++];
        result[1] = data[index++];
        return ByteUtils.bytesToU16(result);
    }

    //u4 无符号四字节，读32位
    public byte[] readUint32(){
        byte[] result  = new byte[4];
        result[0] = data[index++];
        result[1] = data[index++];
        result[2] = data[index++];
        result[3] = data[index++];
        return result;
    }

    //读八个字节(无符号)，64位
    public byte[] readUint64(){
        byte[] result = new byte[8];
        for (int i = 0; i < 8; i++) {
            result[i] = data[index++];
        }
        return result;
    }

    //读取连续的16bit长的数组，首先读出16bit，用来表示接来下要去读的多少个16bit
    public int[] readUint16s(){
        int n = readUint16();
        int[] data = new int[n];
        for (int i = 0; i < n; i++) {
            data[i] = readUint16();
        }
        return data;
    }

    public byte[] readBytes(int n){
        byte[] result = new byte[n];
        for (int i = 0; i < n; i++) {
            result[i] = data[index++];
        }
        return result;
    }

}
