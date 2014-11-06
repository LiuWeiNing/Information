package Action;

import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

import com.opensymphony.xwork2.ActionSupport;

public class AdminLoginAction extends ActionSupport{
	private String username;
	private String password;
	private String result;
	Map<String ,String> map=new HashMap<String,String>();
	public void setUsername(String username) {
		this.username = username;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public String login() throws Exception{
		System.out.println(username);
		System.out.println(password);
		if(username.equals("admin"))
		{
			System.out.print(username);
			if(password.equals("admin"))
			{
				map.put("flag", "true"); // 标识位
				map.put("msg", "登陆成功，正在跳转..."); // 提示信息
				map.put("url", "index.html"); // 跳转页面
				JSONObject json=JSONObject.fromObject(map);
				result=json.toString();
				System.out.println(result);
			}
			else
			{
				map.put("flag", "false");
				map.put("msg", "密码错误！");
				JSONObject json=JSONObject.fromObject(map);
				result=json.toString();
				System.out.println(result);
			}
		}
		else
		{
			map.put("flag", "false");
			map.put("msg", "用户名不存在！");
			JSONObject json=JSONObject.fromObject(map);
			result=json.toString();
			System.out.println(result);
		}
		//JSONObject json=JSONObject.fromObject(map);
		//result=json.toString();
		
		return SUCCESS;
		
	}

}
