package lesson1;

import lesson1.participant.*;

public class TournamentLauncher {
    public static void main(String[] args) {
        Animal[] animals = new Animal[6];
        animals[0] = new Cat("Барсик", 4, Cat.BREED_SIAM);
        animals[1] = new Cat("Соня", 4, Cat.BREED_SIAM);
        animals[2] = new Dog("Мухтар", 4, Cat.BREED_SIAM);
        animals[3] = new Dog("Рекс", 4, Cat.BREED_SIAM);
        animals[4] = new Human("Василий", 24, Gender.MALE);
        animals[5] = new Human("Юлий", 20, Gender.FEMALE);

        Course course = new Course();

        Team team = new Team("Мохнатые", animals);

        course.startCompetition(team);

        team.info();

        System.out.println();

        team.success();
    }
}
