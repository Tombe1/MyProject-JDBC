package javaBeans;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Program {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			Connection con = DriverManager
					.getConnection("jdbc:sqlserver://localhost;databaseName=MyProject; integratedSecurity=true");
			ArrayList<Coupons> coupons = new ArrayList<>();
			PreparedStatement stmt = con.prepareStatement("select * from Coupons");
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {

				System.out.println(
						"Coupon info:  " + rs.getString("Title") + ", His use: " + rs.getString("Description"));
				coupons.add(new Coupons(rs.getInt("ID"), rs.getInt("CompanyID"), Categories.Computers,
						rs.getString("Title"), rs.getString("Description"), rs.getDate("StartDate"),
						rs.getDate("endDate"), rs.getInt("Amount"), rs.getDouble("PriceForCoupon"),
						rs.getString("Image")));

			}
			for (Coupons c1 : coupons) {
				System.out.println(c1.getAmount() * c1.getPrice());
				System.out.println(coupons);

			}

		} catch (ClassNotFoundException e) {
			System.out.println("OOPS JDBC ERROR.." + e.getMessage());
		} catch (SQLException e) {
			System.out.println("SQL ERROR!" + e.getMessage());
		}

	}

}
