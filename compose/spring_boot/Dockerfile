### Build Image ###
FROM maven:3.8.5-openjdk-17 AS build
ARG BUILD_FOLDER_NAME=build_project

RUN mkdir /$BUILD_FOLDER_NAME

COPY ./pom.xml /$BUILD_FOLDER_NAME/pom.xml

WORKDIR /$BUILD_FOLDER_NAME
# build all dependencies for offline use
RUN mvn dependency:go-offline -B

COPY ./src /$BUILD_FOLDER_NAME/src

RUN mvn package -DskipTests


### Runtime Image ###

FROM azul/zulu-openjdk-alpine:17.0.6-17.40.19-jre
ARG BUILD_FOLDER_NAME=build_project
ARG APP_FOLDER_NAME=app
ARG USERNAME=java_user

RUN apk add dumb-init \
    && rm -rf /var/cache/apk/*  \
    && mkdir /$APP_FOLDER_NAME

RUN addgroup --system $USERNAME \
    && adduser -S -s /bin/false -G  $USERNAME $USERNAME

COPY --from=build /$BUILD_FOLDER_NAME/target/store-0.0.1-SNAPSHOT.jar /$APP_FOLDER_NAME/application.jar

WORKDIR /$APP_FOLDER_NAME

RUN chown -R $USERNAME:$USERNAME /$APP_FOLDER_NAME/

USER $USERNAME

ENTRYPOINT ["/usr/bin/dumb-init", "--"]

CMD ["java", "-jar", "application.jar"]