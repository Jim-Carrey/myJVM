package com.github.jimcarrey.bytecodefile.classconstant;

import com.github.jimcarrey.bytecodefile.ClassReader;
import com.github.jimcarrey.utils.ByteUtils;

public class ConstantIntegerInfo implements ConstantInfo {
    private int val;
    private int type;
    public ConstantIntegerInfo(int i) {
        type = i;
    }

    @Override
    public void readInfo(ClassReader reader) {
        byte[] data = reader.readUint32();
        val = ByteUtils.byteToInt32(data);
    }

    public int getVal() {
        return val;
    }
    public int getType(){
        return type;
    }
}