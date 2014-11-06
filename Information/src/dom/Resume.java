package dom;

/**
 * Resume entity. @author MyEclipse Persistence Tools
 */

public class Resume implements java.io.Serializable {

	// Fields

	private String username;
	private String technology;
	private String gpa;
	private String personalHonor;
	private String slogan;

	// Constructors

	/** default constructor */
	public Resume() {
	}

	/** minimal constructor */
	public Resume(String username) {
		this.username = username;
	}

	/** full constructor */
	public Resume(String username, String technology, String gpa,
			String personalHonor, String slogan) {
		this.username = username;
		this.technology = technology;
		this.gpa = gpa;
		this.personalHonor = personalHonor;
		this.slogan = slogan;
	}

	// Property accessors

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getTechnology() {
		return this.technology;
	}

	public void setTechnology(String technology) {
		this.technology = technology;
	}

	public String getGpa() {
		return this.gpa;
	}

	public void setGpa(String gpa) {
		this.gpa = gpa;
	}

	public String getPersonalHonor() {
		return this.personalHonor;
	}

	public void setPersonalHonor(String personalHonor) {
		this.personalHonor = personalHonor;
	}

	public String getSlogan() {
		return this.slogan;
	}

	public void setSlogan(String slogan) {
		this.slogan = slogan;
	}

}