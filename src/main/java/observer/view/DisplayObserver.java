package observer.view;

import observer.model.WeatherObserver;

public class DisplayObserver implements WeatherObserver {

	@Override
	public void update(double temperature) {
		System.out.printf("Display: Current temperature is %.1f°C%n", temperature);
	}
}
