package com.admin.service;

import java.util.List;

import com.admin.adminDTO.AdminDTO;
import com.admin.entity.Admin;
import com.admin.exception.AdminNotFoundException;

public interface AdminService {
	
	
	public AdminDTO addAdmin(Admin admin);
	
	public String updateAdmin(int id,AdminDTO adminDTO);
	
	public AdminDTO getAdminByEmail(String email) throws AdminNotFoundException ;
	
	public String deleteAdmin(int id) throws AdminNotFoundException ;
	
	public List<AdminDTO> readAllAdmins() throws AdminNotFoundException;
	

}
