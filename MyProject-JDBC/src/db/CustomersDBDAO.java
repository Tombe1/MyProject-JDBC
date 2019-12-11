package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import exceptions.CustomersNotFoundException;
import facadeExceptions.CustomerCantBeUpdatedException;
import javaBeans.Coupons;
import javaBeans.Customers;

public class CustomersDBDAO implements CustomersDAO {

	private ConnectionPool pool = ConnectionPool.getInstance();
	CouponsDBDAO couponDB = new CouponsDBDAO();

	public void addCustomer(Customers customer) throws SQLException {
		Connection con = pool.getConnection();

		try {
			PreparedStatement stmt = con.prepareStatement("insert into Customers values(?, ?, ?, ?)");
			stmt.setString(1, customer.getFirstName());
			stmt.setString(2, customer.getLastName());
			stmt.setString(3, customer.getEmail());
			stmt.setString(4, customer.getPassword());
			stmt.execute();

		} catch (SQLException e) {
			throw new SQLException(e);
		} finally {
			pool.returnConnection(con);
		}

	}

	public void deleteCustomer(int id) throws SQLException, CustomersNotFoundException {
		Connection con = pool.getConnection();
		try {
			PreparedStatement st = con.prepareStatement("delete from Customers where CustomerID = ?");
			st.setInt(1, id);
			int count = st.executeUpdate();
			if (count == 0) {
				throw new CustomersNotFoundException();
			}

		} catch (SQLException e) {
			throw new SQLException(e.getMessage());
		} finally {
			pool.returnConnection(con);
		}

	}

	public void updateCustomer(Customers customer) throws SQLException, CustomerCantBeUpdatedException {
		Connection con = pool.getConnection();
		try {
			PreparedStatement stmt = con.prepareStatement("update Customers set FirstName=" + customer.getFirstName()
					+ " , LastName=" + customer.getLastName() + ", Email= " + customer.getEmail() + ", Password= "
					+ customer.getPassword() + " where CustomerID = " + customer.getId());
			int count = stmt.executeUpdate();
			if (count == 0) {
				throw new CustomerCantBeUpdatedException();
			}

		} catch (SQLException e) {
			throw new SQLException(e.getMessage());
		} finally {
			pool.returnConnection(con);
		}
	}

	public List<Customers> getAllCustomers() throws SQLException {
		Connection con = pool.getConnection();
		List<Customers> allCustomers = new ArrayList<>();
		try {
			PreparedStatement st = con.prepareStatement("select * from Customers");
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("CustomerID");
				String firstName = rs.getString("FirstName");
				String lastName = rs.getString("LastName");
				String email = rs.getString("Email");
				String password = rs.getString("Password");
				allCustomers.add(new Customers(id, firstName, lastName, email, password, getAllCouponsByCustomer(id)));
			}

		} catch (SQLException e) {
			throw new SQLException(e.getMessage());
		} finally {
			pool.returnConnection(con);
		}

		return allCustomers;
	}

	public Customers getOneCustomer(int id) throws SQLException {
		Connection con = pool.getConnection();

		try {
			PreparedStatement st = con.prepareStatement("select * from Customers where CustomerID = ?");
			st.setInt(1, id);
			ResultSet rs = st.executeQuery();
			Customers customer = null;

			while (rs.next()) {

				customer = new Customers(id, rs.getString("FirstName"), rs.getString("LastName"), rs.getString("Email"),
						rs.getString("Password"), getAllCouponsByCustomer(id));
			}
			return customer;

		} catch (SQLException e) {
			throw new SQLException(e.getMessage());
		} finally {
			pool.returnConnection(con);
		}
	}

	@Override
	public boolean isCustomerExists(String email, String password) throws SQLException {
		Connection con = pool.getConnection();
		try {
			PreparedStatement st = con.prepareStatement("select * from Customers where Email=? and Password=?");
			st.setString(1, email);
			st.setString(2, password);
			ResultSet rs = st.executeQuery();
			return rs.next();
		} catch (SQLException e) {
			throw new SQLException(e.getMessage());
		} finally {
			pool.returnConnection(con);
		}
	}

	public ArrayList<Coupons> getAllCouponsByCustomer(int customerId) throws SQLException {
		Connection con = pool.getConnection();
		ArrayList<Coupons> customerCoupons = new ArrayList<>();
		try {
			PreparedStatement st = con.prepareStatement("select CouponID from Customers_VS_Coupons where CustomerID=?");
			st.setInt(1, customerId);

			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				customerCoupons.add(couponDB.getOneCoupon(rs.getInt("CouponID")));
			}

		} catch (SQLException e) {
			throw new SQLException(e.getMessage());
		} finally {
			pool.returnConnection(con);
		}

		return customerCoupons;
	}

	@Override
	public Integer getCustomerId(String email, String password) throws SQLException {
		Connection con = pool.getConnection();
		int id = 0;
		try {
			PreparedStatement st = con
					.prepareStatement("select CustomerID from Customers where Email=? and Password=?");
			st.setString(1, email);
			st.setString(2, password);
			ResultSet rs = st.executeQuery();
			while (rs.next()) {

				id = rs.getInt("CustomerID");
			}
		} catch (SQLException e) {
			throw new SQLException(e.getMessage());
		} finally {
			pool.returnConnection(con);
		}
		return id;

	}

}
