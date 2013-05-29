package com.formation.projet.controllers;

import com.formation.projet.business.beans.Computer;
import com.formation.projet.business.dao.CompanyDao;
import com.formation.projet.business.dao.CompanyDaoImpl;
import com.formation.projet.business.dao.ComputerDao;
import com.formation.projet.business.dao.ComputerDaoImpl;
import com.formation.projet.business.forms.ComputerForm;

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

    private ComputerDao computerDao;

    private CompanyDao companyDao;

    @Override
    public void init() throws ServletException {
        computerDao = ComputerDaoImpl.instance;
        companyDao = CompanyDaoImpl.instance;
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
            computerDao.create(computer);

            request.getSession().setAttribute("success", "Computer " + form.getName().getValue() + " has been created");

            response.sendRedirect("../computers");
        } else {
            request.setAttribute("form", form);
            request.setAttribute("companies", companyDao.findAll());

            request.getRequestDispatcher("/WEB-INF/pages/new.jsp").include(request, response);
        }
    }
}
