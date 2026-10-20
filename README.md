# Currency Conversion Microservices (Spring Boot)

| Service | Port | Purpose |
|---|---|---|
| currency-exchange-service | 8000 | Returns exchange rates (H2 database) |
| currency-conversion-service | 8100 | Converts amounts by calling the exchange service |

## Run
Start both apps from IntelliJ (or `./mvnw spring-boot:run` inside each folder).

## Try it
- http://localhost:8000/currency-exchange/from/USD/to/LKR
- http://localhost:8100/currency-conversion/from/USD/to/LKR/quantity/10