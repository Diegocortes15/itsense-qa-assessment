# IT Sense QA Assessment

## Dependencies used

- Selenium 4
- TestNG
- Log4J2
- Allure
- JsonSimple

## Prerequisites to run the project

- [JDK 11](https://www.oracle.com/co/java/technologies/javase/jdk11-archive-downloads.html) (Environment variable)
- [Maven](https://maven.apache.org/download.cgi) (Environment variable)
- [Allure](https://docs.qameta.io/allure-report/#_installing_a_commandline) (Environment variable)
- [Docker](https://docs.docker.com/get-docker/) (Optional if you want to run remotely)
- IDE (ex. [IntelliJ](https://www.jetbrains.com/idea/download/#section=windows))

### How set environment variables

1. Download and save the folders where you want. Look at the links above.
2. Take in mind the `path` where you are saving your folders **that contains **jdk**.
3. Copy jdk path.
4. On windows, in search bar, type `environment variables` and click on `Edit the system environment variables`.

![environment variables](https://user-images.githubusercontent.com/60171460/157496931-f1d25ccc-66c8-4608-9a3b-24fd2411a920.gif)

5. In the `System properties` window, click `environment variables`.
6. In `Environment variables` window in `Advanced tab`, in `System variables` section, double click `Path`.
7. In `Edit environment variable` click `New` button and paste the **path that contains jdk**, then click `Ok`.
8. Close the open windows and that's all to install JDK 🥳.

![environment variables (2)](https://user-images.githubusercontent.com/60171460/157497327-09035824-bc66-4f1f-ad66-a92690bf4313.gif)

#### Browsers

Installed:
- Chrome
- Firefox
- Microsoft Edge


## Download and open project

### Workaround 1

#### Download project

1. Click on the code button in this repository
2. Select the Download Zip option
3. Extract the .zip file with the **Extract here** option
4. Place the project folder on the desired location

### Workaround 2 - Git-bash

1. Select the folder when you would like clone the project
2. Open git bash, copy and paste the following command

```bash
git clone https://github.com/Diegocortes15/itsense-qa-assessment.git
```

![image](https://github.com/Diegocortes15/itsense-qa-assessment/assets/60171460/7bd79a83-24d4-477d-b118-21b259d8d5c7)

## Run project ⚠

Open the project with the desired IDE, then run the following command.

### Local ⚠

To run the project locally you make sure that in ```App Class``` the ```PLATFORM``` variable must be set as ```local```
then in folder project you can run the following command.

```bash
mvn clean verify
```

### Remote ⚠
> **Note**
> You need [Docker](https://docs.docker.com/get-docker/) installed on your machine to run the following commands!

To run the project remotely you make sure that in ```App Class``` the ```PLATFORM``` variable must be set as ```remote```
then you must run the docker container with the following command

```bash
docker-compose -f docker/docker-compose.yml up
```

Then in another terminal you must use the following container to run our tests

```bash
mvn clean verify
```

Once our tests have finished you must run the following command to stop our docker container

```bash
docker-compose -f docker/docker-compose.yml down
```

## Open allure report

To open allure report run the following command

```bash
allure serve target/allure-results
```

![isAssessment](https://github.com/Diegocortes15/itsense-qa-assessment/assets/60171460/936be48a-5475-4ed2-b115-30d1a80aacd4)

## Open Artifacts

In our Github Actions or Azure DevOps you can see some details where you can find artifacts related with the test execution

1. Allure Report (Remember open it with a server like [Live Server](https://marketplace.visualstudio.com/items?itemName=ritwickdey.LiveServer))
2. Specs (Here you can find the text scraped from the header tag)
3. Logs (Information related with test execution)
