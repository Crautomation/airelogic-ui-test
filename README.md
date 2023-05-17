# Airelogic Technical Test - Craig Brooks

## Introduction
Thank you for taking the time to review this assessment.

My approach for this task as with most projects was to look at an 'Automation First' approach,
attempting to ensure any repeatable testing within reason was scripted. 

## Tests
### Automation Setup/Framework
For this project I utilised an old Framework I created 5 years ago that I've recently resurrected from the dead. Whilst
this is likely a bit overkill for this I wanted to showcase some of the features of the framework 
for you to gain a bit of an idea of how I like to create accessible, readable and maintainable test automation. 

The framework utilises Java, Maven, Selenium and TestNG in a standard POM layout and has the ability to test across 
Chrome and Firefox, with the ability to add any other selenium based WebDriver within minutes
(IE/Safari/Appium/AWS Device Farm). All the execution code, reporting configurations, etc are all contained within 
the repository here https://github.com/Crautomation/crautomation-core (Not specifically part of this project I know but
you may be interested)

To answer some common questions:
1. "Why are your tests not named in the proper Selenium convention" - I overrode the basic functionality around how
Selenium picks up anything with "Test" in it due to finding in practice that isn't always the best option. Equally
it allows me to have more control over the build (using the profiles) to enable some unit testing of utilities if required.
2. "Your code layout doesn't match the Java standard" - This is also correct I don't believe it's fully neccesary in an
Automation Framework and I found as it got bigger (1000+ Tests) having the Tests and Page Objects split made it much
easier to manage and maintain. 
3. "Some of your tests too granular?" - Absolutely not! With Automation I've always work with the idea that a quick 
feedback loop is effective, and equally that where possible testing concerns should be seperated. 
4. "You don't use logging at all" - In this instance I havnt. Utilising my current reporting solution (Allure2) and its
inbuild "@Step" function the need for logging is reduced dramatically and outputs extremely verbosely within the reporting. 
The logging is utilised much more within the Core aspects of the framework to investigate build/driver/reporting errors.

_Note: Most of the core framework was built 5 years ago and recently ressurected in a rush, so whilst it's great to 
showcase how I like to run Automation Frameworks some of the coding may be a little rough around the edges as I learnt
a lot after the initial creation of the Core framework. Additionally, there are a couple of historic warnings in the core build which 
I needed to leave for now to ensure I got this in on time!_

### Automation Tests
#### Running Automation Tests
To run the Automation Tests please complete the following steps:

Prerequistes:
1. Java 11 Installed
2. Maven Installed
3. Latest Chrome(113) or Firefox

With the above setup complete navigate to the repository in your preferred command line.

To run all tests:
```mvn clean install -Ptest ```












I created 6 Automation scenarios covering what I believe are some of the basic functionality and the most non-deterministic 
sections of the game. Due to this being blackbox testing without a defined set of expected actions some assumptions have been 
made during some of the testing (I will address these points individually)

_Note: There are some that fail gracefully in here when they hit the bug, I left these in to showcase some of the reporting functionality_

1. [FAILS] MustNotStartGameWithEmptyName - Attempts to start a game with the username field empty. 
2. [PASS]  MustAddOneToCookieCountForEachClick - Validates that when clicking the 'Click Cookie' button a single cookie is added to the 'Cookies' counter
3. [PASS] GameMustPersistAfterNavigatingToMainPage - Enter game, add some cookies and stuff and then go to the homepage, validate the score and then reenter the game
4. [PASS] MustAddCorrectMoneyWhenCookiesAreSold - Validates that when selling a cookie the correct amount (25c) is added to the money field.
5. [FAILS] MustNotBeAllowToStartGameWithNameAlreadyPresent - Validates that when you attempt to start a game with a name already present in the list it will not allow you
6. [FAILS] MoneyMustNotGoBelowZero - Validates that a users money cannot go below zero.

Note: For number 5 - this could be the wrong way round depending on the actual requirements of the game. 
Personally I'd assume that'd not be a great way to do it depending on how as a platform it works globally. 
However this case/automation test could be inverted in a couple of seconds if this is the correct functionality.

