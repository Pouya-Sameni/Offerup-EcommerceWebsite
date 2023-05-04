# AccountServiceEPs REST API Endpoints

This repository provides the implementation of the following REST APIs for user accounts.

## Endpoints

| Endpoint | Method | Description |
| -------- | ------ | ----------- |
| `/accounts/signin` | `POST` | Authenticates a user using their login credentials and returns a JWT token. |
| `/accounts/signup` | `POST` | Registers a new user with the provided user details and returns a JWT token. |

## Usage

### `/accounts/signin`

Authenticates a user using their login credentials and returns a JWT token.

```http
POST /accounts/signin

{
    "usename": "pouyasameni",
    "password": "pouyapass"
}


========
Response
========

If the authentication is successful, the endpoint returns an HTTP 200 OK status code and a JWT token.

"eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c"

If the authentication fails, the endpoint returns an HTTP 401 Unauthorized status code and an error message.


"Incorrect Credentials"

POST /accounts/signup

Registers a new user with the provided user details and returns a JWT token.

POST /accounts/signup

========
Request
========

{

  "email": "pouya@york",
  "password": "password"
  "username": "pouyasameni",
  "firstName": "pouya",
  "lastName": "sameni",
  "address": {
    "address": "11 No where rd",
    "country": "Canada",
    "postalCode": "A1B2C3",
    "province": "Ontario",
    "city": "Toronto"
  }
}

========
Response
========

If the registration is successful, the endpoint returns an HTTP 200 OK status code and a JWT token.


"eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c"

If the registration fails, the endpoint returns an HTTP 404 Not Found status code and an error message.

"Incorrect Credentials"

