package demo.homework.logger;

public class SimpleLogger implements Logger {
    @Override
    public void info(String msg) {
        System.out.println(msg);
    }
}
