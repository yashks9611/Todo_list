# To-do Task List  API

This project provides a RESTful API for creating and managing to-do lists and their task.

## Table of Contents

- [To-do Task List  API](#to-do-task-list--api)
- [Table of Contents](#table-of-contents)
- [Introduction](#introduction)
- [Prerequisites](#prerequisites)
- [Installation](#installation)
- [Usage](#usage)
- [Endpoints](#endpoints)
    -   [Create a To-do list](#create-a-to-do-list)
    -   [Get all To-do list](#get-all-to-do-list)
    -   [Delete a To-do list](#delete-a-to-do-list)
    -   [Update a To-do list](#update-a-to-do-list)
    -   [Create a Task](#create-a-task)
    -   [Get all tasks in a list](#get-all-tasks-in-a-list)
    -   [Delete a task in a list](#delete-a-task-in-a-list)
    -   [Update a task in a  list](#update-a-task-in-a--list)
    -   [Complete a task in a list](#complete-a-task-in-a-list)
    -   [Change the priority a task in a list](#change-the-priority-a-task-in-a-list)
- [Contributing](#contributing)
- [License](#license)


## Introduction

This API is designed to perform create, read, update and delete to-do list and its associated tasks.

## Prerequisites

- Java
- Spring Boot
- IntelliJ
- Hibernate
- MapStruct API
- PostgreSQL Database

## Installation

1. Clone the repository.
2. Open the project in your preferred IDE.
3. Build the project using command `gradlew clean build`.
4. once the project is successfully built, run the application using `gradlew bootrun`.

## Usage

The API provides various endpoints for performing CRUD operations on to-do lists and their associated Task.  
I have also used hibernate to make the code database independent, clean and readable. Also
Hibernate provides for faster development and increased security. I have also used MapStruct API for
seamless conversion between entities and DTOs. It also reduces code complexity and make the code clean.

## Endpoints

### Create a To-do list

Create a new to-do list.

- **Endpoint:** `/todo/createList`
- **HTTP Method:** `POST`
- **Request Body:**
  ```json
  {
    "title" : "List 1",
    "description" : "Daily Task List"
  }
  ```
- **Response Body:**
  ```json
  {
    "listId": "1",
    "title": "List 1",
    "description": "Daily Task List"
  }
  ```
### Get all To-do list

Retrieve all to-do list.

- **Endpoint:** `/todo/getLists`
- **HTTP Method:** `GET`
- **Request Body:**
  None
- **Response Body:**
  ```json
  [
    { 	
      "listId": "1",
      "title": "List 1",
      "description": "Daily Task List"	
    },
    {
      "listId": "3",
      "title": "List 3",
      "description": "Grocery Shopping List"
    },
    {
      "listId": "2",
      "title": "List 2",
      "description": "House-work more"
    }
  ]
  ```

### Delete a To-do list

Delete a to-do list by its ID.

- **Endpoint:** `/todo/deleteList/{id}`
- **HTTP Method:** `DELETE`
- **Parameter:**
    - `id` *(path parameter)*: ID of the To-do list to delete.
- **Request Body:**
  None
- **Sample Request URL:** `localhost:8080/todo/deleteList/1`
- **Response Body:**
  ```json
  { 	
    "listId": "1",
    "title": "List 1",
    "description": "Daily Task List"	
  }
  ```
  
### Update a To-do list

Update a to-do list by its ID.

- **Endpoint:** `/todo/updateListDetails`
- **HTTP Method:** `PUT`
- **Request Body:**
  ```json
  {
    "listId": "1",
    "title" : "List 1",
    "description" : "Daily Workout Task List"
  }
  ```
- **Response Body:**
  ```json
  {
    "listId": "1",
    "title": "List 1",
    "description": "Daily Workout Task List"
  }
  ```
  
### Create a Task

Create a new task in a list.

- **Endpoint:** `/task/createTask`
- **HTTP Method:** `POST`
- **Request Body:**
  ```json
  {
    "title": "Task 1",
    "description": "Buy milk",	
    "priority": 1,
    "listId": 1
  }
  ```
- **Response Body:**
  ```json
  {	
    "taskId": "1",	
    "title": "Task 1",	
    "description": "Buy Milk",	
    "priority": 1,	
    "isComplete": false,	
    "listId": 1
  }
  ```
  
### Get all tasks in a list

Get all tasks in a to-do list by the ID of the to-do list.

- **Endpoint:** `/task/getTasks`
- **HTTP Method:** `GET`
- **Parameter:**
    - `listId` *(query parameter)*: ID of the list to retrieve tasks from. 
- **Request Body:**
  None
- **Sample Request URL:** `localhost:8080/task/getTasks?listId=1` 
- **Response Body:**
  ```json
  [	
    { 
      "taskId": "1",
      "title": "Task 1",
      "description": "Buy Milk",
      "priority": 1,
      "isComplete": false,
      "listId": 1
    },
    { 
      "taskId": "2",
      "title": "Task 2",
      "description": "Buy Eggs",
      "priority": 0,
      "isComplete": false,
      "listId": 1
    }
  ]
  ```

### Delete a task in a list

Delete a task in a list by its ID.

- **Endpoint:** `/task/deleteTask/{id}`
- **HTTP Method:** `DELETE`
- **Parameter:**
    - `id` *(path parameter)*: ID of the task to delete.
- **Request Body:**
  None
- **Sample Request URL:** `localhost:8080/task/deleteTask/2`
- **Response Body:**
  ```json
  { 
    "taskId": "2",
    "title": "Task 2",
    "description": "Buy Eggs",
    "priority": 0,
    "isComplete": false,
    "listId": 1
  }
  ```

### Update a task in a  list

Update a task in a list by its ID.

- **Endpoint:** `/task/updateTask`
- **HTTP Method:** `PUT`
- **Request Body:**
  ```json
  {
    "taskId": "1",
    "title": "Task 1",
    "description": "Buy Cheese",
    "priority": 2,
    "listId": 4
  }
  ```
- **Response Body:**
  ```json
  {
    "taskId": "1",
    "title": "Task 1",
    "description": "Buy Cheese",
    "priority": 2,
    "isComplete": false,
    "listId": 4
  }
  ```

### Complete a task in a list

Complete a task in a list by its ID.

- **Endpoint:** `/task/completeTask/{id}`
- **HTTP Method:** `PUT`
- **Parameter:**
    - `id` *(path parameter)*: ID of the task to complete.
- **Request Body:**
  None
- **Sample Request URL:** `localhost:8080/task/completeTask/1`
- **Response Body:**
  None

### Change the priority a task in a list

Change the priority a task in a list by its ID.

- **Endpoint:** `/task/changePriority/{id}`
- **HTTP Method:** `PUT`
- **Parameter:**
    - `id` *(path parameter)*: ID of the task whose priority is to be changed.
    - `priority` *(query parameter)*: New priority value to assign to the task.
- **Request Body:**
  None
- **Sample Request URL:** `localhost:8080/task/changePriority/1?priority=2`
- **Response Body:**
  ```json
  {
    "taskId": "1",
    "title": "Task 1",
    "description": "Buy Milk",
    "priority": 2,
    "isComplete": false,
    "listId": 1
  }
  ```

## Contributing

Feel free to contribute to this project. Open an issue or submit a pull request!

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.
