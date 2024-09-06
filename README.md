
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
* Code **E1000** customer not found
* Code **E1001** product not found
* Code **E1002** product not available
* Code **E1003** document format invalid
* Code **E1004** uuid invalid
* Code **E1005** Delivered date in the future
* Code **E1006** order not found
* Code **E1007** order already delivered
* Code **E1008** client already exists
* Code **E1009** No changes found in the customer
* Code **E1010** product combo name already exists
* Code **E1011** Invalid or incomplete product data
* Code **E1012** Value written in the enum is not valid
* 
* Code **E1015** Date format invalid
* Code **E1016** Date range is not valid


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

