FROM node:19 AS build

WORKDIR /app
COPY package*.json .
RUN npm install

COPY . .
RUN npm run build 

FROM nginx AS run
COPY --from=build /app/dist/ /usr/share/nginx/html/
COPY nginx.conf /etc/nginx/conf.d/default.conf