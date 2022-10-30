package demo.homework.handler;

import demo.homework.ResponseSerializer;
import demo.homework.SocketService;

import java.io.File;
import java.lang.annotation.Annotation;
import java.net.URL;
import java.util.TreeMap;


public class HandlerCreator {

    private String path = "demo/homework/handler";
    private String packagePath = "demo.homework.handler";
    private MethodHandler firstMethodHandler;

    public MethodHandler getFirstMethodHandler() {
        return firstMethodHandler;
    }

    HandlerCreator(SocketService socketService, ResponseSerializer responseSerializer) {
        try{
            TreeMap<Integer, Class> necessaryMethodHandlers = getAnnotatedClasses();
            Class[] necessaryMethodHandlersArr = necessaryMethodHandlers.values().toArray(new Class[0]);
            MethodHandler[] methodHandlers = new MethodHandler[necessaryMethodHandlersArr.length];

            methodHandlers[methodHandlers.length-1] = (MethodHandler) necessaryMethodHandlersArr[necessaryMethodHandlersArr.length-1]
                    .getConstructor(MethodHandler.class, SocketService.class, ResponseSerializer.class)
                    .newInstance(null, socketService, responseSerializer);

            for (int i = necessaryMethodHandlersArr.length-2; i >=0; i-- ) {
                methodHandlers[i] = (MethodHandler) necessaryMethodHandlersArr[i]
                        .getConstructor(MethodHandler.class, SocketService.class, ResponseSerializer.class)
                        .newInstance(methodHandlers[i+1], socketService, responseSerializer);
            }

            firstMethodHandler = methodHandlers[0];

        } catch (ClassNotFoundException e){
            System.out.println("Не найдены классы с аннотацией @Handler");
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private TreeMap<Integer, Class> getAnnotatedClasses() throws ClassNotFoundException {
        TreeMap<Integer, Class> classesWithAnnotation = new TreeMap<Integer, Class>();
        ClassLoader classLoader = this.getClass().getClassLoader();
        URL urls = classLoader.getResource(path);
        File folder = new File(urls.getPath());
        File[] classes = folder.listFiles();

        for (File classForCheck : classes) {
            int index = classForCheck.getName().indexOf(".");
            String className = classForCheck.getName().substring(0, index);
            String classNamePath = packagePath + "." + className;
            try {
                Class repoClass = Class.forName(classNamePath);
                Annotation[] annotations = repoClass.getAnnotations();
            for (Annotation annotation : annotations) {
                if (annotation.annotationType() == Handler.class) {
                    Handler handler = (Handler) repoClass.getAnnotation(Handler.class);
                    classesWithAnnotation.put(handler.order(), repoClass);
                }
            }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return classesWithAnnotation;
    }

}
