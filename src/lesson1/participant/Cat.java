package lesson1.participant;

public class Cat extends Animal {
    public static final String BREED_SIAM = "SIAM";
    public static final String BREED_BRITISH = "BRITISH";
    public static final String BREED_PERSIAN = "PERSIAN";

    private final double JUMP_DEFAULT = 5.0;
    private final double RUN_DEFAULT  = 200.0;

    private final double RATE_SIAM    = 1.2;
    private final double RATE_BRITISH = 0.8;
    private final double RATE_PERSIAN = 0.6;

    private String breed;

    public Cat(String name, int age, String breed)
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
    public String toString() {
        return super.toString() + "Cat {name: " + super.getName() + ", age: " + super.getAge() + ", breed: " + breed + ", result: " + isResult() + "}";
    }

    /**
     * Возвращает максимально возможное расстояние для данной породы и вида спорта
     * @param breed - порода
     * @param constant - среднее расстояние
     * @return double
     */
    private double getMaxDistance(String breed, double constant) {
        if (breed.equalsIgnoreCase(BREED_SIAM))
        {
            return constant * RATE_CAT * RATE_SIAM;
        }
        else if (breed.equalsIgnoreCase(BREED_BRITISH))
        {
            return constant * RATE_CAT * RATE_BRITISH;
        }
        else if (breed.equalsIgnoreCase(BREED_PERSIAN))
        {
            return constant * RATE_CAT * RATE_PERSIAN;
        }
        else
        {
            return constant * RATE_CAT;
        }
    }
}
