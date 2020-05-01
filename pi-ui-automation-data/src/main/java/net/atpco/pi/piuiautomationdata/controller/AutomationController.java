package net.atpco.pi.piuiautomationdata.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import net.atpco.pi.piuiautomationdata.model.search.FareSearchStatus;
import net.atpco.pi.piuiautomationdata.model.search.UIFareSearchRequest;
import net.atpco.pi.piuiautomationdata.model.search.UIServiceResponse;
import net.atpco.pi.piuiautomationdata.service.AutomationService;

@RestController
@RequestMapping("/automation")
@Slf4j
@AllArgsConstructor
public class AutomationController {

    final private AutomationService automationService;

    @PostMapping("/search")
    public ResponseEntity<UIServiceResponse<FareSearchStatus, String>> search(@RequestHeader final HttpHeaders headers,
                                                                              @Valid @RequestBody final UIFareSearchRequest uiFareSearchRequest) {
        log.info(automationService.toString());
        log.info("Automation Controller: search() - {}", uiFareSearchRequest);
        return ResponseEntity.ok(new UIServiceResponse<>());
    }
}
