package com.github.marceloedudev.integration.infra;

import io.quarkiverse.cucumber.CucumberOptions;
import io.quarkiverse.cucumber.CucumberQuarkusTest;

@CucumberOptions(
        features = "src/test/resources/features",
        plugin = {
                "json",
                "json:target/cucumber/report.json",
                "html:target/cucumber/cucumber-checkout-app"
        }
)
public class CucumberRunnerTest extends CucumberQuarkusTest {
    public static void main(String[] args) {
        runMain(CucumberRunnerTest.class, args);
    }
}
