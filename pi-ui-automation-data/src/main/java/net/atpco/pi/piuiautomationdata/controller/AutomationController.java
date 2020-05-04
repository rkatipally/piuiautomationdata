package net.atpco.pi.piuiautomationdata.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import net.atpco.pi.piuiautomationdata.service.AutomationService;

@RestController("/piuiautomation/api/v1/data")
@AllArgsConstructor
public class AutomationController {

    final AutomationService automationService;

    @DeleteMapping
    public ResponseEntity<String> clearData() {
        try {
            this.automationService.clearTestData();
            return ResponseEntity.ok("Successfully deleted test data!");
        } catch (Exception ex) {
            return ResponseEntity.unprocessableEntity().body("Could not delete data, please try again later!");
        }
    }

    @PostMapping
    public ResponseEntity<String> loadData() {
        try {
            this.automationService.loadData();
            return ResponseEntity.ok("Successfully loaded test data!");
        } catch (Exception ex) {
            return ResponseEntity.unprocessableEntity().body("Could not load data, please try again later!");
        }
    }

}
