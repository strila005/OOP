package sk.stuba.fei.uim.oop.entity.organization;

import sk.stuba.fei.uim.oop.entity.grant.ProjectInterface;
import static sk.stuba.fei.uim.oop.utility.Constants.COMPANY_INIT_OWN_RESOURCES;

public class Company extends University {

    @Override
    public int getBudgetForAllProjects() {
        int allResources = COMPANY_INIT_OWN_RESOURCES;
        for (ProjectInterface pr : super.getAllProjects()) {
            allResources += pr.getTotalBudget();
        }
        return allResources;
    }
}
