FROM node:14 as build
WORKDIR /app
COPY package*.json ./
RUN npm install
COPY . .
RUN npm run build

FROM node:14-alpine
WORKDIR /app
COPY --from=build /app/build ./build
EXPOSE 3000
CMD ["npx", "http-server", "-p", "3000", "./build"]
