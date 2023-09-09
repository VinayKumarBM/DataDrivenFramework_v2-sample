# DataDrivenFramework-sample

## **Overview:**

Data Driven framework is focused on separating the test scripts logic and the test data from each other. This allows us to create test automation scripts by passing different sets of test data. The test data set is kept in the external files or resources such as MS Excel Sheets, MS Access Tables, SQL Database, XML files etc., The test scripts connect to the external resources to get the test data. This framework significantly reduces the number of test scripts compared to a modular based framework when we need to test for multiple sets of data for same functionality.

For Demo purpose all the test cases are created for [Demo Web Shop](http://demowebshop.tricentis.com/) site.

### **Some of the key features of this framework:**

1. Generates Extent & Allure reports with all the step details.
2. Support parallel execution of test cases.
3. Generates test execution log file.
4. Test execution can be triggered form command line.
5. Easy integration to CI/CD pipeline like Jenkins.
6. Framework uses Page Object Design Pattern, hence there is clean separation between test code and page specific code such as locators and layout.
7. Supports re-run of failed test cases.
8. Allows to control the tests to be run using sheet (Sanity/Regression) in TestData.xlsx sheet.

## **Required Setup :**

- [Java v17](https://java.tutorials24x7.com/blog/how-to-install-java-17-on-windows) should be installed and configured.
- [Maven](https://mkyong.com/maven/how-to-install-maven-in-windows/) should be installed and configured.
- Download the files from Git repository either as zip file OR using [Git](https://phoenixnap.com/kb/how-to-install-git-windows).
- Download and install [Allure](https://docs.qameta.io/allure/#_manual_installation) command line application, suitable for your environment.

## **Running Test:**
All the test to be executed can be configured in TestData.xlsx sheet placed in below path.<br><br>
src\test\resources\data\TestData.xlsx<br><br>
List all the tests to be executed in Sanity or Regression sheet. Update the config.properties file sheet parameter with sheet (Eg: Regression/Sanity) that needs to be executed.

Open the command prompt and navigate to the folder in which pom.xml file is present.
Run the below Maven command.

    mvn clean test -Dthreads=10

This will run 10 test cases in parallel (default thread count is 1).

You can also change the execution sheet at run time by using set command as shown below. This will override the sheet value in config.properties file.

    set sheet=Sanity && mvn clean test -Dthreads=10


Once the execution completes report will be generated in below folder structure.

**Extent Report:** 	./target/reports/extent/index.html

**Allure Report:** To generate the report we need to go through below steps.

To generate the report from existing Allure results use below command.

    allure generate allure-results -c -o target/reports/allure

After the report is generated, open it in system browser using below command.

    allure open allure-report

**Execution Log:**  Logs generated at the time of test execution will be available at ./target/logs/automation.log