package octo.stage.octobereats.infra.controller;

import octo.stage.octobereats.API;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiController {

    @GetMapping("/ready")
    public API api(){
        return new API();
    }

}