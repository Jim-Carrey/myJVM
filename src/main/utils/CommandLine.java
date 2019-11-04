package utils;

public class CommandLine {
    boolean isRightFmt;
    boolean helpFlag;
    boolean versionFlag;
    String cpOption;
    String clazz;
    String[] args;

    public CommandLine(String[] args){
        parseCmd(args);
    }

    private void parseCmd(String[] args) {
        int index = 1;

        if (args.length<2){
            isRightFmt = false;
            return;
        }

        if (!args[0].equals("java")){
            isRightFmt = false;
        }else {
            if (args[1].equals("-help")||args[1].equals("-?")){
                helpFlag = true;
            }else if(args[1].equals("-version")){
                versionFlag = true;
            }
        }
    }
}
