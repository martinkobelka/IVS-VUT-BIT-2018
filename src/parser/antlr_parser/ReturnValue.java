package parser.antlr_parser;

public class ReturnValue {

    private double value;
    private String textRepresentation;
    private ReturnValue next;
    private ReturnValue last;

    public ReturnValue() {
        value = 0.0;
        textRepresentation = "";
        next = null;
        last = null;
    }

    public void setNext(ReturnValue returnValue) {

        if(this.next == null) {
            this.next = returnValue;
            this.last = this.next;
        }
        else {
            this.last.next = returnValue;
            this.last = this.last.next;
        }

    }

    public ReturnValue(double value, String textRepresentation) {
        this.value = value;
        this.textRepresentation = textRepresentation;
        next = null;
        last = null;
    }

    /**
     *
     * @return
     */
    public double getValue() {
        return value;
    }

    /**
     *
     * @param value
     */
    public void setValue(double value) {
        this.value = value;
    }

    /**
     *
     * @return
     */
    public String getTextRepresentation() {
        return textRepresentation;
    }

    /**
     *
     * @param textRepresentation
     */
    public void setTextRepresentation(String textRepresentation) {
        this.textRepresentation = textRepresentation;
    }

    public ReturnValue getNext() {
        return next;
    }
}
