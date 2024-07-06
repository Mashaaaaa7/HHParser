FROM node:21.6.1
WORKDIR /home/node
COPY . .
RUN yarn install --ignore-scripts
RUN yarn run build
RUN rm -rf src
ENTRYPOINT yarn run start --port $PORT
