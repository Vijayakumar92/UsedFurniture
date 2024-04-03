package com.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.admin.adminDTO.AdminDTO;
import com.admin.entity.Admin;
import com.admin.exception.AdminNotFoundException;
import com.admin.serviceimplementation.AdminServiceImpl;

@RestController
@RequestMapping("/admin")
@CrossOrigin(origins = "*")	//Frontend Connection
public class AdminController {

	@Autowired
	AdminServiceImpl adminServiceImpl;

	@PostMapping("/register")
	public AdminDTO addAdmin(@RequestBody Admin admin) {
		return adminServiceImpl.addAdmin(admin);
	}

	@PutMapping("/update/{admin_id}")
	public String updateAdmin(@PathVariable(value = "admin_id") int id, @RequestBody AdminDTO adminDTO) {

		return adminServiceImpl.updateAdmin(id, adminDTO);
	}

	@GetMapping("/adminbyemail/{admin_email}")
	public AdminDTO getAdminByEmail(@PathVariable(value = "admin_email") String email) throws AdminNotFoundException {
		return adminServiceImpl.getAdminByEmail(email);
	}
	@GetMapping("/getall")
	public List<AdminDTO> getAllAdmins() throws AdminNotFoundException {
		return adminServiceImpl.readAllAdmins();
	}
	@DeleteMapping("/deleteadmin/{admin_id}")
	public String deleteAdmin(@PathVariable("admin_id") int id) throws AdminNotFoundException {
		return adminServiceImpl.deleteAdmin(id);
	}

}
