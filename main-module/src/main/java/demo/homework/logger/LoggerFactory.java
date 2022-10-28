package demo.homework.logger;

public class LoggerFactory {
    public static Logger createLogger(){
        Logger simpleLogger = new SimpleLogger();
        Logger emailLogger = new EmailLogger(simpleLogger);
        Logger emailFileLogger = new FileLogger(emailLogger);
        return emailFileLogger;
    }
}
