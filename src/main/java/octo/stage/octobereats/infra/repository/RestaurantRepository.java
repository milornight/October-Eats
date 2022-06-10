package octo.stage.octobereats.infra.repository;

import octo.stage.octobereats.domain.Plat;
import octo.stage.octobereats.domain.Restaurant;

import java.util.List;

public interface RestaurantRepository {
    List<Restaurant> getRestaurants();
    Restaurant findById(long id);
    List<Plat> getPlats(long id);
}
