package com.formation.projet.webapp.controllers;

import com.formation.projet.business.beans.Computer;
import com.formation.projet.business.forms.ComputerForm;
import com.formation.projet.business.services.CompanyService;
import com.formation.projet.business.services.ComputerService;
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
 * Date: 29/05/13
 * Time: 10:09
 */
@WebServlet("/computers/save")
public class SaveController extends HttpServlet {

    private ComputerService computerService;

    private CompanyService companyService;

    private ApplicationContext context;

    @Override
    public void init() throws ServletException {
        if (context == null) {
            context = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
        }

        computerService = context.getBean(ComputerService.class);
        companyService = context.getBean(CompanyService.class);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ComputerForm form = new ComputerForm();
        form.setName(request.getParameter("name"));
        form.setIntroduced(request.getParameter("introduced"));
        form.setDiscontinued(request.getParameter("discontinued"));
        form.setCompany(request.getParameter("company"));

        if (form.isValid()) {
            Computer computer = form.toComputer();
            computer = computerService.create(computer);

            request.getSession().setAttribute(
                    "success",
                    String.format("Computer %s with has been created", computer.getName()));

            response.sendRedirect("../computers");
        } else {
            request.setAttribute("form", form);
            request.setAttribute("companies", companyService.findAll());

            request.getRequestDispatcher("/WEB-INF/pages/create.jsp").include(request, response);
        }
    }
}
