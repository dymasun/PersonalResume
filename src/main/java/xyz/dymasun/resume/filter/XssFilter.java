package xyz.dymasun.resume.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class XssFilter implements Filter {
	FilterConfig fc = null;

	public void destroy() {
		fc = null;
	}

	public void doFilter(ServletRequest req, ServletResponse resp,
						 FilterChain fc) throws IOException, ServletException {
		fc.doFilter(new XssHttpServletRequestWrapper((HttpServletRequest)req), resp);
	}

	public void init(FilterConfig fc) throws ServletException {
		this.fc = fc;
	}

}
