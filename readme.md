
Steps to use/test applications 1 and 2.
1.	checkout both code bases and import them to eclipse as existing maven projects.
2.	Both these applications use mysql db installed on the system to save and retrieve data.
3.	Sample CSV file is already present in the resources folder of application 1. This can be edited or replaced with any other csv file in same format.
4.	Run application 2 as a spring boot application using Application2.java. Application 2 comes up on port 8090.
5.	Now start application 1 as a spring boot application using CsvJsonApplication.java. Application1 will come up on port 8080. It will parse the csv file and post the json-formated data to application 2's rest endpoint. 
6.	Application 2 will receive the data and save the data in DB. 
7.	In the absence of a UI, interaction with application 2 is to be done through postman application. Below are the various rest APIs and its usage for application 2.
•	POST /rest/addallcustomer --> used by app1 to send data. Also, can be used from postman to send more customers data in bulk as a json string.
•	GET /rest/showallcustomers --> used to list all customers stored in database.
•	POST /rest/addacustomer -> add a new customer data.
•	POST /rest/searchacustomerbyID --> search a customer by id.
•	POST /rest/searchacustomerbyfirstname --> search a customer by first name.
Application 3
Application 3 is similar to application 2. The only changes lie in added cache management to handle data at a faster rate and batch processing of record insertion. Batch processing can be seen in batchSave() of CustomerService.java.




