package pl.wymiana;

import java.util.HashMap;

class Exchange {

    static HashMap<String, Double> currencyMap = new HashMap<>();
    private static double totalAmount;
    private Ecurrency curriences;
    private String curr1;
    private String curr2;
    private double quotation;
    private String currenciesPair;
    private double changedAmount;



    private void setCurrenciesPair() {
        this.currenciesPair = curr1.concat("/").concat(curr2);
    }

    private String getCurrenciesPair() {
        return currenciesPair;
    }

    private void setCurr1(String curr1) {
        this.curr1 = curr1;
    }

    private void setCurr2(String curr2) {
        this.curr2 = curr2;
    }

/*    void importData(){
// metoda przeniesiona do CurrencyQuotations
    }*/

    static double getTotalAmount() {
        for(Ecurrency ec : Ecurrency.values()){
            System.out.println(ec + " " + ec.getAmount());
        }
        return totalAmount;
    }



    double doChange(String givenIndexCurr1, String givenIndexCurr2, double amount){

        setCurr1(Ecurrency.findByIndexOrThrow(givenIndexCurr1).toString());
        setCurr2(Ecurrency.findByIndexOrThrow(givenIndexCurr2).toString());
        setCurrenciesPair();



        //new CurrencyQuotations().importData();  // odwołanie przez nowy obiekt
        CurrencyQuotations.importData();            // odwołanie do metody statycznej

        // petla zewn sprpawdza czy wystepuje para "curr1/curr2" w hashMapie, jesli nie reversed = true

        if(currencyMap.containsKey(currenciesPair)){
            quotation = currencyMap.get(currenciesPair);
            changedAmount = (float)amount * quotation;

            System.out.println("Wymiana po kursie " + quotation);
        }else{
            String trimmed = currenciesPair.substring(0,3);
            currenciesPair = currenciesPair.substring(4);
            currenciesPair = currenciesPair.concat("/").concat(trimmed);
            quotation = currencyMap.get(currenciesPair);
            changedAmount = (float)amount / quotation;

            System.out.println("Wymiana po kursie " + quotation);
        }

        //sprawdzenie czy wystarczy srodków na wymiane
        if (changedAmount > Ecurrency.findByIndexOrThrow(givenIndexCurr2).getAmount()) {
            changedAmount = 0;
        }else{
            // todo: aktualizacja portfela walut, dochodzi jedna waluta odchodzi druga
            double actualAmount = Ecurrency.findByIndexOrThrow(givenIndexCurr1).getAmount();
            //System.out.println(actualAmount + " " + amount + " " + (actualAmount+amount));
            actualAmount=actualAmount+amount;
            Ecurrency.findByIndexOrThrow(givenIndexCurr1).setAmount(actualAmount);

            actualAmount = Ecurrency.findByIndexOrThrow(givenIndexCurr2).getAmount();

            actualAmount = actualAmount - changedAmount;
            actualAmount = Math.round(actualAmount*100);
            //System.out.println(actualAmount);
            //System.out.println(actualAmount/100);

            Ecurrency.findByIndexOrThrow(givenIndexCurr2).setAmount(actualAmount/100);
        }

        return changedAmount;

    }
}
