# Daily_Trade_Reporting_System
Daily Trade Reporting Engine Java


# The problem
    Sample data represents the instructions sent by various clients to JP Morgan to execute in the international market. Entity Buy/Sell    AgreedFx Currency InstructionDate SettlementDate Units Price per unit
    1) A work week starts Monday and ends Friday, unless the currency of the trade is AED or SAR, where the work week starts Sunday and ends Thursday. No other holidays to be taken into account.
    2) A trade can only be settled on a working day.
    3) If an instructed settlement date falls on a weekend, then the settlement date should be changed to the next working day.
    4) USD amount of a trade = Price per unit * Units * Agreed Fx
  
# Requirements
    Create a report that shows
      1) Amount in USD settled incoming everyday
      2) Amount in USD settled outgoing everyday
      3) Ranking of entities based on incoming and outgoing amount. Eg: If entity foo instructs the highest amount for a buy instruction, then foo is rank 1 for outgoing.
  
# Glossary of terms:
    1) Instruction: An instruction to buy or sell
    2) Entity: A financial entity whose shares are to be bought or sold
    3) Instruction Date: Date on which the instruction was sent to JP Morgan by various clients
    4) Settlement Date: The date on which the client wished for the instruction to be settled with respect to Instruction Date
    5) Buy/Sell flag:
      o B – Buy – outgoing
      o S – Sell – incoming
    6) Agreed Fx is the foreign exchange rate with respect to USD that was agreed
    7) Units: Number of shares to be bought or sold
  
  
# *****Proposed Solution*****

# Assumptions for Development
    1)	Inputs are assumed to be supplied in input Text (.txt) file. 
    2)	Input trade instructions are seperated by : (semicolon).
    3)	Log4J is used for logging purpose as external jar.
    4)	Exception Handling is considered.
    5)  Design proposed consist of using factory design pattern and strategy design pattern to decide working days for settlement date calculation

# Steps to run the project
    1)	Go to the command prompt
    2)	Change the directory to project folder
    3)	Run maven clean install command
    4)	It will run the test cases and gives the output

      
# Logger level
    1)	INFO in current configuration in log4j.properties file
    2)	It can be changed to DEBUG if want to get more information
    3)	Output log file gets generated at C:\\Temp\\log4j-application.log
 
