package com.github.jimcarrey.bytecodefile.classconstant;

import com.github.jimcarrey.bytecodefile.ClassReader;
import com.github.jimcarrey.utils.ByteUtils;

public class ConstantFloatInfo implements ConstantInfo {
    private float val;
    private int type;
    public ConstantFloatInfo(int i) {
        type = i;
    }

    @Override
    public void readInfo(ClassReader reader) {
        byte[] data = reader.readUint32();
        val = ByteUtils.byteToFloat32(data);
    }


    public float getVal() {
        return val;
    }
    public int getType(){
        return type;
    }
}