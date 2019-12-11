package facade;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import db.CouponsDBDAO;
import db.CustomersDBDAO;
import exceptions.CustomersNotFoundException;
import facadeExceptions.CouponCantBeUpdatedException;
import facadeExceptions.CouponPurchasedException;
import facadeExceptions.ExpiredCouponException;
import facadeExceptions.NoCouponsException;
import javaBeans.Categories;
import javaBeans.Coupons;
import javaBeans.Customers;

public class CustomersFacade extends ClientFacade {

	private CouponsDBDAO couponDB = new CouponsDBDAO();
	private CustomersDBDAO customerDB = new CustomersDBDAO();
	private int customerId;

	public CustomersFacade(int customerId) { // constructor
		super();
		this.customerId = customerId;
	}

	public CustomersFacade() {
		super();
	}

	public boolean login(String email, String password) throws SQLException {
		return this.customerDB.isCustomerExists(email, password);
	}

	public void purchaseCoupon(Coupons coupon) throws CouponPurchasedException, ExpiredCouponException,
			NoCouponsException, SQLException, CouponCantBeUpdatedException {
		if (coupon.getEndDate().getTime() < Calendar.getInstance().getTimeInMillis()) {
			throw new ExpiredCouponException();

		}

		if (coupon.getAmount() == 0) {
			throw new NoCouponsException();
		}

		List<Coupons> purchasedCoupons = getCustomerCoupons();
		for (Coupons c : purchasedCoupons) {
			if (c.getId() == coupon.getId()) {
				throw new CouponPurchasedException();

			}
		}
		couponDB.addCouponPurchase(coupon.getId(), customerId);
		coupon.setAmount(couponDB.getOneCoupon(coupon.getId()).getAmount() - 1);
		couponDB.updateCoupon(coupon);
	}

	public List<Coupons> getCustomerCoupons() throws SQLException { // all the coupons.

		List<Coupons> customerCoupons = new ArrayList<>();
		List<Customers> customers = customerDB.getAllCustomers();
		List<Coupons> coupons = couponDB.getAllCoupons();
		for (Customers cus : customers) {
			List<Coupons> nums = customerDB.getAllCouponsByCustomer(cus.getId());
			for (Coupons c : nums) {
				for (Coupons coupon : coupons) {
					if (cus.getId() == customerId) {
						if (c.getId() == coupon.getId()) {
							customerCoupons.add(coupon);
						}

					}

				}

			}
		}
		return customerCoupons;
	}

	public List<Coupons> getCustomerCoupons(Categories category) throws SQLException {

		List<Coupons> getCouponsCategory = getCustomerCoupons();
		List<Coupons> couponsCategory = new ArrayList<>();
		for (Coupons c : getCouponsCategory) {
			if (c.getCategories().equals(category)) {
				couponsCategory.add(c);
			}
		}
		return couponsCategory;
	}

	public List<Coupons> getCustomerCoupons(double maxPrice) throws SQLException {

		List<Coupons> customerCoupons = getCustomerCoupons();
		List<Coupons> customerCouponsMaxPrice = new ArrayList<>();

		for (Coupons c : customerCoupons) {
			if (c.getPrice() <= maxPrice) {
				customerCouponsMaxPrice.add(c);
			}
		}
		return customerCouponsMaxPrice;
	}

	public Customers getCustomerDetails() throws SQLException, CustomersNotFoundException {
		return customerDB.getOneCustomer(customerId);

	}

}
