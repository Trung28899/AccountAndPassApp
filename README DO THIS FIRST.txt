Application Flow: 

activity_main <-----> activity_sign_up
      |			      |
      |			      |
       -----------------------
		   |
		   |
	  activity_add_account
	(list_layout for listing)
		   |
		   |
	  activity_account_info	  
	  |		      |
	  |		      |
     update_info     delete_confirmation

layout that has java class: activity_main, activity_sign_up, 
activity_add_account, activity_account_info

layout that doesn't have java class: list_layout, update_info, 
delete_confirmation

Trick For Delete Account Name: 
Return to the previous page first then delete the data after

Trick to add account into the account node: 
For example, the account name is Trung
databaseAccounts = FirebaseDatabase.getInstance().getReference("accounts/Trung/SavedAccount");
Then generate and id (ideally put Account Name)
databaseAccount.child(id).setValue(account1); 


Small Bugs: 
- In AddAccount.java Activity: when hit "Add Account" button
> showing Toast "Logged In Successfully !" in the MainActivity.java
Activity
(however the button still add the info into the database so it's only a 
small bug)

- In AddAccount.java Activity: the list cut the password part of the 
last account

Application Finished
Room for improvement: 

- Implement back arrow on the top of the screen
- Fix the small bugs
- change to better background screen
- If there is an existing user and another user
register with the same UserName, the app should 
not let the other user register that userName, right
now the app changed the existing node
- Should Have log out button
- Havent had landscape mode

