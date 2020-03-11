package pl.wymiana;

public class Main {
    public static void main(String[] args) {

        Service service = new Service();



        System.out.println(Exchange.getTotalAmount());
/*        System.out.println(Ecurrency.findByIndexOrThrow("2"));
        System.out.println(Ecurrency.findByIndexOrThrow("2").getAmount());*/

        //System.out.println("EUR/USD".substring(0,3));

        service.makeTransaction();

        //stream po enumie
        //Ecurrency.stream().forEach(System.out::println);

        System.out.println(Exchange.getTotalAmount());
    }
}
