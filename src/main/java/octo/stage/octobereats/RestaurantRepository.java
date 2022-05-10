package octo.stage.octobereats;

import java.util.List;

interface RestaurantRepository {
    public List<Restaurant> getRestaurants();
    public Restaurant findById(long id);
    public List<Plat> getPlats(long id);
}
