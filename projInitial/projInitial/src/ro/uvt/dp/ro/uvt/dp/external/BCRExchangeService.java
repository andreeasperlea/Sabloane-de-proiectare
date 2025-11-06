package ro.uvt.dp.external;

import java.util.HashMap;
import java.util.Map;

public class BCRExchangeService {

    private final Map<String, Double> rates = new HashMap<>();

    public BCRExchangeService() {
        rates.put("EUR_RON", 4.97);
        rates.put("RON_EUR", 1 / 4.97);
        rates.put("USD_RON", 4.60);
        rates.put("RON_USD", 1 / 4.60);
    }
    public double getExchangeRate(String fromCurrency, String toCurrency) {
        String key = fromCurrency + "_" + toCurrency;
        return rates.getOrDefault(key, 1.0);
    }
}
