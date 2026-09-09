package observer;

import observer.model.WeatherStation;
import observer.view.DisplayObserver;
import observer.view.PhoneObserver;
import observer.view.WindowObserver;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        WeatherStation weatherStation = new WeatherStation();
        DisplayObserver displayObserver = new DisplayObserver();
        PhoneObserver phoneObserver = new PhoneObserver();
        WindowObserver windowObserver = new WindowObserver();

        weatherStation.registerObserver(displayObserver);
        weatherStation.registerObserver(phoneObserver);
        weatherStation.registerObserver(windowObserver);
        weatherStation.start();

        Thread.sleep(12000);
        weatherStation.removeObserver(phoneObserver);
        System.out.println("Phone observer removed.");

        Thread.sleep(8000);
        weatherStation.stopStation();
        weatherStation.join();
    }
}
