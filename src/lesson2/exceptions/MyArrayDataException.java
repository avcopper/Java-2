package lesson2.exceptions;

public class MyArrayDataException extends Exception {
    private String message;

    public MyArrayDataException(String message) {
        this.message = message;
    }

    public void info() {
        System.out.println(message);
    }
}
