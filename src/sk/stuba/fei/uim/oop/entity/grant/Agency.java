package sk.stuba.fei.uim.oop.entity.grant;

import java.util.*;
import java.util.stream.Collectors;

public class Agency implements AgencyInterface {

    private String name = "";
    private final HashSet<GrantInterface> grants = new HashSet<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addGrant(GrantInterface gi, int year) {
        gi.setYear(year);
        grants.add(gi);
    }

    public Set<GrantInterface> getAllGrants() {
        return grants;
    }

    public Set<GrantInterface> getGrantsIssuedInYear(int year) {
        return grants.stream()
                .filter(g -> year == g.getYear())
                .collect(Collectors.toSet());
    }
}
