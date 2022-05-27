package octo.stage.octobereats.infra.controller;

import octo.stage.octobereats.domain.Livreur;
import octo.stage.octobereats.infra.flux.CommandeFlux;
import octo.stage.octobereats.infra.repository.CommandeRepository;
import octo.stage.octobereats.infra.repository.LivreurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class LivreurController {

    @Autowired
    LivreurRepository livreurRepository;


    public LivreurController(LivreurRepository livreurRepository) {
         this.livreurRepository = livreurRepository;
    }

    @GetMapping("/livreurs")
    public List<Livreur> livreurs(){
        return livreurRepository.getLivreurs();
    }


    @PostMapping("/livreurs")
    public Livreur newLivreur(@RequestBody Livreur livreur){
        return livreurRepository.addLivreur(livreur);
    }

}
