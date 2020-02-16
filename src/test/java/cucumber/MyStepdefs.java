package cucumber;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.api.java8.En;

public class MyStepdefs implements En {
    @Given("I do something here")
    public void iDoSomethingHere() {
        System.out.println("iDoSomethingHere");
    }

    @When("I do something (.*) also")
    public void iDoSomethingHereAlso() {
        System.out.println("iDoSomethingHereAlso");
    }

    @Then("I valid that")
    public void iValidThat() {
        System.out.println("iValidThat");
    }

    @Then("kill all humans")
    public void killAllHumans() {
        System.out.println("kill them all");
    }
}
