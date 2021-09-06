package Runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)

@CucumberOptions(
        features="src//main//java//Features"
                ,glue={"Stepdefination"}
)
 public class TestRunner{
}
//C:\\Users\\shlovan\\IdeaProjects\\Assignment4_BDD\\src\\main\\java\\Features\\BDD.feature",glue="StepDefination
