package com.admin.serviceimplementation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.admin.adminDTO.AdminDTO;
import com.admin.entity.Admin;
import com.admin.exception.AdminNotFoundException;
import com.admin.repository.AdminRepository;
import com.admin.service.AdminService;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	private AdminRepository adminRepository;

	@Override
	public AdminDTO addAdmin(Admin admin) {
		AdminDTO adminDTO = new AdminDTO();
		adminRepository.save(admin);
		
		adminDTO.setUsername(admin.getUsername());
		adminDTO.setAddress(admin.getAddress());
		adminDTO.setEmail(admin.getEmail());
		adminDTO.setNumber(admin.getNumber());
		

		
		adminDTO.setId(admin.getId());
		return adminDTO;
	}

	// Update User
	@Override
	public String updateAdmin(int id, AdminDTO adminDTO) {

		try {
			Admin admin = adminRepository.findById(id).orElseThrow(() -> new AdminNotFoundException("Admin Not Found"));
			if (adminDTO.getUsername() != null)
				admin.setUsername(adminDTO.getUsername());
			if (adminDTO.getNumber() != 0)
				admin.setNumber(adminDTO.getNumber());
			if (adminDTO.getAddress() != null)
				admin.setAddress(adminDTO.getAddress());
			if (adminDTO.getEmail() != null)
				admin.setEmail(adminDTO.getEmail());
			if (adminDTO.getPassword() != null)
				admin.setPassword(adminDTO.getPassword());
			adminRepository.save(admin);

		} catch (AdminNotFoundException e) {

			System.out.println(e);
			return "Admin data not updated";
		}
		return "Admin Updated Successfully";
	}

	// Delete user
	@Override
	public String deleteAdmin(int id) throws AdminNotFoundException {
		Admin admin= adminRepository.findById(id).get();
		if(admin==null) {
			throw new AdminNotFoundException("No admin found for given id....");
		}
		adminRepository.delete(admin);
		return "Admin deleted successfully!!";
		
	}

	@Override
	public AdminDTO getAdminByEmail(String email) throws AdminNotFoundException {
		Admin admin = adminRepository.findByEmail(email);
		if(admin== null) {
			throw new AdminNotFoundException("Admin not found for given email....");
		}
		AdminDTO userDTO = new AdminDTO();

		userDTO.setAddress(admin.getAddress());
		userDTO.setEmail(admin.getEmail());
		userDTO.setNumber(admin.getNumber());
		userDTO.setUsername(admin.getUsername());
		userDTO.setId(admin.getId());

		return userDTO;
	}

	// Read all Users
	@Override
	public List<AdminDTO> readAllAdmins() throws AdminNotFoundException{
		List<Admin> admins = adminRepository.findAll();
		
		if(admins.isEmpty()) {
			throw new AdminNotFoundException("Admins Not Registered");
		}
		System.out.println(admins);
		
		List<AdminDTO> adminDTOs = new ArrayList<>();
		for (Admin admin : admins) {
			AdminDTO adminDTO = new AdminDTO();
			adminDTO.setUsername(admin.getUsername());
			adminDTO.setAddress(admin.getAddress());
			adminDTO.setEmail(admin.getEmail());
			adminDTO.setNumber(admin.getNumber());
			adminDTO.setId(admin.getId());
			adminDTOs.add(adminDTO);

		}

		return adminDTOs;
	}

}
