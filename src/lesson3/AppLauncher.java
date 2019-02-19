package lesson3;

import java.util.HashMap;
import java.util.Map;

/**
 * 1. Создать массив с набором слов (10-20 слов, должны встречаться повторяющиеся). Найти и вывести список уникальных слов,
 * из которых состоит массив (дубликаты не считаем). Посчитать сколько раз встречается каждое слово.
 * 2. Написать простой класс ТелефонныйСправочник, который хранит в себе список фамилий и телефонных номеров. В этот
 * телефонный справочник с помощью метода add() можно добавлять записи. С помощью метода get() искать номер телефона
 * по фамилии. Следует учесть, что под одной фамилией может быть несколько телефонов (в случае однофамильцев), тогда при
 * запросе такой фамилии должны выводиться все телефоны.
 *
 * Желательно как можно меньше добавлять своего, чего нет в задании (т.е. не надо в телефонную запись добавлять еще
 * дополнительные поля (имя, отчество, адрес), делать взаимодействие с пользователем через консоль и т.д.). Консоль
 * желательно не использовать (в том числе Scanner), тестировать просто из метода main() прописывая add() и get().
 */
public class AppLauncher {
    public static void main(String[] args) {
        /* Задание 1 */
        String[] words = {"dog", "cat", "goose", "orange", "apple",
                          "potato", "humster", "tomato", "book", "coffee",
                          "orange", "hat", "dog", "coffee", "dog"};
        Map<String, Integer> hm = new HashMap<>();

//        for (int i = 0; i < words.length; i++) {
//            if (hm.containsKey(words[i])) hm.put(words[i], hm.get(words[i]) + 1);
//            else hm.put(words[i], 1);
//        }

        for (String word : words) {
            if (hm.containsKey(word)) hm.put(word, hm.get(word) + 1);
            else hm.put(word, 1);
        }

        System.out.println(hm.entrySet());
        System.out.println();

        for (Map.Entry<String, Integer> element : hm.entrySet()){
            System.out.println("Слово <" + element.getKey() + "> встречается " + element.getValue() + " раз(а)");
        }

        /* Задание 2 */
        PhoneBook pb = new PhoneBook();
        pb.add("Ivanov", "123");
        pb.add("Petrov", "456");
        pb.add("Sidorov", "789");
        pb.add("Ivanov", "987");

        System.out.println();
        System.out.println("Sidorov: " + pb.get("Sidorov"));
        System.out.println("Ivanov: " + pb.get("Ivanov"));
    }
}
