# Maybank Delivery & Ops CFS & Core Banking (Java Backend) - Technical Assessment

This project is a technical assessment for the Java backend engineer role, the assessment contains the following requirements:
1. Project Structure is required for ease of maintainability and readability.
2. Explore API for Client (Example: to be test via Postman. Please provide the Postman Collection.)
3. Each API required to log REQUEST & RESPONSE info into logs file.
4. Able to connect to a database, preferred MSSQL database (Using Local Machine DB,
   DB name: TESTDB). 
5. @Transactional is required in the project.
6. Explore 1 GET method api with Pagination (Each Page 10 records)
7. Explore an API which will nested calling another api from 3rd party.

## Getting Started

To explore the API service, import the Postman collection script `MayBank-Assessment.postman_collection.json` sit in the project root folder to your Postman.
## Authentication

The current stage of the project does not adopt a full security to protect the API endpoints, only a not-so-valid token check for every request.

**Step**:
Simply add `X-API-TOKEN: encryptedJSONWebToken` in the headers of the request to bypass the authentication check.

## Endpoints

This section documents individual API resources.

---

### `[GET]` `[http://localhost:8081/api/customers]`

**Description:**
List all customers with pagination and sorting

#### Parameters

A table detailing the request parameters.


| Name   | Type      | Required | Description                                                           |
|:-------|:----------|:---------|:----------------------------------------------------------------------|
| `page` | `integer` | `No`     | `Set the offset of the pagination`                                    |
| `size` | `integer` | `No`     | `Set the size of each page`                                           |
| `sort` | `string`  | `No`     | `Specify the field (id, name) and order (asc, desc) separated by ","` |

#### Example
http://localhost:8081/api/customers?sort=name,desc&page=1&size=20

### `[POST]` `[http://localhost:8081/api/customers]`

**Description:**
Create a new customer

#### Parameters

A table detailing the request body payload.

```json
{
    "name": "Harry Hemsworth",
    "email": "h.hemsworth@example.com",
    "mobile": "+601234569890"
}
```

### `[GET]` `[http://localhost:8081/api/users]`

**Description:**
List all users fetched from an external service (https://jsonplaceholder.typicode.com/users) with pagination only

#### Parameters

A table detailing the request parameters.


| Name   | Type      | Required | Description                                                           |
|:-------|:----------|:---------|:----------------------------------------------------------------------|
| `page` | `integer` | `No`     | `Set the offset of the pagination`                                    |
| `size` | `integer` | `No`     | `Set the size of each page`                                           |

#### Example
http://localhost:8081/api/users?page=1&size=20