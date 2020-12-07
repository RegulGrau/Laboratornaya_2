import java.util.Random;

public class Process {
	public int proc_id, all_time, numofstr, priority = 0;
	public Stream[] streams = new Stream[99];
	public Random rand = new Random();
	public boolean isNotEmpty = true;
	public Process(int process_id) {
		this.priority = rand.nextInt(3) + 1;
		this.proc_id = process_id;
		this.numofstr=rand.nextInt(10) + 1;
		
	}
	public void create_streams() {
		for (int i = 0; i < numofstr + 1; i++) {
			int time = rand.nextInt(10) + 1;
			streams[i]=(new Stream(i, time));
			all_time += time;
		}
	}
	public void perf_proc() {
		System.out.println(String.format("Process " + proc_id + " was successful in "+all_time+" sec."));
	}
} 