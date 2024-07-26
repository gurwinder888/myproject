package GenericUtilities;

public class ScenarioContext {
    private static ThreadLocal<Throwable> exception = new ThreadLocal<>();

    public static void setException(Throwable throwable) {
        exception.set(throwable);
    }

    public static Throwable getException() {
        return exception.get();
    }

    public static void clearException() {
        exception.remove();
    }
}
