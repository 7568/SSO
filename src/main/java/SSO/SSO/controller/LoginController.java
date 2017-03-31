package SSO.SSO.controller;

import java.util.UUID;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import SSO.SSO.RedisAPI;
import SSO.SSO.entity.User;
import redis.clients.jedis.Jedis;

@Controller
@RequestMapping(value="/userInfo")
public class LoginController {
	
	@RequestMapping("/getUserTicket")
	@ResponseBody
	String getUser(HttpServletRequest re) {
		//Object value = re.getSession().getAttribute("ticket");
		Jedis jedis = new Jedis("localhost", 6379);
		String value = jedis.get("username");
		return value;
	}
	
	@RequestMapping("/login")
	String getLoginPage() {
		return "login";
	}
	
	@RequestMapping(value="/validate", method=RequestMethod.POST)
	@ResponseBody
	String Login(User user,HttpServletRequest re,HttpServletResponse resp) {
		if(user.getUsername().equals("liyu") && user.getPwd().equals("1234abcd")){
			UUID uuid = UUID.randomUUID();
			RedisAPI.set("ticket", uuid.toString());
			Cookie cookie = new Cookie("ticket",uuid.toString());
			resp.addCookie(cookie);
			return "true";
		}
		return "false";
	}
}
