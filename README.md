# Description:
A **_Spring-Boot-Reactor_** project [**_Project Reactor-> Non-Blocking Spring-Boot_**] capable of _sending Emails without opening Gmail_. Emails will be sent in proper _Email Templates and Values in the templates change with change in data_. Email Templates are done with **Thymeleaf**__ Template Engine. **_MongoDB_** is used in the Database side. Emails can be sent to Multiple IDs, and also with Attachments
# Steps:
  1] download this repository
  2] open Terminal/CMD and give the following command =>
      **_mvn clean validate package verify install -DskipTests_**
      **_mvn clean install -DskipTests_**
      you can add **-X** to setup everything in the **DEBUG** mode
      **-DskipTests** is to skip Tests while Installing
