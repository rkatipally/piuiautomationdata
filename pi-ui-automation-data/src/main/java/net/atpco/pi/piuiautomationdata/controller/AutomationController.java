package net.atpco.pi.piuiautomationdata.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import net.atpco.common.domain.model.Message;
import net.atpco.pi.piuiautomationdata.model.search.BaseSearchStatus;
import net.atpco.pi.piuiautomationdata.model.search.FareSearchResponse;
import net.atpco.pi.piuiautomationdata.model.search.FareSearchStatus;
import net.atpco.pi.piuiautomationdata.model.search.UIFareSearchRequest;
import net.atpco.pi.piuiautomationdata.model.search.UISearchPageRequest;
import net.atpco.pi.piuiautomationdata.model.search.UIServiceResponse;
import net.atpco.pi.piuiautomationdata.service.AutomationService;

@RestController
@RequestMapping("/atpapps/pistrategyskipper/api/v1/crud")
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

    @RequestMapping(value = "/status/{queryId}", method = RequestMethod.GET)
    public ResponseEntity<UIServiceResponse<BaseSearchStatus, Message>> getStatus(@PathVariable String queryId) {
        log.info("calling common controller for query status");
        BaseSearchStatus searchStatus = new BaseSearchStatus();
        return ResponseEntity.ok(new UIServiceResponse<>(searchStatus, new Message()));
    }

    @PostMapping("/page")
    public ResponseEntity<UIServiceResponse<FareSearchResponse, Message>> getPage(@RequestHeader final HttpHeaders headers, @RequestBody UISearchPageRequest searchPageRequest) {
        UIServiceResponse<FareSearchResponse, Message> uiServiceResponse =  new UIServiceResponse<>();
        if (uiServiceResponse.getMessage() != null ) {
            return ResponseEntity.unprocessableEntity().body(uiServiceResponse);
        }
        return ResponseEntity.ok(uiServiceResponse);
    }
}
