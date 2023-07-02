package PortalTracker.Tracker.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Entity
@Table(name = "recentURL")
public class RecentURL {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String path;

    private LocalDateTime dateSearched;

    public RecentURL(String path) {
        this.path = path;
        this.dateSearched = LocalDateTime.now();
        System.out.println(" Path inserted "+path);
    }
    public RecentURL() {
        this.dateSearched=LocalDateTime.now();

        System.out.println(" No arg constructor " + dateSearched);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public LocalDateTime getDateSearched() {
        return dateSearched;
    }

    public void setDateSearched(LocalDateTime dateSearched) {
        this.dateSearched = dateSearched;
    }


    @ManyToOne
    @JoinColumn(name ="employee_id",referencedColumnName ="id" )
    private Employee employee;

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}
