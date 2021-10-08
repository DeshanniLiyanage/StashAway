--------------------------------------------------------------------------------------
---------------------------StashAway - Deposit Plan Scenario--------------------------
--------------------------------------------------------------------------------------

** customer can have multiple portfolios at StashAway
** those portfolios can have one-time or monthly deposit plans
** customer transfer funds from bank to StashAway by including their personal StashAway reference code
** StashAway has to divide the transfer accordingly to cover the customer's portfolios
** So the trick is to find the most effective and efficient way to do it

MISSING SCENARIOS in the solution
- Validate customer reference ID
- Check whether one time deposits are completed

Questions
- Why only monthly and one-time plan are there? Why not weekly or quarterly?
- How the customer will collect the profits?

Data Structure : JSON file(dataStructure.json)
- portfolios list
- deposit plans list

MVC architecture
- model
- view
- controller

When fund deposit
* 3 main scenarios
    - excess fund
        > save extra till next deposit happens
    - exact fund
        > divide between deposit plans
        > a priority list should be there
    - less fund
        > save till next deposit happens