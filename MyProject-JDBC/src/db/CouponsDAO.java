package db;

import java.sql.SQLException;
import java.util.List;

import exceptions.CouponsNotFoundException;
import facadeExceptions.CouponCantBeAddedException;
import facadeExceptions.CouponCantBeUpdatedException;
import javaBeans.Coupons;

public interface CouponsDAO {
	
	/**
	 * A method to add a new Coupon object to the DB
	 * @param coupon The coupon to add to the Coupons table in the DB
	 * @throws SQLException 
	 * @throws CouponCantBeAddedException 
	 */
	void addCoupon (Coupons coupon) throws SQLException, CouponCantBeAddedException;
	
	/**
	 * A method to delete a coupon from the DB
	 * @param id The coupon id to delete...
	 * @throws CouponsNotFoundException 
	 * @throws SQLException 
	 */
	void deleteCoupon(int id) throws SQLException, CouponsNotFoundException;
	
	/**
	 * A method to update a Coupon from the DB
	 * @param coupon The coupon to update..
	 * @throws SQLException 
	 * @throws CouponCantBeUpdatedException 
	 */
	void updateCoupon(Coupons coupon) throws SQLException, CouponCantBeUpdatedException;
	
	/** A method to get all the Coupons details, as ArrayList
	 * @return 
	 * @throws SQLException 
	 */
	List<Coupons> getAllCoupons() throws SQLException;
	
	
	/**
	 * A method to select and get One Coupon
	 * @param id The coupon id to get one of them..
	 * @return
	 * @throws CouponsNotFoundException 
	 * @throws SQLException 
	 */
	Coupons getOneCoupon(int id) throws SQLException, CouponsNotFoundException;
	
	/**
	 * A method to add a new purchased Coupon object to the DB
	 * @param coupon The coupon to add to the Coupons table in the DB
	 * @throws SQLException 
	 */
	void addCouponPurchase (int id, int customerId) throws SQLException; // need to ask Nir about the CustomerID
	
	/**
	 * A method to delete a purchased coupon from the DB
	 * @param id The coupon id to delete...
	 * @throws CouponsNotFoundException 
	 * @throws SQLException 
	 */
	void deleteCouponPurchase( int id, int customerId) throws SQLException, CouponsNotFoundException;


}
