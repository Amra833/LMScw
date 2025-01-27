package nibm.kd.hdse242.lmscw.services;

import nibm.kd.hdse242.lmscw.dto.StaffDTO;
import nibm.kd.hdse242.lmscw.entities.Staff;
import nibm.kd.hdse242.lmscw.repositories.StaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class StaffService {
    @Autowired
    private StaffRepository staffRepository;

    public StaffService(StaffRepository staffRepository) {
        this.staffRepository = staffRepository;
    }

    // Create Staff
    public StaffDTO addStaff(StaffDTO staffDTO) {
        Staff staff = new Staff();
        staff.setId(staffDTO.getId());
        staff.setName(staffDTO.getName());
        staff.setEmail(staffDTO.getEmail());
        staff.setPhone(staffDTO.getPhone());
        staffRepository.save(staff);
        return staffDTO;
    }

    // Retrieve Staff
    public List<StaffDTO> getAllStaffs() {
        List<Staff> staffList = staffRepository.findAll();
        List<StaffDTO> staffDTOs = new ArrayList<>();
        for (Staff staff : staffList) {
            StaffDTO staffDTO = new StaffDTO();
            staffDTO.setId(staff.getId());
            staffDTO.setName(staff.getName());
            staffDTO.setEmail(staff.getEmail());
            staffDTO.setPhone(staff.getPhone());
            staffDTOs.add(staffDTO);
        }
        return staffDTOs;
    }

    // Retrieve a Staff
    public StaffDTO getStaff(long id) {
        Staff staff = staffRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Staff Not Found"));
        StaffDTO staffDTO = new StaffDTO();
        staffDTO.setId(staff.getId());
        staffDTO.setName(staff.getName());
        staffDTO.setEmail(staff.getEmail());
        staffDTO.setPhone(staff.getPhone());
        return staffDTO;
    }

    // Update Staff
    public StaffDTO updateStaff(long id, StaffDTO staffDTO) {
        Staff staff = staffRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Staff Not Found"));
        //staff.setId(staffDTO.getId());
        staff.setName(staffDTO.getName());
        staff.setEmail(staffDTO.getEmail());
        staff.setPhone(staffDTO.getPhone());
        staffRepository.save(staff);
        return staffDTO;
    }

    // Delete Staff
    public StaffDTO deleteStaff(Long id) {
        Staff staff = staffRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Staff Not Found"));
        staffRepository.deleteById(id);

        StaffDTO staffDTO = new StaffDTO();
        staffDTO.setId(staff.getId());
        staffDTO.setName(staff.getName());
        staffDTO.setEmail(staff.getEmail());
        staffDTO.setPhone(staff.getPhone());

        return staffDTO;
    }
}
