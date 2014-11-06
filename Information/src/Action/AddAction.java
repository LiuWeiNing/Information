package Action;

import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;
import Service.AddService;

import com.opensymphony.xwork2.ActionSupport;

public class AddAction extends ActionSupport {
	private String title;
	private String content;
	private String result;
	Map<String, String> map = new HashMap<String, String>();
	private AddService addService;

	public void setAddService(AddService addService) {
		this.addService = addService;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String addNews() throws Exception {
		add("New", "News-list.html");
		return SUCCESS;
	}

	public String addKnowledge() throws Exception {
		add("Knowledge", "Knowledge-list.html");
		return SUCCESS;
	}

	public String addNotice() throws Exception {
		add("Notice", "Notice-list.html");
		return SUCCESS;

	}

	public String addProject() throws Exception {
		add("Project", "Project-list.html");
		return SUCCESS;

	}

	public void add(String path, String url) {
		if (title != null&&!title.equals("")) {
			if (content != null) {
				
					if (addService.add(path, title, content)) {
						map.put("flag", "true");
						map.put("msg", "添加成功！");
						map.put("url", url);
					} else {
						map.put("flag", "false");
						map.put("msg", "添加失败！");
					}

			} else {
				map.put("flag", "false");
				map.put("msg", "the content can't be empty！");
			}
		} else {
			map.put("flag", "false");
			map.put("msg", "title为空！");
		}
		JSONObject json = JSONObject.fromObject(map);
		result = json.toString();
	}
}
