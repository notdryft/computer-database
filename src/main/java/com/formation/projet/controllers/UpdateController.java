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
 * Time: 17:09
 */
@WebServlet("/computers/update")
public class UpdateController extends HttpServlet {

    private ComputerDao computerDao;

    private CompanyDao companyDao;

    @Override
    public void init() throws ServletException {
        computerDao = ComputerDaoImpl.instance;
        companyDao = CompanyDaoImpl.instance;
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ComputerForm form = new ComputerForm();
        form.setId(LongHelper.parseId(request.getParameter("id")));
        form.setName(request.getParameter("name"));
        form.setIntroduced(request.getParameter("introduced"));
        form.setDiscontinued(request.getParameter("discontinued"));
        form.setCompany(request.getParameter("company"));

        if (form.isValid()) {
            Computer computer = form.toComputer();
            computerDao.update(computer);

            request.getSession().setAttribute("success", "Computer has been updated");

            response.sendRedirect("../computers");
        } else {
            request.setAttribute("form", form);
            request.setAttribute("companies", companyDao.findAll());

            request.getRequestDispatcher("/WEB-INF/pages/edit.jsp").include(request, response);
        }
    }
}
