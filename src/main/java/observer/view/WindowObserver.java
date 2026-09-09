package observer.view;

import observer.model.WeatherObserver;

public class WindowObserver implements WeatherObserver {

	@Override
	public void update(double temperature) {
		System.out.printf("Window: Showing outdoor temperature: %.1f°C%n", temperature);
	}
}
