package demo.example.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "ai_systems")

public class AISystem {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    private String developer;

    @Column(name = "launch_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd") 
    private LocalDate launchDate;

    private String version;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SystemType type;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SystemStatus status;

    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDeveloper() {
		return developer;
	}

	public void setDeveloper(String developer) {
		this.developer = developer;
	}

	public LocalDate getLaunchDate() {
		return launchDate;
	}

	public void setLaunchDate(LocalDate launchDate) {
		this.launchDate = launchDate;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public SystemType getType() {
		return type;
	}

	public void setType(SystemType type) {
		this.type = type;
	}

	public SystemStatus getStatus() {
		return status;
	}

	public void setStatus(SystemStatus status) {
		this.status = status;
	}

	// Constructors
    public AISystem() {}

    
}