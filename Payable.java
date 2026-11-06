public interface Payable {
    /**
     * Calculates the weekly compensation for the payable entity.
     * @return the total weekly earnings.
     */
    double calculateWeeklyEarnings();

    /**
     * Returns a brief one-line payment summary or classification.
     * @return summary string.
     */
    String getPaymentSummary();
}
