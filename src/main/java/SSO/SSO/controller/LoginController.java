package SSO.SSO.controller;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import SSO.SSO.entity.User;

@Controller
@RequestMapping(value = "/")
public class LoginController {

	@Autowired
	private StringRedisTemplate template;
	
	@RequestMapping("/getUserTicket")
	@ResponseBody
	String getUser(HttpServletRequest re,String callback) {
		Cookie mycookies[] = re.getCookies();
		if (mycookies != null) {
			for (int i = 0; i < mycookies.length; i++) {
				if ("ticket".equalsIgnoreCase(mycookies[i].getName())) {
					if(!StringUtils.isEmpty(template.opsForValue().get(mycookies[i].getValue()))){
						return callback+"({result:'"+mycookies[i].getValue()+"'})";
					}
					
				}
			}
		}
		return callback+"({result:''})";
	}

	@RequestMapping("/")
	String getLoginPage() {
		return "login";
	}

	@RequestMapping(value = "/validate", method = RequestMethod.POST)
	@ResponseBody
	String Login(User user, HttpServletRequest re, HttpServletResponse resp) {
		if (user.getUsername().equals("liyu") && user.getPwd().equals("1234abcd")) {
			UUID uuid = UUID.randomUUID();
			template.opsForValue().set(uuid.toString(), user.getUsername(),60*30,TimeUnit.SECONDS);
			Cookie cookie = new Cookie("ticket",uuid.toString());
			resp.addCookie(cookie);
			return "true";
		}
		return "false";
	}
}
