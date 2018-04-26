# OmniIndiSimulation
A Java based agent-based simplified simulation of the healthcare data system

Stakeholders/Agents:
1. User
    Properties:
    a. "Months in System" - Every iteration = 1 Month
    b. "Money" - Tracks the money flow of the user
    c. "Condition" - Normal-0 / Chronic-1 / Rare-2
    d. "Number of visits" - Tracks number of interactions with the healthcare system - Currently only hospitals in this model
    e. Quanitified Self Rating - The Users interest in collecting and organising data
    f. Privacy Thresholds - Individual thresholds beyond which users are willing to share their data
    g. Data - Data collected in three different conditions - Normal/Chronic/Rare
    h. Sick Count - Number of times the user shifts from Normal to temporarily worse conditions (Same as Chronic/Rare)
    i. Hospital - Hospital that the user frequents/is part of the plan

2. Hospitals
    Properties:
    a. "Size" - Size of the Hospital - Small/Medium/Large
    b. "Money" - Revenues of the Hospital
    c. "Data" - Data collected by the Hospital for different types of patients - Normal/Chronic/Rare
    d. Number of Patients by Condition - Patient demographics that this hospital treats

3. Research
    Properties:
    a. "Research Area" - Area that this researcher is interested in - Normal Research/Chronic conditions/Rare diseases
    b. "Money" - Amount of money spent on research
    c. "Data" - Amount of data gathered by this researcher over past iteration
    d. "Total Data" - Cumulative amount of data gathered by the researcher
    e. "Price List" - Price at which the researcher is willing to buy data for different kinds of data - Data can be from normal/chronic/rare condition users


4. Data Miners

5. Insurance
