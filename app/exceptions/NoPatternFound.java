package exceptions;


import java.util.function.Supplier;

public class NoPatternFound extends Exception {
    public NoPatternFound(String message) {
        super(message);
    }
}
