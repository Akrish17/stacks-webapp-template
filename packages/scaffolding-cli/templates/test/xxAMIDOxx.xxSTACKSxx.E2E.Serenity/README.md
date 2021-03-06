## CONFIGURATION

### Configure the `serenity.conf` file:

The following properties for different environments are required in the ``src/test/resources/serenity.conf`` configuration file.
- webdriver.base.url
- api.base.url

#### Enable Authorisation

To enable the authorisation please set the`generate.auth0.token` value to true if authorisation token is requested,
and set environment variables for  authorisation details like: CLIENT_ID, CLIENT_SECRET, AUDIENCE, GRANT_TYPE, OAUTH_TOKEN_URL.

### WebDriver Downloads

If you wish to use a local instance of Serenity Webdriver, be sure to download the WebDriver file for the browsers with which you wish to perform cross browser testing from the links mentioned below:

| BROWSER	| DOWNLOAD LOCATION 											|
| -------	| -----------------												|
| Opera		| https://github.com/operasoftware/operachromiumdriver/releases	|
| Firefox	| https://github.com/mozilla/geckodriver/releases				|
| Chrome	| http://chromedriver.chromium.org/downloads					|
| Internet Explorer	| https://github.com/SeleniumHQ/selenium/wiki/InternetExplorerDriver |
| Microsoft Edge	| https://blogs.windows.com/msedgedev/2015/07/23/bringing-automated-testing-to-microsoft-edge-through-webdriver/	|


### Configure the `serenity.properties` file:

```serenity.take.screenshots``` - Set this property to have more finer control on how screenshots are taken, default is serenity.take.screenshots=BEFORE_AND_AFTER_EACH_STEP This property can take the following values:

- FOR_EACH_ACTION: Saves a screenshot at every web element action (like click(), typeAndEnter(), type(), typeAndTab() etc.).
- BEFORE_AND_AFTER_EACH_STEP: Saves a screenshot before and after every step.
- AFTER_EACH_STEP: Saves a screenshot after every step
- FOR_FAILURES: Saves screenshots only for failing steps.
- DISABLED: Does not save screenshots for any steps.

```webdriver.driver``` - What browser do you want your tests to run in, for example firefox, chrome, phantomjs or iexplorer.

```webdriver.chrome.driver``` - Path to the Chrome driver, if it is not on the system path.

```headless.mode``` - This property allows running tests without a browser opening.

More properties here: https://serenity-bdd.github.io/theserenitybook/latest/serenity-system-properties.html

## Run Tests Locally

1. Open current folder in the terminal

2. Execute tests by running one of the following commands:

  a. Run all tests on the default environment: `mvn clean verify`

  b. Run all tests on the specific environment e.g. staging: `mvn clean verify -Denvironment=staging`

  b. Run Smoke tests only: `mvn clean verify -Dcucumber.options="--tags @Smoke"`

  c. Run Functional tests only: `mvn clean verify -Dcucumber.options="--tags @Functional"`

  d. Run tests by other tags and ignore tests that contain @Ignore tags:
  `mvn clean verify  verify -Dcucumber.options="--tags ~@Ignore --tags @YourTag"`

## Check the output report
As a result of the test execution - `serenity-maven-plugin` will automatically generate the test report with name - `index.html`.

Test Report Location is: `..target/site/serenity/index.html`

## Manual Aggregation of the Test Report
1. Open the `../E2E/Serenity` path in the terminal
2. Execute the `mvn serenity:aggregate` command

## Running tests in parallel threads - Using forks

Choosing the right forking strategy and parallel execution settings can have a substantial impact on the memory requirements and the execution time of the build system.
Using multiple forks can be a good alternative to running all tests in a single JVM, and can reduce the risk of certain types of errors.


Using Forked Test Execution, new JVM processes are spun up to execute the tests, up to a configurable maximum number of processes. This creates better separation between tests, which can improve their reliability.


Maven is set to spawn new processes by the `forkCount` configuration element, as shown here below. This can either be a number (the maximum number of forks) or a multiplier (the number of forks per CPU).
The current configuration uses the value of "2", which means 2 forked processes per CPU:
```
<plugin>
    <artifactId>maven-failsafe-plugin</artifactId>
    <version>2.22.2</version>
    <configuration>
        <parallel>classes</parallel>
        <threadCount>2</threadCount>
        <forkCount>2</forkCount>
    </configuration>
```

#### Note:
The more threads are used, the higher the chances of sporadic, hard-to-reproduce test failures due to timeouts and other related issues. The added performance gain of each thread also tends to drop off for higher numbers of threads.
