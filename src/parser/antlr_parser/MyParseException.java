package parser.antlr_parser;

public class MyParseException extends Exception{

    @Override
    public String getMessage() {
        return message;
    }

    private String message;

    public MyParseExceptionType getType() {
        return type;
    }

    private MyParseExceptionType type;

    public MyParseException(String message, MyParseExceptionType type) {
        this.message = message;
        this.type = type;
    }


}
