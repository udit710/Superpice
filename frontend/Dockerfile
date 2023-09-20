FROM node:20-alpine AS base

FROM base AS deps
RUN apk add --no-cache libc6-compat
WORKDIR /app

COPY package*.json ./
RUN npm ci


FROM base AS builder
WORKDIR /app
COPY --from=deps /app/node_modules ./node_modules
COPY . .

RUN npm run build

FROM base AS runner
WORKDIR /app

ENV NODE_ENV production

RUN npm install -g serve

RUN addgroup --system --gid 1001 nodejs
RUN adduser --system --uid 1001 reactjs
USER reactjs

# for hosting dev server (using npm start)
# COPY --from=builder --chown=reactjs:nodejs /app .

# for hosting build folder (using serve -s)
COPY --from=builder /app/package.json ./package.json
COPY --from=builder --chown=reactjs:nodejs /app/build .


EXPOSE 3000

ENV PORT 3000

# for hosting dev server (using npm start)
# CMD ["npm", "start"]

# for hosting build folder (using serve -s)
CMD ["serve", "-s"]
