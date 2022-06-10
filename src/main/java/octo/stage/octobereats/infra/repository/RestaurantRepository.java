package octo.stage.octobereats.infra.repository;

import octo.stage.octobereats.domain.Plat;
import octo.stage.octobereats.domain.Restaurant;

import java.util.List;
import java.util.Map;

public interface RestaurantRepository {
    public List<Restaurant> getRestaurants();
    public Restaurant findById(long id);
    public List<Plat> getPlats(long id);
}
