package facade;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import db.CompaniesDBDAO;
import db.CouponsDBDAO;
import exceptions.CouponsNotFoundException;
import facadeExceptions.CouponCantBeAddedException;
import facadeExceptions.CouponCantBeUpdatedException;
import javaBeans.Categories;
import javaBeans.Companies;
import javaBeans.Coupons;

public class CompaniesFacade extends ClientFacade {

	private CompaniesDBDAO companyDB = new CompaniesDBDAO();
	private CouponsDBDAO couponDB = new CouponsDBDAO();
	private int companyID;

	public CompaniesFacade() {
		super();
	}

	public CompaniesFacade(int companyID) {
		super();
		this.companyID = companyID;
	}

	public boolean login(String email, String password) throws SQLException {
		return this.companiesDB.isCompanyExists(email, password);
	}

	public void addCoupon(Coupons coupon) throws SQLException, CouponCantBeAddedException {

		List<Coupons> addCoupon = couponDB.getAllCoupons();
		for (Coupons c : addCoupon) {
			if (coupon.getTitle().equals(c.getTitle())) {
				if (coupon.getCompanyId() == (companyID)) {

				}
				throw new CouponCantBeAddedException();
			}

		}
		couponDB.addCoupon(coupon);

	}

	public void updateCoupon(Coupons coupon) throws SQLException, CouponCantBeUpdatedException {

		List<Coupons> updateCoupon = couponDB.getAllCoupons();

		for (Coupons c : updateCoupon) {
			if (coupon.getId() == c.getId() && coupon.getCompanyId() == c.getCompanyId()) { // need to ask nir what i do
																							// here.
				throw new CouponCantBeUpdatedException();

			}
			break;
		}

		couponDB.updateCoupon(coupon);
	}

	public void deleteCoupon(int CouponID) throws SQLException, CouponsNotFoundException {
		List<Coupons> deleteCoupon = couponDB.getAllCoupons();
		for (Coupons c : deleteCoupon) {
			if (!companyDB.getAllCouponsByCompany(CouponID).isEmpty()) {
				couponDB.deleteCouponPurchase(CouponID, c.getId());
			}

		}
		couponDB.deleteCoupon(CouponID);

	}

	public List<Coupons> getCompanyCoupons() throws SQLException {

		List<Coupons> companyCoupons = new ArrayList<>();
		List<Companies> companies = companyDB.getAllCompanies();
		List<Coupons> coupons = couponDB.getAllCoupons();

		for (Companies com : companies) {
			List<Coupons> nums = companyDB.getAllCouponsByCompany(com.getId());
			for (Coupons coup : nums) {
				for (Coupons coupon : coupons) {
					if (com.getId() == companyID) {
						if (coup.getId() == coupon.getId()) {
							companyCoupons.add(coup);

						}
					}
				}
			}

		}
		return companyCoupons;
	}

	public List<Coupons> getCompanyCoupons(Categories category) throws SQLException {

		List<Coupons> getCouponsCategory = getCompanyCoupons();
		List<Coupons> couponsCategory = new ArrayList<>();

		for (Coupons c : getCouponsCategory) {
			if (c.getCategories().equals(category)) {
				couponsCategory.add(c);
			}
		}
		return couponsCategory;
	}

	public List<Coupons> getCompanyCoupons(Double maxPrice) throws SQLException {

		List<Coupons> getCompanyCouponsMaxPrice = getCompanyCoupons();
		List<Coupons> companyCouponsMaxPrice = new ArrayList<>();

		for (Coupons c : getCompanyCouponsMaxPrice) {
			if (c.getPrice() <= maxPrice) {
				companyCouponsMaxPrice.add(c);
			}
		}
		return companyCouponsMaxPrice;
	}

	public Companies getCompanyDetails() throws SQLException {
		return companyDB.getOneCompany(companyID);
	}

}
