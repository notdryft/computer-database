package com.formation.projet.webapp.controllers;

import com.formation.projet.business.beans.*;
import com.formation.projet.business.forms.ComputerForm;
import com.formation.projet.business.forms.ComputerValidator;
import com.formation.projet.business.services.CompanyService;
import com.formation.projet.business.services.ComputerService;
import com.formation.projet.core.helpers.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

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
    private ComputerValidator computerValidator;

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

    @InitBinder
    public void commeTuVeux(WebDataBinder binder) {
        binder.registerCustomEditor(Company.class, new CompanyConverter(companyService));
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String create(ModelMap model) {
        model.addAttribute("computer", new Computer());
        model.addAttribute("companies", companyService.findAll());

        return "/create";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(
            @ModelAttribute Computer computer,
            BindingResult result, SessionStatus status,
            HttpSession session, ModelMap model) {
        computerValidator.validate(computer, result);

        if (result.hasErrors()) {
            model.addAttribute("computer", computer);
            model.addAttribute("companies", companyService.findAll());
            model.addAttribute("result", result);

            return "/create";
        } else {
            computer = computerService.create(computer);

            status.setComplete();

            session.setAttribute(
                    "success",
                    "Computer named " + computer.getName() + " has been created");

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

            session.setAttribute("success", "Computer with id " + computer.getId() + " has been updated");

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
