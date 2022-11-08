package item39;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class RunTests {
	public static void main(String[] args) throws ClassNotFoundException {
		int tests = 0;
		int passed = 0;

		Class<?> testClass = Class.forName(Sample.class.getName());
		for (Method method : testClass.getDeclaredMethods()) {
			if (method.isAnnotationPresent(Test.class)) {
				tests++;
				try {
					method.invoke(null);
					passed++;
				} catch (InvocationTargetException exception) {
					Throwable cause = exception.getCause();
					System.out.println(method + " 실패:" + cause);
				} catch (Exception exception) {
					System.out.println("잘못 사용한 @Test: " + method);
				}
			}
		}
		System.out.printf("성공: %d, 실패: %d%n%n", passed, tests - passed);
	}
}
