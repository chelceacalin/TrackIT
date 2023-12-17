package PortalTracker.Tracker.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "recentURL")
@AllArgsConstructor
@Getter
@Setter
public class RecentURL {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String path;

    private LocalDateTime dateSearched;
    public RecentURL() {
        this.dateSearched=LocalDateTime.now();
    }

    @ManyToOne
    @JoinColumn(name = "fk_employee_id",referencedColumnName = "id")
    Employee employee;

}
