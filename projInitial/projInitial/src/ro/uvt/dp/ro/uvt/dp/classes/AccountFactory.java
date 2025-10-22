package ro.uvt.dp.classes;

public abstract class AccountFactory {
    public abstract Account createAccount(String accountNumber, double initialAmount, CurrencyType currencyType);
}
