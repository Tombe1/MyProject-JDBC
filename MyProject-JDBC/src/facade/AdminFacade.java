package facade;

import java.sql.SQLException;
import java.util.List;
import db.CompaniesDBDAO;
import db.CouponsDBDAO;
import db.CustomersDBDAO;
import exceptions.CompaniesNotFoundException;
import exceptions.CouponsNotFoundException;
import exceptions.CustomersNotFoundException;
import facadeExceptions.CompanyCantBeAddedException;
import facadeExceptions.CompanyCantBeUpdatedException;
import facadeExceptions.CustomerCantBeAddedException;
import facadeExceptions.CustomerCantBeUpdatedException;
import javaBeans.Companies;
import javaBeans.Coupons;
import javaBeans.Customers;

public class AdminFacade extends ClientFacade {
	private CompaniesDBDAO companyDB = new CompaniesDBDAO();
	private CouponsDBDAO couponDB = new CouponsDBDAO();
	private CustomersDBDAO customerDB = new CustomersDBDAO();

	public AdminFacade() {
		super();
	}

	public AdminFacade(CustomersDBDAO customerDB2, CompaniesDBDAO companyDB2, CouponsDBDAO couponDB2) {
	}

	public boolean login(String email, String password) throws SQLException {
		boolean loginDeatils = false;
		if (email.equals("admin@admin.com") && password.equals("admin")) {
			loginDeatils = true;
		} else {
			loginDeatils = false;
		}
		return loginDeatils;
	}

	public void addCompany(Companies company) throws SQLException, CompanyCantBeAddedException {

		List<Companies> addcompany = companyDB.getAllCompanies();
		for (Companies c : addcompany) {
			if (company.getName().equals(c.getName()) && company.getEmail().equals(c.getEmail())) {
				throw new CompanyCantBeAddedException();
			}

		}
		companyDB.addCompany(company);

	}

	public void updateCompany(Companies company) throws SQLException, CompanyCantBeUpdatedException {

		List<Companies> updateCompany = companyDB.getAllCompanies();
		for (Companies c : updateCompany) {
			if (company.getName().equals(c.getName()) && company.getId() == (c.getId())) {
				throw new CompanyCantBeUpdatedException();

			}
			break;
		}

		companyDB.updateCompany(company);

	}

	public void deleteCompany(int id) throws SQLException, CompaniesNotFoundException, CouponsNotFoundException {
		List<Coupons> deleteCompany = couponDB.getAllCoupons();
		for (Coupons c : deleteCompany) {
			if (!companyDB.getAllCouponsByCompany(id).isEmpty()) {
				couponDB.deleteCoupon(c.getId());
				couponDB.deleteCouponPurchase(id, c.getId());
			}
		}
		companyDB.deleteCompany(id);
	}

	public List<Companies> getAllCompanies() throws SQLException {
		return companyDB.getAllCompanies();
	}

	public Companies getOneCompany(int id) throws SQLException {
		return companyDB.getOneCompany(id);

	}

	public void addCustomer(Customers customer) throws SQLException, CustomerCantBeAddedException {

		List<Customers> addCustomer = customerDB.getAllCustomers();
		for (Customers c : addCustomer) {
			if (customer.getEmail().equals(c.getEmail())) {
				throw new CustomerCantBeAddedException();

			}
			break;

		}
		customerDB.addCustomer(customer);
	}

	public void updateCustomer(Customers customer) throws SQLException, CustomerCantBeUpdatedException {

		List<Customers> updateCustomer = customerDB.getAllCustomers();
		for (Customers c : updateCustomer) {
			if (customer.getId() == (c.getId())) {
				throw new CustomerCantBeUpdatedException();

			}
			break;
		}

		customerDB.updateCustomer(customer);
	}

	public void deleteCustomer(int customerId)
			throws SQLException, CustomersNotFoundException, CouponsNotFoundException {
		List<Coupons> deleteCustomer = couponDB.getAllCoupons();
		for (Coupons c : deleteCustomer) {
			if (!customerDB.getAllCouponsByCustomer(customerId).isEmpty()) {
				couponDB.deleteCouponPurchase(customerId, c.getId());
			}
		}
		customerDB.deleteCustomer(customerId);

	}

	public List<Customers> getAllCustomers() throws SQLException {
		return customerDB.getAllCustomers();
	}

	public Customers getOneCustomer(int id) throws SQLException {
		return customerDB.getOneCustomer(id);

	}

}
