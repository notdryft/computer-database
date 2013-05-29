package com.formation.projet.controllers;

import com.formation.projet.business.beans.ComputersAndCount;
import com.formation.projet.business.services.ComputerService;
import com.formation.projet.business.services.ComputerServiceImpl;
import com.formation.projet.configuration.Configuration;
import com.formation.projet.helpers.IntHelper;
import com.formation.projet.helpers.StringHelper;

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

    private ComputerService computerService;

    @Override
    public void init() throws ServletException {
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
        int page = IntHelper.parsePage(request.getParameter("p"));
        int sortColumn = IntHelper.parseSortColumn(request.getParameter("s"));
        String filter = StringHelper.parseFilter(request.getParameter("f"));

        request.setAttribute("page", page);
        request.setAttribute("sortColumn", sortColumn);
        request.setAttribute("filter", filter);

        int offset = page * Configuration.MAX_ITEMS_PER_PAGE;
        ComputersAndCount computersAndCount =
                computerService.findAllAndCount(filter, sortColumn, offset, Configuration.MAX_ITEMS_PER_PAGE);

        request.setAttribute("offset", offset);

        int total = computersAndCount.getTotal();
        request.setAttribute("total", total);
        request.setAttribute("maxPages", total / Configuration.MAX_ITEMS_PER_PAGE);

        request.setAttribute("computers", computersAndCount.getComputers());

        // Quick and dirty
        purgeSession(request, "success");
        purgeSession(request, "error");

        request.getRequestDispatcher("/WEB-INF/pages/index.jsp").include(request, response);
    }
}
