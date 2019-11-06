package bytecodefile;

//由javac 编译生成的class文件的bean
public class ByteCodeFile {

        private int minorVersion; //此版本
        private int majorVersion; //主版本
        public ConstantPool constantPool; //常量池
        private int accessFlags; // 魔数是否合法

        //该索引值指向常量池中的一个类型为CONSTANT_CLASS_INFO的类描述符常量
        private int thisClass;
        private int superClass;  //同thisClass的索引值
        private int[] interfaces;   //存放所实现的接口在常量池中的索引。同thisClass的索引值
        private MemberInfo[] fields;//存放类中的所有字段，包括静态/非静态；不同的属性通过字段的访问修饰符来读取
        private MemberInfo[] methods;//存放类中的所有方法，包括静态/非静态；不同的属性通过方法的访问修饰符来读取
        private AttributeInfo[] attributes;//属性表，存放类的属性

        public ByteCodeFile(byte[] classData){
            ClassReader reader = new ClassReader(classData);
            read(reader);

        }
        //读取class文件，解析各个字段
        private void read(ClassReader reader) {
            readAndCheckMagicNumber(reader);
            readAndCheckVersion(reader);
            constantPool = new ConstantPool(reader);
            accessFlags = reader.readUint16();
            thisClass = reader.readUint16();
            superClass = reader.readUint16();
            interfaces = reader.readUint16s();
            fields = MemberInfo.readMembers(reader,constantPool);
            methods = MemberInfo.readMembers(reader,constantPool);
            attributes = AttributeInfo.readAttributes(reader,constantPool);

        }



    private void readAndCheckMagicNumber(ClassReader reader) {
            String magic = ByteUtils.byteToHexString(reader.readUint32());
            if (!magic.equals("CAFEBABE")){//0xCAFEBABE
                throw new RuntimeException("java.lang.ClassFormatError:illegal magic number! ")
            }
        }

    //版本号，16字节，分别代表主版本号和次版本号，并向前兼容
    private void readAndCheckVersion(ClassReader reader) {
            minorVersion = reader.readUint16();
            majorVersion = reader.readUint16();
            if (majorVersion == 45){
                return;
            }
            if (minorVersion == 0 && majorVersion >= 46 &&majorVersion <= 52){
                return ;
            }
            throw new RuntimeException("java.lang.UnsupportedClassVersionError!");
    }

    public int getMinorVersion(){
            return minorVersion;
    }

    public int getMajorVersion(){
            return majorVersion;
    }

    public ConstantPool getConstantPool(){
            return constantPool;
    }

    public int getAccessFlags(){
            return accessFlags;
    }

    public int getThisClass(){
            return thisClass;
    }

    public int getSuperClass(){
            return superClass;
    }

    public int[] getInterfaces(){
            return interfaces;
    }

    public MemberInfo[] getFields(){
            return fields;
    }

    public MemberInfo[] getMethods(){
            return methods;
    }

    public AttributeInfo[] getAttributes(){
            return attributes;
    }

    public String getClassName(){
            return constantPool.getClassName(thisClass);
    }

    public String getSuperClassName(){
            if (superClass > 0){
                return constantPool.getClassName(superClass);
            }else{
                return "";
            }
    }

    public String[] getInterfaceNames(){
            String[] interfaceNames = new String[interfaces.length];
        for (int i = 0; i < interfaceNames.length; i++) {
            interfaceNames[i] = constantPool.getClassName(interfaces[i]);
        }
        return interfaceNames;
    }

    public String getSourceFile(){
            for (AttributeInfo info : attributes){
                if (info instanceof SourceFileAttribute){
                    return ((SourceFileAttribute)info).getFileName();
                }
            }
            return "unknow";
    }


}
