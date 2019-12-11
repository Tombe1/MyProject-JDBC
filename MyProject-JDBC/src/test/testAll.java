package test;

import java.sql.Date;
import java.sql.SQLException;
import java.time.Year;
import java.util.Calendar;
import java.util.Locale.Category;

import db.CouponsDAO;
import db.CouponsDBDAO;
import db.CustomersDAO;
import db.CustomersDBDAO;
import exceptions.CompaniesNotFoundException;
import exceptions.CouponsNotFoundException;
import exceptions.CustomersNotFoundException;
import facade.AdminFacade;
import facade.ClientFacade;
import facade.CompaniesFacade;
import facade.CustomersFacade;
import facade.LoginManager.ClientType;
import facade.LoginManager.LoginManager;
import facadeExceptions.CompanyCantBeAddedException;
import facadeExceptions.CompanyCantBeUpdatedException;
import facadeExceptions.CouponCantBeAddedException;
import facadeExceptions.CouponCantBeUpdatedException;
import facadeExceptions.CouponPurchasedException;
import facadeExceptions.CustomerCantBeAddedException;
import facadeExceptions.CustomerCantBeUpdatedException;
import facadeExceptions.ExpiredCouponException;
import facadeExceptions.LoginFailedException;
import facadeExceptions.NoCouponsException;
import javaBeans.Categories;
import javaBeans.Companies;
import javaBeans.Coupons;
import javaBeans.Customers;
import job.CouponExpireationDailyJob;

public class testAll {
	private CouponsDBDAO couponDB = new CouponsDBDAO();
	private CustomersDBDAO customerDB = new CustomersDBDAO();

	public void testAllAndFaill()
			throws SQLException, CompanyCantBeAddedException, CustomersNotFoundException, CustomerCantBeAddedException,
			CouponsNotFoundException, CompaniesNotFoundException, CompanyCantBeUpdatedException,
			CustomerCantBeUpdatedException, CouponCantBeAddedException, CouponCantBeUpdatedException,
			CouponPurchasedException, ExpiredCouponException, NoCouponsException, LoginFailedException {

		// JOB METHOD WORKING:

//		 Thread t1 = new Thread(new  CouponExpireationDailyJob(couponDB, true));
		// t1.start();

		// LOGIN METHOD WORKING:

//	LoginManager adminLogin = LoginManager.getInstance(); // working double checked
//	AdminFacade admin = (AdminFacade) adminLogin.login("admin@admin.com", "admin", ClientType.Administrator); //working double checked

//  LoginManager companyLogin =LoginManager.getInstance(); //working double checked
//  CompaniesFacade company = (CompaniesFacade) companyLogin.login("John@intel.com", "intel12345", ClientType.Company); //working double checked

//  LoginManager customerLogin =LoginManager.getInstance(); //working double checked
//  CustomersFacade customer = (CustomersFacade) customerLogin.login("danielpaz@gmail.com", "paz326158", ClientType.Customer); //working double checked

		
		// WORKING:
  
// System.out.println(admin.getOneCustomer(2)); //working double checked
// admin.addCompany(new Companies("Toyota Israel","israeli@toyota.com", "blabla2255")); //working double checked
// admin.addCustomer(new Customers("Daniel", "Paz", "danielpaz@gmail.com", "paz326158")); //working double checked
// admin.deleteCustomer(6); //working double checked
// admin.deleteCompany(9); //working double checked
// admin.updateCompany(new Companies(4,"'RCL Chimicals'" ,"'bar717@bezeqint.com'", "'bar326158'")); //working double checked
// admin.updateCustomer(new Customers(4, "'Orly'", "'Hason'", "'TutBanana@gmail.com'", "'orlyorly92'")); //working double checked
// System.out.println(admin.getOneCompany(3)); //working double checked
// System.out.println(admin.getOneCustomer(1)); //working double checked
// System.out.println(admin.getAllCompanies()); //working double checked
// System.out.println(admin.getAllCustomers()); //working double checked

  
// customer.purchaseCoupon(couponDB.getOneCoupon(32)); //working double checked
// System.out.println(customer.getCustomerDetails()); //working double checked
// System.out.println(customer.getCustomerCoupons()); //working double checked
// System.out.println(customerDB.getOneCustomer(3)); //working double checked
// System.out.println(customer.getCustomerCoupons(Categories.Cars)); //working double checked
// System.out.println(customer.getCustomerCoupons(75)); //working double checked

// Calendar c = Calendar.getInstance();
// c.set(Calendar.YEAR, 2019);
// c.set(Calendar.MONTH, 7);
// c.set(Calendar.DAY_OF_MONTH, 25);

// Calendar cc = Calendar.getInstance();
// cc.set(Calendar.YEAR, 2019);
// cc.set(Calendar.MONTH, 11);
// cc.set(Calendar.DAY_OF_MONTH, 01);

// company.updateCoupon(new Coupons(33 , 7 , Categories.valueOf("Games"), "'Games'", "'Riot Points'", new Date(c.getTimeInMillis()), new Date(cc.getTimeInMillis()), 51000, 20, "'url-blabla'")); //working double checked
// company.addCoupon(new Coupons(2, Categories.valueOf("Music"), "Music", "Live Show tickets in 30% off", new Date(c.getTimeInMillis()), new Date(cc.getTimeInMillis()), 1500, 25.0, "url-blabla")); //working double checked
// company.deleteCoupon(36); //working double checked
// System.out.println(company.getCompanyCoupons()); //working double checked
// System.out.println(company.getCompanyCoupons(Categories.Computers)); //working double checked
// System.out.println(company.getCompanyCoupons(511111.0)); //working double checked
// System.out.println(company.getCompanyDetails());//working double checked

		// t1.stop();

	}
}
