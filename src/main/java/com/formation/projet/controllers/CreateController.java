package com.formation.projet.controllers;

import com.formation.projet.business.dao.CompanyDaoImpl;
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
 * Date: 27/05/13
 * Time: 17:53
 */
@WebServlet("/computers/new")
public class CreateController extends HttpServlet {

    private CompanyDaoImpl dao;

    @Override
    public void init() throws ServletException {
        dao = CompanyDaoImpl.instance;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("form", new ComputerForm());
        request.setAttribute("companies", dao.findAll());

        request.getRequestDispatcher("/WEB-INF/pages/new.jsp").include(request, response);
    }
}
