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
				map.put("flag", "true"); // ��ʶλ
				map.put("msg", "��½�ɹ���������ת..."); // ��ʾ��Ϣ
				map.put("url", "index.html"); // ��תҳ��
				JSONObject json=JSONObject.fromObject(map);
				result=json.toString();
				System.out.println(result);
			}
			else
			{
				map.put("flag", "false");
				map.put("msg", "�������");
				JSONObject json=JSONObject.fromObject(map);
				result=json.toString();
				System.out.println(result);
			}
		}
		else
		{
			map.put("flag", "false");
			map.put("msg", "�û��������ڣ�");
			JSONObject json=JSONObject.fromObject(map);
			result=json.toString();
			System.out.println(result);
		}
		//JSONObject json=JSONObject.fromObject(map);
		//result=json.toString();
		
		return SUCCESS;
		
	}

}
