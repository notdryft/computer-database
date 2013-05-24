package com.formation.projet.controllers;

import com.formation.projet.business.dao.ComputerDao;
import com.formation.projet.business.dao.ComputerDaoImpl;

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
@WebServlet("/")
public class IndexController extends HttpServlet {

    private ComputerDao dao;

    @Override
    public void init() throws ServletException {
        dao = ComputerDaoImpl.instance;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("computers", dao.findAll());

        request.getRequestDispatcher("/WEB-INF/pages/index.jsp").include(request, response);
    }
}
