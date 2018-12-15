package errors_handling;

import javax.servlet.http.HttpServletRequest;

public class ErrorsHandling {
    public Integer throwStatusCode(HttpServletRequest request) {
        return (Integer) request
                .getAttribute("javax.servlet.error.status_code");
    }

    public String throwRequestUri(HttpServletRequest request) {
        return (String) request
                .getAttribute("javax.servlet.error.request_uri");
    }

    public String throwServletName(HttpServletRequest request) {
        return (String) request
                .getAttribute("javax.servlet.error.servlet_name");
    }

    public void throwThrowable(HttpServletRequest request) {
        Throwable throwable = (Throwable) request
                .getAttribute("javax.servlet.error.exception");
    }
}