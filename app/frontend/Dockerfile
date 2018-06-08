###############################
##### Endava Techflow 2018 ####
###############################
#This is the frontend Dockerfile. We chose to copy the node_modules from the repo for time limits.

FROM node:9-slim
USER root
WORKDIR /home/frontend
COPY . .

#Added a fix for react init scripts (https://github.com/facebook/create-react-app/issues/200)
RUN ln -sf /home/frontend/node_modules/react-scripts/scripts /home/frontend/node_modules/scripts
RUN chown -R node /home/frontend
RUN chmod u+x /home/frontend/node_modules/.bin/react-scripts

USER node

EXPOSE 3000
CMD [ "npm", "start" ]
