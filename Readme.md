<h2>Bank Account Project</h2>

Clone the application and update the necessary dependencies and then run it. 
Remember to configure a database for the application to run successfully. I used PostgreSQL. 

I have 3 endpoints

1. http://localhost:8081/api/account/balance/1 - To get the bank details together with the bank balance.
2. http://localhost:8081/api/account/create - To create a bank account
3. http://localhost:8081/api/transaction - To do both deposits and withdrawals (To distinguish between a deposit and a 
	withdrwal I have used transactionType which is send together with the requet.
	For transactionType use <b>Deposit</b> and <b>Withdrawal</b> keywords respectively) e.g
	
		<b>Deposit</b>
			{
			  "bankAccountId": 1,
			  "transactionAmount": 1000,
			  "transactionType": "Deposit"
			}
			
		<b>Withdrawal</b>
		
			{
			  "bankAccountId": 1,
			  "transactionAmount": 1000,
			  "transactionType": "Withdrawal"
			}

<h5>NOTE: I have documented my web service with Swagger(http://localhost:8081/swagger-ui.html)<h5>
