package db;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import exceptions.CouponsNotFoundException;
import facadeExceptions.CouponCantBeAddedException;
import facadeExceptions.CouponCantBeUpdatedException;
import javaBeans.Categories;
import javaBeans.Coupons;

public class CouponsDBDAO implements CouponsDAO {

	private ConnectionPool pool = ConnectionPool.getInstance();

	public void addCoupon(Coupons coupon) throws SQLException, CouponCantBeAddedException {
		Connection con = pool.getConnection();

		try {
			PreparedStatement stmt = con.prepareStatement("insert into Coupons values(?, ?, ?, ?, ?, ?, ?, ?, ?)");
			stmt.setInt(1, coupon.getCompanyId());
			stmt.setInt(2, coupon.getCategories().ordinal() + 1);
			stmt.setString(3, coupon.getTitle());
			stmt.setString(4, coupon.getDescription());
			stmt.setDate(5, coupon.getStartDate());
			stmt.setDate(6, coupon.getEndDate());
			stmt.setInt(7, coupon.getAmount());
			stmt.setDouble(8, coupon.getPrice());
			stmt.setString(9, coupon.getImage());
			stmt.execute();

		} catch (SQLException e) {
			throw new SQLException(e);
		} finally {
			pool.returnConnection(con);
		}
	}

	public void deleteCoupon(int id) throws SQLException, CouponsNotFoundException {
		Connection con = pool.getConnection();
		try {
			PreparedStatement st = con.prepareStatement("delete from Coupons where ID = ?");
			st.setInt(1, id);
			int count = st.executeUpdate();
			if (count == 0) {
				throw new CouponsNotFoundException();
			}

		} catch (SQLException e) {
			throw new SQLException(e.getMessage());
		} finally {
			pool.returnConnection(con);
		}
	}

	public void updateCoupon(Coupons coupon) throws SQLException, CouponCantBeUpdatedException {
		Connection con = pool.getConnection();
		try {
			PreparedStatement stmt = con.prepareStatement("update Coupons set CompanyID= " + coupon.getCompanyId()
					+ " , CategoryID=" + (coupon.getCategories().ordinal() + 1) + " , Title=" + coupon.getTitle()
					+ " , Description=" + coupon.getDescription() + " , StartDate='" + coupon.getStartDate()
					+ "' , EndDate='" + coupon.getEndDate() + "' , Amount=" + coupon.getAmount() + " , PriceForCoupon="
					+ coupon.getPrice() + " , Image=" + coupon.getImage() + " where ID= " + coupon.getId());
			int count = stmt.executeUpdate();
			if (count == 0) {
				throw new CouponCantBeUpdatedException();
			}
		} catch (SQLException e) {
			throw new SQLException("Coupon has purchased");
		} finally {
			pool.returnConnection(con);
		}
	}

	public List<Coupons> getAllCoupons() throws SQLException {
		Connection con = pool.getConnection();
		ArrayList<Coupons> coupons = new ArrayList<>();
		try {
			PreparedStatement st = con.prepareStatement("select * from Coupons");
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("ID");
				int companyId = rs.getInt("CompanyID");
				// int categoryId = rs.getInt("CategoryID");
				String title = rs.getString("Title");
				String description = rs.getString("Description");
				Date startDate = rs.getDate("StartDate");
				Date endDate = rs.getDate("EndDate");
				int amount = rs.getInt("Amount");
				double price = rs.getDouble("PriceForCoupon");
				String image = rs.getString("Image");

				coupons.add(new Coupons(id, companyId, Categories.values()[(rs.getInt("CategoryID")) - 1], title,
						description, startDate, endDate, amount, price, image));
			}

		} catch (SQLException e) {
			throw new SQLException(e.getMessage());
		} finally {
			pool.returnConnection(con);
		}

		return coupons;
	}

	public Coupons getOneCoupon(int id) throws SQLException {
		Connection con = pool.getConnection();
		Coupons coupon = new Coupons();
		try {
			PreparedStatement st = con.prepareStatement("select * from Coupons where ID = ?");
			st.setInt(1, id);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				coupon = new Coupons(id, rs.getInt("CompanyID"), Categories.values()[(rs.getInt("CategoryID")) - 1],
						rs.getString("Title"), rs.getString("Description"), rs.getDate("StartDate"),
						rs.getDate("endDate"), rs.getInt("Amount"), rs.getDouble("PriceForCoupon"),
						rs.getString("Image"));

			}
			return coupon;

		} catch (SQLException e) {
			throw new SQLException(e.getMessage());
		} finally {
			pool.returnConnection(con);
		}
	}

	public void addCouponPurchase(int id, int customerId) throws SQLException {
		Connection con = pool.getConnection();
		try {
			PreparedStatement stmt = con.prepareStatement("insert into Customers_VS_Coupons values(?,?)");
			stmt.setInt(1, customerId);
			stmt.setInt(2, id);
			stmt.execute();

		} catch (SQLException e) {
			throw new SQLException(e.getMessage());
		} finally {
			pool.returnConnection(con);

		}
	}

	@Override
	public void deleteCouponPurchase(int id, int customerId) throws SQLException, CouponsNotFoundException {
		Connection con = pool.getConnection();
		try {
			PreparedStatement stmt = con
					.prepareStatement("delete from Customers_VS_Coupons where CustomerID = ? and CouponID = ?");
			stmt.setInt(1, customerId);
			stmt.setInt(2, id);
			int count = stmt.executeUpdate();
			if (count == 0) {
				throw new CouponsNotFoundException();
			}

		} catch (SQLException e) {
			throw new SQLException(e.getMessage());
		} finally {
			pool.returnConnection(con);

		}

	}
}
