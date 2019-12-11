package test;

import java.sql.SQLException;

import exceptions.CompaniesNotFoundException;
import exceptions.CouponsNotFoundException;
import exceptions.CustomersNotFoundException;
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

public class Program {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		testAll adminTest = new testAll();
		try {
			adminTest.testAllAndFaill();

			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CompanyCantBeAddedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CustomersNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CustomerCantBeAddedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CouponsNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CompaniesNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CompanyCantBeUpdatedException e) {
			System.out.println(e.getMessage());
		} catch (CustomerCantBeUpdatedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CouponCantBeAddedException e) {
			System.out.println(e.getMessage());
		} catch (CouponCantBeUpdatedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CouponPurchasedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExpiredCouponException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoCouponsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (LoginFailedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
