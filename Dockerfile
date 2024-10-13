FROM maven:3-eclipse-temurin-17 as builder
RUN mkdir -p /build
WORKDIR /build
COPY pom.xml /build
RUN mvn -B dependency:resolve dependency:resolve-plugins
COPY src /build/src
RUN mvn package

FROM tomcat:9-jdk17-temurin as integrate
COPY --from=builder /build/target/ROOT.war /usr/local/tomcat/webapps/