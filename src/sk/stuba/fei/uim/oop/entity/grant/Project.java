package sk.stuba.fei.uim.oop.entity.grant;

import sk.stuba.fei.uim.oop.entity.organization.OrganizationInterface;
import sk.stuba.fei.uim.oop.entity.people.PersonInterface;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static sk.stuba.fei.uim.oop.utility.Constants.PROJECT_DURATION_IN_YEARS;

public class Project implements ProjectInterface {

    private String name;
    private int startingYear;
    private Map<Integer, Integer> budgetForYear = new HashMap<>();
    private OrganizationInterface applicant;
    private Set<PersonInterface> participants = new HashSet<>();

    public String getProjectName() {
        return name;
    }

    public void setProjectName(String name) {
        this.name = name;
    }

    public int getStartingYear() {
        return startingYear;
    }

    public void setStartingYear(int year) {
        this.startingYear = year;
    }

    public int getEndingYear() {
        return startingYear + PROJECT_DURATION_IN_YEARS - 1;
    }

    public int getBudgetForYear(int year) {
//        System.out.println("Current budget of this project("+getProjectName()+") for year is " + budgetForYear.get(year));
        return budgetForYear.get(year);
    }

    public void setBudgetForYear(int year, int budget) {
        System.out.println("Set budget for project " + getProjectName() + " for year " + year + " is " + budget);
        budgetForYear.put(year, budget);
    }

    public int getTotalBudget() {
        int totalBudget = 0;
        for (int year : budgetForYear.keySet()) {
            totalBudget += getBudgetForYear(year);
        }
        return totalBudget;
    }

    public void addParticipant(PersonInterface participant) {
        participants.add(participant);
    }

    public Set<PersonInterface> getAllParticipants() {
        return participants;
    }

    public OrganizationInterface getApplicant() {
        return applicant;
    }

    public void setApplicant(OrganizationInterface applicant) {
        this.applicant = applicant;
    }
}
