package bosk.jakob.kodeEksempler.Traade;

public class TestPrintJobs {

	public static void main(String[] args) throws InterruptedException {
		PrintQueue sharedqueue = new PrintQueue();

		Thread provider1 = new Thread(new JobProvider(sharedqueue, "signe"));
		Thread provider2 = new Thread(new JobProvider(sharedqueue, "jakob"));
		// Thread provider3 = new Thread(new JobProviderBatch(sharedqueue,
		// "kenn"));

		Thread consumer = new Thread(new JobConsumer(sharedqueue), "Consumer");

		consumer.start();
		provider1.start();
		provider2.start();
		Thread.sleep(10000);
		// provider3.start();
		System.exit(0);
	}
}