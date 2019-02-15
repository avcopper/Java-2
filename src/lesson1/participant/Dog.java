package lesson1.participant;

public class Dog extends Animal implements Swiming {
    public static final String BREED_GERMAN = "GERMAN";
    public static final String BREED_BULLDOG = "BULLDOG";
    public static final String BREED_CHIHUAHUA = "CHIHUAHUA";

    private final double JUMP_DEFAULT = 8.0;
    private final double RUN_DEFAULT  = 2000.0;
    private final double SWIM_DEFAULT = 500.0;

    private final double RATE_GERMAN    = 1.2;
    private final double RATE_BULLDOG   = 0.7;
    private final double RATE_CHIHUAHUA = 0.4;

    private String breed;

    public Dog(String name, int age, String breed)
    {
        super(name, age);
        this.breed = breed;
    }

    @Override
    public boolean jump(double distance) {
        return distance <= getMaxDistance(breed, JUMP_DEFAULT);
    }

    @Override
    public boolean run(double distance) {
        return distance <= getMaxDistance(breed, RUN_DEFAULT);
    }

    @Override
    public boolean swim(double distance) {
        return distance <= getMaxDistance(breed, SWIM_DEFAULT);
    }

    @Override
    public String toString() {
        return super.toString() + "Dog {name: " + super.getName() + ", age: " + super.getAge() + ", breed: " + breed + ", result: " + isResult() + "}";
    }

    /**
     * Возвращает максимально возможное расстояние для данной породы и вида спорта
     * @param breed - порода
     * @param constant - среднее расстояние
     * @return double
     */
    private double getMaxDistance(String breed, double constant) {
        if (breed.equalsIgnoreCase(BREED_GERMAN))
        {
            return constant * RATE_DOG * RATE_GERMAN;
        }
        else if (breed.equalsIgnoreCase(BREED_BULLDOG))
        {
            return constant * RATE_DOG * RATE_BULLDOG;
        }
        else if (breed.equalsIgnoreCase(BREED_CHIHUAHUA))
        {
            return constant * RATE_DOG * RATE_CHIHUAHUA;
        }
        else
        {
            return constant * RATE_DOG;
        }
    }
}
