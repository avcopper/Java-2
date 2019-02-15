package lesson1.participant;

public abstract class Animal implements Jumping, Runing
{
    protected final static double RATE_CAT = 0.8;
    protected final static double RATE_DOG = 1.5;
    protected final static double RATE_HUMAN = 1.0;

    private String name;
    private int age;
    private boolean result;

    public Animal(String name, int age)
    {
        this.name = name;
        this.age  = age;
        this.result = false;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "Animal -> ";
    }
}
