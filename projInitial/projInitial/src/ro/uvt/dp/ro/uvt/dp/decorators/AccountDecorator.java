package ro.uvt.dp.decorators;

import ro.uvt.dp.interfaces.Operations;

public abstract class AccountDecorator implements Operations {
    protected Operations decoratedAccount;

    public AccountDecorator(Operations decoratedAccount) {
        this.decoratedAccount = decoratedAccount;
    }

    @Override
    public double getTotalAmount() {
        return decoratedAccount.getTotalAmount();
    }

    @Override
    public double getInterest() {
        return decoratedAccount.getInterest();
    }

    @Override
    public void depose(double suma) {
        decoratedAccount.depose(suma);
    }

    @Override
    public void retrieve(double suma) {
        decoratedAccount.retrieve(suma);
    }
}
