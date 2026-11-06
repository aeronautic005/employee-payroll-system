public class InvalidPayrollException extends Exception {
    private String fieldName;
    private double invalidValue;

    public InvalidPayrollException(String message) {
        super(message);
        this.fieldName = "Unknown";
        this.invalidValue = 0.0;
    }

    public InvalidPayrollException(String fieldName, double invalidValue, String message) {
        super(String.format("Invalid %s [%.2f]: %s", fieldName, invalidValue, message));
        this.fieldName = fieldName;
        this.invalidValue = invalidValue;
    }

    public String getFieldName() {
        return fieldName;
    }

    public double getInvalidValue() {
        return invalidValue;
    }
}
