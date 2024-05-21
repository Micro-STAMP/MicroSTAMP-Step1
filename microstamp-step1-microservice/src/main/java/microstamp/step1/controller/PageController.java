package microstamp.step1.controller;

import microstamp.step1.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class PageController {

    @Autowired
    private ProjectRepository ProjectRepository;

    @Autowired
    private SystemGoalRepository SystemGoalRepository;

    @Autowired
    private AssumptionRepository AssumptionRepository;

    @Autowired
    private LossRepository LossRepository;

    @Autowired
    private HazardRepository HazardRepository;

    @Autowired
    private SystemSafetyConstraintRepository SystemSafetyConstraintRepository;

    @GetMapping("/home")
    public String projects(Model model){
        model.addAttribute("projects", ProjectRepository.findAll());
        return "projects";
    }

    @GetMapping("/")
    public String redirectHome(Model model){
        return projects(model);
    }

    @GetMapping("/{projectId:\\d+}")
    public String projectIndexPage(@PathVariable Long projectId, Model model){
        model.addAttribute("systemGoals", SystemGoalRepository.findByProjectId(projectId).get());
        model.addAttribute("assumptions", AssumptionRepository.findByProjectId(projectId).get());
        model.addAttribute("losses", LossRepository.findByProjectId(projectId).get());
        model.addAttribute("hazards", HazardRepository.findByProjectId(projectId).get());
        model.addAttribute("systemSafetyConstraints", SystemSafetyConstraintRepository.findByProjectId(projectId).get());

        model.addAttribute("project_id", projectId);

        return "projectIndex";
    }

    @GetMapping("/guests")
    public String guestsProjects(Model model){
        model.addAttribute("projects", ProjectRepository.findProjectsForGuests().get());
        return "guestsProjects";
    }

    @GetMapping("/guests/{projectId}")
    public String guestsProjectIndexPage(@PathVariable Long projectId, Model model){
        model.addAttribute("systemGoals", SystemGoalRepository.findByProjectId(projectId).get());
        model.addAttribute("assumptions", AssumptionRepository.findByProjectId(projectId).get());
        model.addAttribute("losses", LossRepository.findByProjectId(projectId).get());
        model.addAttribute("hazards", HazardRepository.findByProjectId(projectId).get());
        model.addAttribute("systemSafetyConstraints", SystemSafetyConstraintRepository.findByProjectId(projectId).get());

        model.addAttribute("project_id", projectId);

        return "guestsProjectIndex";
    }
}
