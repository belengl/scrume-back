package com.spring.Model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.validator.constraints.SafeHtml;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Task extends BaseEntity {

	@JsonProperty
	@NotBlank
	@SafeHtml
	private String title;
	
	@JsonProperty
	@NotBlank
	@SafeHtml
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

	@ManyToOne
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JoinColumn(name = "`column`")
	@JsonProperty
	private Column column;
}
