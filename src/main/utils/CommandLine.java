package utils;

public class CommandLine {
    boolean isRightFmt = true;
    boolean helpFlag;
    boolean versionFlag;
    String cpOption;
    String clazz;
    String[] args;
    String XjreOption;

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
            }else if (args[1].equals("-cp")||args[1].equals("classpath")){
                if (args.length < 4){
                    isRightFmt = false;
                }
                index = 4;
                this.cpOption = args[2];
            }else if (args[1].equals("-Xjre")){
                if (args.length < 4){
                    isRightFmt = false;
                }
                index = 4;
                this.XjreOption = args[2];
            }

            this.clazz  =args[index - 1];
            this.args = new String[args.length - index];
            for (int i = index; i < args.length; i++) {
                this.args[i - index] = args[i];
            }
        }
    }

    public void printUsage(){
        System.out.println("Usage:java [-option] class [args...]\n");
    }

    public static void main(String[] args) {
        CommandLine cmd = new CommandLine(args);

        if (!cmd.isRightFmt){
            cmd.printUsage();
        }else{
            if (cmd.versionFlag){
                System.out.println("java version \"1.8.0_20\"\n"
                +"Java(TM) SE Runtime Enviroment (build 1.8.0_20-b26)\n"
                +"Java HotSpot(TM) 64-Bit Server VM (build 25.20-b23,mixed mode)"
                );
            }else if (cmd.helpFlag || cmd.args == null){
                cmd.printUsage();
            }else{
                //startJVM(cmd);
                System.out.println("启动虚拟机ing....");
            }
        }
    }
}
