package com.spring.Model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Data
public class Task extends BaseEntity {

	@JsonProperty
	@NotBlank
	private String title;

	@JsonProperty
	@NotBlank
	private String description;

	@JsonProperty
	@Min(value = 0)
	private Integer points;

	@ManyToOne
	@JsonProperty
	private Project project;

	@ManyToMany
	@JsonProperty
	private List<User> users;

//	@ManyToOne
//	@JsonProperty
//	private Column column;

}