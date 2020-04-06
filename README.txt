This project was created by Andrejs Kijonoks from 01.04.2020 to 06.04.2020 for Accenture.
In project were used custom libraries as:

com.squareup.retrofit2:retrofit:2.3.0
com.squareup.retrofit2:converter-gson:2.3.0
androidx.room:room-runtime
org.robolectric:robolectric:4.3
com.google.android.material:material:1.2.0-alpha05

retrofit2 library was used to work with OpenWeather API, and for converting JSON response from their sever to Kotlin Data Object.

Room library was used as local database to store weather objects and to work with them.

Material library was used to work with google material design components.

Robolectric library was used to application Unit tests.

In project several optional tasks were done:

 - More details screen (From response I have used not only city name and temperature but also a lot of more details. All details can be found when user will click on Details menu Button. They will be shown in DetailsActivity)

 - Fetching and processing weather Data for more locations (In Load fragment user can not only load one city. He can choose from 7 provided cities. He must check cities which he wants to load and then press on floating action button to upload them from server)

 - All CRUD operations (After loading weather Room Data Base CREATES weather object in it. Then user can READ it from SAVED fragment. By clicking on Delete menu item weather will be DELETED From RDB. If user will upload weather again. Existing weather objects in RDB will UPDATED with new values)

 - Kotlin Usage (All project is written on Kotlin)
