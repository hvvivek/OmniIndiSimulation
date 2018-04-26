package OmniIndiMarketplace;

import java.util.ArrayList;
import java.util.HashMap;

public class HealthcareAgent implements Runnable{

	private int size;
	private double money = 0;
	private double[] data_wallet = {0.0, 0.0, 0.0};
	private int[] num_patients_condition = {0, 0, 0};
	
	public HealthcareAgent(int size) {
		this.size = size;
	}

	@Override
	public void run() {
		
		while(Main.RUN_SIMULATION)
		{
//			System.out.println("Healthcare Running");
			interact_with_data_miners();
			interact_with_research();
			
			
			// Sleep for a month
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		System.out.println("Hospital Size: " + this.size + " Money: " + this.money);
		System.out.println("Hospital Size: " + this.size + " Patient Distribution: " + this.num_patients_condition[0] + ", " + this.num_patients_condition[1] + ", " +this.num_patients_condition[2]);

	}
	
	public void addPatient(int condition)
	{
		this.num_patients_condition[condition]++;
	}
	
	
	public void interact_with_data_miners()
	{
		if(!Main.OMNI_INDI)
		{
			if(this.data_wallet[0] > 0 | this.data_wallet[1] > 0 | this.data_wallet[2] > 0 )
			{
				this.money += Constants.Miner.incrementData(this.data_wallet);
				this.data_wallet[0] = 0;
				this.data_wallet[1] = 0;
				this.data_wallet[2] = 0;
			}
		}
	}
	
	public void interact_with_research()
	{
		//No relation - Everything via data miners
	}

	public void incrementData(double[] data_wallet2) {
		this.data_wallet[0] += data_wallet2[0];
		this.data_wallet[1] += data_wallet2[1];
		this.data_wallet[2] += data_wallet2[2];
		
	}

	public void incrementMoney(double d) {
		if(Main.OMNI_INDI)
		{
			this.money += d*Constants.ADMIN_COSTS_OMNI[this.size];
		}
		else
		{
			this.money += d*Constants.ADMIN_COSTS[this.size];
		}		
	}

	public int getSize() {
		// TODO Auto-generated method stub
		return this.size;
	}
}
