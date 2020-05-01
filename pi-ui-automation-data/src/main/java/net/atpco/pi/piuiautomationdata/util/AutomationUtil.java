package net.atpco.pi.piuiautomationdata.util;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static net.atpco.pi.piuiautomationdata.constants.AutomationConstants.JSON_EXTENSION;

@Slf4j
public class AutomationUtil {

    public static <T> List<T> readResource(Resource resource, Class<T> resourceType) throws IOException {
        Path configFilePath = Paths.get(resource.getURI());
        ObjectMapper objectMapper = new ObjectMapper();
        List<T> data = Files.walk(configFilePath)
                .filter(s -> s.toString().endsWith(JSON_EXTENSION))
                .map(Path::toFile)
                .peek(System.out::println)
                .map(file -> {
                    try {
                        JavaType type = objectMapper.getTypeFactory().constructParametricType(List.class, resourceType);
                        return (ArrayList<T>) objectMapper.readValue(file, type);
                    } catch (IOException e) {
                        log.error("Error while deserializing test file : {} ", file.getAbsoluteFile());
                        e.printStackTrace();
                        throw new RuntimeException(e.getMessage());
                    }
                })
                .flatMap(List::stream)
                .collect(Collectors.toList());
        return data;
    }

}
