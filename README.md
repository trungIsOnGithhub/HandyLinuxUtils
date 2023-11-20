#### Car rent Java EE web application

#### Table of contents


Car rent application studying project.

####	Preparing required instruments
#### <a name="mysql"></a>	Installing MySQL

The application requires MySQL version 5.7 and higher.

[MySQL distributive](https://dev.mysql.com/downloads/mysql/)

[MySQL install instruction](https://dev.mysql.com/doc/refman/8.0/en/installing.html)

### <a name="jdk"></a>	Installing Java Development Kit

The application requires Java Development Kit version 8 or Higher (1.8)

[JDK 8 download page](https://wiki.openjdk.org/display/jdk8u/Main)

[JDK install instruction](https://docs.oracle.com/javase/8/docs/technotes/guides/install/index.html)

### <a name="tomcat"></a>	Installing Tomcat
The application has been tested in Tomcat 8.5
Download and install Tomcat 8.5

[Tomcat 8.5 download page](https://tomcat.apache.org/download-70.cgi)

[Tomcat install instruction](https://tomcat.apache.org/tomcat-8.5-doc/setup.html)

### <a name="tom_dir"></a>    Preparing a deployment directory

Remove or move the content of tomcat/webapps/ROOT to another directory.

### <a name="git"></a>	Installing Git version control system

Git installation is optional, in order to simplify downloading application from git-hub.

[Git download page](https://git-scm.com/downloads)

### <a name="maven"></a> Installing maven build automation system

Maven 3.3 build automation system is required to build, test and deploy application.

[Maven download page](https://maven.apache.org/download.cgi)

[Maven install instruction](https://maven.apache.org/install.html)


## Installing, building, preparing to run
### <a name="clone"></a>	Downdloading the application
  
### <a name="import"></a>	Database dump deploying

To deploy the database dump type in command line:
	
	mysql -u root -p < project path/db_dump.sql

Then input the root user's password. Database scheme will be created automatically.

To deploy the test database dump (whether is required to successfully build the project) input into command line:
	
	mysql -u root -p < project path/test_db_dump.sql
	
Then input the root user's password. Database scheme will be created automatically.	

### <a name="setup_db"></a>	Database user creating

The application uses default database username car_rent_app (and password Un3L41NoewVA).
To create the user run the mysql terminal and input:
	
	CREATE USER 'car_rent_app'@'localhost' IDENTIFIED BY 'Un3L41NoewVA';

If you want to use another username and password, you have to specify them to [database properties file](#set_param)

Use the following command to grant to the user access to the database (scheme):
    
    GRANT SELECT, INSERT, UPDATE, DELETE ON * . * TO 'car_rent_app'@'localhost';
