package sk.stuba.fei.uim.oop.entity.grant;

import sk.stuba.fei.uim.oop.entity.organization.OrganizationInterface;
import sk.stuba.fei.uim.oop.entity.people.PersonInterface;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import static sk.stuba.fei.uim.oop.entity.grant.GrantState.*;
import static sk.stuba.fei.uim.oop.utility.Constants.MAX_EMPLOYMENT_PER_AGENCY;
import static sk.stuba.fei.uim.oop.utility.Constants.PROJECT_DURATION_IN_YEARS;

public class Grant implements GrantInterface {

    private String identifier;
    private int year;
    private AgencyInterface agency;
    private int budget;
    private int remainingBudget;
    private GrantState state;
    private Set<ProjectInterface> registeredProjects = new HashSet<>(); //projects that passed registration (registerProject())

    private Set<ProjectInterface> fundedProjects = new HashSet<>();

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public AgencyInterface getAgency() {
        return agency;
    }

    public void setAgency(AgencyInterface agency) {
        this.agency = agency;
    }

    public int getBudget() {
        return budget;
    }

    public void setBudget(int budget) {
        this.budget = budget;
        this.remainingBudget = budget;
    }

    public int getRemainingBudget() {
        return remainingBudget;
    }

    public int getBudgetForProject(ProjectInterface project) {
        return project.getTotalBudget();
    }

    public boolean registerProject(ProjectInterface project) {
        if (this.getState() == STARTED && getYear() == project.getStartingYear() && project.getAllParticipants().size() != 0) {
            return registeredProjects.add(project);
        } else return false;
    }

    public Set<ProjectInterface> getRegisteredProjects() {
        return registeredProjects;
    }

    public GrantState getState() {
        return state;
    }

    public void callForProjects() {
        state = STARTED;
    }

    public void closeGrant() {
        state = CLOSED;
    }

    public void evaluateProjects() {
        state = GrantState.EVALUATING;

        for (ProjectInterface project : registeredProjects) {
            OrganizationInterface applicant = project.getApplicant();
            boolean passesEvaluation = true;
            for (PersonInterface participant : project.getAllParticipants()) {
                if (applicant.getEmploymentForEmployee(participant) >= MAX_EMPLOYMENT_PER_AGENCY) {
                    passesEvaluation = false;
                    break;
                }
            }

            if (passesEvaluation) {
                fundedProjects.add(project);
            } else {
                for (int year = project.getStartingYear(); year < project.getStartingYear() + PROJECT_DURATION_IN_YEARS; year++) {
                    project.setBudgetForYear(year, 0);
                }
                System.out.println("Project " + project.getProjectName() + " didn't pass the evaluation criteria.");
            }
        }
        int projectsToFund = fundedProjects.size() / 2;
        if (fundedProjects.size() % 2 != 0) {
            projectsToFund++;
        }

        int budgetPerProject = budget / projectsToFund;

        for (ProjectInterface project : fundedProjects) {
            for (int year = project.getStartingYear(); year < project.getStartingYear() + PROJECT_DURATION_IN_YEARS; year++) {
                project.setBudgetForYear(year, budgetPerProject / PROJECT_DURATION_IN_YEARS);
                remainingBudget -= budgetPerProject / PROJECT_DURATION_IN_YEARS;
            }
        }
    }

}
