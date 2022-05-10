package octo.stage.octobereats;

public class RestaurantNotFoundException extends RuntimeException {

    RestaurantNotFoundException(Long id) {
        super("Could not find restaurant " + id);
    }
}
