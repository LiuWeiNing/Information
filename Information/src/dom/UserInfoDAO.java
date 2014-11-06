package dom;

import java.util.List;

import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.Transaction;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A data access object (DAO) providing persistence and search support for
 * UserInfo entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see dom.UserInfo
 * @author MyEclipse Persistence Tools
 */
public class UserInfoDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(UserInfoDAO.class);
	// property constants
	public static final String PASSWORD = "password";
	public static final String EMAIL = "email";
	public static final String PHONE = "phone";
	public static final String GENDER = "gender";
	public static final String NAME = "name";
	public static final String CLASS_ = "class_";
	public static final String GRADE = "grade";
	public static final String SEI = "sei";
	public static final String MYLAB = "mylab";

	public void save(UserInfo transientInstance) {
		log.debug("saving UserInfo instance");
		Transaction tr = getSession().beginTransaction();
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
		tr.commit();
		getSession().flush();
		getSession().close();
	}

	public void delete(UserInfo persistentInstance) {
		log.debug("deleting UserInfo instance");
		Transaction tr = getSession().beginTransaction();
		
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
		tr.commit();
		getSession().flush();
		getSession().close();
	}

	public UserInfo findById(java.lang.String id) {
		log.debug("getting UserInfo instance with id: " + id);
		Transaction tr = getSession().beginTransaction();
		try {
			UserInfo instance = (UserInfo) getSession().get("dom.UserInfo", id);
			tr.commit();
			getSession().flush();
			getSession().close();
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(UserInfo instance) {
		log.debug("finding UserInfo instance by example");
		try {
			List results = getSession().createCriteria("dom.UserInfo")
					.add(Example.create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding UserInfo instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from UserInfo as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByPassword(Object password) {
		return findByProperty(PASSWORD, password);
	}

	public List findByEmail(Object email) {
		return findByProperty(EMAIL, email);
	}

	public List findByPhone(Object phone) {
		return findByProperty(PHONE, phone);
	}

	public List findByGender(Object gender) {
		return findByProperty(GENDER, gender);
	}

	public List findByName(Object name) {
		return findByProperty(NAME, name);
	}

	public List findByClass_(Object class_) {
		return findByProperty(CLASS_, class_);
	}

	public List findByGrade(Object grade) {
		return findByProperty(GRADE, grade);
	}

	public List findBySei(Object sei) {
		return findByProperty(SEI, sei);
	}

	public List findByMylab(Object mylab) {
		return findByProperty(MYLAB, mylab);
	}

	public List findAll() {
		log.debug("finding all UserInfo instances");
		
		try {
			String queryString = "from UserInfo";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public UserInfo merge(UserInfo detachedInstance) {
		log.debug("merging UserInfo instance");
		Transaction tr = getSession().beginTransaction();
		try {
			UserInfo result = (UserInfo) getSession().merge(detachedInstance);
			log.debug("merge successful");
			tr.commit();
			getSession().flush();
			getSession().close();
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
		
	}
	public void update (UserInfo userInfo)
	{
		log.debug("merging UserInfo instance");
		Transaction tr = getSession().beginTransaction();
		try {
			getSession().update(userInfo);
			log.debug("merge successful");
			
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			
			throw re;
			
		}
		tr.commit();
		getSession().flush();
		getSession().close();
	}

	public void attachDirty(UserInfo instance) {
		log.debug("attaching dirty UserInfo instance");
		Transaction tr = getSession().beginTransaction();
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
		tr.commit();
		getSession().flush();
		getSession().close();
	}

	public void attachClean(UserInfo instance) {
		log.debug("attaching clean UserInfo instance");
		try {
			getSession().buildLockRequest(LockOptions.NONE).lock(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}