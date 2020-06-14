package app.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.stream.IntStream;

@Configuration
@EnableWebMvc
public class MyAppConfigurer implements WebMvcConfigurer {
    private static final String PREFIX = "classpath:/static";
    // web mapping
    private static final String[] MAPPINGS = {"/css/**", "/js/**", "/img/**"};
    // file system mapping
    private static final String[] LOCATIONS = {"/css/", "/js/", "/img/"};

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        if (MAPPINGS.length != LOCATIONS.length) throw new IllegalArgumentException(
                "Config: Mapping size must be equal to the Location size");

        IntStream.range(0, MAPPINGS.length)
                .forEach(idx -> registry.addResourceHandler(MAPPINGS[idx])
                .addResourceLocations(PREFIX + LOCATIONS[idx]));
    }

    public void addResourceHandlers2(ResourceHandlerRegistry registry) {
        registry
                .addResourceHandler("/img/**")
                .addResourceLocations("classpath:/static/img/");
    }
}
