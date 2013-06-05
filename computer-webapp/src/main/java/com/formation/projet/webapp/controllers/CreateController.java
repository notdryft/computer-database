package com.formation.projet.webapp.controllers;

import com.formation.projet.business.forms.ComputerForm;
import com.formation.projet.business.services.CompanyService;
import com.formation.projet.core.properties.Routes;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: gcorre
 * Date: 27/05/13
 * Time: 17:53
 */
@WebServlet("/computers/create")
public class CreateController extends HttpServlet {

    private Routes routes;

    private CompanyService companyService;

    private ApplicationContext context;

    @Override
    public void init() throws ServletException {
        if (context == null) {
            context = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
        }

        routes = Routes.getInstance();
        companyService = context.getBean(CompanyService.class);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("form", new ComputerForm());
        request.setAttribute("companies", companyService.findAll());

        request.getRequestDispatcher(routes.getCreate()).include(request, response);
    }
}
