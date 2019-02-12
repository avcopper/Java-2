package lesson1;

import lesson1.participant.Animal;
import lesson1.participant.Dog;
import lesson1.participant.Human;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Course {
    private final double MAX_JUMP_DISTANCE = 5;
    private final double MAX_RUN_DISTANCE = 300;
    private final double MAX_SWIM_DISTANCE = 200;

    private double[] distance = new double[3];


    public Course()
    {
        distance[0] = new BigDecimal(Math.random() * MAX_JUMP_DISTANCE + 1).setScale(1, RoundingMode.UP).doubleValue();
        distance[1] = new BigDecimal(Math.random() * MAX_RUN_DISTANCE + 1).setScale(1, RoundingMode.UP).doubleValue();
        distance[2] = new BigDecimal(Math.random() * MAX_SWIM_DISTANCE + 1).setScale(1, RoundingMode.UP).doubleValue();
    }

    /**
     * Запускает испытания для каждого участника команды и устанавливает результат
     * @param team - массив участников
     */
    public void startCompetition(Team team)
    {
        for (Animal animal : team.getAnimals()) {
            boolean jumpResult = animal.jump(distance[0]);
            boolean runResult = animal.run(distance[1]);
            boolean swimResult = true;

            if (animal instanceof Dog) {
                swimResult = ((Dog)animal).swim(distance[2]);
            }

            if (animal instanceof Human) {
                swimResult = ((Human)animal).swim(distance[2]);
            }

            if (jumpResult && runResult && swimResult) animal.setResult(true);
        }
    }
}
