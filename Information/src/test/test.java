package test;

import Log.Log;
import ServiceImpl.DBServiceImpl;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;
import dom.User;
import dom.UserDAO;
import dom.UserId;
import dom.UserInfo;
import dom.UserInfoDAO;

public class test {

	public static void main(String[] args) {

		EditUser("{\"grade\":\"大四\",\"phone\":\"1111\",\"username\":\"admin\",\"email\":\"111\",\"class_\":\"一班\",\"name\":\"1111\"}");
		//return false;
	}
	public static String register(String json) {
		// TODO Auto-generated method stub
		JSONObject newUser = JSONObject.fromObject(json);
		User user = new User();
		UserId userId = new UserId();
		UserDAO userDao = new UserDAO();
		if (newUser.containsKey("username")) {
			if (newUser.containsKey("password")) {
				userId.setUsername(newUser.getString("username"));
				userId.setPassword(newUser.getString("password"));
				
				User tempUser = userDao.findById(userId);
				if(tempUser!=null)
					return "用户名已存在！";
				user.setId(userId);
				if (newUser.containsKey("gender")) {
					user.setMGender(newUser.getString("gender"));
				}
				if (newUser.containsKey("telnum")) {
					user.setMTelnum(newUser.getString("telnum"));
				}
				if (newUser.containsKey("name")) {
					user.setMName(newUser.getString("name"));
				}
				UserId tempUserId = userDao.save(user);
				if (tempUserId != null
						&& tempUserId.getUsername()
								.equals(userId.getUsername()))
					return "success";
				else {
					return "fail";
				}
			}
			else
				return "password is null";
		}
		return "username is null";
	}
	public static String EditUser(String json) {
		// TODO Auto-generated method stub
		UserInfo userInfo;
		UserInfoDAO userInfoDao = new UserInfoDAO();
		try {
			JSONObject newUser = JSONObject.fromObject(json);
			if (newUser.containsKey("username")) {
				userInfo = userInfoDao.findById(newUser.getString("username"));
				if (userInfo != null) {
					Log.info(userInfo.getUsername());
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
					userInfoDao.attachDirty(userInfo);
					UserInfo id = userInfoDao.findById(userInfo.getUsername());
					if (id != null) {
						Log.info("EditUser:success");
						return "success";
					} else {
						Log.info("EditUser:编辑失败");
						return "编辑失败！";
					}

				} else
				{
					Log.info("EditUser:用户名不存在！");
					return "用户名不存在！";
				}
			}

		} catch (JSONException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		Log.info("EditUser:用户名为空！");
		return "用户名为空！";
	}
}
