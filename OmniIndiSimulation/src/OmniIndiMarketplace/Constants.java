package OmniIndiMarketplace;

import java.util.ArrayList;

public class Constants {

	public static String FILE_USERS = "NonOmni_30Months_User_Data.txt";
	public static final int DATA_RATE_PER_MONTH     = 30;
	public static final int CONDITION_RARE          = 2;
	public static final int CONDITION_CHRONIC       = 1;
	public static final int CONDITION_NORMAL        = 0;
	
	public static final int[] RATE_OF_VISIT         = {1, 12, 24};
	public static final double[] CONDITION_PROB     = {0.57, 0.90, 1};
	public static final int[] DATA_RATE_PER_VISIT   = {10, 15, 25};
	public static final int[] DATA_COSTS			= {10, 33, 100};
	
	public static final double[] RESEARCH_PROB      = {0.3, 0.4, 0.4};
	public static final int[] RESEARCH_DATA_REQ     = {1000, 100000, 10000000};
	public static final int[] RESEARCH_DATA_REQ_RATE= {50, 250, 1000};

	
	public static final double[][] HOSPITALS_PROB		= {{0.4, 0.7, 1}, {0.2, 0.6, 1.0}, {0.0, 0.1, 1.0}};
	public static final double[] CONDITION_COSTS = {25, 100, 300};
	
	public static final double[] ADMIN_COSTS_OMNI = {0.9, 0.9, 0.9};
	public static final double[] ADMIN_COSTS = {0.6, 0.7, 0.85};
	
	
	
	public static final int HOSPITAL_LARGE = 2;
	public static final int HOSPITAL_MEDIUM = 1;
	public static final int HOSPITAL_SMALL = 0;

	public static ArrayList<HealthcareAgent> Hospitals = new ArrayList<HealthcareAgent>();
	public static ArrayList<ResearchAgent> Researchers = new ArrayList<ResearchAgent>();
	public static ArrayList<UserAgent> Users = new ArrayList<UserAgent>();
	public static DataMiner Miner = new DataMiner();
	
	public static InsuranceAgent Insurance;
}
