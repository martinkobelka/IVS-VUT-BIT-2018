package my_math;

public class MathException extends Exception{

    private String message;

    public MathException(String message) {

        this.message = message;

    }

    @Override
    public String toString() {
        return this.message;
    }

}
