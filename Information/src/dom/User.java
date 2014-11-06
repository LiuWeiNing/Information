package dom;

/**
 * User entity. @author MyEclipse Persistence Tools
 */

public class User implements java.io.Serializable {

	// Fields

	private UserId id;
	private String MGender;
	private String MName;
	private String MTelnum;

	// Constructors

	/** default constructor */
	public User() {
	}

	/** minimal constructor */
	public User(UserId id) {
		this.id = id;
	}

	/** full constructor */
	public User(UserId id, String MGender, String MName, String MTelnum) {
		this.id = id;
		this.MGender = MGender;
		this.MName = MName;
		this.MTelnum = MTelnum;
	}

	// Property accessors

	public UserId getId() {
		return this.id;
	}

	public void setId(UserId id) {
		this.id = id;
	}

	public String getMGender() {
		return this.MGender;
	}

	public void setMGender(String MGender) {
		this.MGender = MGender;
	}

	public String getMName() {
		return this.MName;
	}

	public void setMName(String MName) {
		this.MName = MName;
	}

	public String getMTelnum() {
		return this.MTelnum;
	}

	public void setMTelnum(String MTelnum) {
		this.MTelnum = MTelnum;
	}

}