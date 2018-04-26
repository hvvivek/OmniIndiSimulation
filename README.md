# OmniIndiSimulation
A Java based agent-based simplified simulation of the healthcare data system

Stakeholders/Agents:
1. User<br />
    Properties:
    1. "Months in System" - Every iteration = 1 Month
    2. "Money" - Tracks the money flow of the user
    3. "Condition" - Normal-0 / Chronic-1 / Rare-2
    4. "Number of visits" - Tracks number of interactions with the healthcare system - Currently only hospitals in this model
    5. "Quanitified Self Rating" - The Users interest in collecting and organising data
    6. "Privacy Thresholds" - Individual thresholds beyond which users are willing to share their data
    7. "Data" - Data collected in three different conditions - Normal/Chronic/Rare
    8. "Sick Count" - Number of times the user shifts from Normal to temporarily worse conditions (Same as Chronic/Rare)
    9. "Hospital" - Hospital that the user frequents/is part of the plan
    10. "Insurance Multiplier" - Insurance surcharge for this user

2. Hospitals<br />
    Properties:
    1. "Size" - Size of the Hospital - Small/Medium/Large
    2. "Money" - Revenues of the Hospital
    3. "Data" - Data collected by the Hospital for different types of patients - Normal/Chronic/Rare
    4. "Number of Patients by Condition" - Patient demographics that this hospital treats

3. Research<br />
    Properties:
    1. "Research Area" - Area that this researcher is interested in - Normal Research/Chronic conditions/Rare diseases
    2. "Money" - Amount of money spent on research
    3. "Data" - Amount of data gathered by this researcher over past iteration
    4. "Total Data" - Cumulative amount of data gathered by the researcher
    5. "Price List" - Price at which the researcher is willing to buy data for different kinds of data - Data can be from normal/chronic/rare condition users

4. Data Miners<br />
    Properties:
    1. "Data Costs" - Costs at which Data Miners have access to health data in the system
    2. "Data Sales" - Amount at which Data Miners sell data
    3. "Money" - Revenues for Data Mining companies

5. Insurance<br />
    Properties:
    1. "Users" - Users currently subscribed to this Insurance
    2. "Money" - Revenues for this insurance company in the system

Initial Conditions:

Number of Users - 1000<br/>
Number of Hospitals - 3<br/>
Number of Research Institutions - 10<br/>
Number of Data Miners - 1<br/>
Number of Insurance Companies - 1<br/>

Condition:
1. Normal Condition - Probability of condition - 57%
2. Chronic Condition - Probability of condition - 33%
3. Rare Condition - Probability of condition - 10%
4. Normal Condition - Rate of Visit - 1 per 12 months (1)
5. Chronic Condition - Rate of Visit - 1 per 1 month (12)
6. Rare/Serious condition - Rate of Visit - 2 per 1 month (24)

Data
1. Normal Condition - Data points produced per month - 30
2. Normal Condition - Data generater per visit to hospital - 10
3. Chronic Condition - Data generated per visit to hospital - 15
4. Rare Condition - Data generated per visit to hospital - 25
5. Normal Condition - Relative cost of data - 10
6. Chronic Condition - Relative cost of data - 33
7. Rare Condition - Relative cost of data - 100

Research
1. Research based on normal condition data - 20%
    1. Relative Data Required Rate - 50
2. Research based on chronic condition data - 30%
    2. Relative Data Required Rate - 250
3. Research based on rare/serious condition data - 40%
    3. Relative Data Required Rate - 1000

Hospitals
1. Normal Condition:
    1. Probability to visit small hospital - 40%
    2. Probability to visit medium hospital - 30%
    3. Probability to visit large hospital - 30%
    4. Cost per hospital visit - 25
2. Chronic Condition:
    1. Probability to visit small hospital - 20%
    2. Probability to visit medium hospital - 40%
    3. Probability to visit large hospital - 40%
    4. Cost per hospital visit - 100
3. Rare/Serious Condition:
    1. Probability to visit small hospital - 0%
    2. Probability to visit medium hospital - 10%
    3. Probability to visit large hospital - 90%
    4. Cost per hospital visit - 300
4. Administration Costs:
    1. Small Hospital - 15%
    2. Medium Hospital - 30%
    3. Large Hospitals - 40%