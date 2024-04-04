import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

public class PuttingIntoPractice {

    public static void main(String... args) {
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario", "Milan");
        Trader alan = new Trader("Alan", "Cambridge");
        Trader brian = new Trader("Brian", "Cambridge");

        List<Trader> traders = Arrays.asList(raoul, mario, alan, brian);

        List<Transaction> transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );


        System.out.println(transaction2011SortedAsc(transactions));
        System.out.println(distinctCityTrader(traders));
        System.out.println(tradersFromCambridgeSortedByName(traders));
        System.out.println(stringTraderSortedByName(traders));
        System.out.println(traderFromMilan(traders));
        System.out.println(sumTransactionFromCambridge(transactions));
        System.out.println(maxSumTransaction(transactions).getAsInt());

        Optional<Transaction> result = transactionWithMinSum(transactions);
        System.out.println(result.orElse(null));

    }


//  1. Найти все транзакции за 2011 год и отсортировать их по сумме (от меньшей к большей).
    public static List<String> transaction2011SortedAsc(List<Transaction> transactions){
        List<String> collect = transactions.stream()
                .filter((transaction) -> transaction.getYear() == 2011)
                .sorted((transaction1, transaction2) ->
                        Integer.compare(transaction1.getValue(), transaction2.getValue()))
                .map((transaction) -> transaction.toString())
                .collect(toList());
        return collect;
    }


//  2. Вывести список неповторяющихся городов, в которых работают трейдеры.
    public static List<String> distinctCityTrader(List<Trader> traders){
        List<String> collect = traders.stream()
                .map((trader) -> trader.getCity())
                .distinct()
                .collect(toList());
        return collect;
    }

//  3. Найти всех трейдеров из Кембриджа и отсортировать их по именам.
    public static List<String> tradersFromCambridgeSortedByName(List<Trader> traders){
        List<String> collect = traders.stream()
                .filter((trader) -> trader.getCity() == "Cambridge")
                .sorted((trader1, trader2) ->
                        trader1.getName().compareTo(trader2.getName()))
                .map((trader) -> trader.toString())
                .collect(toList());
        return collect;
    }

//  4. Вернуть строку со всеми именами трейдеров, отсортированными в алфавитном порядке.
    public static String stringTraderSortedByName(List<Trader> traders) {
        String collect = traders.stream()
                .map((trader) -> trader.getName())
                .sorted((trader1, trader2) ->
                        trader1.compareTo(trader2))
                .collect(Collectors.joining(" "));
        return collect;
    }

//  5. Выяснить, существует ли хоть один трейдер из Милана.
    public static Boolean traderFromMilan(List<Trader> traders) {
        Boolean result = traders.stream()
                .anyMatch((trader) -> trader.getCity() == "Milan");
        return result;
    }

//  6. Вывести суммы всех транзакций трейдеров из Кембриджа.
    public static Integer sumTransactionFromCambridge(List<Transaction> transactions) {
        Integer result = transactions.stream()
                .filter((transaction) -> transaction.getTrader().getCity() == "Cambridge")
                .mapToInt((transaction) -> transaction.getValue())
                .sum();
        return result;
    }


//  7. Какова максимальная сумма среди всех транзакций?
    public static OptionalInt maxSumTransaction(List<Transaction> transactions) {
        OptionalInt result = transactions.stream()
                .mapToInt((transaction) -> transaction.getValue())
                .max();
        return result;
    }



//  8. Найти транзакцию с минимальной суммой.
    public static Optional<Transaction> transactionWithMinSum(List<Transaction> transactions) {
        Optional<Transaction> result = transactions.stream()
                .min((transaction1, transaction2) ->
                        Integer.compare(transaction1.getValue(), transaction2.getValue()));
        return result;
    }
}

