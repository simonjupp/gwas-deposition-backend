# Import base image
FROM openjdk:8u212-jre

# Create log file directory and set permission
RUN groupadd -r gwas-deposition-backend && useradd -r --create-home -g gwas-deposition-backend gwas-deposition-backend
RUN if [ ! -d /var/log/gwas/ ];then mkdir /var/log/gwas/;fi
RUN chown -R gwas-deposition-backend:gwas-deposition-backend /var/log/gwas

# Move project artifact
ADD target/deposition-app-backend-*.jar /home/gwas-deposition-backend/
USER gwas-deposition-backend

# Launch application server
ENTRYPOINT exec $JAVA_HOME/bin/java $XMX $XMS -jar -Dspring.profiles.active=$ENVIRONMENT /home/gwas-deposition-backend/deposition-app-backend-*.jar