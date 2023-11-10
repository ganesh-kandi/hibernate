package in.project.Util;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

		static Configuration cfg=null;
			static SessionFactory sessionFactory=null;
			static Session session=null;
			
		static {
			try {
				cfg=new Configuration();
				System.out.println(cfg.configure());
				sessionFactory=cfg.buildSessionFactory();
				
			} catch (HibernateException e) {
				e.printStackTrace();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		
		public static  Session getSession() {
			if(session==null)
				session=sessionFactory.openSession();
			
			return session;
		}
		public static  void closeSession(Session session) {
			if(session!=null)
				session.close();
		}
		
}
