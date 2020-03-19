package com.spring.customobject;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ListAllTaskByProjectDto {
		
		private Integer id;
		
		private String name;
		
		private String description;
		
		private List<TaskListDto> tasks;
		
}