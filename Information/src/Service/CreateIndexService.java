package Service;

import net.sf.json.JSONArray;

public interface CreateIndexService {
	void createIndex(String path) throws Exception;
	String sreach(String path,String key);
}
