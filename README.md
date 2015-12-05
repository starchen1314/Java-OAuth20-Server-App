# Java-OAuth20-Server-App
A Java implementation of an OAuth 2.0 web application server

This proejct is my own solution to user authentication within my own RESTful services.

## Project Highlights
  - Out of the box solution for maintaining and authenticating users
	- Uses annotations for configuration
		- Minimal configuration through web.xml
	- REST Client 
		- RESTEasy
		- org.jboss.resteasy : v3.0.11
	- Dependency Injection
		- Google Guice
		- com.google.inject : guice : v3.0
	- Commons Lang
		- org.apache.commons : commons-lang3 : v3.3.2
	- Logging
		- Log4j
		- org.apache.logging.log4j : v2.1

# Usage Instructions to Pull down and use template project from GitHub
1. From command line retrieve project from GitHub
	- git clone https://github.com/DEV3L/Java-OAuth20-Server-App.git

2. Run maven package from inside the created directory
	- mvn clean package

3. Java-OAuth20-Server-App.war can be deployed to enterprise container of choice

4. Use Maven to resolve the dependencies and create eclipse dynamic web project and class/project files
	- mvn eclipse:eclipse -Dwtpversion=2.0

5. Import the project into Eclipse as an existing project

-- Project was copied from RESTfulCRUDDev3l WS Template - More functionality to come
