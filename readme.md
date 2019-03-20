

# README #

This API offers Geo Data to internal Dexma Consumers.

It should be called via RESTful API standards.

### Build
* 'mvn clean compile -Pdev'

### Create Artifact 
* 'mvn clean package -Pdev'

### Install Artifact
* 'mvn clean install -Pdev'

### for Run Rest API with Profile
* This Api must be run with environment profile. In case of local running, the profile must be 'dev'.
* The environment profile triggers different Api Port, or Context path which depends on each environment.
  * To run please execute: 'mvn spring-boot:run -Dspring-boot.run.profiles=dev|pre|prod'

### properties
* In dev environment, used properies are in the file `application-dev.yml` triggered with Maven Profile or Spring Boot Profile.
* In pre and prod environment, used properies are External Application, triggered throught run parameters when application is run.
* In pre and prod environments, the running commands must be: 
  * java -jar geo-api-1.0.0-SNAPSHOT.jar --spring.config.location=/etc/<application_path>/application-config.yaml