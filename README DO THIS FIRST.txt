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