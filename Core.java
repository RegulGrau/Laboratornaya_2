import java.util.Random;

public class Core {
	public Random rand = new Random();
	public Process [] proc = new Process[99];
	
	public int allocated_time, numb_proc;
	public void create_proc() {
		for (int i = 0; i < numb_proc; i++) {
			proc[i]=new Process(i);
			proc[i].create_streams();
			
		}
	}
	public void print() {
		for (int i = 0; i < numb_proc; i++) {
			System.out.println(String.format("\nProcess "+proc[i].proc_id+" with priority "+proc[i].priority+" {"));	
			for (int j = 0; j < proc[i].numofstr; j++) {
				System.out.println(String.format("Stream %d - time: %d sec.", proc[i].streams[j].stream_id, proc[i].streams[j].req_time));
			}
			System.out.println("}");
		}
		System.out.println();
	}
	public void start() {
		allocated_time = 1;
		numb_proc = 3;
		create_proc();
		print();
		boolean flag = true;
		System.out.println("Results:\n");
		while (flag) {
			flag = false;
			
			for (int i = 0; i < numb_proc; i++) {
				allocated_time = 3;
				if (proc[i].isNotEmpty)
				{
					allocated_time += proc[i].priority;
					System.out.println(String.format("Process "+ (int)proc[i].proc_id +" started running {"));
					boolean flag2 = false;
					for (int j = 0; j < proc[i].numofstr; j++) {
						
						if (proc[i].streams[j].isNotEmpty)
						{
							
						int required_time = proc[i].streams[j].req_time;
						int stream_id = proc[i].streams[j].stream_id;
						if (required_time > allocated_time) {
							int remaining_time = required_time - allocated_time;
							proc[i].streams[j].change_time(allocated_time);
							System.out.println(String.format("Stream "+stream_id+" was break (required time: "+required_time+" sec. - allocated time: "+allocated_time+" sec. - remaining time: "+remaining_time+" sec.)"));
						} else {
							System.out.println(String.format("Stream "+ proc[i].streams[j].stream_id + " was successful in "+ proc[i].streams[j].req_time+ "sec." ));
							proc[i].streams[j].isNotEmpty=false;
						}
						
						}
						flag2=flag2||proc[i].streams[j].isNotEmpty;
					}
					if (!flag2) {
						proc[i].perf_proc();
						
						proc[i].isNotEmpty=false;
					}
					System.out.println("}\n");
				}
				flag = flag||proc[i].isNotEmpty;
				
			}
		}
	}
}