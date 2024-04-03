package com.admin.adminDTO;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdminDTO {

	
	protected int id;
	protected String username;
	protected String password;
	protected String email;
	protected long number;
	protected String address;

	

	

}