# ChessEngine-XBoard
[Proiect - Proiectarea Algoritmilor] [Echipa DUMB.BLONDES]

Tema proiectului este realizarea unei inteligențe artificiale capabile să joace șah. <br>
Enuntul se gaseste [aici](https://ocw.cs.pub.ro/courses/pa/regulament-proiect-2020).

## Descriere
Se urmareste realizare reprezentarii interne a tablei de joc și a pieselor
de joc, precum și o interfațare cu programul XBoard. Interfațarea va urmari posibilitatea de a interpreta și interacționa cu următoarele 
comenzi ale XBoard: xboard, new, force, go, white, black, quit, resign, move. <br>
Programul va implementa algoritmul Minimax pentru a juca impotriva unui adversar. Programul va trebui să poată interpreta orice mișcare legală primită de la adversar, respectand toate regulile jocului.
<br>
<br>

## Membrii
> BADITA Rares-Octavian 	&nbsp;&nbsp; &nbsp; &nbsp;			[@WhyNotRaresh](https://github.com/WhyNotRaresh) <br>
> GHERASIE Stefania-Elena 	&nbsp; &nbsp; 					        [@stefania.gherasie](https://github.com/stefaniagherasie) <br>
> MANDRU Cosmina  	&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;	[@mcosmina06](https://github.com/mcosmina06)
<br>

## Compilare si Rulare
Pentru rulare, programul necesita Xboard 4.8 (sau o versiune mai noua). Makefile-ul contine urmatoarele reguli:
> * ```make jar / default``` - realizeaza fisierul cu extensia .jar
> * ```make run``` - face fisierul .jar
> * ```make xboard``` - face fisierul .jar si il pune ca fcp pentru XBoard
> * ```make clean``` - sterge fisierele .class
<br>

Pentru rularea in cadrul XBoard-ului:
> ```make xboard```  - apelat in folderul sursa (src/). <br>
> ```make clean```  - se recomanda pentru testari repetate.
<br>
 
Pentru testarea impotriva lui FairyMax:
> ```shell
> xboard -fcp “make run” -scp “fairymax” -secondInitString “new\nrandom\nsd 2\n” 
>        -tc 5 -inc 2 -autoCallFlag true -mg 10 -sgf partide.txt -reuseFirst false```
> ```
<br>

## Structura proiectului
Proiectul are urmatoarea organizare:
> * pachetele: ```main```, ```pieces```, ```engine```, ```opening```, ```commands```, ```auxiliary```
> * ```Makefile``` - compileaza si ruleaza in XBoard
> * ```MANIFEST.MF```
> * ```README.md```
> * ```book.csv``` - contine succesiunea de miscari pentru deschideri celebre ale jocului de sah.
<br>

| Pachet      | Componență  |
| ----------- | ----------- |
| main        | ChessBoard, ChessMain|
| pieces      | AbstractPiece, Bishop, King, Knight, Pawn, Queen, Rook, VoidPiece|
| engine      | Engine, Strategy, OpeningStrateg, MainStrategy, LimitedTimeStrategy|
| opening     | OpeningMove, OpeningParser|
| commands    | BlackCommand, Command, ForceCommand, GoCommand, MoveCommand, NewCommand, ProtoverCommand, QuitCommand, ResignCommand, UndoCommand, VoidCommand, WhiteCommand, XBoardCommand|
| auxiliary   | CommandFactory, CommandReader, LineErrorException, Pair, PiecesFactory, Position|
<br>

## Implementare
Clasa ```ChessMain``` ruleaza programul. Se creeaza o tabla, se interpreteaza comenzile de la Xboard si se ruleaza algoritmul Minimax pentru a castiga jocul.


#### ► Tabla de sah
Clasa ```ChessBoard``` contine reprezentarea interna a tablei de sah. Am folosit Design Pattern-ul Singleton pentru a asigura unicitate. Tabla este
reprezentata sub forma unei matrici 8x8 de piese. Variabilele ```playingColor/ 
playerTurn``` sunt folosite pentru a retine ce culoare joaca si ce culoare urmeaza
sa mute. Aceasta clasa contine metode pentru resetarea tablei la pozitia initiala,
obtinerea unei piese in functie de pozitie.
<br>
<br>

#### ► Piesele
Reprezentarea pieselor porneste de la clasa abstracta ```AbstractPiece```. Se foloseste clasa ```Position``` pentru a reprezenta pozitia pe tabla a unei piese.
Pentru a reprezenta locul liber pe tabla se foloseste clasa ```VoidPiece```, adica o piesa nula. <br>
Am creat clase pentru fiecare tip de piesa(```Pawn```, ```Bishop```, ```King```, ```Knight```, ```Queen```,
```Rook```) si am implementat miscarile specifice. Se salveaza pozitiile mutarilor posibile, tinand cont de 
mutarile legale si de marginile tablei. Mutarile se realizeaza in functie de culoarea jucata, de pozitiile ocupate pe tabla, de locurile
libere. S-a facut un ```PieceFactory``` pentru realizarea de piese corespunzatoare tinand cont de pozitie.

Am implementat scoaterea regelui din sah cand acesta este amenintat de piesele 
adversarului. Ne-am ocupat si de alte detalii ale jocului de sah, cum ar fi 
rocada sau promovarea pionului la regina cand ajunge la marginea opusa a tablei.
<br>
<br>

#### ► Interpretarea Comenzilor
 ```CommandReader``` este folosit pentru citirea de la stdin a comenzilor. Se
proceseaza pe rand comenzile si se asigura prelucrarea argumentelor primite de
la XBoard. <br>
Clasa ```Command``` reprezinta aceste comenzi si va fi mostenita pentru individualizarea comenzilor. Clasa 
```VoidCommand``` reprezinta o comanda nula. S-au creat clase pentru fiecare comanda specificata in cerinta(```xboard```, ```new```, 
```force```, ```go```, ```white```, ```black```, ```quit```, ```resign``` si ```protover```).
<br>
<br>

#### ► Strategia de Deschidere 
Pentru primele miscari, am folosit strategii populare de sah.
In fisierul ```"book.csv"``` se afla succesiuni de miscari din strategii bine cunoscute
de sah care ne-ar putea ajuta sa avem un avantaj pentru inceputul jocului.

In ```OpeningParser``` am parsat succesiunile de miscari, salvandu-le intr-un Map
sub forma <Lista de miscari anterioare, Lista cu miscari urmatoare avantajoase>.
Am folosit clasa ```OpeningMove``` pentru a retine miscarea urmatoare, asociind un ```gain``` care reprezinta importanta
miscarii. <br>
Pentru ```OpeningStrategy``` se alege cea mai buna miscare dupa ```gain```, in functie de miscarile din istoric.
In acest fel ne asiguram ca pentru inceputul jocului avem o strategie destul de
buna atat timp cat succesiunea de miscari respecta un tipar.
<br>
<br>

#### ► Strategia Principala
```MainStrategy``` reprezinta strategia principala care se bazeaza pe Minimax si care 
va fi folosita cand ```OpeningStrategy``` ramane fara miscari prevazute sau esueaza.

Pentru fiecare piesa am asignat un scor prestabilit si o variabila ```safety``` care 
retine nivelul de siguranta a unei piese(scade cand piesa poate fi luata, creste
cand se pot lua alte piese ale oponentului daca aceasta este sacrificata). Se tine cont ca in sah se considera
mai avantajos sa ai 2 nebuni si se obtine un rezultat care este privit
ca un scor avantaj sau dezavantaj. 

Se calculeaza prima data safety-ul pieselor vide pentru a vedea
cat de avantajos ar fi sa mutam o piesa in pozitia aceea libera. Se calculeaza un
rezultat care reprezinta cat de valoroasa este tabla noastra, reprezentand
raportul dintre ```boardOccupacyScore```(suma safety-urilor pozitiilor vide) si 
```dangerScore```(suma scorurilor pozitiilor in pericol - scade cand piesele noastre sunt
in pericol mare, creste cand piesele adversatului sunt in pericol). <br>
Am implementat comanda de ```undo``` folosita in special pentru a ne intoarce in timp
cand facem o miscare proasta (ex: o piesa importanta ne-ar fi luata). 
<br>
<br>

#### ► Engine-ul
Clasa ```Engine``` implementeaza algoritmul de Minimax, calculand cea mai buna miscare 
posibila a jucatorului curent. Initial alegem sa jucam dupa OpeningStrategy, stiind
ca incepem jocul folosind strategii cunoscute in sah. Cand OpeningStrategy a ramas 
insa fara mutari se trece la MainStrategy. Folosind functiile de evaluare, se alege 
mutarea considerata cea mai avantajoasa de algoritmul Minimax.
<br>
<br>

## Abordarea Algoritmica
S-a implementat un algoritm de Minimax care se bazeaza pe ideea
ca jucatorul maxi urmareste sa-si maximizeze castigul prin mutarea pe care o face,
iar jucatorul mini sa isi minimizeze pierderea. Pentru miscarile din OpeningStrategy
```DEPTH = 6```, iar pentru MainStrategy ```DEPTH = 4```, algoritmul de FairyMax cu care jucam
avand adancimea de 2.  

Functiile de evaluare de care ne-am folosit tin cont de gain-ul pieselor pentru 
miscarile de inceput, trecand apoi la evaluarea pieselor in functie de importanta lor 
si de nivelul de siguranta a acestora, de pericolul in care acestea de afla.
<br>
<br>

## Bibliografie
1. https://www.gnu.org/software/xboard/
2. https://www.gnu.org/software/xboard/engine-intf.html
3. https://www.howtoforge.com/tutorial/xboard-add-chess-engines/
4. https://stackoverflow.com/questions/22060432/how-to-make-a-simple-protocol-to-winboard
5. https://codereview.stackexchange.com/questions/194736/chess-application-in-java
6. https://stackoverflow.com/questions/9556577/communicating-with-xboard-chess-engine-c-c
