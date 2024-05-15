package sk.stuba.fei.uim.oop.entity.people;

import sk.stuba.fei.uim.oop.entity.organization.OrganizationInterface;

import java.util.HashSet;
import java.util.Set;

public class Person implements PersonInterface {

    private String name;
    private String address;
    private Set<OrganizationInterface> employers = new HashSet<OrganizationInterface>();

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void addEmployer(OrganizationInterface organization) {
        employers.add(organization);
    }

    public Set<OrganizationInterface> getEmployers() {
        return employers;
    }
}
