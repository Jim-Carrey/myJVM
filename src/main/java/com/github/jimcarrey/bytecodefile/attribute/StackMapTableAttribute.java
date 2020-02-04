package com.github.jimcarrey.bytecodefile.attribute;

import com.github.jimcarrey.bytecodefile.ClassReader;
import com.github.jimcarrey.bytecodefile.ConstantPool;

public class StackMapTableAttribute implements AttributeInfo {

    ConstantPool constantPool;
    int attributeNameIndex;
    int attributeLenght;
    int numberOfEntries;
    StackMapFrameEntries[] stackMapFrameEntries;
    public StackMapTableAttribute(ConstantPool constantPool,int numberOfEntries) {

        this.constantPool = constantPool;
    }

    @Override
    public void readInfo(ClassReader reader) {
        attributeNameIndex = reader.readUint16();
        byte[] bytes = reader.readUint32();
        numberOfEntries = reader.readUint16();
        StackMapTableAttribute stackMapTableAttribute = new StackMapTableAttribute(constantPool,numberOfEntries);

    }
}
