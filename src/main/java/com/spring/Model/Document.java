package com.spring.Model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.validator.constraints.SafeHtml;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class Document extends BaseEntity {
	
	@JsonProperty
	@Enumerated(EnumType.STRING)
	private DocumentType type;
	
	@NotBlank
	@SafeHtml
	@JsonProperty
	private String name;
	
	@JsonProperty
	@SafeHtml
	@NotBlank
	private String content;
	
	@ManyToOne
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JsonProperty
	@NotNull
	private Sprint sprint;

}
