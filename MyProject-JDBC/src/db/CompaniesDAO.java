package db;

import java.sql.SQLException;
import java.util.List;

import exceptions.CompaniesNotFoundException;
import facadeExceptions.CompanyCantBeUpdatedException;
import javaBeans.Companies;

public interface CompaniesDAO {
	
	/**
	 * A method to add a new Company object to the DB
	 * @param company The company to add to the Companies table in the DB
	 * @throws SQLException 
	 */
	void addCompany (Companies company) throws SQLException;
	
	/**
	 * A method to delete a company from the DB
	 * @param id The company id to delete...
	 * @throws CompaniesNotFoundException 
	 * @throws SQLException 
	 */
	void deleteCompany(int id) throws SQLException, CompaniesNotFoundException;
	
	/**
	 * A method to update a company from the DB
	 * @param company The company to update..
	 * @throws SQLException 
	 * @throws CompanyCantBeUpdatedException 
	 */
	void updateCompany(Companies company) throws SQLException, CompanyCantBeUpdatedException;
	
	/** A method to get all the Companies details, as ArrayList
	 * @return 
	 * @throws SQLException 
	 */
	List<Companies> getAllCompanies() throws SQLException;
	
	
	/**
	 * A method to select and get One Company
	 * @param id The company id to get one of them..
	 * @return
	 * @throws CompaniesNotFoundException 
	 * @throws SQLException 
	 */
	Companies getOneCompany(int id) throws SQLException, CompaniesNotFoundException;
	
	/**
	 * A method to check if a Company is exists in the system.
	 * @param email
	 * @param password
	 * @return
	 * @throws SQLException 
	 */
	boolean isCompanyExists(String email, String password) throws SQLException;
	
	
	/**
	 * A method to check the Company ID by email and password.
	 * @param email
	 * @param password
	 * @return
	 * @throws SQLException 
	 */
	 Integer getCompanyId(String email, String password) throws SQLException;

	

}
