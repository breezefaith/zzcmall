package cn.breezefaith.util.cookie;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

public class CookieUtils {
	public static String getCookie(HttpServletRequest req, String key) {
		Cookie cookie[] = req.getCookies();
		if (cookie != null) {
			for (int i = 0; i < cookie.length; i++) {
				if (cookie[i].getName().equals(key)) {
					return cookie[i].getValue();
				}
			}
		}
		return null;
	}
	
}
