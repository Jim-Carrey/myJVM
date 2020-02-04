package com.github.jimcarrey.bytecodefile.classconstant;

import com.github.jimcarrey.bytecodefile.ClassReader;
import com.github.jimcarrey.utils.ByteUtils;

public class ConstantDoubleInfo implements ConstantInfo {
    private double val;
    private int type;
    public ConstantDoubleInfo(int i) {
        type = i;
    }


    @Override
   public void readInfo(ClassReader reader) {
        byte[] data = reader.readUint64();
        val = ByteUtils.byteToDouble64(data);
    }

    public double getVal() {
        return val;
    }

    public int getType() {
        return type;
    }
}