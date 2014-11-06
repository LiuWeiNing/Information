package Action;

import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;
import Log.Log;
import Service.DBService;

import com.opensymphony.xwork2.ActionSupport;

public class AndroidAction extends ActionSupport{
	private String username;
	private String password;
	private String user;
//	private String resume;
	private String result;
	private String title;
	public void setTitle(String title) {
		this.title = title;
	}

	//	private void SetResume(String resume)
//	{
//		this.resume=resume;
//	}
	public void setUser(String user) {
		this.user = user;
	}

	private Map<String,String> map;
	private DBService dbService;
	
	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	public String getResult() {
		return result;
	}

	public void setDbService(DBService dbService) {
		this.dbService = dbService;
	}

	public String Login()
	{
		map = new HashMap<String,String>();
		if(username!=null)
		{
			if(password!=null)
			{
				Log.info("Login:"+username+","+password);
				if(dbService.Login(username, password))
				{
					map.put("msg", "登录成功！");
					map.put("flag", "true");
				}
				else
				{
					map.put("msg", "用户名或密码不存在！");
					map.put("flag", "false");
				}
			}
			else
			{
				map.put("msg", "密码为空！");
				map.put("flag", "false");
			}
		}else
		{
			map.put("msg", "用户名为空！");
			map.put("flag", "false");
		}
		JSONObject json = JSONObject.fromObject(map);
		result = json.toString();
//		System.out.println(json);
		return SUCCESS;
	}
	public String EditResume()
	{
		map = new HashMap<String,String>();
		
		if(user!=null)
		{
			Log.info("resume edit:"+user);
			String info = dbService.EditResume(user);
			if(info.equals("success"))
			{
				map.put("flag", "true");
				map.put("msg", "编辑成功");
			}
			else
			{
				map.put("flag", "false");
				map.put("msg", info);
			}
		}
		else
		{
			map.put("flag", "false");
			map.put("msg", "简历为空");
		}
		JSONObject json = JSONObject.fromObject(map);
		result = json.toString();
		return SUCCESS;
		
	}
	public String ApplyProject()
	{
		result = dbService.ApplyProject(username, title);
		return SUCCESS;
	}
	public String getUserInfo()

	{
		result = dbService.getUserInfo(username);
		return SUCCESS;
	}
	public String getResume()
	{
		result = dbService.getResume(username);
		return SUCCESS;
	}
	public String FindMyPsd()
	{
		map = new HashMap<String,String>();
		if(user!=null)
		{
			Log.info("FindMyPsd:"+user);
			String info = dbService.FindMyPsd(user);
			if(info.equals("success"))
			{
				map.put("flag","true");
				map.put("msg","修改成功");
			}
			else
			{
				map.put("flag", "false");
				map.put("msg", info);
			}
		}
		else
		{
			map.put("flag", "false");
			map.put("msg", "信息为空");
		}
		JSONObject json = JSONObject.fromObject(map);
		result = json.toString();
		return SUCCESS;
	}
	public String EditUserInfo()
	{
		map = new HashMap<String,String>();
		
		if(user!=null)
		{
			Log.info("user edit:"+user);
			String info = dbService.EditUser(user);
			if(info.equals("success"))
			{
				map.put("flag", "true");
				map.put("msg", "修改成功！");
			}
			else
			{
				map.put("flag", "false");
				map.put("msg", info);
			}
			
		}else
		{
			map.put("flag", "false");
			map.put("msg", "信息为空");
		}
		JSONObject json = JSONObject.fromObject(map);
		result = json.toString();
		return SUCCESS;
	}
	public String Register()
	{
		map = new HashMap<String,String>();
		System.out.println(user);
		if(user!=null)
		{
			String info = dbService.Register(user);
			Log.info(info);
			if(info.equals("success"))
			{
				map.put("flag", "true");
				map.put("msg", "注册成功！");
			}else
			{
				map.put("flag", "false");
				map.put("msg", info);
			}
		}
		else
		{
			map.put("flag", "false");
			map.put("msg", "信息为空");
		}
		JSONObject json = JSONObject.fromObject(map);
		result = json.toString();
		return SUCCESS;
	}

}
