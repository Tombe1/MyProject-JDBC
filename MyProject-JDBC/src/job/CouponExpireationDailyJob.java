package job;

import java.sql.SQLException;
import java.util.Calendar;

import db.CouponsDAO;
import exceptions.CouponsNotFoundException;
import javaBeans.Coupons;

public class CouponExpireationDailyJob implements Runnable {

	private CouponsDAO couponsDAO;
	private boolean quit;

	public CouponExpireationDailyJob() {
		super();
	}

	public CouponExpireationDailyJob(boolean quit) {
		super();
		this.quit = quit;
	}

	public CouponExpireationDailyJob(CouponsDAO couponsDAO, boolean quit) {
		super();
		this.couponsDAO = couponsDAO;
		this.quit = quit;
	}

	public CouponsDAO getCouponsDAO() {
		return couponsDAO;
	}

	public void setCouponsDAO(CouponsDAO couponsDAO) {
		this.couponsDAO = couponsDAO;
	}

	public boolean isQuit() {
		return quit;
	}

	public void setQuit(boolean quit) {
		this.quit = quit;
	}

	@Override
	public void run() {
		while (quit == true) {
			Calendar cc = Calendar.getInstance();
			try {
				for (Coupons c : couponsDAO.getAllCoupons()) {
					if (c.getEndDate().after(cc.getTime())) {
						couponsDAO.deleteCoupon(c.getId());
					}
				}
				Thread.sleep(86400000);
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			} catch (CouponsNotFoundException e) {
				System.out.println(e.getMessage());
			} catch (InterruptedException e) {
				System.out.println(e.getMessage());
			}
		}
	}

	public void stop() {
		if (quit == true) {
			quit = false;
		}
	}
}
