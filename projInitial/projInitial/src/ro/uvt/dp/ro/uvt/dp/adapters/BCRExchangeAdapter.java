package ro.uvt.dp.adapters;

import ro.uvt.dp.external.BCRExchangeService;
import ro.uvt.dp.interfaces.CurrencyConverter;

public class BCRExchangeAdapter implements CurrencyConverter {

    private final BCRExchangeService externalService;

    public BCRExchangeAdapter(BCRExchangeService externalService) {
        this.externalService = externalService;
    }

    @Override
    public double convert(double amount, String fromCurrency, String toCurrency) {
        double rate = externalService.getExchangeRate(fromCurrency, toCurrency);
        double result = amount * rate;
        System.out.printf("Converted %.2f %s → %.2f %s (rate=%.4f)%n",
                amount, fromCurrency, result, toCurrency, rate);
        return result;
    }
}
