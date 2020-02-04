package com.github.jimcarrey.bytecodefile.classconstant;

import com.github.jimcarrey.bytecodefile.ClassReader;
import java.io.IOException;
import java.io.UTFDataFormatException;

public class ConstantUtf8Info implements ConstantInfo {
    private String val;
    private int type ;

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
                        throw new UTFDataFormatException("malformed input : partial character at end");
                    }
                    chararr[chararr_count++] = (char) (((c & 0x1F) << 6) |
                            (char2 & 0x3F));
                    break;
                case 14:
                    /* 1110 xxxx  10xx xxxx  10xx xxxx */
                    count += 3;
                    if (count > utflen) {
                        throw new UTFDataFormatException(
                                "malformed input: partial character at end");
                    }
                    char2 = (int) data[count - 2];
                    char3 = (int) data[count - 1];
                    if (((char2 & 0xC0) != 0x80) || ((char3 & 0xC0) != 0x80)) {
                        throw new UTFDataFormatException(
                                "malformed input around byte " + (count - 1));
                    }
                    chararr[chararr_count++] = (char) (((c & 0x0F) << 12) |
                            ((char2 & 0x3F) << 6) |
                            ((char3 & 0x3F) << 0));
                    break;
                default:
                    /* 10xx xxxx,  1111 xxxx */
                    throw new UTFDataFormatException(
                            "malformed input around byte " + count);
            }

            }
             return new String(chararr, 0, chararr_count);
        }
        public String getVal(){
        return val;
        }

    public int getType() {
        return type;
    }
}
