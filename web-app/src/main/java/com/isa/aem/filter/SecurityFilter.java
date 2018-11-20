/*
package com.isa.aem.filter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(urlPatterns = {"/currency-manager", "/global-extremum", "/local-extremum"})
public class SecurityFilter extends HttpFilter {
	@Override
	protected void doFilter(HttpServletRequest request, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
		Object logged = request.getSession().getAttribute("userName");
		if (logged == null) {
			res.setStatus(HttpServletResponse.SC_FORBIDDEN);
			return;
		}

		chain.doFilter(request, res);
	}
}
*/
