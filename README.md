# Banking Application

This project is a banking application developed using Spring Boot and implementing Domain-Driven Design (DDD) principles. It demonstrates complex domain logic, transaction management, and event-driven architecture using domain events. The application manages account operations such as credits, debits, and transfers between accounts.

## Features

- **Account Management**: Handle operations like account creation, credit, debit, and viewing account balances.
- **Transaction Management**: Process transactions with complete atomicity using Spring's `@Transactional`.
- **Domain Events**: Use Spring Data's `AbstractAggregateRoot` to handle domain events for account operations.
- **Transfer Service**: Implements a saga for transferring money between accounts ensuring transactional integrity.
- **Event-Driven Notifications**: Domain events trigger notifications, logging, or integration with other systems like message brokers.

## Technical Stack

- **Spring Boot**: For the application framework.
- **Java**: Programming language (Java 17 recommended).
- **Maven**: Dependency management.
- **PostgreSQL**: Primary database.
- **Kafka**: For asynchronous message handling and event-driven architecture.
- **Spring Data JPA**: For ORM and repository support.
- **H2 Database**: For integration testing.

## Setup Instructions

**Clone the repository:**
   git clone https://github.com/yourrepository/banking-application.git

## Usage
Describe how to interact with the application through its REST API or any other interface you provide. Include example requests and expected responses.

## License
Open source