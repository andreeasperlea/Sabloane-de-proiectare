package ro.uvt.dp.interfaces;

public interface CurrencyConverter {
    double convert(double amount, String fromCurrency, String toCurrency);
}
