package observer.model;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class WeatherStation extends Thread {

    private static final int MIN_TEMPERATURE = -20;
    private static final int MAX_TEMPERATURE = 40;
    private static final int MIN_INTERVAL_MILLISECONDS = 1000;
    private static final int MAX_INTERVAL_MILLISECONDS = 5000;

    private volatile double temperature;
    private volatile boolean running = true;

    private final List<WeatherObserver> observers = new ArrayList<>();

    public WeatherStation() {
        temperature = ThreadLocalRandom.current().nextDouble(MIN_TEMPERATURE, MAX_TEMPERATURE);
        setName("weather-station");
    }

    public synchronized void registerObserver(WeatherObserver observer) {
        if (!observers.contains(observer)) {
            observers.add(observer);
        }
    }

    public synchronized void removeObserver(WeatherObserver observer) {
        observers.remove(observer);
    }

    public void notifyObservers() {
        List<WeatherObserver> currentObservers;
        synchronized (this) {
            currentObservers = new ArrayList<>(observers);
        }
        for (WeatherObserver observer : currentObservers) {
            observer.update(temperature);
        }
    }

    public double getTemperature() {
        return temperature;
    }

    public void updateTemperature() {
        int change = ThreadLocalRandom.current().nextBoolean() ? 1 : -1;
        temperature = Math.max(MIN_TEMPERATURE, Math.min(MAX_TEMPERATURE, temperature + change));
        notifyObservers();
    }

    public void stopStation() {
        running = false;
        interrupt();
    }

    @Override
    public void run() {
        while (running) {
            updateTemperature();
            try {
                Thread.sleep(ThreadLocalRandom.current().nextInt(
                        MIN_INTERVAL_MILLISECONDS, MAX_INTERVAL_MILLISECONDS + 1));
            } catch (InterruptedException exception) {
                if (!running) {
                    break;
                }
            }
        }
    }
}