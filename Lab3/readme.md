Creez lista de aeronave (aircraftList) in care sunt adaugate diferite tipuri de aeronave: Airliner, Drone si Freighter. 
Fiecare obiect are atribute specifice (nr locuri, sarcina utila max, modelul si detalii aripa- vezi Aircraft).
Lista de aeronave este sortata alfabetic dupa nume (folosesc compareTo).
Dupa sortare, fiecare aeronava este afisata prin metoda toString().
Aircraft - o clasa abstracta (prop comune: nume, model, nr identif, detalii aripa -mentionate mai sus).

Clasele derivate:
* Airliner – Avion pt pasageri (PassengerCapable si CargoCapable) . Are locuri si sarcina utila max.
* Drone – Drona de transport (CargoCapable). Are sarcina utila si autonomie a bateriei.
* Freighter – Avion cargo (PassengerCapable si CargoCapable). Are locuri si sarcina utila.
  
Interfete folosite:
* CargoCapable – metoda getMaximumPayload() pt aeronavele capabile sa transporte marfa.
* PassengerCapable – metoda getSeatCount() pt aeronavele pt pasageri si o metoda pt verif existentei locurilor business class.
