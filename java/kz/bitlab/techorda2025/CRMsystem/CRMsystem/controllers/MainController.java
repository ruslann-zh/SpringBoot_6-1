package kz.bitlab.techorda2025.CRMsystem.CRMsystem.controllers;

import kz.bitlab.techorda2025.CRMsystem.CRMsystem.repositories.ApplicationRequest;
import kz.bitlab.techorda2025.CRMsystem.CRMsystem.repositories.CourseRepo;
import kz.bitlab.techorda2025.CRMsystem.CRMsystem.repositories.Courses;
import kz.bitlab.techorda2025.CRMsystem.CRMsystem.repositories.ReqRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {

    @Autowired
    ReqRepo reqRepo;
    @Autowired
    CourseRepo courseRepo;

    @GetMapping(value = "/")
    public String getHome(Model model) {
        model.addAttribute("requests", reqRepo.findAll());
        return "home";
    }

    @GetMapping(value = "/falseReq")
    public String getFalseReq(Model model) {
        model.addAttribute("requests", reqRepo.findAll());
        return "falseReq";
    }

    @GetMapping(value = "/trueReq")
    public String getTrueReq(Model model) {
        model.addAttribute("requests", reqRepo.findAll());
        return "trueReq";
    }

    @GetMapping(value = "/addReq")
    public String getAddReq(Model model) {
        model.addAttribute("courses", courseRepo.findAll());
        return "addReq";
    }
    @PostMapping(value = "/addReq")
    public String addReq(@RequestParam(name = "username") String username,
                         @RequestParam(name = "course") Long courseId,
                         @RequestParam(name = "phone") String phone,
                         @RequestParam(name = "comment") String comment) {
        Courses courseSelected = courseRepo.findById(courseId).orElse(null);
        ApplicationRequest req = ApplicationRequest.builder()
                .userName(username)
                .course(courseSelected)
                .phone(phone)
                .commentary(comment)
                .handled(false)
                .build();

        reqRepo.save(req);
        return "redirect:/";
    }

    @GetMapping(value = "/details/{id}")
    public String getDetails(@PathVariable("id") Long id, Model model) {
        model.addAttribute("req", reqRepo.findById(id).orElse(null));
        return "details";
    }

    @PostMapping(value = "/details")
    public String Details(@RequestParam("id") Long id,
                          @RequestParam("action") String action) {
        String redirect = "redirect:/";
        ApplicationRequest req = reqRepo.findById(id).orElse(null);

        if (action.equals("handle")) {
            req.setHandled(true);
            reqRepo.save(req);
            redirect = "redirect:/details/" + id;
        } else {
            reqRepo.deleteById(id);
        }
        return redirect;
    }
}
