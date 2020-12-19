# AlbusHealthCare

>> Albus health Care is an Android Application used to Track the Doctor's appointment, follow up and User’s Medical Records.

>> Java programming is used in this application for Google materials UI components and Firebase Auth to handle the authentication and Firebase Realtime Database for storing the appointments and user details.

>> The main purpose of the application is to store the appointments of the user with
doctor's and reminds them at Appropriate time.


CLICK THE GIF


![ezgif com-gif-maker (1)](https://user-images.githubusercontent.com/51704455/102693223-16556480-423f-11eb-8575-e04ca810ccd1.gif)



>> The User authentication is Handled by FirebaseAuth . The Dashboard Activity uses
List View to display the appointments with Date, Time, Doctor's Name and Contact Number.
the List view uses BaseAdapter to fetch and store the results . 

>> It is characterized with value listener from firebase to notify the Adapter whenever the data is changed (Add/Remove).

>> The creation of appointment is straight forward one. The alert for user is taken care as following , as the user create the appointment using edit text and Data/Time
pickers , it is transformed into an model (appointment) , Time in Mills for the appointment
is fetched using Calendar API and Pending Intent is Created for for Firing An intent aimed at Broadcast Receiver NotificationForwarder.

>> On receiving the boaracast , the Doctor Name is fetched from the intent and Notification is displayed .

