package bosk.jakob.kodeEksempler.Traade;


public class JobConsumer implements Runnable {

	PrintQueue queue;

	public JobConsumer(PrintQueue queue) {
		this.queue = queue;
	}

	public void run() {
		synchronized(queue){
			while(true){	
				if(queue.size() == 0){
					try {
						queue.wait();
					} catch (InterruptedException e) {
						removeJobs();
					}
				}
				else{
					removeJobs();
				}
			}
		}
	}

	public void removeJobs(){
		while(queue.size() != 0){
			System.out.println(queue.removeJob());
		}
	}
}