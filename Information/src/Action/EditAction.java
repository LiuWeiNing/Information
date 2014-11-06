package Action;

import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import Service.GetService;

import com.opensymphony.xwork2.ActionSupport;

public class EditAction extends ActionSupport {
	private String title;
	private String result;
	private GetService getService;
	private String path;
	private Map <String,String>map ;
	public void setPageIndex(String pageIndex) {
		this.pageIndex = pageIndex;
	}

	private String pageIndex;

	public void setPath(String path) {
		this.path = path;
	}

	public String getResult() {
		return result;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public void setGetService(GetService getService) {
		this.getService = getService;
	}


	public String getAllTitle() {
		// result=getService.getTitle(path);
		System.out.println(path);
		if (getService.getTitle(path).equals("")) {
			result = "";
			System.out.println("+"+path);
		} else {
			JSONArray array = JSONArray.fromObject(getService.getTitle(path));
			JSONArray json = new JSONArray();
			int index = Integer.parseInt(pageIndex);
			for (int i = (index - 1) * 5; i < index * 5; i++) {
				if (i < array.size()) {
					json.add(array.get(i));
				}
			}
			result = json.toString();
			System.out.println(path);
		}
		System.out.println("++"+result);
		return SUCCESS;
	}

	public String RequestTitle() {
		map = new HashMap<String,String>();
		JSONArray array = new JSONArray();
		if (path != null && !path.equals(""))
		{
			if (getService.getTitle(path).equals("")) {
//				result = array.toString();
				map.put("flag","false");

			} else {
				array = JSONArray.fromObject(getService.requestTitle(path));

//				result = array.toString();
				map.put("flag","true");
			}
		}
		else
		{
			map.put("flag","false");
//			result=array.toString();
		}
		map.put("result",array.toString());
		JSONObject json = JSONObject.fromObject(map);
		result = json.toString();
//		System.out.println(result);
		return SUCCESS;
	}

	public String getContent() {
		map = new HashMap<String,String>();
//		System.out.println(path+"/"+title);
		if (title != null && !title.equals("")) {
			if (path != null && !path.equals("")) {
				String content = getService.getContent(path, title);
//				System.out.println("/"+content);
				map.put("flag", "true");
				map.put("content", content);
//				System.out.println("/"+content);
			} else {
				map.put("flag", "false");
				map.put("msg", "pathÎª¿Õ");
			}
		} else {
			map.put("flag", "false");
			map.put("msg", "titleÎª¿Õ");
		}
//		System.out.println(path+"/"+title);
		result = JSONObject.fromObject(map).toString();
//		System.out.println(result);
		return SUCCESS;
	}

	public String Delete() {
		map = new HashMap<String,String>();
		if (title != null && !title.equals("")) {
			if (path != null && !path.equals("")) {
				String content = getService.Delete(path, title);
				System.out.println(content);
				if (content.equals("success")) {
					map.put("flag", "true");
					map.put("msg", "É¾³ý³É¹¦!");

				} else {
					map.put("flag", "false");
					map.put("msg", "É¾³ýÊ§°Ü!");
				}
				map.put("content", content);
			} else {
				map.put("flag", "false");
				map.put("msg", "pathÎª¿Õ");
			}
		} else {
			map.put("flag", "false");
			map.put("msg", "titleÎª¿Õ");
		}
		result = JSONObject.fromObject(map).toString();
		return SUCCESS;
	}

}
