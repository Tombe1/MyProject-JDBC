package facade.LoginManager;

import java.sql.SQLException;

import db.CompaniesDBDAO;
import db.CouponsDBDAO;
import db.CustomersDBDAO;
import facade.AdminFacade;
import facade.ClientFacade;
import facade.CompaniesFacade;
import facade.CustomersFacade;
import facadeExceptions.LoginFailedException;

public class LoginManager {

	private static LoginManager instance = new LoginManager();

	public LoginManager() {
		super();
	}

	public static LoginManager getInstance() {
		return instance;
	}

	public ClientFacade login(String email, String password, ClientType clientType)
			throws SQLException, LoginFailedException {
		CustomersDBDAO customerDB = new CustomersDBDAO();
		CompaniesDBDAO companyDB = new CompaniesDBDAO();
		CouponsDBDAO couponDB = new CouponsDBDAO();

		switch (clientType) {

		case Administrator:
			AdminFacade adminFacade = new AdminFacade();

			if (adminFacade.login(email, password)) {

				return new AdminFacade(customerDB, companyDB, couponDB);

			} else {
				throw new LoginFailedException();
			}
		case Company:
			CompaniesFacade companyFacade = new CompaniesFacade();
			if (companyFacade.login(email, password) == true) {
				int id = companyDB.getCompanyId(email, password);
				return new CompaniesFacade(id);
			} else {
				throw new LoginFailedException();
			}
		case Customer:
			CustomersFacade customerFacade = new CustomersFacade();
			if (customerFacade.login(email, password) == true) {
				int id = customerDB.getCustomerId(email, password);
				return new CustomersFacade(id);
			} else {
				throw new LoginFailedException();
			}

		default:
			return null;
		}
	}
}
