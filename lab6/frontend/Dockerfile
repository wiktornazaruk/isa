# Step 1: Build angular app using production config
FROM node:22.12.0 AS build
# Set working directory
WORKDIR /app
# Copy the package.json and package-lock.json files
COPY package*.json ./
# Install dependencies
RUN npm install
# Instal Angular cli globally
RUN npm install -g @angular/cli
# Copy all the files
COPY . .
# Build the app
RUN ng build --configuration=production

# Step 2: Use nginx image to serve the app
FROM nginx:latest
# Set working directory
WORKDIR /usr/share/nginx/html
# Copy build output to replace the default nginx contents
COPY --from=build /app/dist/frontend/browser ./
COPY nginx.conf /etc/nginx/conf.d/default.conf
# Expose port 80
EXPOSE 80

# Build: docker build -t frontend .
# Run: docker run -d -p 4200:80 frontend
