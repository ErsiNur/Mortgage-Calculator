public class MortgageComputer {
    public MortgageComputer() {
    }

    public static double computePayment(double amount, double rate, int years) {
        rate /= 100.0D;
        double monthlyRate = rate / 12.0D;
        double months = (double)(years * 12);
        double payment = amount * monthlyRate * Math.pow(1.0D + monthlyRate, months) / (Math.pow(1.0D + monthlyRate, months) - 1.0D);
        return payment;
    }
}
