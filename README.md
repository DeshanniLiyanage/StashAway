--------------------------------------------------------------------------------------
---------------------------StashAway - Deposit Plan Scenario--------------------------
--------------------------------------------------------------------------------------

** A customer can have multiple portfolios at StashAway
** Those portfolios can have one-time or monthly deposit plans
** Customer transfer funds from bank to StashAway by including their personal StashAway reference code
** StashAway has to divide the transfer accordingly to cover the customer's portfolios
** So the trick is to find the most effective and efficient way to do it

MISSING SCENARIOS in the solution
- Validate customer reference ID
- Check whether one time deposits are completed
- reimburse excess balance to the next deposit plan cycle

Questions
- Why only monthly and one-time plan are there? Why not weekly or quarterly?
- How the customer will collect the profits?

Data Structure : JSON file(dataStructure.json)
- portfolios list
- deposit plans list

MVC architecture
- model
    DataStructure.java - Serialized class, generation of JSON object
    DepositPlan - Fund deposit distribution based of customer plans, return distributed criteria
- view
    CustomerDepositView - Input fund deposit and view the distribution criteria
    DataStructureView - View data structuring progress
- controller
    DataStructureController - handle requests of DataStructureView
    DepositPlanController - handle requests of CustomerDepositView
StashAwayMain
    Execute the program

When fund deposit
 3 main scenarios
    - excess fund
        > save extra till next deposit happens
    - exact fund
        > divide between deposit plans
        > according to the given priority
    - less fund
        > save till next deposit happens
