package bytecodefile.classconstant;

import bytecodefile.ClassReader;
import org.omg.CORBA.DATA_CONVERSION;

import java.io.IOException;
import java.io.UTFDataFormatException;

public class ConstantUtf8Info implements ConstantInfo {
    public String val;

    public int type ;
    public ConstantUtf8Info(int i) {
       type = i;
    }

    @Override
    public void readInfo(ClassReader reader) {
        int length = reader.readUint16();
        byte[] data = reader.readBytes(length);
        try {
            val = decodeMUTF8(data);
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    //将MUTF8转为UTF8编码，根据java.io.DataInputStream.readUTF()方法改写。
    private String decodeMUTF8(byte[] data) throws IOException{
        int utflen = data.length;
        char[] chararr = new char[utflen];
        int c,char2 = 0,char3;
        int count = 0;
        int chararr_count = 0;

        while (count < utflen){
            c = (int)data[count] & 0xff;
            if (c > 127){
                break;
            }
            count++;
            chararr[chararr_count++] = (char) c;
        }

        while(count < utflen){
            c = (int) data[count] & 0xff;
            switch (c >> 4){
                case 0:
                case 1:
                case 2:
                case 3:
                case 4:
                case 5:
                case 6:
                case 7:
                    count++;
                    chararr[chararr_count++] = (char) c;
                    break;
                case 12:
                case 13:
                    count += 2;
                    if (count > utflen){
                        throw new UTFDataFormatException("malformed input : partial character at end")
                    }
                    chararr[chararr_count++] = (char) (((c & 0x1F) << 6) |
                            (char2 & 0x3F));
                    break;


            }

        }



    }

}
}
