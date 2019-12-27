package com.springboot.fixedTermAccountVip.document;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
@Document(collection = "FixedTermAccountVip")
public class FixedTermAccountVip {
	
	@Id
	private String id;
	
	@NotNull(message = "Account' name must not be null")
	private String nameAccount;
	
	@NotNull(message = "Account' number must not be null")
	private String numberAccount;
	
	@NotNull(message = "Account' tea must not be null")
	@Min(10)
	@Max(25)
	private Double tea;
	
	@NotNull(message = "Account' state must not be null")
	private String state;
	
	@NotNull(message = "Account' balance must not be null")
	@Min(0)
	private Double balance;
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date createDate;
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date updateDate;
	
	private List<String> idOperation; 

}
