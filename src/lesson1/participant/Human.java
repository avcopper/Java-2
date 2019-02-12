package lesson1.participant;

public class Human extends Animal implements Swiming {
    private final double JUMP_DEFAULT = 2.0;
    private final double RUN_DEFAULT  = 1000.0;
    private final double SWIM_DEFAULT = 1000.0;

    private Gender gender;

    public Human(String name, int age, Gender gender)
    {
        super(name, age);
        this.gender = gender;
    }

    @Override
    public boolean jump(double distance) {
        return distance <= getMaxDistance(gender, JUMP_DEFAULT);
    }

    @Override
    public boolean run(double distance) {
        return distance <= getMaxDistance(gender, RUN_DEFAULT);
    }

    @Override
    public boolean swim(double distance) {
        return distance <= getMaxDistance(gender, SWIM_DEFAULT);
    }

    @Override
    public String toString() {
        return super.toString() + "Human {name: " + super.getName() + ", age: " + super.getAge() + ", gender: " + gender + ", result: " + isResult() + "}";
    }

    /**
     * Возвращает максимально возможное расстояние для данного пола и вида спорта
     * @param gender - пол
     * @param constant - среднее расстояние
     * @return double
     */
    private double getMaxDistance(Gender gender, double constant) {
        return constant * RATE_HUMAN * gender.getRate();
    }
}
