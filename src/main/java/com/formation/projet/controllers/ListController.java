package com.formation.projet.controllers;

import com.formation.projet.business.beans.ComputersAndCount;
import com.formation.projet.business.beans.PageState;
import com.formation.projet.business.services.ComputerService;
import com.formation.projet.business.services.ComputerServiceImpl;
import com.formation.projet.helpers.PageHelper;
import com.formation.projet.properties.Routes;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: gcorre
 * Date: 24/05/13
 * Time: 12:27
 */
@WebServlet("/computers")
public class ListController extends HttpServlet {

    private Routes routes;

    private ComputerService computerService;

    @Override
    public void init() throws ServletException {
        routes = Routes.getInstance();
        computerService = ComputerServiceImpl.instance;
    }

    private void purgeSession(HttpServletRequest request, String name) {
        String attribute = (String) request.getSession().getAttribute(name);
        if (attribute != null) {
            request.getSession().removeAttribute(name);
            request.setAttribute(name, attribute);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PageState pageState = new PageState();
        pageState.setPage(PageHelper.parsePage(request.getParameter("p")));
        pageState.setSortColumn(PageHelper.parseSortColumn(request.getParameter("s")));
        pageState.setFilter(PageHelper.parseFilter(request.getParameter("f")));

        ComputersAndCount computersAndCount = computerService.findAllAndCount(pageState);

        pageState.setTotal(computersAndCount.getTotal());
        request.setAttribute("computers", computersAndCount.getComputers());

        // Now that we computed everything
        request.setAttribute("state", pageState);

        // Quick and dirty
        purgeSession(request, "success");
        purgeSession(request, "error");

        request.getRequestDispatcher(routes.getIndex()).include(request, response);
    }
}
