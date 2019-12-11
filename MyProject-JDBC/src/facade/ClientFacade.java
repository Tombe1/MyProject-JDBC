package facade;

import java.sql.SQLException;

import db.CompaniesDBDAO;
import db.CustomersDBDAO;
import db.CouponsDBDAO;

public abstract class ClientFacade {
	
	protected CompaniesDBDAO companiesDB = new CompaniesDBDAO();
	protected CustomersDBDAO customersDB=new CustomersDBDAO();
	protected CouponsDBDAO couponsDB=new CouponsDBDAO();	
	
	public abstract boolean login (String email, String password) throws SQLException;
	

}
