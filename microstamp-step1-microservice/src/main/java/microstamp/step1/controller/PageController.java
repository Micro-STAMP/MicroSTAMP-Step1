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
    private ProjectRepository projectRepository;

    @Autowired
    private SystemGoalRepository systemGoalRepository;

    @Autowired
    private AssumptionRepository assumptionRepository;

    @Autowired
    private LossRepository lossRepository;

    @Autowired
    private HazardRepository hazardRepository;

    @Autowired
    private SystemSafetyConstraintRepository systemSafetyConstraintRepository;

    @GetMapping("/home")
    public String projects(Model model) {
        model.addAttribute("projects", projectRepository.findAll());
        return "projects";
    }

    @GetMapping("/")
    public String redirectHome(Model model) {
        return projects(model);
    }

    @GetMapping("/{projectId:\\d+}")
    public String projectIndexPage(@PathVariable Long projectId, Model model) {
        model.addAttribute("systemGoals", systemGoalRepository.findByProjectId(projectId));
        model.addAttribute("assumptions", assumptionRepository.findByProjectId(projectId));
        model.addAttribute("losses", lossRepository.findByProjectId(projectId));
        model.addAttribute("hazards", hazardRepository.findByProjectId(projectId));
        model.addAttribute("systemSafetyConstraints", systemSafetyConstraintRepository.findByProjectId(projectId));

        model.addAttribute("project_id", projectId);

        return "projectIndex";
    }

    @GetMapping("/guests")
    public String guestsProjects(Model model) {
        model.addAttribute("projects", projectRepository.findProjectsForGuests());
        return "guestsProjects";
    }

    @GetMapping("/guests/{projectId}")
    public String guestsProjectIndexPage(@PathVariable Long projectId, Model model) {
        model.addAttribute("systemGoals", systemGoalRepository.findByProjectId(projectId));
        model.addAttribute("assumptions", assumptionRepository.findByProjectId(projectId));
        model.addAttribute("losses", lossRepository.findByProjectId(projectId));
        model.addAttribute("hazards", hazardRepository.findByProjectId(projectId));
        model.addAttribute("systemSafetyConstraints", systemSafetyConstraintRepository.findByProjectId(projectId));

        model.addAttribute("project_id", projectId);

        return "guestsProjectIndex";
    }
}
