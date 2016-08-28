package hackathon.slashhack.evict;

import com.amazonaws.auth.InstanceProfileCredentialsProvider;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Table;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

/**
 * Created by pramod.setlur on 8/28/16.
 */

@RestController
@RequestMapping("/v1")
public class EvictController {
            DynamoDB dynamoDB = new DynamoDB(new AmazonDynamoDBClient(
                    new InstanceProfileCredentialsProvider()));
    private final static String PRIMARY_KEY = "BeaconCategory";
    private final static ArrayList<String> ALL_CATEGORIES = new ArrayList<String>() {{
        add("lunch");
        add("dinner");
        add("breakfast");
    }};


//    DynamoDB dynamoDB = new DynamoDB(new AmazonDynamoDBClient(
//            new ProfileCredentialsProvider("shantanu")));

    private Table table = dynamoDB.getTable("EstimoteBeacon");

    @RequestMapping(value = "/evict", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public String evict() {
        for (String category:ALL_CATEGORIES) {
            table.deleteItem(PRIMARY_KEY, category);
        }
        return "Evicted!!";
    }
}
