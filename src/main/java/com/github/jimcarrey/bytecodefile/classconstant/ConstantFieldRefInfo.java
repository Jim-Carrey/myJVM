package com.github.jimcarrey.bytecodefile.classconstant;

import com.github.jimcarrey.bytecodefile.ClassReader;
import com.github.jimcarrey.bytecodefile.ConstantPool;

public class ConstantFieldRefInfo implements ConstantInfo {
    int type;
    ConstantPool constantPool;
    int classIndex;
    int nameAndTypeIndex;

    public ConstantFieldRefInfo(ConstantPool constantPool,int type){
        this.constantPool = constantPool;
        this.type = type;
    }

    @Override
    public void readInfo(ClassReader reader) {
        int classIndex = reader.readUint16();
        int nameAndTypeIndex = reader.readUint16();

    }

    public String getString(){
        return constantPool.getUtf8(classIndex);


    }

    public int getType(){
        return type;
    }
}
