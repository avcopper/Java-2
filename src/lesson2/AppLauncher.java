package lesson2;

import lesson2.exceptions.MyArrayDataException;
import lesson2.exceptions.MyArraySizeException;

/**
 * 1. Напишите метод, на вход которого подаётся двумерный строковый массив размером 4х4, при подаче массива другого
 *    размера необходимо бросить исключение MyArraySizeException.
 * 2. Далее метод должен пройтись по всем элементам массива, преобразовать в int, и просуммировать. Если в каком-то
 *    элементе массива преобразование не удалось (например, в ячейке лежит символ или текст вместо числа), должно быть
 *    брошено исключение MyArrayDataException, с детализацией в какой именно ячейке лежат неверные данные.
 * 3. В методе main() вызвать полученный метод, обработать возможные исключения MySizeArrayException и MyArrayDataException,
 *    и вывести результат расчета.
 */
public class AppLauncher {

    public static void main(String[] args) {
        String[][] array = {{"1", "2", "3", "4"},
                            {"5", "6", "7", "8"},
                            {"9", "10", "11", "12"},
                            {"13", "14", "15", "16"}
        };

        try {
            arrayConvert(array);
        } catch (MyArraySizeException e) {
            e.info();
        } catch (MyArrayDataException e) {
            e.info();
        } finally {
            System.out.println();
            System.out.println("Работа программы завершена!");
        }
    }

    private static void arrayConvert(String[][] arr) throws MyArraySizeException, MyArrayDataException {
        if (arr.length != 4) throw new MyArraySizeException("Не тот размерчик 1-го уровня");

        int sum = 0;

        for (int i = 0; i < arr.length; i++) {
            if (arr[i].length != 4) throw new MyArraySizeException("Не тот размерчик 2-го уровня");

            for (int j = 0; j < arr[i].length; j++) {
                try {
                    sum += Integer.parseInt(arr[i][j]);
                } catch (NumberFormatException  e) {
                    throw new MyArrayDataException("Некорректный символ '" + arr[i][j] + "' в массиве по координатам X: " + j + " Y: " + i);
                }
            }
        }
        System.out.println("Сумма: " + sum);
    }
}
