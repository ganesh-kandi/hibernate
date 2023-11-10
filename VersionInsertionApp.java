package in.project.second;


import in.project.Model.MobileCustomer;
import in.project.Util.HibernateUtil;

import org.hibernate.Session;
import org.hibernate.*;

public class VersionInsertionApp {

	public static void main(String[] args) {
		Session session=null;
		Transaction transaction=null;
		boolean flag=false;
		MobileCustomer customer=null;
		try {
			session=HibernateUtil.getSession();
			if(session!=null) {
				transaction=session.beginTransaction();
				System.out.println("session obj is came...");
					if(transaction!=null) {
						customer=new MobileCustomer();
						customer.setcName("ganesh");
						customer.setMobileNo(222888999);
						customer.setCallerTune("KGF-2");
					
						session.save(customer);
						flag=true;
					}
		}
		}catch (HibernateException e) {
			e.printStackTrace();
		}finally {
			if(flag==true) {
				transaction.commit();
				System.out.println("successfully saved...");
				System.out.println("The version count after operation is "+customer.getVersionCount());
			}
			else
				transaction.rollback();
		}
		
		HibernateUtil.closeSession(session);
		System.out.println("session closed...");
		
	}

}
