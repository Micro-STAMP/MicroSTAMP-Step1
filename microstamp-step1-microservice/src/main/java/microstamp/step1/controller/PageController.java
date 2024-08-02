package microstamp.step1.controller;

import microstamp.step1.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@Controller
public class PageController {

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

    /*
    @GetMapping("/home")
    public String projects(Model model) {
        model.addAttribute("projects", projectRepository.findAll());
        return "projects";
    }

    @GetMapping("/")
    public String redirectHome(Model model) {
        return projects(model);
    }
    */

    @GetMapping("/{projectId}")
    public String projectIndexPage(@PathVariable UUID projectId, Model model) {
        model.addAttribute("systemGoals", systemGoalRepository.findByAnalysisId(projectId));
        model.addAttribute("assumptions", assumptionRepository.findByAnalysisId(projectId));
        model.addAttribute("losses", lossRepository.findByAnalysisId(projectId));
        model.addAttribute("hazards", hazardRepository.findByAnalysisId(projectId));
        model.addAttribute("systemSafetyConstraints", systemSafetyConstraintRepository.findByAnalysisId(projectId));

        model.addAttribute("project_id", projectId);

        return "projectIndex";
    }

    /*
    @GetMapping("/guests")
    public String guestsProjects(Model model) {
        model.addAttribute("projects", projectRepository.findProjectsForGuests());
        return "guestsProjects";
    }
    */

    @GetMapping("/guests/{projectId}")
    public String guestsProjectIndexPage(@PathVariable UUID projectId, Model model) {
        model.addAttribute("systemGoals", systemGoalRepository.findByAnalysisId(projectId));
        model.addAttribute("assumptions", assumptionRepository.findByAnalysisId(projectId));
        model.addAttribute("losses", lossRepository.findByAnalysisId(projectId));
        model.addAttribute("hazards", hazardRepository.findByAnalysisId(projectId));
        model.addAttribute("systemSafetyConstraints", systemSafetyConstraintRepository.findByAnalysisId(projectId));

        model.addAttribute("project_id", projectId);

        return "guestsProjectIndex";
    }
}
