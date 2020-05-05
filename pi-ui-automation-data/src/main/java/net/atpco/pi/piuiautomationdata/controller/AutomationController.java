package net.atpco.pi.piuiautomationdata.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import net.atpco.pi.piuiautomationdata.service.AutomationService;

@RestController("/piuiautomation/api/v1/data")
@AllArgsConstructor
@Slf4j
public class AutomationController {

    final AutomationService automationService;

    @GetMapping
    public ResponseEntity<String> loadFromGitHub() {
        try {
            this.automationService.loadDataFromGitHub();
            return ResponseEntity.ok("Successfully loaded test data from GitHub!");
        } catch (Exception ex) {
            log.error("Error occurred while loading data from GitHub - ", ex);
            return ResponseEntity.unprocessableEntity().body("Could not load data, please try again later!");
        }
    }

    @PostMapping
    public ResponseEntity<String> loadData() {
        try {
            this.automationService.loadData();
            return ResponseEntity.ok("Successfully loaded test data!");
        } catch (Exception ex) {
            log.error("Error occurred while loading data - ", ex);
            return ResponseEntity.unprocessableEntity().body("Could not load data, please try again later!");
        }
    }

    @DeleteMapping
    public ResponseEntity<String> clearData() {
        try {
            this.automationService.clearTestData();
            return ResponseEntity.ok("Successfully deleted test data!");
        } catch (Exception ex) {
            return ResponseEntity.unprocessableEntity().body("Could not delete data, please try again later!");
        }
    }

}
