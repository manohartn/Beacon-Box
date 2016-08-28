package hackathon.slashhack.food.model;

import lombok.Data;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by pramod.setlur on 8/27/16.
 */

@Data
public class FoodConfig {
    @JsonProperty("category")
    private String category;

    @JsonProperty("store")
    private String store;

    @JsonProperty("deal")
    private String deal;

    @JsonProperty("details")
    private String details;
}
