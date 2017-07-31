# Cloud Native Developer Training Course
In this course we will create a simple Cloud Native Architecture. In completing this task key technologies and cloud native patterns will be reviewed.

# Prerequisites For Labs

1. Has completed PCF Developer training
2. Java JDK 8 Installed
3. STS or IntelliJ
4. Have git installed

## Java 8 JDK Download

http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html

## IntelliJ

https://www.jetbrains.com/idea/

## STS Windows 64 Bit:

http://download.springsource.com/release/STS/3.8.4.RELEASE/dist/e4.6/spring-tool-suite-3.8.4.RELEASE-e4.6.3-win32-x86_64.zip

## Mac 64 Bit

http://download.springsource.com/release/STS/3.8.4.RELEASE/dist/e4.6/spring-tool-suite-3.8.4.RELEASE-e4.6.3-macosx-cocoa-x86_64.tar.gz

## Key Technologies and Patterns Reviewed

Through out the course the following will be covered:

1. Spring Boot Overview
2. Gradle Quick Start
3. Testing in Spring Boot
4. Using Repositories To Work With Data
5. Externalizing Configurations and Working With Profiles
6. Review of Working With Data In PCF
7. Basic Security with Spring Boot and introduction to OAuth in PCF
8. Adding Fault Tolerance
9. Overview of Messaging In The Cloud

## What This Course Is Not

This course is designed to reinforce concepts already reviewed and learned and give a direction on where to go to learn more. This course is for developers who will be building and running applications on Pivotal Cloud Foundry.

## Reference Application

![Architecture](/images/architecture.png)


# Set Up 

## Gradle

For this course Gradle will be the build tool and dependancy management tool of choice. Maven will not be an option.

First install Gradle on your machine:

https://gradle.org/install

In STS Gradle support needs to be added. At the time of writing this Gradle Buildship was not working with STS. Instead classic Gradle was installed.

Get the dashboard open.

![Architecture](/images/setup-1-find-dashboard.png)

Select the IDE Extensions

![Architecture](/images/setup-2-ide.png)

Select the Gradle Classic Extension

![Architecture](/images/setup-3-classic-gradle.png)

## Lombok

Lombok makes creating domain objects easy. Rather than coding all the boiler plate constructors and methods we normally need, with Lombok we can create JPA Entity Bean with everything we need like this:

```java

@Entity
@Table(name="product")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter @Setter
    private Long id;
    
    @Getter @Setter
    private String name;
    
    @Getter @Setter
    private Double price;
    
    @Getter @Setter
    private String description;
    
    @Getter @Setter
    private Integer quantity;

}

```
To get Lombok running we need to do two things:

1. Install it into the IDE (https://projectlombok.org/download.html)
2. Add it as a Gradle dependancies: compileOnly "org.projectlombok:lombok:1.16.14"

## Running This Application

The application can be ran locally (running Spring Boot or PCF Dev) on in a hosted PCF (in this case PWS).

## PWS Account

To get the full Cloud Native experience, create an account on PWS for free to complete these labs. This is PCF running in AWS:

http://run.pivotal.io/

## PCF Dev

You can develop locally against PCF Dev while providing a similar development experience as PWS. It does require 6GB of memory to be free:

https://pivotal.io/pcf-dev

To run this application on locally or in PWS the following Services are required.

### Postgres

#### Development
One of our services is backed with a relation DB. This can be installed locally without much pain:

https://www.postgresql.org/download/

#### Cloud

PWS's market place offers Postgres (Elephant SQL). PCF Dev only offers MySQL, due to the way we develop our applications the SQL will not have to change. PWS will take care of puttting in the correct DB driver, for PCF Dev the application package will need to contain the correct driver

### Rabbit MQ

Our services use Rabbit MQ to communicate.

#### Development

Rabbit can be installed locally:

https://www.rabbitmq.com/download.html

It also can be obtained via Docker:

To start rabbit on the Docker images:

docker run -d -p 15672:15672 -p 5672:5672 rabbitmq:3.6.6-management

#### Cloud

PWS's market place offers Rabbit as does PCF Dev

### Redis

Redis is used as a system of record for the Order service.

#### Development

Redis can be installed locally:

https://redis.io/topics/quickstart

There is also a Docker image:

https://hub.docker.com/_/redis/

#### Cloud

PWS market place offers a Redis services





