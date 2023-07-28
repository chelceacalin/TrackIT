package PortalTracker.Tracker.specification;

import PortalTracker.Tracker.model.Employee;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public class EmployeeSpecification {

    public static Specification<Employee> firstNameIn(List<String> firstNames) {
        return (root, query, criteriaBuilder) -> root.get("firstName").in(firstNames);
    }

    public static Specification<Employee> firstNameLike(String firstName){
        return ((root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("firstName"),"%"+firstName+"%"));
    }


    public static Specification<Employee> lastNameLengthGreaterThan(int length) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.greaterThan(criteriaBuilder.length(root.get("lastName")), length);
    }
}
