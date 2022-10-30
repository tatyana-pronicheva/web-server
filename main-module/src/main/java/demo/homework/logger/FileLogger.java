package demo.homework.logger;

import java.io.FileWriter;

public class FileLogger implements Logger{
    private FileWriter fw;
    private Logger logger;

    public FileLogger(Logger logger){
        this.logger = logger;
        try{
        FileWriter fw = new FileWriter("log.txt");
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void info(String msg) {
        logger.info(msg);
        try {
            fw.write(msg);
            fw.write("\n");
        } catch (Exception e){
           // e.printStackTrace();
        }
    }
}
