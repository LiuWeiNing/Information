package dom;

/**
 * UserInfo entity. @author MyEclipse Persistence Tools
 */

public class UserInfo implements java.io.Serializable {

	// Fields

	private String username;
	private String password;
	private String email;
	private String phone;
	private String gender;
	private String name;
	private String class_;
	private String grade;
	private String sei;
	private String mylab;

	// Constructors

	/** default constructor */
	public UserInfo() {
	}

	/** minimal constructor */
	public UserInfo(String username, String password, String email) {
		this.username = username;
		this.password = password;
		this.email = email;
	}

	/** full constructor */
	public UserInfo(String username, String password, String email,
			String phone, String gender, String name, String class_,
			String grade, String sei, String mylab) {
		this.username = username;
		this.password = password;
		this.email = email;
		this.phone = phone;
		this.gender = gender;
		this.name = name;
		this.class_ = class_;
		this.grade = grade;
		this.sei = sei;
		this.mylab = mylab;
	}

	// Property accessors

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getGender() {
		return this.gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getClass_() {
		return this.class_;
	}

	public void setClass_(String class_) {
		this.class_ = class_;
	}

	public String getGrade() {
		return this.grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getSei() {
		return this.sei;
	}

	public void setSei(String sei) {
		this.sei = sei;
	}

	public String getMylab() {
		return this.mylab;
	}

	public void setMylab(String mylab) {
		this.mylab = mylab;
	}

}