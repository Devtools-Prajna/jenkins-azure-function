FROM mcr.microsoft.com/azure-functions/java:4-java11

WORKDIR /home/site/wwwroot

COPY target/azure-functions/javaapp/ .

EXPOSE 80

