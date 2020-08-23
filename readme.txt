OrderService:
  This is a microservice that is responsible for creating and retrieving the order details.

  Endpoints:

** Swagger UI is enabled.

    ****localhost:8083/swagger-ui.html

 postrequest: /createorder : (ex : localhost:8083/createorder)
  sample json request:

  ** Valid product codes
  productCode: can be any of pc001,pc002,pc003,pc004 (as these are the products available at our inventory)

Request
  {
    "customerName": "abcde",
    "orderItems": [
      {
        "productCode": "pc003",
        "quantity": 4
      },
      {
        "productCode": "pc001",
        "quantity": 2
      }
    ],
    "shippingAddress": "address123",
    "totalAmount": 20.85
  }

Response
  {
      "message": "Order Created successfully with order number :8-Characters alphanumeric order number"
  }

  
 getrequest: /retrieveorder: (ex: localhost:8083/retrieveorder?orderId=99AESBQG)

Response:

 	{
    "orderNumber": "99AESBQG",
    "customerName": "abcde",
    "orderDate": "2020-08-23T09:45:02.304+00:00",
    "shippingAddress": "adddddasdfasd",
    "orderItems": [
        {
            "productCode": "pc003",
            "productName": "prod003",
            "quantity": 4
        },
        {
            "productCode": "pc001",
            "productName": "prod001",
            "quantity": 2
        }
    ],
    "totalAmount": 20.85
}
