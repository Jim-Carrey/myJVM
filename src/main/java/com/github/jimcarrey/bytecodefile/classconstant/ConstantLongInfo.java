package com.github.jimcarrey.bytecodefile.classconstant;

import com.github.jimcarrey.bytecodefile.ClassReader;
import com.github.jimcarrey.utils.ByteUtils;

public class ConstantLongInfo implements ConstantInfo {
    private long val;
    private int type;
    public ConstantLongInfo(int i) {
        type = i;
    }


    @Override
    public void readInfo(ClassReader reader) {
        byte[] data = reader.readUint64();
       /* String hexData = ByteUtils.bytesToHexString(data);
        val = Long.parseLong(hexData, 16);*/
        val = ByteUtils.byteToLong64(data);
    }

    public long getVal() {
        return val;
    }

    public int getType(){
        return type;
    }



}