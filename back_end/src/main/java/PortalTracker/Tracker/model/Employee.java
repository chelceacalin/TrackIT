package PortalTracker.Tracker.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "employee")
@Data
@AllArgsConstructor
@Builder
public class Employee implements UserDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;

	@Column(name = "first_Name", length = 80, nullable = false)
	String firstName;
	String lastName;

	@Column(name = "email")
	String email;


	String password;

	@Enumerated(EnumType.STRING)
	Role role;

	public Employee() {
		this.role = Role.ADMIN;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return List.of(new SimpleGrantedAuthority(role.name()));
	}

	@Override
	public String getUsername() {
		return email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	@OneToMany(mappedBy = "employee")
	@JsonIgnore
	List<RecentURL> recentURLS;

	@JsonIgnore
	@OneToOne(mappedBy = "employee")
	ImageData imageData;
}
