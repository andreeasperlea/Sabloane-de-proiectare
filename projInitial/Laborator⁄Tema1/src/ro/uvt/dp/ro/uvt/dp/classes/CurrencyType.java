package ro.uvt.dp.classes;

public enum CurrencyType {
    EUR {
        @Override
        public double calculateDailyInterest(double amount) {
            return 0.1; 
        }
    },
    RON {
        @Override
        public double calculateDailyInterest(double amount) {
            return amount < 500 ? 0.3 : 0.8;
        }
    };

    public abstract double calculateDailyInterest(double amount);
}
