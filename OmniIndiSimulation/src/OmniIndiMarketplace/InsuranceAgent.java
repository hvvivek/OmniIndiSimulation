package OmniIndiMarketplace;

public class InsuranceAgent implements Runnable {
	
	int money;
	
	@Override
	public void run() {
		while(Main.RUN_SIMULATION)
		{
			if(Main.OMNI_INDI)
			{
			for(int i=0; i < Constants.Users.size(); i++)
			{
				UserAgent User = Constants.Users.get(i);
				if(User.getPrevYearSickCount()>3)
				{
					User.setInsuranceRate(User.getInsuranceRate() * 1.5);
				}
				else
				{
					User.setInsuranceRate(1);
				}
				
				User.setPrevYearSickCount();
			}
			}
			
			try {
				Thread.sleep(12000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		System.out.println("Insurance Represent: " + this.money);
	}

}
