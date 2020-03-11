package pl.wymiana;
import java.util.Scanner;

public class Service {

    Exchange exchange = new Exchange();

    void currencyChoice() {
        for (Ecurrency ec : Ecurrency.values()) {
            System.out.println(ec + " - wybierz [" + ec.getIndex() + "]");
        }

    }

    void makeTransaction() {
        Scanner sc = new Scanner(System.in);

        System.out.println("Jaką walutę chcesz wymienić?");
        currencyChoice();                              //wybór waluty po indeksie

        String curr1Index = sc.nextLine();

        System.out.println("Podaj kwotę do wymiany w walucie " + Ecurrency.findByIndexOrThrow(curr1Index));
        Scanner scd = new Scanner(System.in);
        double curr1Amount = scd.nextDouble();

        System.out.println("Na jaką walutę chcesz wymienić?");
        currencyChoice();

        String curr2Index = sc.nextLine();


// todo zaokraglic
        double exchangedAmount = exchange.doChange(curr1Index, curr2Index, curr1Amount);         // dokonaj wymiany waluty i zwroc kwotę float

        if (exchangedAmount > 0) {
            System.out.println("Otrzymujesz kwotę " + exchangedAmount + " w walucie " + Ecurrency.findByIndexOrThrow(curr2Index));
        }else{
            System.out.println("Transakcja nie może być zrealizowana"); // + result jaki błąd (klasa Result jak w kaminsko)

        }
    }


}
