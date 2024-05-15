package sk.stuba.fei.uim.oop.entity.organization;

import sk.stuba.fei.uim.oop.entity.grant.ProjectInterface;
import sk.stuba.fei.uim.oop.entity.people.PersonInterface;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class University implements OrganizationInterface {

    private String name;
    private Set<ProjectInterface> projects = new HashSet<>();
    private Map<PersonInterface, Integer> employees = new HashMap<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addEmployee(PersonInterface p, int employment) {
        employees.put(p, employment);
    }

    public Set<PersonInterface> getEmployees() {
        return employees.keySet();
    }

    public int getEmploymentForEmployee(PersonInterface p) {
        return employees.getOrDefault(p, 0);
    }

    public Set<ProjectInterface> getAllProjects() {
        return projects;
    }

    public Set<ProjectInterface> getRunningProjects(int year) {
        Set<ProjectInterface> runningProjects = new HashSet<>();
        for (ProjectInterface project : projects) {
            if (project.getStartingYear() <= year && project.getEndingYear() >= year) {
                runningProjects.add(project);
            }
        }
        return runningProjects;
    }

    public void registerProjectInOrganization(ProjectInterface project) {
        projects.add(project);
    }

    public int getProjectBudget(ProjectInterface pi) {
        return pi.getTotalBudget();
    }

    public int getBudgetForAllProjects() {
        int budget = 0;
        for (ProjectInterface pr : projects) {
            budget += pr.getTotalBudget();
        }
        return budget;
    }

    public void projectBudgetUpdateNotification(ProjectInterface pi, int year, int budgetForYear) {
        System.out.println("Set budget for project " + pi.getProjectName() + " for year " + year + " is " + budgetForYear);
    }
}