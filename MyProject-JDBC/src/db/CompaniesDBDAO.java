package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import exceptions.CompaniesNotFoundException;
import facadeExceptions.CompanyCantBeUpdatedException;
import javaBeans.Companies;
import javaBeans.Coupons;

public class CompaniesDBDAO implements CompaniesDAO {

	private ConnectionPool pool = ConnectionPool.getInstance();
	CouponsDBDAO couponDB = new CouponsDBDAO();

	public void addCompany(Companies company) throws SQLException {
		Connection con = pool.getConnection();

		try {
			PreparedStatement stmt = con.prepareStatement("insert into Companies values(?, ?, ?)");
			stmt.setString(1, company.getName());
			stmt.setString(2, company.getEmail());
			stmt.setString(3, company.getPassword());
			stmt.execute();

		} catch (SQLException e) {
			throw new SQLException(e);
		} finally {
			pool.returnConnection(con);
		}

	}

	public void deleteCompany(int id) throws SQLException, CompaniesNotFoundException {
		Connection con = pool.getConnection();
		try {
			PreparedStatement st = con.prepareStatement("delete from companies where companyid = ?");
			st.setInt(1, id);
			int count = st.executeUpdate();
			if (count == 0) {
				throw new CompaniesNotFoundException();
			}

		} catch (SQLException e) {
			throw new SQLException(e.getMessage());
		} finally {
			pool.returnConnection(con);
		}
	}

	public void updateCompany(Companies company) throws SQLException, CompanyCantBeUpdatedException {
		Connection con = pool.getConnection();
		try {
			PreparedStatement stmt = con.prepareStatement("update Companies set CompanyEmail=" + company.getEmail()
					+ ", Password=" + company.getPassword() + " where CompanyID = " + company.getId());
			int count = stmt.executeUpdate();
			if (count == 0) {
				throw new CompanyCantBeUpdatedException();
			}

		} catch (SQLException e) {
			throw new SQLException(e.getMessage());
		} finally {
			pool.returnConnection(con);
		}

	}

	public List<Companies> getAllCompanies() throws SQLException {
		Connection con = pool.getConnection();
		ArrayList<Companies> companies = new ArrayList<>();
		try {
			PreparedStatement st = con.prepareStatement("select * from companies");
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("CompanyID");
				String name = rs.getString("CompanyName");
				String email = rs.getString("CompanyEmail");
				String password = rs.getString("password");
				companies.add(new Companies(id, name, email, password, getAllCouponsByCompany(id)));
			}

		} catch (SQLException e) {
			throw new SQLException(e.getMessage());
		} finally {
			pool.returnConnection(con);
		}

		return companies;
	}

	public Companies getOneCompany(int id) throws SQLException {
		Connection con = pool.getConnection();

		try {
			PreparedStatement st = con.prepareStatement("select * from companies where companyid = ?");
			st.setInt(1, id);
			ResultSet rs = st.executeQuery();
			Companies company = null;

			while (rs.next()) {
				id = rs.getInt("CompanyID");
				String name = rs.getString("CompanyName");
				String email = rs.getString("CompanyEmail");
				String password = rs.getString("password");

				company = new Companies(id, name, email, password, getAllCouponsByCompany(id));
			}
			return company;
		} catch (SQLException e) {
			throw new SQLException(e.getMessage());
		} finally {
			pool.returnConnection(con);
		}
	}

	@Override
	public boolean isCompanyExists(String email, String password) throws SQLException {
		Connection con = pool.getConnection();
		try {
			PreparedStatement st = con.prepareStatement("select * from Companies where CompanyEmail=? and Password=?");
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

	public ArrayList<Coupons> getAllCouponsByCompany(int companyId) throws SQLException {
		Connection con = pool.getConnection();
		ArrayList<Coupons> coupons = new ArrayList<>();
		try {
			PreparedStatement st = con.prepareStatement("select * from Coupons where CompanyID=?");
			st.setInt(1, companyId);

			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				companyId = rs.getInt("CompanyID");
				coupons.add(couponDB.getOneCoupon(rs.getInt("ID")));
			}

		} catch (SQLException e) {
			throw new SQLException(e.getMessage());
		} finally {
			pool.returnConnection(con);
		}

		return coupons;
	}

	public Integer getCompanyId(String email, String password) throws SQLException {
		Connection con = pool.getConnection();
		int id=0;
		try {
			PreparedStatement st = con
					.prepareStatement("select CompanyID from Companies where CompanyEmail=? and Password=?");
			st.setString(1, email);
			st.setString(2, password);
			ResultSet rs = st.executeQuery();
			while (rs.next()) {

				id = rs.getInt("CompanyID");
			}
		} catch (SQLException e) {
			throw new SQLException(e.getMessage());
		} finally {
			pool.returnConnection(con);
		}
		return id;
	}

}
