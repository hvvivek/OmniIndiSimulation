package OmniIndiMarketplace;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.StampedLock;

public class ResearchAgent implements Runnable 
{
	private double money;
	private int month_in_sim = 0;
	private double[] data = {0.0, 0.0, 0.0};
	private double totalData;
	private int condition;
	private double[] price_list = {0.0, 0.0, 0.0};
	public boolean print = false;
	
	ExecutorService executor = Executors.newFixedThreadPool(2);
	StampedLock lock = new StampedLock();
	
	public ResearchAgent(int condition)
	{
		this.condition = condition;
		if(!Main.OMNI_INDI )
		{
			price_list[condition] = Constants.CONDITION_COSTS[this.condition];
			price_list[0] = Constants.CONDITION_COSTS[0] * (this.condition + 1);
		}
	}
	
	@Override
	public void run() {
		while(Main.RUN_SIMULATION )
		{
			month_in_sim++;
//			this.data[0] = 0.0;
//			this.data[1] = 0.0;
//			this.data[2] = 0.0;
			if(Main.OMNI_INDI)
			{
				//interact with users
				interact_with_users();			
			}
			else
			{
				this.price_list[this.condition] = Constants.Miner.getDataPrice(this.condition);
				this.price_list[0] = Constants.Miner.getDataPrice(0);
				//interact with research
				interact_with_miners();	
			}
			
			totalData = getTotalData();
			// Sleep for a month
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		System.out.println("Research: Condition: " + this.condition + " Data Gathered: " + (int)this.totalData + " Price List: " + printArray(this.price_list) );
	}

	private double getTotalData() {
		return data[0] + data[1] + data[2];
	}

	private void interact_with_miners() {
		this.money -= Constants.RESEARCH_DATA_REQ_RATE[this.condition] *Constants.Miner.getDataPrice(this.condition) * Constants.Miner.getData(this.condition);
		this.money -= Constants.RESEARCH_DATA_REQ_RATE[0] *Constants.Miner.getDataPrice(0) * (this.condition + 1) * Constants.Miner.getData(0);
		
		this.data[this.condition] += Constants.Miner.getData(this.condition); 
		this.data[0] += Constants.Miner.getData(0); 
	}

	private void interact_with_users() 
	{

		synchronized(lock) {
		if(print)
		{
			System.out.println("Data Gathered for Condition " + this.condition + ": " + this.data[this.condition] + "/" + Constants.RESEARCH_DATA_REQ_RATE[this.condition]);
		}
		if(this.data[this.condition] < Constants.RESEARCH_DATA_REQ_RATE[this.condition])
		{
			//Increase Price
			this.price_list[this.condition] += (Constants.RESEARCH_DATA_REQ_RATE[this.condition] - this.data[this.condition])/(10 + (month_in_sim));
			if(print)
			{
				System.out.println(this.data[this.condition] + " " + Constants.RESEARCH_DATA_REQ_RATE[this.condition]);
			}
			this.data[this.condition] = 0;

			if(print)
			{
				System.out.println("Increasing price to " + this.price_list[this.condition]);
			}

		}
		else
		{
			//Decrease Price
			this.price_list[this.condition] += (Constants.RESEARCH_DATA_REQ_RATE[this.condition] - this.data[this.condition])/(10+ (month_in_sim));
			this.data[this.condition] = 0;
			if(this.price_list[this.condition] < 0)
			{
				this.price_list[this.condition] = 0;
			}
			if(print)
			{
				System.out.println("Decreasing price to " + this.price_list[this.condition]);
			}
		}
		}
		
	}

	public int getCondition() {
		return this.condition;
	}

	public double getPrice(int condition2) {
		return this.price_list[condition2];
	}

	public String printArray(double[] array)
	{
		String returnValue = "[";
		for(int i=0; i<array.length; i++)
		{
			returnValue += array[i] + ",";
		}
		return returnValue + "]";
	}

	public synchronized void incrementData(double d, int condition2) {
		// TODO Auto-generated method stub
		this.data[condition2] += d;
//		System.out.println("Data in condition wallet: " + this.data[condition2]);
	}
}