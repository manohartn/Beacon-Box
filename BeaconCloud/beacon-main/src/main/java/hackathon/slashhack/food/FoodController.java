package hackathon.slashhack.food;

import hackathon.slashhack.food.model.FoodConfig;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by pramod.setlur on 8/27/16.
 */

@RestController
@RequestMapping("/v1")
public class FoodController {
    private FoodService foodService = new FoodService();


    @RequestMapping(value = "/healthcheck", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public String healthCheck() {
        return "OK";
    }
    @RequestMapping(value = "/food", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public String food(@RequestBody(required = true) FoodConfig foodConfig) {

        String update = foodService.pushToDb(foodConfig);
        return update;
    }
}
