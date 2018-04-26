# OmniIndiSimulation
A Java based agent-based simplified simulation of the healthcare data system

Stakeholders/Agents:
1. User
    Properties:
    1. "Months in System" - Every iteration = 1 Month
    2. "Money" - Tracks the money flow of the user
    3. "Condition" - Normal-0 / Chronic-1 / Rare-2
    4. "Number of visits" - Tracks number of interactions with the healthcare system - Currently only hospitals in this model
    5. Quanitified Self Rating - The Users interest in collecting and organising data
    6. Privacy Thresholds - Individual thresholds beyond which users are willing to share their data
    7. Data - Data collected in three different conditions - Normal/Chronic/Rare
    8. Sick Count - Number of times the user shifts from Normal to temporarily worse conditions (Same as Chronic/Rare)
    9. Hospital - Hospital that the user frequents/is part of the plan

2. Hospitals
    Properties:
    1. "Size" - Size of the Hospital - Small/Medium/Large
    2. "Money" - Revenues of the Hospital
    3. "Data" - Data collected by the Hospital for different types of patients - Normal/Chronic/Rare
    4. Number of Patients by Condition - Patient demographics that this hospital treats

3. Research
    Properties:
    1. "Research Area" - Area that this researcher is interested in - Normal Research/Chronic conditions/Rare diseases
    2. "Money" - Amount of money spent on research
    3. "Data" - Amount of data gathered by this researcher over past iteration
    4. "Total Data" - Cumulative amount of data gathered by the researcher
    5. "Price List" - Price at which the researcher is willing to buy data for different kinds of data - Data can be from normal/chronic/rare condition users


4. Data Miners

5. Insurance
