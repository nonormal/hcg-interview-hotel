-Folder document contains:
+ ERD
+ booking-app-database.sql(Script+mock data)
- APIs implemented:
1. Get the room rate and room availability for each room type and rate
plan in a period of time.
CRUD: GEThttp://localhost:8080/api/v1/rooms/availability?date=2024-01-10
2. Create a new booking.
CRUD: POST http://localhost:8080/api/v1/bookings
Body:
{
  "roomTypeId": 2,
  "arrivalDate": "2024-02-10",
  "departureDate": "2024-02-15",
  "guests": [
    {
      "firstName": "Alice",
      "lastName": "Johnson",
      "contactInfo": "alice.johnson@example.com"
    
  ]
}
