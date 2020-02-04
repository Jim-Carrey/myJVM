package com.github.jimcarrey.bytecodefile.classconstant;

import com.github.jimcarrey.bytecodefile.ClassReader;
import com.github.jimcarrey.bytecodefile.ConstantPool;

/*本身并不存放字符串数据,只存了常量池索引，这个索引指向一个CONSTANT_Utf8_info常量
 * 所以在readInfo中首先读出索引，然后在去对应的CONSTANT_Utf8_info常量中读取具体的字符串
 * */
public class ConstantStringInfo implements ConstantInfo {
    private int type;
    private int stringIndex;
    ConstantPool constantPool;

    public ConstantStringInfo( ConstantPool constantPool,int type) {
        this.type = type;
        this.constantPool = constantPool;
    }

    //读出索引
    @Override
    public void readInfo(ClassReader reader) {
       stringIndex = reader.readUint16();
    }

    public String getString(){
        return constantPool.getUtf8(stringIndex);
    }

    public int getType() {
        return type;
    }
}
