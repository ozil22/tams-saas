package com.pactera.tams.filter;

import com.pactera.tams.jwt.Jwt2;
import com.pactera.tams.jwt.TokenState;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;


/**
* token校验过滤器
* @Author: mjh
* @Date: 2018-03-19 15:59:20
*/
public class CheckTokenFilter2  implements Filter {
	private Logger logger = LoggerFactory.getLogger(CheckTokenFilter2.class);
	
	/**
	 * 不走过滤，尾部匹配
	 */
	private static final Map<String, String> NOT_FILTER_URLS_ENDS_WITH = new HashMap<String, String>(16);
	
	/**
	 * 不走过滤，包含匹配
	 */
	private static final Map<String, String> NOT_FILTER_URLS_CONTAINS = new HashMap<String, String>(16);
	
	static {
		NOT_FILTER_URLS_ENDS_WITH.put("/", "根路径");
		NOT_FILTER_URLS_ENDS_WITH.put("/login", "登录");
		NOT_FILTER_URLS_ENDS_WITH.put("/register", "注册");
		NOT_FILTER_URLS_ENDS_WITH.put("/comfirmLogin", "");
		NOT_FILTER_URLS_ENDS_WITH.put("/forgetpwd", "忘记密码");
		NOT_FILTER_URLS_ENDS_WITH.put("/initSMSToken", "初始化短信token");
		NOT_FILTER_URLS_ENDS_WITH.put("sendSMS", "发送短信");
		NOT_FILTER_URLS_ENDS_WITH.put(".html", "html页面");
		
		NOT_FILTER_URLS_CONTAINS.put("attachment", "附件");
		NOT_FILTER_URLS_CONTAINS.put("swagger", "在线api");
		NOT_FILTER_URLS_CONTAINS.put("api-", "在线api"); 
		NOT_FILTER_URLS_CONTAINS.put("static", "静态资源");
		NOT_FILTER_URLS_CONTAINS.put("upload", "上传");
		NOT_FILTER_URLS_CONTAINS.put("/solr/", "solr");
	}
	
	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain ) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
 		String uri = request.getRequestURI();
 		
 		//尾部匹配，放行
		for (Map.Entry<String, String> entry : NOT_FILTER_URLS_ENDS_WITH.entrySet()) {
			 String key = entry.getKey();
			 if(uri.endsWith(key)){
				chain.doFilter(request, response);
				return;
			 }
		}
 		//包含匹配放行
		for (Map.Entry<String, String> entry : NOT_FILTER_URLS_CONTAINS.entrySet()) {
			 String key = entry.getKey();
			 if(uri.contains(key)){
				chain.doFilter(request, response);
				return;
			 }
		}
		
		//其他API接口一律校验token
		logger.debug("开始校验token");
		//从请求头中获取token
		String token=request.getHeader("token");
		TokenState state = Jwt2.validateToken(token);

		switch (state) {
		
			case VALID:
				
				break;
				
			case EXPIRED:
				
				break;
	
			case INVALID:
				
				break;
				
			default:
				
				break;
		}  
		
	}
	
	
	public void output(String jsonStr,HttpServletResponse response) throws IOException{
		response.setContentType("text/html;charset=UTF-8;");
		PrintWriter out = response.getWriter();
		out.write(jsonStr);
		out.flush();
		out.close();
		
	}
	
	@Override
	public void init(FilterConfig arg0) throws ServletException {
		System.out.println("token过滤器初始化了");
	}

	@Override
	public void destroy() {
		
	}

}
