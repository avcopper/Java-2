package lesson2.exceptions;

public class MyArraySizeException extends Exception {
    private String message;

    public MyArraySizeException(String message) {
        this.message = message;
    }

    public void info() {
        System.out.println(message);
    }
}
