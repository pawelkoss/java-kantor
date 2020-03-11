package pl.wymiana;

import java.util.stream.Stream;

public enum Ecurrency {
    USD("1", 4540.80),
    EUR("2", 1523.12),
    CHF("3", 2301.40),
    GBP("4", 1754.50),
    PLN("5", 8903.44);


    private String index;
    private double amount;

    Ecurrency(String index, double amount) {
        this.index = index;
        this.amount = amount;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getIndex() {
        return index;
    }

    static Ecurrency findByIndexOrThrow(String value){

        for (Ecurrency rodz: Ecurrency.values()){
            if(rodz.index.equals(value)){
                return rodz;
            }
        }
        throw new IllegalArgumentException("Cannot find currency by " + value);
    }

    public static Stream<Ecurrency> stream() {
        return Stream.of(Ecurrency.values());
    }
}
