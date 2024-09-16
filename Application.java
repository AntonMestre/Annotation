import java.lang.reflect.Method;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.annotation.ElementType;

public class Application {
    public static void main(String[] args){
        Operation operation = new Operation();
        executeDangerousMultiplication(operation);
    }

    public static void executeDangerousMultiplication(Object instance) {
        Class<?> clazz = instance.getClass();

        for (Method method : clazz.getDeclaredMethods()) {
            if (method.isAnnotationPresent(DangerousTask.class)) {
                System.out.println("Starting dangerous task!");
                
                try{
                    System.out.println(method.invoke(instance, 1, 2));
                }catch (Exception e){
                    System.out.println(e);
                }

                System.out.println("Finishing dangerous task!");
            }
        }
    }
}

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface DangerousTask {}

public class Operation {
    @DangerousTask
    public Integer multiplication(Integer a, Integer b){
        return a * b;
    }
}