package com.github.jimcarrey.bytecodefile.classconstant;

import com.github.jimcarrey.bytecodefile.ClassReader;
import com.github.jimcarrey.bytecodefile.ConstantPool;


public class ConstantClassInfo implements ConstantInfo {
    ConstantPool constantPool;
    private int nameIndex;
    private int type;
    public ConstantClassInfo(ConstantPool constantPool, int i) {
        this.constantPool = constantPool;
        type = i;
    }


    @Override
    public void readInfo(ClassReader reader) {
        nameIndex = reader.readUint16();
    }

    public String getName() {
        return constantPool.getUtf8(nameIndex);
    }

    public int getType() {
        return type;
    }
}
