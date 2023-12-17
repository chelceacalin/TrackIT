package PortalTracker.Tracker.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "imageData")
@AllArgsConstructor
@Data
@Builder
public class ImageData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    private String type;

    private LocalDateTime dateSearched;
    public ImageData() {
        this.dateSearched=LocalDateTime.now();
    }

    @Column(name = "imagedata", columnDefinition = "LONGBLOB")
    private byte[] imageData;

    @OneToOne
    @JoinColumn(name = "employee_id",referencedColumnName = "id")
    @JsonIgnore
    private Employee employee;
}
