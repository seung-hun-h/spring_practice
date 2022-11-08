package item39;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class RunTests2 {
	public static void main(String[] args) throws ClassNotFoundException {
		int tests = 0;
		int passed = 0;

		Class<?> testClass = Class.forName(Sample2.class.getName());
		for (Method method : testClass.getDeclaredMethods()) {
			if (method.isAnnotationPresent(ExceptionTest.class) || method.isAnnotationPresent(ExceptionTestContainer.class)) {
				tests++;
				try {
					method.invoke(null);
					passed++;
				} catch (InvocationTargetException exception) {
					int oldPassed = passed;
					Throwable cause = exception.getCause();
					ExceptionTest[] exceptionTypes = method.getAnnotationsByType(ExceptionTest.class);
					for (ExceptionTest exceptionType : exceptionTypes) {
						if (exceptionType.value().isInstance(cause)) {
							passed++;
							break;
						}
					}

					if (passed == oldPassed) {
						System.out.printf("테스트 %s 실패: %s%n", method, cause);
					}

				} catch (Exception exception) {
					System.out.println("잘못 사용한 @Test: " + method);
				}
			}
		}
		System.out.printf("성공: %d, 실패: %d%n%n", passed, tests - passed);
	}
}
