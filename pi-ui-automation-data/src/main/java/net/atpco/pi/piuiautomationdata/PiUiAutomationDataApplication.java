package net.atpco.pi.piuiautomationdata;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"net.atpco.pi.piuiautomationdata.*"})
public class PiUiAutomationDataApplication {

	public static void main(String[] args) {
		SpringApplication.run(PiUiAutomationDataApplication.class, args);
	}

}
