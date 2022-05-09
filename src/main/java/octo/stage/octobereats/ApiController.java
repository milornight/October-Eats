package octo.stage.octobereats;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiController {

    @GetMapping("/ready")
    public API api(){
        API api = new API();
        return api;
    }
}
