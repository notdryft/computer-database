package com.formation.projet.webapp.controllers;

import com.formation.projet.business.beans.ComputerAndCompanies;
import com.formation.projet.business.forms.ComputerForm;
import com.formation.projet.business.services.ComputerService;
import com.formation.projet.core.helpers.LongHelper;
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
 * Date: 28/05/13
 * Time: 17:08
 */
@WebServlet("/computers/edit")
public class EditController extends HttpServlet {

    private Routes routes;

    private ComputerService computerService;

    private ApplicationContext context;

    @Override
    public void init() throws ServletException {
        if (context == null) {
            context = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
        }

        routes = Routes.getInstance();
        computerService = context.getBean(ComputerService.class);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        long id = LongHelper.parseId(request.getParameter("id"));

        ComputerAndCompanies computerAndCompanies = computerService.findWithAllCompanies(id);
        if (computerAndCompanies == null) {
            request.getSession().setAttribute("error", "Computer not found");

            response.sendRedirect(routes.getBack());
        } else {
            request.setAttribute("form", new ComputerForm(computerAndCompanies.getComputer()));
            request.setAttribute("companies", computerAndCompanies.getCompanies());

            request.getRequestDispatcher(routes.getEdit()).include(request, response);
        }
    }
}
