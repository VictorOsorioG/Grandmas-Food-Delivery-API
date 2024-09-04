
<h1 align="center">Grandmas Food Delivery API</h1>

This repository contains the code for the Minimum Viable Product (MVP) of the cloud-based home delivery management system for Grandma's Food franchise. The system is designed to streamline the process of managing and fulfilling home delivery orders for the franchise's restaurants.


## Tech Stack

**Server:** Java 21, Spring Boot, MySQL

**Documentation:** Swagger


## Installation

- Clone the repository.
```bash
  - Install the required dependencies
  - Set up the database and configure the connection details
  - Start the server and launch the application
```

## 🛠 Error Code Management
* Codigo **E1000** customer not found
* Codigo **E1001** product not found
* Codigo **E1002** product not available
* Codigo **E1003** document format invalid
* Codigo **E1004** uuid invalid
* Codigo **E1005** Delivered date in the future
* Codigo **E1006** order not found
* Codigo **E1007** order already delivered


## API Reference

#### Get items

```http
  GET /api/delivery/products/${uuid}
```

#### POST create order

```http
  POST /api/delivery/orders/
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
|      |  |  |




## Documentation

[Documentation](http://localhost:8080/api/delivery/swagger-ui/index.html)


## Authors

- [@VictorOsorioG](https://github.com/VictorOsorioG)
- [@DavidTSGlo](https://github.com/DavidTSGlo)
- [@JulianRgGlob](https://github.com/JulianRgGlob)
- [@GfBuitrago](https://github.com/GfBuitrago)


![Logo](https://dtxalliance.org/wp-content/uploads/2022/06/Globant-Original-Logo-06.03.2022-2048x404.png)

