
1. Am o clasa GrapfGenerator, iar primul lucru pe care îl fac in interiorul ei este sa verific daca numărul de argumente este dat bine sau daca n si k  respecta anumite proprietăți (cum ar fi k < n) , + argumentele se convertesc la nr de tip INT .
2. Generez random o matrice de adiacenta ( prima data am un subgraf complet de k noduri,  se completează random nodurile k cu restul grafului, apoi se mai conectează niște noduri  (care nu fac parte din subgraf ) intre ele).
3.  Afisez matricea doar daca e n<30.000  si măsor timpul de execuție daca n>30.000
4.  Calculez nr de muchii (am muchie doar daca am valori de 1 in mat de adiacenta (pătratul plin)), apoi gradele nodurilor, apoi gradul minim si maxim, apoi verific relația: suma tuturor nodurilor=2*nr muchii.
