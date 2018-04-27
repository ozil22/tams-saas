package com.pactera.tams.exception;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pactera.tams.common.entity.RestResult;
import com.pactera.tams.common.entity.ResultUtils;


/**
* 系统异常统一处理
* @Author: mjh
* @Date: 2018-03-19 15:58:52
*/
@ControllerAdvice  
public class RestExceptionHandler{  
	
	private static final Logger logger = LoggerFactory.getLogger(RestExceptionHandler.class);
	
	/**
	 * 运行时异常
	 * @param ex
	 * @return
	 */
    @ExceptionHandler(RuntimeException.class)    
    @ResponseBody    
    public <T> RestResult<T> runtimeExceptionHandler(RuntimeException ex) {
    	if(logger.isDebugEnabled()) {
    		logger.error("[RestExceptionHandler] -----> ", ex);
    	}
        return ResultUtils.genErrorResult(ErrorInfo.SERVICE_ERROR);
    }    
  
    /**
     * 空指针异常
     * @param ex
     * @return
     */
    @ExceptionHandler(NullPointerException.class)    
    @ResponseBody    
    public <T> RestResult<T> nullPointerExceptionHandler(NullPointerException ex) {    
    	if(logger.isDebugEnabled()) {
    		logger.error("[RestExceptionHandler] -----> ", ex);
    	}
        return ResultUtils.genErrorResult(ErrorInfo.SERVICE_ERROR);
    }  
    
    /**
     * 非法参数异常
     * @param ex
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)    
    @ResponseBody    
    public <T> RestResult<T> illegalParamsExceptionHandler(MethodArgumentNotValidException ex) {    
    	if(logger.isDebugEnabled()) {
    		logger.error("[RestExceptionHandler] -----> ", ex);
    	}
        return ResultUtils.genErrorResult(ErrorInfo.ILLEGAL_PARAM);
    } 
    
    /**
     * 类型转换异常
     * @param ex
     * @return
     */
    @ExceptionHandler(ClassCastException.class)    
    @ResponseBody    
    public <T> RestResult<T> classCastExceptionHandler(ClassCastException ex) {    
    	if(logger.isDebugEnabled()) {
    		logger.error("[RestExceptionHandler] -----> ", ex);
    	}  
        return ResultUtils.genErrorResult(ErrorInfo.SERVICE_ERROR);
    }    
  
    /**
     * IO异常
     * @param ex
     * @return
     */
    @ExceptionHandler(IOException.class)    
    @ResponseBody    
    public <T> RestResult<T> iOExceptionHandler(IOException ex) {    
    	if(logger.isDebugEnabled()) {
    		logger.error("[RestExceptionHandler] -----> ", ex);
    	}  
        return ResultUtils.genErrorResult(ErrorInfo.SERVICE_ERROR);   
    }    
    
    /**
     * 未知方法异常
     * @param ex
     * @return
     */
    @ExceptionHandler(NoSuchMethodException.class)    
    @ResponseBody    
    public <T> RestResult<T> noSuchMethodExceptionHandler(NoSuchMethodException ex) {    
    	if(logger.isDebugEnabled()) {
    		logger.error("[RestExceptionHandler] -----> ", ex);
    	}  
        return ResultUtils.genErrorResult(ErrorInfo.SERVICE_ERROR);
    }    
  
    /**
     * 数组越界异常
     * @param ex
     * @return
     */
    @ExceptionHandler(IndexOutOfBoundsException.class)    
    @ResponseBody    
    public <T> RestResult<T> indexOutOfBoundsExceptionHandler(IndexOutOfBoundsException ex) {    
    	if(logger.isDebugEnabled()) {
    		logger.error("[RestExceptionHandler] -----> ", ex);
    	}  
        return ResultUtils.genErrorResult(ErrorInfo.SERVICE_ERROR); 
    }  
    
    /**
     * 请求消息不可读-->缺少参数
     * @param ex
     * @return
     */
    @ExceptionHandler({HttpMessageNotReadableException.class})  
    @ResponseBody  
    public <T> RestResult<T> requestNotReadable(HttpMessageNotReadableException ex){  
    	if(logger.isDebugEnabled()) {
    		logger.error("[RestExceptionHandler] -----> ", ex);
    	} 
        return ResultUtils.genErrorResult(ErrorInfo.MISSING_PARAM); 
    }  
    
    /**
     * 类型不匹配
     * @param ex
     * @return
     */
    @ExceptionHandler({TypeMismatchException.class})  
    @ResponseBody  
    public <T> RestResult<T> requestTypeMismatch(TypeMismatchException ex){  
    	if(logger.isDebugEnabled()) {
    		logger.error("[RestExceptionHandler] -----> ", ex);
    	} 
        return ResultUtils.genErrorResult(ErrorInfo.TYPE_MISMATCH);  
    }  
    
    /**
     * 缺少参数
     * @param ex
     * @return
     */
    @ExceptionHandler({MissingServletRequestParameterException.class})  
    @ResponseBody  
    public <T> RestResult<T> requestMissingServletRequest(MissingServletRequestParameterException ex){  
    	if(logger.isDebugEnabled()) {
    		logger.error("[RestExceptionHandler] -----> ", ex);
    	}
        return ResultUtils.genErrorResult(ErrorInfo.MISSING_PARAM);
    }  
    
    /**
     * 405错误
     * @return
     */
    @ExceptionHandler({HttpRequestMethodNotSupportedException.class})  
    @ResponseBody  
    public <T> RestResult<T> request405(){
    	if(logger.isDebugEnabled()) {
    		logger.error("[RestExceptionHandler] -----> ", 405);
    	}  
        return ResultUtils.genErrorResult(ErrorInfo.METHOD_NOT_SUPPORT);
    }  
    
    /**
     * 406错误
     * @return
     */
    @ExceptionHandler({HttpMediaTypeNotAcceptableException.class})  
    @ResponseBody  
    public <T> RestResult<T> request406(){  
    	if(logger.isDebugEnabled()) {
    		logger.error("[RestExceptionHandler] -----> ", 406);
    	}  
        return ResultUtils.genErrorResult(ErrorInfo.SERVICE_ERROR);
    }  
    
    /**
     * 500错误
     * @param ex
     * @return
     */
    @ExceptionHandler({ConversionNotSupportedException.class,HttpMessageNotWritableException.class})  
    @ResponseBody  
    public <T> RestResult<T> server500(RuntimeException ex){  
    	if(logger.isDebugEnabled()) {
    		logger.error("[RestExceptionHandler] -----> ", ex);
    	}  
        return ResultUtils.genErrorResult(ErrorInfo.SERVICE_ERROR); 
    }  
    
    
}  