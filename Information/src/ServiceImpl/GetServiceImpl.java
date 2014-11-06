package ServiceImpl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

import net.sf.json.JSONArray;
import Log.Log;
import Service.GetService;

public class GetServiceImpl implements GetService {
	private String basePath = this.getClass().getClassLoader().getResource("/")
			.getPath();

	@Override
	public String getTitle(String path) {
		// TODO Auto-generated method stub
		String path1 = basePath + path + "/";
		File file = new File(path1);
		JSONArray array = new JSONArray();
		String result = "";
		if (file.exists()) {
			if (file != null) {
				String Title[] = file.list();
				if (Title != null) {
					ArrayList<String> arrayList = new ArrayList<String>();

					for (int i = 0; i < Title.length; i++) {
						if (Title[i].lastIndexOf(".") != -1) {
							Title[i] = Title[i].substring(0,
									Title[i].lastIndexOf("."));
							arrayList.add(Title[i]);
						}
					}
					if (Title.length > 0) {

						array = JSONArray.fromObject(arrayList);
						result = array.toString();
						return result;
					}
				}
			}
		}
		result = array.toString();
		return result;

	}

	public String requestTitle(String path) {
		String path1 = basePath + path + "/";
		File file = new File(path1);
		JSONArray array = new JSONArray();
		String result = "";
		if (file.exists()) {
			if (file != null) {
				ArrayList<HashMap> arrayList = new ArrayList<HashMap>();
				for (File f : file.listFiles()) {
					if (f.getName().endsWith(".txt")) {
						HashMap<String, String> map = new HashMap<String, String>();
						map.put("title", f.getName().replace(".txt", ""));
						Calendar cal = Calendar.getInstance();
						cal.setTimeInMillis(f.lastModified());
						map.put("time", cal.getTime().toLocaleString().substring(0,cal.getTime().toLocaleString().lastIndexOf(' ')));
						arrayList.add(map);
					}
				}
				array = JSONArray.fromObject(arrayList);
			}
		}
		result = array.toString();
		Log.info("requestTitle:"+result);
		return result;
	}

	@Override
	public String getContent(String path, String title) {
		// TODO Auto-generated method stub
		String path1 = basePath + path + "/" + title + ".txt";
		String tempString = "";
		String result = "";
		try {
			File file = new File(path1);
			if (file.isFile() && file.exists()) {
				InputStreamReader read = new InputStreamReader(
						new FileInputStream(file), "utf-8");
				BufferedReader reader = new BufferedReader(read);
				while ((tempString = reader.readLine()) != null) {
					if (!result.equals(""))
						result = result + "\n" + tempString;
					else {
						result = result + tempString;
					}

				}
				reader.close();
			}

		} catch (IOException e) {
			e.printStackTrace();

		}
		Log.info("RequestContent:"+result);
		return result;
	}

	@Override
	public String Delete(String path, String title) {
		// TODO Auto-generated method stub
		String path1 = basePath + path + "/" + title + ".txt";
		System.out.println(path1);
		String result = "";
		File file = new File(path1);
		if (file.isFile() && file.exists()) {
			file.delete();
			result = "success";
		} else {
			result = "not exist";
		}
		System.out.println(result);
		return result;
	}

}
