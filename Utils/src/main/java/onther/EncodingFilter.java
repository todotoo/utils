package onther;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class EncodingFilter implements Filter {
	protected final Log logger = LogFactory.getLog(this.getClass());

	private String encoding = "UTF-8";

	public void init(FilterConfig config) throws ServletException {
		encoding = config.getInitParameter("encoding");
	} 
  
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		httpRequest.setCharacterEncoding(encoding);
		chain.doFilter(httpRequest, httpResponse);
	}

	public void destroy() {
		encoding = null;
	}
}