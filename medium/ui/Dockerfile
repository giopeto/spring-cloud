FROM node:alpine

# Create app directory

RUN mkdir -p /usr/src/app

WORKDIR /usr/src/app

# Bundle app source
COPY . /usr/src/app

# Install AngularCLI
RUN npm install -g @angular/cli

# Install app dependencies
COPY package.json /usr/src/app/
COPY .angular-cli.json /usr/src/app/

RUN npm install

#Expose ports and start application
EXPOSE 4200

CMD [ "npm", "start" ]