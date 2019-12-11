package db;

import java.sql.SQLException;
import java.util.List;

import exceptions.CompaniesNotFoundException;
import exceptions.CustomersNotFoundException;
import facadeExceptions.CustomerCantBeUpdatedException;
import javaBeans.Customers;

public interface CustomersDAO {
	

	/**
	 * A method to add a new Customer object to the DB
	 * @param customer The customer to add to the Customers table in the DB
	 * @throws SQLException 
	 */
	void addCustomer (Customers customer) throws SQLException;
	
	/**
	 * A method to delete a customer from the DB
	 * @param id The customer id to delete...
	 * @throws SQLException 
	 * @throws CompaniesNotFoundException 
	 * @throws CustomersNotFoundException 
	 */
	void deleteCustomer(int id) throws SQLException, CustomersNotFoundException;
	
	/**
	 * A method to update a customer from the DB
	 * @param customer The customer to update..
	 * @throws SQLException 
	 * @throws CustomerCantBeUpdatedException 
	 */
	void updateCustomer(Customers customer) throws SQLException, CustomerCantBeUpdatedException;
	
	/** A method to get all the Customers details, as ArrayList
	 * @return 
	 * @throws SQLException 
	 */
	List<Customers> getAllCustomers() throws SQLException;
	
	
	/**
	 * A method to select and get One Customer
	 * @param id The customer id to get one of them..
	 * @return
	 * @throws SQLException 
	 * @throws CustomersNotFoundException 
	 */
	Customers getOneCustomer(int id) throws SQLException, CustomersNotFoundException;
	
	/**
	 * A method to check if a Customer is exists in the system.
	 * @param email
	 * @param password
	 * @return
	 * @throws SQLException 
	 */
	boolean isCustomerExists(String email, String password) throws SQLException;
	
	
	/**
	 * A method to get Customer id by email and password.
	 * @param email
	 * @param password
	 * @return
	 * @throws SQLException 
	 */
	Integer getCustomerId(String email, String password) throws SQLException;
	


}
