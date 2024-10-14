# Car Lease Booking Rest application

This is a car lease booking application.


## Requirement

### Functional Requirements
    
The Car-lease Platform API has the following functional requirements:
#####  You can maintain (read, add, change, and delete) basic customer attributes:
- Name
- Street
- House number
- Zip code
- Place
- Email address
- Phone number

#####  You can maintain basic car attributes:
- Make
- Model
- Version
- Number of doors
- CO2-emission
- Gross price
- Nett price

#####  The leaserate is depending on the following parameters:
- Mileage - the amount of kilometers on annual base
- Duration - the number of months in the contract
- Interest rate with startdate
- Nett price
- Leaserate is calculated as: ((( mileage / 12 )*duration )/Nett price) + ((( Interest rate / 100 ) * Nett price) / 12)
• Example calculation:
- Mileage: 45000 km/yr
- Duration: 60 months
- Interest rate: 4.5%
- Nett Price: € 63000
- Leaserate: € 239,76 per month
• The broker and employees who keep track of the data must log into the API before any
subsequent call can be made.
• The identity should be validated with every call.

## TL;DR - Build and Run
 
	 mvn clean install

## REST API

The REST API to the example app is described below.


## Create a new Car entry

### Request

`POST http://localhost:8081/api/createCar`

    {
        "maker": "Toyota",
        "carModel": "Corolla",
        "version": "XLE",
        "doors": 4,
        "co2": "120g/km",
        "grossPrice": 20000.00,
        "nettPrice": 18000.00,
        "mileage": 5000.00
    }
    
### Response

    {
    "id": 1,
    "mileage": 45000.0,
    "nettPrice": 63000.0,
    "maker": "maker",
    "carModel": "Model",
    "version": "vers",
    "doors": 2,
    "co2": "560g/km",
    "grossPrice": 9452.13
	}

## Get all car entries

### Request

`GET http://localhost:8081/api/allCar`

### Response

    {
    "id": 1,
    "mileage": 5000.0,
    "nettPrice": 18000.0,
    "maker": "Toyota",
    "carModel": "Corolla",
    "version": "XLE",
    "doors": 4,
    "co2": "120g/km",
    "grossPrice": 20000.0
	}

## Create a new Customer

### Request

`POST http://localhost:8081/api/createCust`

        {
        "name": "Rupesh",
        "street": "Camera Obsuralaan",
        "house_No": "32",
        "place": "AMstelveen",
        "email": "rupesh@gmail.com",
        "ph_Number": "0645678813"
    }

### Response

    {
    "id": 1,
    "name": "Rupesh",
    "street": "Camera Obsuralaan",
    "house_No": "32",
    "place": "AMstelveen",
    "email": "rupesh@gmail.com",
    "ph_Number": "0645678813"
	}

## Get all customers

### Request

`GET http://localhost:8081/api/allCust`

    

### Response

    [
    {
        "id": 1,
        "name": "Rupesh",
        "street": "Camera Obsuralaan",
        "house_No": "32",
        "place": "AMstelveen",
        "email": "rupesh@gmail.com",
        "ph_Number": "0645678813"
    }
	]

## Make a reservation

### Request

`POST http://localhost:8081/api/makeReservation`

    {
        "custId": 1,
        "carId": 1,
        "startDate": "2024-10-31",
        "endDate": "2024-11-02",
        "bookingDate": "2024-10-12",
        "duration": 60
    }
    
### Response

    {
    "headers": {},
    "body": "success",
    "statusCode": "OK",
    "statusCodeValue": 200
	}

## Get all reservations

### Request

`GET http://localhost:8081/api/allReservations`

### Response

    [
    {
        "id": 1,
        "custId": 1,
        "carId": 1,
        "startDate": "2024-10-31",
        "endDate": "2024-11-02",
        "bookingDate": "2024-10-12",
        "totalBill": 239.82142857142858,
        "duration": 60
    }
	]

## Error case: Make a reservation with past date as startDate

### Request

`POST http://localhost:8081/api/makeReservation`

        {
        "custId": 2,
        "carId": 2,
        "startDate": "2024-09-12",
        "endDate": "2024-12-02",
        "bookingDate": "2024-10-14",
        "duration": 60
    }

### Response

    {
    "startDate": "must be a date in the present or in the future"
}

## Error case: Make a reservation where the same car is already booked

### Request

`POST http://localhost:8081/api/makeReservation`

     {
        "custId": 2,
        "carId": 1,
        "startDate": "2024-10-30",
        "endDate": "2024-11-02",
        "bookingDate": "2024-10-14",
        "duration": 60
    }

### Response

    {
    "headers": {},
    "body": "Car is already booked for the selected dates.",
    "statusCode": "OK",
    "statusCodeValue": 200
}

## Error case: Make a reservation with End Date is before Start Date

### Request

`POST http://localhost:8081/api/makeReservation`

           {
        "custId": 2,
        "carId": 2,
        "startDate": "2024-11-12",
        "endDate": "2024-11-11",
        "bookingDate": "2024-10-14",
        "duration": 60
    }

### Response

    {
    "headers": {},
    "body": "End date cannot be before start date",
    "statusCode": "OK",
    "statusCodeValue": 200
}

# Next Steps
#####  The Booking date can be fetched automatically as today's date.
#####  The address can be fetched directly from POSTNL API. 
#####  Currency can have only two unit digits, Eg: 125.22 EUR rather than 125.234243 Eur
#####  The car model can have more attributes like number of cars available, and if there are multiple cars, multiple customer can book the same car model.
#####  Swagger API
#####  Security using OAuth or JWT
#####  Containerization. 