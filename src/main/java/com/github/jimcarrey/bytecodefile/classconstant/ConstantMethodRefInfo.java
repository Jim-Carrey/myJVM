package com.github.jimcarrey.bytecodefile.classconstant;

import com.github.jimcarrey.bytecodefile.ClassReader;
import com.github.jimcarrey.bytecodefile.ConstantPool;

public class ConstantMethodRefInfo implements ConstantInfo {
    private int type;
    private ConstantPool constantPool;
    private int referenceKind;
    private int getReferenceIndex;

    public ConstantMethodRefInfo(ConstantPool constantPool, int i) {
        this.type = i;
        this.constantPool = constantPool;
    }

    @Override
    public void readInfo(ClassReader reader) {
        this.referenceKind = reader.readUint8();
        this.getReferenceIndex = reader.readUint16();
    }

    public int getType(){
        return type;
    }

    public String getIndex(){
            if (referenceKind == 1 ||referenceKind == 2||referenceKind == 3|| referenceKind ==4){
                constantPool.getFieldRefInfo(referenceKind);
            }else(referenceKind == 5 || referenceKind == 8){
                constantPool.getMethodrefInfo(referenceKind);

        }

    }
}
