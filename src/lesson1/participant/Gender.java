package lesson1.participant;

public enum Gender {
    MALE(1.1), FEMALE(0.9);

    private double rate;

    Gender(double rate)
    {
        this.rate = rate;
    }

    public double getRate()
    {
        return rate;
    }
}
