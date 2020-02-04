package com.github.jimcarrey.bytecodefile.attribute;

import com.github.jimcarrey.bytecodefile.ClassReader;
import com.github.jimcarrey.bytecodefile.ConstantPool;
import com.github.jimcarrey.utils.ByteUtils;

public interface AttributeInfo {

    void readInfo(ClassReader reader);

    /**
     * 读取属性表;
     * 和ConstantPool中的方法类似,一般都是一下子全部读取出来,不会只读一个
     * 整个 JVM 中有三个地方用到了读取属性表
     * 1. 由 class 文件转为 ClassFile 对象时，读取 Class 的属性
     * 2. 为 class 中定义的 Field 和 Method 读取属性
     * 3. 为 Method 中的字节码读取属性(本地变量表大小，操作数大小，字节码，异常表)
     */
    default AttributeInfo[] readAttributes(ClassReader reader,ConstantPool constantPool){
        int attributesCount = reader.readUint16();
        AttributeInfo[] attributes = new AttributeInfo[attributesCount];
        for (int i = 0; i < attributesCount ; i++) {
            attributes[i] = readAttribute(reader,constantPool);
        }
        return attributes;

    }

    //读取单个属性
    default AttributeInfo readAttribute(ClassReader reader, ConstantPool constantPool){
        int attributeNameIndex = reader.readUint16();
        String attributeName = constantPool.getUtf8(attributeNameIndex);
        int attributeLength = ByteUtils.byteToInt32(reader.readUint32());
        AttributeInfo attributeInfo = create(attributeName,attributeLength,constantPool);
        attributeInfo.readInfo(reader);
        return attributeInfo;

    }


    default AttributeInfo create(String attributeName, int attributeLength, ConstantPool constantPool){
        switch (attributeName){
            case "Code":
                return new CodeAttribute(constantPool);
                break;
            case "ConstantValue":
                return new ConstantValueAttribute(constantPool);
                break;
            case "StackMapTable":
                return new StackMapTableAttribute(constantPool);
                break;
            case "Exceptions":
                return ExceptionsAttribute(constantPool);
                break;
            case "InnerClasses":
                return InnerClassesAttribute(constantPool);
                break;
            case "EnclosingMethod":
                return EnclosingMethodAttribute(constantPool);
                break;



        }


    }


}
