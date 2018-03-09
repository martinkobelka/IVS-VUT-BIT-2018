package parser.antlr_parser;

public class ReturnValue {

    private double value;
    private String textRepresentation;

    public ReturnValue() {
        value = 0.0;
        textRepresentation = "";
    }


    public ReturnValue(double value, String textRepresentation) {
        this.value = value;
        this.textRepresentation = textRepresentation;
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

}
