package lesson5;

public class MyThread extends Thread {
    private float[] array;

    public MyThread(float[] arr)
    {
        this.array = arr;
    }

    @Override
    public void run() {
        calculate();
    }

    /**
     * Производим математические вычисления
     */
    private void calculate()
    {
        for (int i = 0; i < array.length; i++) {
            array[i] = (float)(array[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }
    }

    /**
     * Возвращаем массив
     * @return array
     */
    public float[] getArray() {
        return array;
    }
}
