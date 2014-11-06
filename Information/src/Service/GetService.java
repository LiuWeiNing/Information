package Service;

public interface GetService {
	String getTitle(String path);
	String getContent(String path,String title);
	String Delete(String path,String title);
	String requestTitle(String path);
}
