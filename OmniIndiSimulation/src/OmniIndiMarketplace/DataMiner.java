package OmniIndiMarketplace;

public class DataMiner {
	
	private int money = 0;
	private double[] DATA_COSTS = {0.01, 0.01, 0.01};
	private double[] DATA_SALES = {10, 100, 250};
	private double [] data_wallet = {0.0, 0.0, 0.0};
	
	public double incrementData(double[] data_wallet) {
		int costs = 0;
		this.data_wallet = data_wallet;
		for(int i=0; i<data_wallet.length; i++)
		{
			costs += DATA_COSTS[i] * data_wallet[i];
			money += (DATA_SALES[i] - DATA_COSTS[i]) * data_wallet[i];
		}
		return costs;
	}
	
	public double getDataPrice(int i)
	{
		return DATA_SALES[i];
	}

	public double getData(int condition) {
		return data_wallet[condition];
	}

	public int getMoney() {
		// TODO Auto-generated method stub
		return this.money;
	}

}
