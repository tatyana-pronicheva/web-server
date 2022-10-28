package demo.homework.logger;

public class EmailLogger implements Logger{
    private Logger logger;

    public EmailLogger(Logger logger){
        this.logger = logger;
    }

    @Override
    public void info(String msg) {
        logger.info(msg);
        System.out.println("Запись из лога отправлена на емейл");
    }
}
