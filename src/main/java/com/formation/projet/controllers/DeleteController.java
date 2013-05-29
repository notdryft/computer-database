package com.formation.projet.controllers;

import com.formation.projet.business.services.ComputerService;
import com.formation.projet.business.services.ComputerServiceImpl;
import com.formation.projet.helpers.LongHelper;

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

    private ComputerService computerService;

    @Override
    public void init() throws ServletException {
        computerService = ComputerServiceImpl.instance;
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        long id = LongHelper.parseId(request.getParameter("id"));

        if (computerService.seekAndDestroy(id)) {
            request.getSession().setAttribute("success", "Computer has been deleted");

            response.sendRedirect("../computers");
        } else {
            request.getSession().setAttribute("error", "Computer not found");

            response.sendRedirect("../computers");
        }
    }
}
