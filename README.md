# AlbusHealthCare

>> Albus health Care is an Android Application used to Track the Doctor's appointments, follow up and User’s Medical Records.

>> Java programming is used in this application for Google materials Ui Components and Firebase Auth to Handle the Authentication and Firebase Realtime Database for storing the Appointments and User details.

>> The Main Purpose of the Application is to Store the Appointments of the user with
Doctors and reminds them at Appropriate time.

>> The User authentication is Handled by FirebaseAuth . The Dashboard Activity Uses
List View to display the Appointments with Date, Time, Doctor's Name and Contact Number.
the List view uses BaseAdapter to Fetch and store the Results . It is characterized with value listener from firebase to notify the Adapter whenever the data changed (Add/Removed)
The Creation of Appointment is Straight Forward one. The Alert for user is Taken Care as following , as The user Create the Appointment Using Edit Text and Data/Time
Pickers , it is Transformed into an model (Appointment) , Time in Mills for the Appointment
is fetched using Calendar API and Pending Intent is Created for for Firing An intent aimed at Broadcast Receiver NotificationForwarder. On receiving the Boaracast , the Doctor Name is fetched from the intent and Notification is Displayed .

![ezgif com-gif-maker (1)](https://user-images.githubusercontent.com/51704455/102693223-16556480-423f-11eb-8575-e04ca810ccd1.gif)
