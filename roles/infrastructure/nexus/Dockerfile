FROM sonatype/nexus3:3.3.2

USER root

COPY ensure-repos-created.sh ${NEXUS_HOME}/
COPY create-docker-repo.json ${NEXUS_HOME}/
RUN chmod +x ${NEXUS_HOME}/ensure-repos-created.sh

USER nexus

# Port of the Docker repo connector
EXPOSE 5000

CMD bash -c "nohup /opt/sonatype/nexus/ensure-repos-created.sh & /opt/sonatype/nexus/bin/nexus run"
