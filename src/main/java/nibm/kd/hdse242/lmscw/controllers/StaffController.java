package nibm.kd.hdse242.lmscw.controllers;

import nibm.kd.hdse242.lmscw.dto.StaffDTO;
import nibm.kd.hdse242.lmscw.services.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/lms/v1/staffs")
public class StaffController {
    @Autowired
    private StaffService staffService;

    @GetMapping
    public List<StaffDTO> getAllStaffs() {
        try {
            return staffService.getAllStaffs();
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    @GetMapping("/{Id}")
    public StaffDTO getStaff(@PathVariable long Id) {
        try {
            return staffService.getStaff(Id);
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    @PostMapping
    public Map<String, Object> addStaff(@RequestBody StaffDTO staffDTO) {
        Map<String, Object> response = new HashMap<>();
        try {
            StaffDTO addedStaff = staffService.addStaff(staffDTO);
            response.put("message", "Staff added successfully");
            response.put("staff", addedStaff);
        } catch (Exception e) {
            System.out.println(e);
            response.put("message", "Staff not added");
        }
        return response;
    }

    @PutMapping("/{Id}")
    public Map<String, Object> updateStaff(@PathVariable long Id, @RequestBody StaffDTO staffDTO) {
        Map<String, Object> response = new HashMap<>();
        try {
            StaffDTO updatedStaff = staffService.updateStaff(Id, staffDTO);
            response.put("message", "Staff updated successfully");
            response.put("staff", updatedStaff);
        } catch (Exception e) {
            System.out.println(e);
            response.put("message", e.getMessage());
        }
        return response;
    }

    @DeleteMapping("/{Id}")
    public String deleteStaff(@PathVariable long Id) {
        try {
            staffService.deleteStaff(Id);
            return "Staff deleted";
        } catch (Exception e) {
            System.out.println(e);
            return e.getMessage();
        }
    }
}
