# API Documentation

## Auth Service

- POST `/api/auth/register`
- POST `/api/auth/login`

## User Service

- GET `/api/users`
- GET `/api/users/{id}`
- POST `/api/users`
- PUT `/api/users/{id}`
- DELETE `/api/users/{id}`
- PUT `/api/users/{id}/roles`
- PUT `/api/users/{id}/status`

## CMS Service

- GET `/api/cms/pages`
- GET `/api/cms/pages/{id}`
- GET `/api/cms/pages/slug/{slug}`
- POST `/api/cms/pages`
- PUT `/api/cms/pages/{id}`
- DELETE `/api/cms/pages/{id}`
- PUT `/api/cms/pages/{id}/publish`
- PUT `/api/cms/pages/{id}/unpublish`

## Event Service

- GET `/api/events`
- GET `/api/events/upcoming`
- GET `/api/events/{id}`
- GET `/api/events/slug/{slug}`
- POST `/api/events`
- PUT `/api/events/{id}`
- DELETE `/api/events/{id}`

## Media Service

- GET `/api/sermons`
- GET `/api/sermons/{id}`
- POST `/api/sermons`
- PUT `/api/sermons/{id}`
- DELETE `/api/sermons/{id}`
- GET `/api/livestreams/active`
- POST `/api/livestreams`
- PUT `/api/livestreams/{id}`
- DELETE `/api/livestreams/{id}`
- GET `/api/gallery`
- POST `/api/gallery`
- PUT `/api/gallery/{id}`
- DELETE `/api/gallery/{id}`

## Interaction Service

- POST `/api/prayer-requests`
- GET `/api/prayer-requests`
- PUT `/api/prayer-requests/{id}/status`
- POST `/api/contact-messages`
- GET `/api/contact-messages`
- PUT `/api/contact-messages/{id}/status`
- GET `/api/announcements`
- POST `/api/announcements`
- PUT `/api/announcements/{id}`
- DELETE `/api/announcements/{id}`
