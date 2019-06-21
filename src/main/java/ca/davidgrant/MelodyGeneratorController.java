package ca.davidgrant;

import ca.davidgrant.melodygenerator.MelodyGenerator;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.Produces;

@Controller("/melody")
public class MelodyGeneratorController {

    @Get("/")
    @Produces(MediaType.TEXT_PLAIN)
    public String index() {
        return "hello";
    }
}