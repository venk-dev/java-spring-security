# java-spring-security

* This application demonstrates adding Security (HTTPS + Basic Auth with hashed password) using Spring Security.

* Dockerfile has been added to create a docker image such that the application can be run as a standalone docker container.
* Spring Security configuration has been added. Please check the source com.venk.org.tutorials.config directory.
* Security configuration has been added in application.properties file.
* Skeleton code for tutorial CRUD operations is taken from https://github.com/bezkoder/spring-boot-h2-database-crud

* HTTPS Enablement
    * Add below environment variables in the env file to be used with "docker run" command. For HTTPS, port 8443 is used.
    * In case, KEY_PASS value is not provided, KEYSTORE_PASS value is used instead. Both the values need to be the same in that case.
      > KEYSTORE=KEYSTORE_VALUE
      KEYSTORE_PASS=KEYSTORE_PASS_VALUE
      KEYSTORE_TYPE=KEYSTORE_TYPE_VALUE
      KEY_ALIAS=KEY_ALIAS_VALUE
      KEY_PASS=KEY_PASS_VALUE
      SERVER_PORT=8443
    * Run the docker image using env variable HTTPS_MODE with true value to enable HTTPS.
      > docker run -v <host_directory>:/spring-app-data -p 8443:8443 -e HTTPS_MODE=true --env-file <host_directory>/env.list -it <image_tag>

* Basic Authentication Enablement
    * Add below environment variables in the env file to be used with "docker run" command.
      > REQUEST_USERNAME=user1
      REQUEST_PASSWORD=hashed password
    * Run the docker image using env variable ENABLE_BASIC_AUTH with true value to enable Basic Authentication.
      > docker run -v <host_directory>:/spring-app-data -p 8080:8080 -e ENABLE_BASIC_AUTH=true --env-file <host_directory>/env.list -it <image_tag> 


* HTTPS + Basic Authentication Enablement
    * Run the docker image using env variable ENABLE_BASIC_AUTH with true value and env variable HTTPS_MODE with true value.
      > docker run -v <host_directory>:/spring-app-data -p 8443:8443 -e ENABLE_BASIC_AUTH=true -e HTTPS_MODE=true --env-file <host_directory>/env.list -it <image_tag> 