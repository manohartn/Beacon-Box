package hackathon.slashhack;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.yaml.snakeyaml.Yaml;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ApplicationTest {

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testMain() throws Exception {
        Set testSet = new HashSet();
        testSet.add("service");
        testSet.add("platform");

        Map config = new HashMap();
        config.put("tags", testSet);

        Yaml yaml = new Yaml();
        System.out.println(yaml.dump(config));
    }
}
