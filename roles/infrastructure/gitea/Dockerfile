FROM gitea/gitea:1.4

EXPOSE 3000 5022

COPY autosetup-gitea.sh /etc/s6/autosetup-gitea/run
RUN chmod 755 /etc/s6/autosetup-gitea/run