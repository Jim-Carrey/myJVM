package com.github.jimcarrey.bytecodefile.classconstant;

import com.github.jimcarrey.bytecodefile.ClassReader;
import com.github.jimcarrey.bytecodefile.ConstantPool;

public interface ConstantInfo {

    int CONSTANT_Utf8 = 1;
    int CONSTANT_Integer = 3;
    int CONSTANT_Float = 4;
    int CONSTANT_Long = 5;
    int CONSTANT_Double = 6;
    int CONSTANT_Class = 7;
    int CONSTANT_String = 8;
    int CONSTANT_Fieldref = 9;
    int CONSTANT_Methodref = 10;
    int CONSTANT_InterfaceMethodref = 11;
    int CONSTANT_NameAndType = 12;
    int CONSTANT_MethodHandle = 15;
    int CONSTANT_MethodType = 16;
    int CONSTANT_InvokeDynamic = 18;

    void  readInfo(ClassReader reader);

    //表明当前常量的类型是上述常量的哪一种
   /* int type = 0;

    static int getType(){
        return type;
    }*/

    static ConstantInfo readConstantInfo(ClassReader reader, ConstantPool constantPool){
        //开始读取常量池，先读取一个字节的tag,tag表示紧跟其后的这个常量池的类型
        int type = (reader.readUint8() + 256) % 256;
        ConstantInfo info = create(type,constantPool);
        info.readInfo(reader);
        return info;

    }

    static ConstantInfo create(int type, ConstantPool constantPool) {
        switch (type) {
            case CONSTANT_Utf8:
                return new ConstantUtf8Info(1);
            case CONSTANT_Integer:
                return new ConstantIntegerInfo(3);
            case CONSTANT_Float:
                return new ConstantFloatInfo(4);
            case CONSTANT_Long:
                return new ConstantLongInfo(5);
            case CONSTANT_Double:
                return new ConstantDoubleInfo(6);
            case CONSTANT_String:
                return new ConstantStringInfo(constantPool, 8);
            case CONSTANT_Class:
                return new ConstantClassInfo(constantPool, 7);
            case CONSTANT_Fieldref:
                return new ConstantFieldRefInfo(constantPool, 9);
            case CONSTANT_Methodref:
                return new ConstantMethodRefInfo(constantPool, 10);
            case CONSTANT_InterfaceMethodref:
                return new ConstantInterfaceMethodRefInfo(constantPool, 11);
            case CONSTANT_NameAndType:
                return new ConstantNameAndTypeInfo(12);
            case CONSTANT_MethodType:
                return new ConstantMethodTypeInfo(16);
            case CONSTANT_MethodHandle:
                return new ConstantMethodHandleInfo(15);
            case CONSTANT_InvokeDynamic:
                return new ConstantInvokeDynamicInfo(18);
            default:
                throw new RuntimeException("java.lang.ClassFormatError: constant pool tag!");
        }
    }


}
