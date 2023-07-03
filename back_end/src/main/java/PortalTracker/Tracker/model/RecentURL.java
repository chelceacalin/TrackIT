package PortalTracker.Tracker.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;

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
