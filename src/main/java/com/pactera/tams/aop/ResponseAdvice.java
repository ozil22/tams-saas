package com.pactera.tams.aop;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.github.pagehelper.PageInfo;
import com.pactera.tams.jwt.Jwt;

import net.sf.json.JSONArray;

/**
* 
* @Author: mjh
* @Date: 2018-03-06 11:56:31
*/

@Aspect
@Component
public class ResponseAdvice {
	
	private Logger logger = LoggerFactory.getLogger(ResponseAdvice.class);

	@Pointcut("execution(* com.pactera.saas111.*.controller..getPage*(..)) and @annotation(org.springframework.web.bind.annotation.RestController)")
	public void getPageListCut() {
		
		logger.debug("【ResponseAdvice】==={}", "getPageListCut");
		
	}
	
	@Around("getPageListCut()")
	public ModelMap unifyResponse(ProceedingJoinPoint pjp) throws Throwable {
		logger.debug("【ResponseAdvice】==={}", "unifyResponse");
		
		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = attributes.getRequest();  
		String token=request.getHeader("token");
		
		String userId = Jwt.getUserId(token);
		
		logger.debug("【ResponseAdvice】userId==={}", userId);
		
		//TODO:获取用户角色
		
		String uri = request.getRequestURI();
		
		logger.debug("【ResponseAdvice】uri==={}", uri);
		
		
	    Object controllerResult = pjp.proceed();
	    
	    ModelMap result = (ModelMap) controllerResult;
	    
	    PageInfo page = (PageInfo) result.get("list");
	    
	    List list = page.getList();

	    JSONArray jsonArray = JSONArray.fromObject(list);
	    
	   //TODO:获取角色对应uri可以访问的字段
	    
	    logger.debug("【ResponseAdvice】result==={}", result);
	    return result;
	}
}
