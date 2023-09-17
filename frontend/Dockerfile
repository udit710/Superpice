# Define the base image with a specific version of node for consistency
FROM node:20-alpine AS base

# Set the working directory
WORKDIR /app

# Install system dependencies
RUN apk add --no-cache libc6-compat

# Install production dependencies
FROM base AS deps
COPY package*.json ./
RUN npm ci --production

# Build the application
FROM base AS builder

# Create a non-root user for security in the builder stage
RUN addgroup -S nodejs && adduser -S reactjs -G nodejs

# Change ownership of the /app directory in the builder stage
RUN chown -R reactjs:nodejs /app

# Switch to the non-root user for the builder stage
USER reactjs

COPY --from=deps /app/node_modules ./node_modules
COPY . .
RUN npm run build

# Prepare the runner image using Nginx
FROM nginx:alpine AS runner

# Set the working directory
WORKDIR /usr/share/nginx/html

# Remove default nginx static assets
RUN rm -rf ./*

# Copy static assets from builder stage
COPY --from=builder /app/build .

# Containers run nginx with global directives and daemon off
ENTRYPOINT ["nginx", "-g", "daemon off;"]
