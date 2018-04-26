package OmniIndiMarketplace;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Main {

	public static boolean RUN_SIMULATION = false;
	private static final long SIMULATION_TIME = 30;
	public static final boolean OMNI_INDI = false;
	
	public static int NUM_USERS = 1000;
	public static int NUM_RESEARCH = 10;
	
	public static void main(String[] args) {
		
		String str = "";
	    BufferedWriter writer;
		try {
			writer = new BufferedWriter(new FileWriter(Constants.FILE_USERS));
		    writer.write(str);		     
		    writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	    
		DataMiner Miner = new DataMiner();
		Constants.Miner = Miner;
		HealthcareAgent hp1 = new HealthcareAgent(Constants.HOSPITAL_SMALL);
		HealthcareAgent hp2 = new HealthcareAgent(Constants.HOSPITAL_MEDIUM);
		HealthcareAgent hp3 = new HealthcareAgent(Constants.HOSPITAL_LARGE);
		
		
		InsuranceAgent Ins = new InsuranceAgent();
		Constants.Insurance = Ins;
		Thread in = new Thread(Ins);
		in.start();
		
		RUN_SIMULATION = true;
		startHealthcare(hp1, hp2, hp3);
		introduceUsers(NUM_USERS);
		introduceResearch(NUM_RESEARCH);
		
		printProgress();
		RUN_SIMULATION = false;
		
		double[] avg_cost = {0.0,0.0,0.0};
		int[] condition_counts = {0, 0, 0};
		for(int i=0; i<Constants.Users.size(); i++)
		{
			avg_cost[Constants.Users.get(i).getCondition()] += Constants.Users.get(i).getCost();
			condition_counts[Constants.Users.get(i).getCondition()]++;

		}
		
		System.out.println("Average cost for Condition 0: " + (avg_cost[0]/condition_counts[0]));
		System.out.println("Average cost for Condition 1: " + (avg_cost[1]/condition_counts[1]));
		System.out.println("Average cost for Condition 2: " + (avg_cost[2]/condition_counts[2]));
		System.out.println("Data Miner Money: " + Constants.Miner.getMoney());

	}

	private static void introduceResearch(int num_research) {
		// TODO Auto-generated method stub
		for(int i=0; i<num_research; i++)
		{
			double condition = Math.random() * 3;
			System.out.println((int)condition);
			ResearchAgent r1 = new ResearchAgent((int)condition);
			Constants.Researchers.add(r1);
			Thread t = new Thread(r1);
			t.start();
		}
		
//		Constants.Researchers.get(5).print = true;
	}

	private static void introduceUsers(int num_users) {
		for(int i=0; i<num_users; i++)
		{
			UserAgent u1 = new UserAgent();
			Constants.Users.add(u1);
			Thread t = new Thread(u1);
			t.start();
		}
	}

	private static void startHealthcare(HealthcareAgent hp1, HealthcareAgent hp2, HealthcareAgent hp3) {
		// TODO Auto-generated method stub
		Constants.Hospitals.add(hp1);
		Constants.Hospitals.add(hp2);
		Constants.Hospitals.add(hp3);
		Thread H1 = new Thread(hp1);
		Thread H2 = new Thread(hp2);
		Thread H3 = new Thread(hp3);
		
		H1.start();H2.start();H3.start();
	}

	private static void printProgress() {
		SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
		Date date1 = new Date();
		long difference = 0;
		
		int prev_value = 0;
		while(difference < SIMULATION_TIME)
		{
			Date date2 = new Date();
			difference = (date2.getTime() - date1.getTime())/1000;
			if(prev_value + 1 < difference)
			{
				prev_value++;
				System.out.println("Percent Complete:" + ((int) 100*difference/SIMULATION_TIME));
			}
		}
		System.out.println("Simulation End");
	}

}
