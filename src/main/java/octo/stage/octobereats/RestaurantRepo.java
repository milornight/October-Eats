package octo.stage.octobereats;

import java.util.ArrayList;
import java.util.List;

public class RestaurantRepo implements RestaurantRepository{

    private List<Restaurant> list= new ArrayList<Restaurant>();

    Restaurant R1 = new Restaurant("Aki", "Japonais");
    Restaurant R2 = new Restaurant("K-Chicken", "Fast food");
    Restaurant R3 = new Restaurant("On The Bab", "Coreen");
    Restaurant R4 = new Restaurant("Faubourg", "Francais");

    public RestaurantRepo(){
        list.add(R1);
        list.add(R2);
        list.add(R3);
        list.add(R4);
    }

    public List<Restaurant> getRestaurants() {
        return list;
    }

    public Restaurant findById(long id) {
        for(int i = 0; i< list.size(); i++){
            if (id == list.get(i).getId()) {
                return list.get(i);
            }
        }
        return null;
    }
}
