package demo;

public class TryCatchDemo {
    public static void main(String[] args) {
        tryCatchExx();
    }

    public static void tryCatchExx() {


        try {
            System.out.println("Start");
            int i = 1/0;
            System.out.println("End");
        }
        catch (ArithmeticException e) {
            System.out.println("Inside catch ArithmeticException");
            System.out.println(e.getCause());
            System.out.println(e.getStackTrace());
            System.out.println(e.getMessage());
        }
        finally {
            System.out.println("I am the finally message");
        }

    }
}
