package microstamp.step1.controller;

import microstamp.step1.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Collections;

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
        model.addAttribute("systemGoals", systemGoalRepository.findByProjectId(projectId).orElseGet(Collections::emptyList));
        model.addAttribute("assumptions", assumptionRepository.findByProjectId(projectId).orElseGet(Collections::emptyList));
        model.addAttribute("losses", lossRepository.findByProjectId(projectId).orElseGet(Collections::emptyList));
        model.addAttribute("hazards", hazardRepository.findByProjectId(projectId).orElseGet(Collections::emptyList));
        model.addAttribute("systemSafetyConstraints", systemSafetyConstraintRepository.findByProjectId(projectId).orElseGet(Collections::emptyList));

        model.addAttribute("project_id", projectId);

        return "projectIndex";
    }

    @GetMapping("/guests")
    public String guestsProjects(Model model) {
        model.addAttribute("projects", projectRepository.findProjectsForGuests().orElseGet(Collections::emptyList));
        return "guestsProjects";
    }

    @GetMapping("/guests/{projectId}")
    public String guestsProjectIndexPage(@PathVariable Long projectId, Model model) {
        model.addAttribute("systemGoals", systemGoalRepository.findByProjectId(projectId).orElseGet(Collections::emptyList));
        model.addAttribute("assumptions", assumptionRepository.findByProjectId(projectId).orElseGet(Collections::emptyList));
        model.addAttribute("losses", lossRepository.findByProjectId(projectId).orElseGet(Collections::emptyList));
        model.addAttribute("hazards", hazardRepository.findByProjectId(projectId).orElseGet(Collections::emptyList));
        model.addAttribute("systemSafetyConstraints", systemSafetyConstraintRepository.findByProjectId(projectId).orElseGet(Collections::emptyList));

        model.addAttribute("project_id", projectId);

        return "guestsProjectIndex";
    }
}
