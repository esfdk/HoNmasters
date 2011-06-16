package bosk.jakob.kodeEksempler.Traade;


public class JobProvider implements Runnable{
	PrintQueue queue;
	String user;

	public JobProvider (PrintQueue queue, String user){
		this.queue = queue;
		this.user = user;
	}

	public void run(){
		synchronized(queue){
			queue.addJob(new PrintJob(user));
			queue.notifyAll();
		}
	}
}