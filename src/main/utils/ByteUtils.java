package utils;

public class ByteUtils {

    public static String bytesToHexString(byte[] src){
        return bytesToHexString(src,src.length);
    }


    public static String bytesToHexString(byte[] src,int length){
        StringBuilder sBuilder = new StringBuilder("");
        if (src == null || length <= 0){
            return null;
        }
        for (int i = 0; i < length ; i++) {
            int v = src[i] & 0xFF;
            String hv = Integer.toHexString(v).toUpperCase();
            if (hv.length() < 2){
                sBuilder.append(0);
            }
            sBuilder.append(hv);
        }
        return sBuilder.toString();
    }

    //java中并没有u16，所以这里使用int来表示
    public static int bytesToU16(byte[] data){
        assert data.length == 2;
        return (data[0] + 256) % 256*256 + (data[1] + 256) % 256;
    }


    public static int byteToInt32(byte[] data){
        assert data.length == 4;
        int result = 0;
        for (int i = 0; i < data.length; i++) {
            result = result << 8 | (data[i] + 256) % 256;
        }
        return result;
    }

    public static long byteToLong64(byte[] data){
        assert data.length == 8;
        long result = 0;
        for (int i = 0; i < data.length; i++) {
            result = result << 8 | (data[i] + 256) % 256;
        }
        return result;
    }

    public static float byteToFloat32(byte[] data){
        int i = byteToInt32(b);
        return Float.intBitsToFloat(i);
    }

    public static double byteToDouble64(byte[] data){
        long l = byteToLong64(data){
            return Double.longBitsToDouble(l);
        }
    }
}
