FROM jboss/wildfly:13.0.0.Final

LABEL maintainer="adam.smakulski@gamil.com"

COPY /web-app/target/web-app.war /opt/jboss/wildfly/standalone/deployments/
COPY /.currencyDataFiles /opt/jboss/.currencyDataFiles
RUN /opt/jboss/wildfly/bin/add-user.sh cm pass123 --silent

CMD ["echo Waiting 5 seconds"]
CMD ["sleep", "5"]
CMD ["echo Starting Wildfly"]
CMD ["/opt/jboss/wildfly/bin/standalone.sh", "-b", "0.0.0.0", "-bmanagement", "0.0.0.0"]