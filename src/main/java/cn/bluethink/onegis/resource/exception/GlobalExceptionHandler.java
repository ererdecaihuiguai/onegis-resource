package cn.bluethink.onegis.resource.exception;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import cn.bluethink.onegis.resource.utils.BtResult;

@RestControllerAdvice
public class GlobalExceptionHandler {

	    private static Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	    @ExceptionHandler
	    public BtResult handle(HttpServletRequest request, HttpServletResponse response, Exception ex) {
	    	String path = request.getRequestURI();
	        String method = request.getMethod();
	        String params = "";
	        StringBuffer sb = new StringBuffer();
	        Enumeration<String> parameterNames = request.getParameterNames();
	        while (parameterNames.hasMoreElements()) {
	            String key = parameterNames.nextElement();
	            sb.append(key + "=" + request.getParameter(key) + "&");
	        }
	        if(sb!=null && sb.length()>0){
	            params = sb.substring(0, sb.length()-1);
	        }

	        logger.info("Request URI:{}", path);
	        logger.info("Request Method:{}", method);
	        logger.info("Request Parameters:{}", params);
	        logger.error("Exception Details:", ex);
	        
	        StackTraceElement[]traceElements = ex.getStackTrace();
	        String[] exceptions = new String[traceElements.length];
	        for(int i=0;i<traceElements.length;i++){
	            exceptions[i]=traceElements[i].toString();
	        }
	        return new BtResult(500, ex.getMessage(), exceptions);
	    }

}
