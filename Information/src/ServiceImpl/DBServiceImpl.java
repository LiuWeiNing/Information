package ServiceImpl;

import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONException;
import net.sf.json.JSONObject;
import Log.Log;
import Service.DBService;
import dom.Resume;
import dom.ResumeDAO;
import dom.User;
import dom.UserDAO;
import dom.UserId;
import dom.UserInfo;
import dom.UserInfoDAO;

public class DBServiceImpl implements DBService {
	// private Logger log =
	private UserDAO userDao;
	private UserId userId;
	private User user;
	private UserInfo userInfo;
	private UserInfoDAO userInfoDao;
	private Resume resume;


	private ResumeDAO resumeDao;
	public void setResumeDao(ResumeDAO resumeDao) {
		this.resumeDao = resumeDao;
	}

	public void setUserDao(UserDAO userDao) {
		this.userDao = userDao;
	}

	public void setUserInfoDao(UserInfoDAO userInfoDao) {
		this.userInfoDao = userInfoDao;
	}

	@Override
	public boolean Login(String username, String password) {
		// TODO Auto-generated method stub
//		System.out.println(username);
		UserInfo tempUser = userInfoDao.findById(username);
		
		if(tempUser!=null)
		{
///			System.out.println(tempUser.getPassword());
			if(tempUser.getPassword().equals(password))
			{
				Log.info("Login:success");
				return true;
			}
		}
		Log.info("Login:failure");
		return false;
	}

	@Override
	public String Register(String json) {
		
			JSONObject newUser = JSONObject.fromObject(json);
			// userInfo = new UserInfo();
			if (newUser.containsKey("username")) {
				UserInfo tempUser = userInfoDao.findById(newUser
						.getString("username"));
				if (tempUser != null)
					return "�û����Ѵ��ڣ�";
				if (newUser.containsKey("password")) {
					if (newUser.containsKey("email")) {
						userInfo = new UserInfo();
						userInfo.setUsername(newUser.getString("username"));
						userInfo.setPassword(newUser.getString("password"));
						userInfo.setEmail(newUser.getString("email"));
						if (newUser.containsKey("phone"))
							userInfo.setPhone(newUser.getString("phone"));
						if (newUser.containsKey("gender"))
							userInfo.setGender(newUser.getString("gender"));
						if (newUser.containsKey("name"))
							userInfo.setName(newUser.getString("name"));
						if (newUser.containsKey("class"))
							userInfo.setClass_(newUser.getString("class"));
						if (newUser.containsKey("grade"))
							userInfo.setGrade(newUser.getString("grade"));
						if (newUser.containsKey("sei"))
							userInfo.setSei(newUser.getString("sei"));
						if (newUser.containsKey("mylab"))
							userInfo.setMylab(newUser.getString("mylab"));
						userInfoDao.save(userInfo);
						UserInfo id = userInfoDao.findById(userInfo
								.getUsername());
						if (id != null
								&& id.getPassword().equals(
										newUser.getString("password"))) {
							Log.info("Register:"+newUser.getString("username")+" ע��ɹ���");
							return "success";
						} else {
							Log.info("Register:"+newUser.getString("username")+" ע��ʧ�ܣ�");
							return "ע��ʧ�ܣ�";
						}

					} else {
						Log.info("Register:"+newUser.getString("username")+" ����Ϊ��!");
						return "����Ϊ��!";
					}
				} else {
					Log.info("Register:"+newUser.getString("username")+" ����Ϊ�գ�");
					return "����Ϊ�գ�";
				}
			} else
			{
				Log.info("Register:"+newUser.getString("username")+" �û���Ϊ�գ�");
				return "�û���Ϊ�գ�";
			}

		
		//return json;

	}
	@Override
	public String EditUser(String json) {
		// TODO Auto-generated method stub
		try {
			JSONObject newUser = JSONObject.fromObject(json);
			if (newUser.containsKey("username")) {
				userInfo = userInfoDao.findById(newUser.getString("username"));
				if (userInfo != null) {

//					userInfo = new UserInfo();
					 userInfo.setUsername(newUser.getString("username"));
//					 userInfo.setPassword(newUser.getString("password"));
//					 userInfo.setPassword(newUser.getString("eamil"));
					if (newUser.containsKey("email")) {
						userInfo.setEmail(newUser.getString("email"));
					}
					if (newUser.containsKey("phone"))
						userInfo.setPhone(newUser.getString("phone"));
					if (newUser.containsKey("gender"))
						userInfo.setGender(newUser.getString("gender"));
					if (newUser.containsKey("name"))
						userInfo.setName(newUser.getString("name"));
					if (newUser.containsKey("class_"))
						userInfo.setClass_(newUser.getString("class_"));
					if (newUser.containsKey("grade"))
						userInfo.setGrade(newUser.getString("grade"));
					if (newUser.containsKey("sei"))
						userInfo.setSei(newUser.getString("sei"));
					if (newUser.containsKey("mylab"))
						userInfo.setMylab(newUser.getString("mylab"));
					userInfoDao.update(userInfo);
					UserInfo id = userInfoDao.findById(userInfo.getUsername());
					if (id != null) {
						Log.info("EditUser:success");
						return "success";
					} else {
						Log.info("EditUser:�༭ʧ��");
						return "�༭ʧ�ܣ�";
					}

				} else
				{
					Log.info("EditUser:�û��������ڣ�");
					return "�û��������ڣ�";
				}
			}

		} catch (JSONException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		Log.info("EditUser:�û���Ϊ�գ�");
		return "�û���Ϊ�գ�";
	}

	@Override
	public String EditResume(String json) {
		// TODO Auto-generated method stub
		JSONObject tempResume = JSONObject.fromObject(json);
		if(tempResume.containsKey("username"))
		{
			userInfo = userInfoDao.findById(tempResume.getString("username"));
			if(userInfo==null)
			{
				Log.info("EditResume:�û���������");
				return "�û���������";
			}
			resume = new Resume(tempResume.getString("username"));
			if(tempResume.containsKey("technology"))
			{
				resume.setTechnology(tempResume.getString("technology"));
			}
			if(tempResume.containsKey("gpa"))
			{
				resume.setGpa(tempResume.getString("gpa"));
			}
			if(tempResume.containsKey("honor"))
			{
				resume.setPersonalHonor(tempResume.getString("honor"));
			}
			if(tempResume.containsKey("slogan"))
			{
				resume.setSlogan(tempResume.getString("slogan"));
			}
			resumeDao.attachDirty(resume);
			if(resumeDao.findById(resume.getUsername())!=null)
				return "success";
			else
			{
				Log.info("EditResume:�༭ʧ��");
				return "�༭ʧ��";
			}
		}
		else
		{
			Log.info("EditResume:�û���Ϊ��");
			return "�û���Ϊ��";
		}
//		return null;
	}
	

	@Override
	public String EditMyProject(String json) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String FindMyPsd(String json) {
		// TODO Auto-generated method stub
		JSONObject tempUser = JSONObject.fromObject(json);
		if(tempUser.containsKey("username"))
		{
			userInfo = userInfoDao.findById(tempUser.getString("username"));
			if(userInfo!=null)
			{
				if(tempUser.containsKey("email"))
				{
					if(userInfo.getEmail().equals(tempUser.getString("email")))
					{
						if(tempUser.containsKey("password"))
						{
							userInfo.setPassword(tempUser.getString("password"));
							userInfoDao.attachDirty(userInfo);
							userInfo = userInfoDao.findById(tempUser.getString("username"));
							if(userInfo.getPassword().equals(tempUser.getString("password")))
							{
								Log.info("FindMyPsd:�޸ĳɹ�");
								return "success";
							}
								
							else
							{
								Log.info("FindMyPsd:�޸�ʧ��");
								return "�޸�ʧ��";
							}
							
						}
						else
						{
							Log.info("FindMyPsd:����Ϊ��");
							return "����Ϊ��";
						}
					}
					else
					{
						Log.info("FindMyPsd:���䲻����");
						return "���䲻����";
					}
				}
				else
				{
					Log.info("FindMyPsd:����Ϊ��");
					return "����Ϊ��";
				}
			}
			else
			{
				Log.info("FindMyPsd:�û���������");
				return "�û���������";
			}
		}
		Log.info("FindMyPsd:�û���Ϊ��");
		return "�û���Ϊ��";
	}

	@Override
	public String getResume(String username) {
		// TODO Auto-generated method stub
		Map <String,String> map =new HashMap<String,String>();
		if(username!=null)
		{
			userInfo = userInfoDao.findById(username);
			if(userInfo!=null)
			{
				resume = resumeDao.findById(username);
				if(resume!=null)
				{
					map.put("flag", "true");
					map.put("msg", "success");
					map.put("username", username);
					if(resume.getGpa()!=null)
						map.put("gpa",resume.getGpa());
					if(resume.getPersonalHonor()!=null)
						map.put("honor", resume.getPersonalHonor());
					if(resume.getTechnology()!=null)
						map.put("technology", resume.getTechnology());
					if(resume.getSlogan()!=null)
						map.put("slogan",resume.getSlogan());
				}else
				{
					map.put("flag", "true");
					map.put("msg", "empty");
				}
			}
			else{
				map.put("flag", "false");
				map.put("msg", "�û���Ϊ��");
			}
		}
		Log.info("getResume:"+map.get("msg"));
		return JSONObject.fromObject(map).toString();
	}

	@Override
	public String getUserInfo(String username) {
		// TODO Auto-generated method stub
		Map <String,String> map=new HashMap<String,String>();
		if(username!=null)
		{
			userInfo = userInfoDao.findById(username);
			if(userInfo!=null)
			{
				map.put("flag", "true");
				map.put("msg", "��ȡ�ɹ�");
				map.put("username", username);
				if(userInfo.getEmail()!=null)
					map.put("email", userInfo.getEmail());
				if(userInfo.getName()!=null)
				{
					map.put("name", userInfo.getName());
				}
				if(userInfo.getPhone()!=null)
					map.put("phone",userInfo.getPhone());
				if(userInfo.getClass_()!=null)
					map.put("class_", userInfo.getClass_());
				if(userInfo.getGrade()!=null)
					map.put("grade", userInfo.getGrade());
				
			}
			else
			{
				map.put("flag", "false");
				map.put("msg", "�û���������");
			}
		}
		else{
			map.put("flag", "false");
			map.put("msg", "�û���Ϊ��");
		}
		Log.info("getUserInfo:"+map.get("msg"));
		return JSONObject.fromObject(map).toString();
	}

	@Override
	public String ApplyProject(String username, String title) {
		// TODO Auto-generated method stub
		return null;
	}

}
