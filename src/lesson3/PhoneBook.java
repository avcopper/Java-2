package lesson3;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class PhoneBook {
    private Map<String, HashSet<String>> phoneBook;

    public PhoneBook() {
        this.phoneBook = new HashMap<>();
    }

    /**
     * Добавляет номер фамилию и номер телефона в телефонную книгу
     * @param lastName - фамилия
     * @param phone - номер телефона
     */
    public void add(String lastName, String phone) {
        HashSet<String> numbers;

        if (phoneBook.containsKey(lastName)) numbers = phoneBook.get(lastName);
        else numbers = new HashSet<>();

        numbers.add(phone);
        phoneBook.put(lastName, numbers);
    }

    /**
     * Возвращает все телефонные номера по фамилии
     * @param lastName - фамилия
     * @return коллекцию телефонных номеров
     */
    public Set<String> get(String lastName) {
        return phoneBook.get(lastName);
    }
}
