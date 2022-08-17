package octo.stage.octobereats;

import octo.stage.octobereats.domain.Livreur;
import octo.stage.octobereats.infra.repository.LivreurRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
public class LivreurRepoTest {

    @MockBean @Autowired
    LivreurRepository livreurRepo;

    @Test
    public void TestFindById(){
        Livreur l1 = new Livreur("Martin","Kiki");
        Livreur l2 = new Livreur("Richard","Tom");
        List<Livreur> listLivreur = List.of(l1,l2);
        livreurRepo.addLivreur(l1);
        livreurRepo.addLivreur(l2);

        Mockito.when(livreurRepo.findById(2)).thenReturn(l2);

        Mockito.verify(livreurRepo, times(1)).findById(2);
    }

}
