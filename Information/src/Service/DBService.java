package Service;

public interface DBService {
	boolean Login(String username,String password);
	String EditUser(String json);
	String EditResume(String json);
	String Register(String json);
	String EditMyProject(String json);
	String FindMyPsd(String json);
	String getResume(String username);
	String getUserInfo(String username);
	String ApplyProject(String username,String title);
}
