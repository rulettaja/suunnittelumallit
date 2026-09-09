package observer.view;

import observer.model.WeatherObserver;

public class PhoneObserver implements WeatherObserver {

	@Override
	public void update(double temperature) {
		System.out.printf("Phone: Weather notification: %.1f°C%n", temperature);
	}
}
