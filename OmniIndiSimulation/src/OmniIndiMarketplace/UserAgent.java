package OmniIndiMarketplace;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class UserAgent implements Runnable{
	public static int patient_id = 0;
	
	private int      month_in_system     = 0;
	private double   number_of_visits = 0;
	private int      condition;
	private String   name;
	private double   quantified_self_rating = 0;
	private double   quanitfied_self_max = 0;
	private double[] thresholds = new double[3];
	private double[] condition_cost_multiplier = {1.0, 1.0, 1.0};
	
	private double   money_wallet = 0;
	private double[] data_wallet = {0.0, 0.0, 0.0};
	private int originalCondition;
	private HealthcareAgent Hospital;
	private int sickCount;

	private double insurance_rate = 1.0;
	
	public UserAgent()
	{
		this.condition = getRandomCondition();
		this.name = "User " + patient_id;
		patient_id++;
		quantified_self_rating = Math.random();
		quanitfied_self_max    = 1 + Math.random();
		this.thresholds = getRandomThresholds();
		this.Hospital = getHospital();
		this.originalCondition = this.condition;
		this.setInsuranceRate(this.condition + 1);
	}
	
	private HealthcareAgent getHospital() {
		double prob = Math.random();
		double[] probs = Constants.HOSPITALS_PROB[this.condition];
		double cum_probs = 0;
		int index = 0;
//		System.out.println("Probability: " + prob + " Condition: " + this.condition);
		for(int i=0; i<probs.length; i++)
		{
			if(prob >= probs[i])
			{
				index = i+1;
			}
		}
//		System.out.println("Chosen hospital: " + index);
		HealthcareAgent returnValue = Constants.Hospitals.get(index);
		returnValue.addPatient(this.condition);
		return returnValue;
	}

	private double[] getRandomThresholds() {
		double randomNum = (int) (Math.random() * 75);
		double[] thres = {randomNum, randomNum*2, randomNum*3};
		return thres;
	}

	private int getRandomCondition() {
		double prob = Math.random();
		double[] probs = Constants.CONDITION_PROB;
		int condition = 0;
//		System.out.println("Probability: " + prob + " Condition: " + this.condition);
		for(int i=0; i<probs.length; i++)
		{
			if(prob >= probs[i])
			{
				condition = i+1;
			}
		}
//		System.out.println("Chosen hospital: " + index);
		return condition;
	}

	@Override
	public void run() {
		
		while(Main.RUN_SIMULATION)
		{
//			System.out.println(this.name + ": Breathing");
			month_in_system++;
			data_wallet[this.condition] = 0;
			data_wallet[0] += quantified_self_rating * Constants.DATA_RATE_PER_VISIT[0];

			
			// Interact with Healthcare
			number_of_visits += ((float)Constants.RATE_OF_VISIT[this.condition])/12;
			double multiplier = (int)number_of_visits;
			if((int)number_of_visits > 1)
			{
				number_of_visits -= multiplier;
			}
//			if(this.condition == 0)
//			{
//				System.out.println("Multiplier:: " + number_of_visits + " " + multiplier);
//			}
			
			for(int i = 0; i<multiplier; i++)
			{
				produceData();
				interact_with_healthcare();
			}
			
			if(multiplier > 1)
			{
				multiplier = 0;
			}
			
			// Interact with Researchers
			interact_with_research();
			
			if(this.month_in_system % 2 == 0)
			{
				conditionCheck();
			}
			try {
				WriteToFile(this.getState());
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			// Sleep for a month
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
//		System.out.println(this.name + ": Hospital:" + this.Hospital.getSize() + ": Condition:" + this.condition + ": Money:" + (int)this.money_wallet);
	}
	
	
	
	private void conditionCheck() {
		// TODO Auto-generated method stub
		if(this.originalCondition == 0 && this.originalCondition == this.condition)
		{
			this.condition = (int) Math.random() *3;
			if(this.condition != 0)
			{
				this.sickCount += this.condition;
			}
		}
		
		if(this.originalCondition == 0 && this.condition != 0)
		{
			this.condition = 0;
		}
		
	}

	private void produceData() {
		// TODO Auto-generated method stub
		data_wallet[this.condition] += Constants.DATA_RATE_PER_VISIT[this.condition];

	}

	//Logic for healthcare transaction
	public void interact_with_healthcare()
	{
		this.Hospital.incrementMoney( this.getInsuranceRate() * Constants.CONDITION_COSTS[this.condition]);
//		if(this.condition == 0)
//		{
//			System.out.println("Losing:: " + this.getInsuranceRate() * Constants.CONDITION_COSTS[this.condition]);
//		}
		this.money_wallet -= this.getInsuranceRate() * Constants.CONDITION_COSTS[this.condition];		
		Constants.Insurance.money += this.getInsuranceRate() * Constants.CONDITION_COSTS[this.condition];		

		//Non-omni indi condition
		if(!Main.OMNI_INDI)
		{
			this.Hospital.incrementData(data_wallet);
			data_wallet[this.condition] = 0.0;
		}
		
	}
	
	//Logic for research transaction
	public void interact_with_research()
	{
		if(Main.OMNI_INDI)
		{
	//		System.out.println("Condition:" + this.condition + " Data produced: " + this.data_wallet[this.condition]);
			for(int i=0; i<Constants.Researchers.size(); i++)
			{
				ResearchAgent r = Constants.Researchers.get(i);
				if(r.getCondition() == this.condition)
				{
					if(r.getPrice(this.condition) > this.thresholds[this.condition])
					{
						Constants.Researchers.get(i).incrementData(this.data_wallet[this.condition], this.condition);
						this.money_wallet += r.getPrice(this.condition) * this.data_wallet[this.condition];
					}
					else
					{
	//					System.out.println("Threshold: " + this.thresholds[this.condition]);
					}
				}
			}
		}
			
	}

	public int getCondition() {
		// TODO Auto-generated method stub
		return this.condition;
	}

	public double getCost() {
		// TODO Auto-generated method stub
		return this.money_wallet;
	}
	
	public String getState()
	{
		String returnValue = "";
		returnValue += this.month_in_system; 
		returnValue += "," + this.name;
		returnValue += "," + this.condition;
		returnValue += "," + this.money_wallet;
		returnValue += "," + this.thresholds[0];
		returnValue += "," + this.thresholds[1];
		returnValue += "," + this.thresholds[2];
		
		return returnValue;
	}
	
	public void WriteToFile(String str) throws IOException
	{
	    BufferedWriter writer = new BufferedWriter(new FileWriter(Constants.FILE_USERS, true));
	    writer.append('\n');
	    writer.append(str);
	    
	    writer.close();

	}

	public int getPrevYearSickCount() {
		// TODO Auto-generated method stub
		return this.sickCount;
	}
	
	public void setPrevYearSickCount() {
		// TODO Auto-generated method stub
		this.sickCount = 0;
	}

	public void setInsuranceRate(double ins) {
		// TODO Auto-generated method stub
		this.insurance_rate = ins ;
	}
	
	public double getInsuranceRate() {
		// TODO Auto-generated method stub
		return this.insurance_rate ;
	}
	
	
}
