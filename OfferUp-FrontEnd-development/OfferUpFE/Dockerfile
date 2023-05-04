# Use a Node.js image as the base
FROM node:16

# Set the working directory
WORKDIR /OfferUpFE

# Copy the rest of the app's files into the container
COPY src /OfferUpFE/src

COPY public /OfferUpFE/public


COPY package*.json /OfferUpFE/

COPY *.js /OfferUpFE/
COPY *.cjs /OfferUpFE/
COPY index.html /OfferUpFE/

# Install the app's dependencies
RUN npm install --force

# Build the app for production
RUN npm run build

EXPOSE 3000
# Set the command to run the app
CMD [ "npm" , "start"]
