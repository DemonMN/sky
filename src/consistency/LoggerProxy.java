package consistency;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class LoggerProxy implements InvocationHandler {

	private Object object;
	public static Object newInstance(Object obj) {
         return Proxy.newProxyInstance(obj.getClass().getClassLoader(), obj.getClass().getInterfaces(), new LoggerProxy(obj));
    }

	
	public LoggerProxy(Object object) {
		this.object = object;
	}


	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		Object result;
        try {
            System.out.println("Method Called " + method.getName());
            result = method.invoke(object, args);
        } catch (InvocationTargetException e) {
            throw e.getTargetException(); 
        } catch (Exception e) {
            throw new RuntimeException("unexpected invocation exception: " + e.getMessage());
        }																							
        return result;
	}

}
