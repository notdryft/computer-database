package com.formation.projet.webapp.controllers;

import com.formation.projet.business.beans.Computer;
import com.formation.projet.business.beans.ComputerAndCompanies;
import com.formation.projet.business.beans.ComputersAndCount;
import com.formation.projet.business.beans.PageState;
import com.formation.projet.business.forms.ComputerForm;
import com.formation.projet.business.services.CompanyService;
import com.formation.projet.business.services.ComputerService;
import com.formation.projet.core.helpers.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created with IntelliJ IDEA.
 * User: gcorre
 * Date: 06/06/13
 * Time: 10:13
 */
@Controller
@RequestMapping("/computers")
public class ComputersController {

    @Autowired
    private ComputerService computerService;

    @Autowired
    private CompanyService companyService;

    private void purgeSession(HttpServletRequest request, String name) {
        String attribute = (String) request.getSession().getAttribute(name);
        if (attribute != null) {
            request.getSession().removeAttribute(name);
            request.setAttribute(name, attribute);
        }
    }

    @RequestMapping(method = RequestMethod.GET)
    public String list(
            @RequestParam(required = false) String p,
            @RequestParam(required = false) String s,
            @RequestParam(required = false) String f,
            HttpServletRequest request, ModelMap model) {
        PageState pageState = new PageState();
        pageState.setPage(PageHelper.parsePage(p));
        pageState.setSortColumn(PageHelper.parseSortColumn(s));
        pageState.setFilter(PageHelper.parseFilter(f));

        ComputersAndCount computersAndCount = computerService.findAllAndCount(pageState);

        pageState.setTotal(computersAndCount.getTotal());
        model.addAttribute("computers", computersAndCount.getComputers());

        // Now that we computed everything
        model.addAttribute("state", pageState);

        // Quick and dirty
        purgeSession(request, "success");
        purgeSession(request, "error");

        return "/index";
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String create(ModelMap model) {
        model.addAttribute("form", new ComputerForm());
        model.addAttribute("companies", companyService.findAll());

        return "/create";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String introduced,
            @RequestParam(required = false) String discontinued,
            @RequestParam(required = false) String company,
            HttpSession session, ModelMap model) {
        ComputerForm form = new ComputerForm();
        form.setName(name);
        form.setIntroduced(introduced);
        form.setDiscontinued(discontinued);
        form.setCompany(company);

        if (!form.isValid()) {
            model.addAttribute("form", form);
            model.addAttribute("companies", companyService.findAll());

            return "/create";
        } else {
            Computer computer = form.toComputer();
            computer = computerService.create(computer);

            session.setAttribute(
                    "success",
                    String.format("Computer %s with has been created", computer.getName()));

            return "redirect:/computers";
        }
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String edit(@PathVariable Long id, HttpSession session, ModelMap model) {
        ComputerAndCompanies computerAndCompanies = computerService.findWithAllCompanies(id);
        if (computerAndCompanies == null) {
            session.setAttribute("error", "Computer not found");

            return "redirect:/computers";
        } else {
            model.addAttribute("form", new ComputerForm(computerAndCompanies.getComputer()));
            model.addAttribute("companies", computerAndCompanies.getCompanies());

            return "/edit";
        }
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    public String update(
            @PathVariable Long id,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String introduced,
            @RequestParam(required = false) String discontinued,
            @RequestParam(required = false) String company,
            HttpSession session, ModelMap model) {
        ComputerForm form = new ComputerForm();
        form.setId(id);
        form.setName(name);
        form.setIntroduced(introduced);
        form.setDiscontinued(discontinued);
        form.setCompany(company);

        if (!form.isValid()) {
            model.addAttribute("form", form);
            model.addAttribute("companies", companyService.findAll());

            return "/edit";
        } else {
            Computer computer = form.toComputer();
            computer = computerService.update(computer);

            session.setAttribute("success", String.format("Computer %s has been updated", computer.getId()));

            return "redirect:/computers";
        }
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    public String delete(@PathVariable Long id, HttpSession session) {
        if (computerService.seekAndDestroy(id)) {
            session.setAttribute("success", "Computer has been deleted");
        } else {
            session.setAttribute("error", "Computer not found");
        }

        return "redirect:/computers";
    }
}
