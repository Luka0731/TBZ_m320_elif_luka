package ch.tbz.budgedbuddy.domain;


import ch.tbz.budgedbuddy.exception.ParseException;

public class Money {
    private final int cents;

    public Money(int cents) {
        this.cents = cents;
    }

    public static Money ofString(String string) throws ParseException {
        try {
            double d = Double.parseDouble(string);
            return new Money((int)Math.round(d * 100));
        } catch (NumberFormatException _) {
            throw new ParseException("Betrag keine Zahl");
        }
    }

    public Money add(Money money) {
        return new Money(this.cents + money.cents);
    }

    public int compareTo(Money money) {
        return Integer.compare(this.cents, money.cents);
    }

    public int getCents() {
        return cents;
    }

    @Override
    public String toString() {
        return (cents / 100.0) + " CHF";
    }
}