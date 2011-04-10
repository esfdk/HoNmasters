package bosk.ovelse07.ovelse71;

public class Main {
	public static void main(String[] args) {
			TrafficLight trafficlight = new TrafficLight();
			trafficlight.setSignalColor(SignalColor.GREEN);
			System.out.println(trafficlight);
			trafficlight.setSignalColor(SignalColor.RED);
			System.out.println(trafficlight);
			}
	}