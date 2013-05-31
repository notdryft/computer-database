package com.formation.projet.webapp.controllers;

import com.formation.projet.business.services.ComputerService;
import com.formation.projet.business.services.ComputerServiceImpl;
import com.formation.projet.webapp.helpers.LongHelper;
import com.formation.projet.application.properties.Routes;

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
 * Time: 10:54
 */
@WebServlet("/computers/delete")
public class DeleteController extends HttpServlet {

    private Routes routes;

    private ComputerService computerService;

    @Override
    public void init() throws ServletException {
        routes = Routes.getInstance();
        computerService = ComputerServiceImpl.instance;
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        long id = LongHelper.parseId(request.getParameter("id"));

        if (computerService.seekAndDestroy(id)) {
            request.getSession().setAttribute("success", "Computer has been deleted");
        } else {
            request.getSession().setAttribute("error", "Computer not found");
        }

        response.sendRedirect(routes.getBack());
    }
}
