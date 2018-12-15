package com.isa.aem.servlets;

import com.isa.aem.freemarker.TemplateName;
import com.isa.aem.freemarker.TemplateProvider;
import errors_handling.ErrorsHandling;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/AppExceptionHandler")

public class ServletExceptionHandling extends HttpServlet {
    protected static final Integer SERVER_ERROR = 500;
    public String message = "API NBP is Offline. Pleas try later.";
    private ErrorsHandling errorsHandling = new ErrorsHandling();
    @Inject
    public TemplateProvider templateProvider;

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        processError(request, response);
    }

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
        processError(request, response);
    }

    private void processError(HttpServletRequest request,
                              HttpServletResponse response) throws IOException {

        errorsHandling.throwThrowable(request);
        Integer statusCode = errorsHandling.throwStatusCode(request);
        String servletName = errorsHandling.throwServletName(request);

        if (servletName == null) {
            servletName = "Unknown";
            message = "Unknown address";

        }

        String requestUri = errorsHandling.throwRequestUri(request);

        if (requestUri == null) {
            requestUri = "Unknown";
            message = "Unknown address";
        }

        if (statusCode != SERVER_ERROR) {
            message = "Problem with Server. Pleas try later";

        }
            Template template = templateProvider
                    .getTemplate(getServletContext(), TemplateName.ERROR.getName());

            Map<String, Object> model = new HashMap<>();
            model.put("errors_message", message);

            try {
                template.process(model, response.getWriter());
            } catch (TemplateException e) {
                e.printStackTrace();
            }
    }
}