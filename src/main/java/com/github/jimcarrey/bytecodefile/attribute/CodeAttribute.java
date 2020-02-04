package com.github.jimcarrey.bytecodefile.attribute;

import com.github.jimcarrey.bytecodefile.ClassReader;
import com.github.jimcarrey.bytecodefile.ConstantPool;

public class CodeAttribute implements AttributeInfo {
    int attributeNameInde;
    int attributeLength;
    ConstantPool constantPool;
    int maxStack;
    int maxLocalVar;
    byte[] code;
    ExceptionTableEntry[] exceptionTable;
    AttributeInfo[] attributeInfos;


    public CodeAttribute(ConstantPool constantPool) {
        this.constantPool = constantPool;
    }

    @Override
    public void readInfo(ClassReader reader) {

    }
}
