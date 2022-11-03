package mk.ukim.finki.wp.lab_1.web.filter;

import mk.ukim.finki.wp.lab_1.model.Course;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter
public class CheckFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String courseId = request.getParameter("courseId");

        String path = request.getServletPath();

        if ((courseId == null && "/addStudent".equals(path)) || "/createStudent".equals(path) || ("/StudentEnrollmentSummary".equals(path) && courseId == null)) {
            response.sendRedirect("/listCourses");
        } else {
            filterChain.doFilter(servletRequest,servletResponse);
        }
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
