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
	Long id;
	String name;

	String type;

	LocalDateTime dateSearched;

	public ImageData() {
		this.dateSearched = LocalDateTime.now();
	}

	@Column(name = "imagedata", columnDefinition = "LONGBLOB")
	byte[] imageData;

	@OneToOne
	@JoinColumn(name = "employee_id", referencedColumnName = "id")
	@JsonIgnore
	Employee employee;
}
