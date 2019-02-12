package lesson1;

import lesson1.participant.Animal;

public class Team {
    private String name;
    private Animal[] animals;

    public Team(String name, Animal[] animals)
    {
        this.name = name;
        this.animals = animals;
    }

    /**
     * Выводит информацию о всех членах команды
     */
    public void info() {
        System.out.println("Команда: " + name);
        for (Animal animal : animals) {
            System.out.println(animal.toString());
        }
    }

    /**
     * Выводит информацию о членах команды, успешно прошедших испытания
     */
    public void success() {
        System.out.println("Успешно прошли испытания:");
        for (Animal animal : animals) {
            if (animal.isResult()) System.out.println(animal.toString());
        }
    }

    /**
     * @return - возвращает массив участников
     */
    public Animal[] getAnimals() {
        return animals;
    }
}
