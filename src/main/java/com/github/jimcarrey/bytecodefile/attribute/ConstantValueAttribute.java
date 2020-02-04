package com.github.jimcarrey.bytecodefile.attribute;

import com.github.jimcarrey.bytecodefile.ClassReader;
import com.github.jimcarrey.bytecodefile.ConstantPool;

public class ConstantValueAttribute implements AttributeInfo {
    ConstantPool constantPool;
    int attributeNameIndex;
    int attributeLength;

    public ConstantValueAttribute(ConstantPool constantPool) {
        this.constantPool = constantPool;
    }

    @Override
    public void readInfo(ClassReader reader) {
            attributeNameIndex = reader.readUint16();
            String value = constantPool.getUtf8(attributeNameIndex);
            byte[] bytes = reader.readUint32();

    }
    public int getAttributeNameIndex(){
        return attributeNameIndex;
    }
}
