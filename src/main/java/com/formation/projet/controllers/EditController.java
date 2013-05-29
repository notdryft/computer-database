package com.formation.projet.controllers;

import com.formation.projet.business.beans.Computer;
import com.formation.projet.business.dao.CompanyDao;
import com.formation.projet.business.dao.CompanyDaoImpl;
import com.formation.projet.business.dao.ComputerDao;
import com.formation.projet.business.dao.ComputerDaoImpl;
import com.formation.projet.business.forms.ComputerForm;
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
 * Date: 28/05/13
 * Time: 17:08
 */
@WebServlet("/computers/edit")
public class EditController extends HttpServlet {

    private ComputerDao computerDao;

    private CompanyDao companyDao;

    @Override
    public void init() throws ServletException {
        computerDao = ComputerDaoImpl.instance;
        companyDao = CompanyDaoImpl.instance;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long id = LongHelper.parseId(request.getParameter("id"));

        Computer computer = computerDao.find(id);
        if (computer == null) {
            request.getSession().setAttribute("error", "Computer not found");

            response.sendRedirect("../computers");
        } else {
            request.setAttribute("form", new ComputerForm(computer));
            request.setAttribute("companies", companyDao.findAll());

            request.getRequestDispatcher("/WEB-INF/pages/edit.jsp").include(request, response);
        }
    }
}
