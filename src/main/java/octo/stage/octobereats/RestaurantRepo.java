package octo.stage.octobereats;

import java.util.ArrayList;
import java.util.List;

public class RestaurantRepo implements RestaurantRepository {

    private List<Restaurant> list = new ArrayList<Restaurant>();
    private List<Plat> LP1 = new ArrayList<Plat>();
    private List<Plat> LP2 = new ArrayList<Plat>();
    private List<Plat> LP3 = new ArrayList<Plat>();
    private List<Plat> LP4 = new ArrayList<Plat>();

    Plat P1 = new Plat("Chesse cake", 6);
    Plat P2 = new Plat("Mochi", 4);
    Plat P3 = new Plat("Wings", 10);
    Plat P4 = new Plat("Frites", 3);
    Plat P5 = new Plat("Guabao", 14);
    Plat P6 = new Plat("Bibimbab", 13);
    Plat P7 = new Plat("on the rice", 14);
    Plat P8 = new Plat("Millefeuille", 6);
    Plat P9 = new Plat("Foie gras", 16);
    Plat P10 = new Plat("Roasted Duck", 20);

    Restaurant R1 = new Restaurant("Aki", "Japonais", LP1);
    Restaurant R2 = new Restaurant("K-Chicken", "Fast food", LP2);
    Restaurant R3 = new Restaurant("On The Bab", "Coreen", LP3);
    Restaurant R4 = new Restaurant("Le Comptoir de la Gastronomie", "Francais", LP4);

    public RestaurantRepo() {
        LP1.add(P1);
        LP1.add(P2);
        LP1.add(P8);
        LP2.add(P3);
        LP2.add(P4);
        LP3.add(P5);
        LP3.add(P6);
        LP3.add(P7);
        LP4.add(P9);
        LP4.add(P10);
        list.add(R1);
        list.add(R2);
        list.add(R3);
        list.add(R4);
    }

    public List<Restaurant> getRestaurants() {
        return list;
    }

    public Restaurant findById(long id) {
        for (int i = 0; i < list.size(); i++) {
            if (id == list.get(i).getId()) {
                return list.get(i);
            }
        }
        return null;
    }

    public List<Plat> getPlats(long id) {
        for (int i = 0; i < list.size(); i++) {
            if (id == list.get(i).getId()) {
                return list.get(i).getPlats();
            }
        }
        return null;
    }
}
