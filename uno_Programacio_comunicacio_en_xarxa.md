***Qué es una aplicació distribuïda?***

És aquella que s’executa en més d’un dispositiu. Entenem per 
aplicació distribuïda transparent aquella aplicació distribuïda 
que no necessita un tractament especial pel fet d’estar distribuïda.

### 1.1.Rols client - servidor
És la forma més clàssica de comunicar dispositius digitals

nternet fa servir un sistema molt senzill anomenat protocol IP.
La gran senzillesa d’aquest sistema és el que ha permès la seva 
proliferació tan ràpida, ja que els costos d’implementació dels 
nodes intermedis de la xarxa són considerablement reduïts. 

El protocol IP és tan simple que no contempla cap procés de 
control per verificar que la informació arriba realment al seu 
destí de forma complerta. És per això que la majoria de serveis 
i aplicacions d’Internet afegeixen un protocol extra de control 
anomenat TCP.

### 1.2.1.Arquitectura d'internet
>Els **processos d’una comunicació** són aquells que permeten als 
> dispositius generar, transmetre i rebre un conjunt de senyals
> seqüenciats d’acord amb una convenció comuna

[MODEL OSI](Images/OSI.png)

Els protocols de control poden ser a internet (prinicipalment) 
TCP o UDP.
- TCP: és un protocol molt més sofisticat que gestiona el control 
  de l’ordre i també la pèrdua de paquets a més de la integritat 
  de la informació.
- UDP:és un protocol de control força senzill, només controla 
  la integritat dels paquets enviats. Això vol dir que l’utilitzen 
  aquelles aplicacions que no necessiten mantenir cap ordre en l’
  arribada de la informació i en la qual ni la pèrdua d’alguna 
  dada impedeix el normal funcionament. 

### 1.2.2.Capçeleres i dades
Les capes a l'hora d'enviar la informació omplen de capes el
paquet per tal d'aportar la informació necessaria per al bon 
funcionament, a l'hora de rebre un paquet, aquest es va desgloçant
i reduint.
[Esquema de traspàs de dades entre capes](Images/traspasCapcelera.png)

### 1.2.3.Contingut de la informació a cada capa
La capa d’aplicació formata d’una manera concreta les dades a enviar 
i segueix un protocol(HTTP, FTP,...) establert per tal de fer 
factible la comunicació entre els dispositius implicats.

## CAPA DE TRANSPORT
Es tracta de la capa situada immediatament per sota de les 
aplicacions. Durant la recepció de les dades, necessitarà 
reconèixer quina és l’aplicació destinatària de la informació 
ja que, per descomptat, podria donar-se el cas de tenir diverses 
aplicacions independents esperant dades.
Aquest programa s'indentifica amb el seu propi port.
>Els principals protocols usats en aquest nivell són UDP i TCP.

S’utilitzen **2 bytes** per identificar el **port**. Això significa 
que hi ha 65.535 ports disponibles en cada dispositiu.
[PRINCIPALES PUERTOS TCP/UDP](https://www.redeszone.net/tutoriales/configuracion-puertos/puertos-tcp-udp/)
- Entre 1 i 1.023: es reserven per aplicacions específiques i estàndards (FTP, HTTP, SSH).
- Entre 1.024 i 49.151: cal destinar-los a serveis no estàndards.
- Entre 49.152 i el 65.535: es destinen a comunicacions temporals dels clients 
  i seran assignats directament per la capa de transport en el moment 
  d’iniciar una comunicació.
  
#### Protocol UPD
UDP utilitza la longitud de les dades i un valor de 
comprovació anomenat checksum per a realitzar la verificació 
de la coherència. El valor **checksum** s’obté a través d’un 
algoritme de suma que porta el mateix nom. 

***L’algoritme checksum*** consisteix en sumar un conjunt de 
bytes agrupats en blocs de 16 bits (2 bytes). En 
tractar-se d’una suma, el resultat que obtindríem en qualsevol 
dels possibles casos podria representar-se sempre amb un màxim de 
17 bits. En aquells casos que el resultat de la suma necessiti 17 
bits, s’elimina el bit més significatiu (el de més a l’esquerra) 
per tal d’ajustar sempre la mida a 16 bits i s’incrementa el valor 
en una unitat. 

#### Protocol TCP
Les aplicacions que necessitin fiabilitat en les transmissions 
hauran de fer servir el protocol TCP. Es tracta d’un protocol 
de gran complexitat amb el qual es garanteix que totes les dades 
enviades des de l’origen, arriben al destí íntegrament i en el mateix 
ordre en què han sortit de l’emissor. 

>Es diu que el protocol TCP està orientat a la
> connexió perquè manté un diàleg permanent entre l’emissor i el receptor. 
> En canvi el protocol UDP no està orientat a la connexió perquè un cop 
> enviades les dades l’emissor es desentén de la transmissió.

TCP és un protocol pensat per comunicar només dos interlocutors. 
Les aplicacions que necessitin transmetre a múltiples dispositius 
hauran d’escollir entre aplicar el protocol UDP, amb el risc de pèrdua
d’informació que això suposa, o bé implementar múltiples connexions dos 
a dos amb les quals poder controlar a través de TCP la transmissió. 

Per aconseguir la connexió caldrà establir un petit diàleg entre 
ambdós dispositius en el qual un d’ells enviarà un senyal específic 
anomenat **PETICIÓ**, que comportarà la reserva d'un port per comunicar-se.

Diem que TCP és un protocol **full-duplex** perquè estableix dos canals de 
comunicació en cada connexió, cada un en un sentit diferent. també els 
canals de la connexió estan orientats a fluxos (*streaming-oriented*) 
utilitzat per determinar i controlar l'ordre del traspass. Sovint les 
limitacions de la xarxa impedeixen enviar les dades en un únic bloc. 
En aquests casos el protocol TCP permet dividir els enviaments en 
segments més petits en els quals cada un d’ells contindrà parcialment 
les dades a enviar.

Per tal d’assegurar la fiabilitat de les dades, el protocol estableix 
que per cada segment enviat des d’un o altre dispositiu, el receptor 
contestarà amb un senyal especial de reconeixement que indiqui a l’emissor 
que les dades del segment han arribat amb èxit.
[Esquema del TCP](Images/DataTCP.png)

##### Adreça IP
>**Adreces IP** identifiquen les connexions directes d'un dispositiu de manera única.
> Un dispositiu pot tenir diferents connexions.

Interfícies de xarxa lòguiques: és una emulació de les IPs, *Ex: un Sistema 
Operatiu té l'adreça 127.0.0.1 per identificar el dispositiu propi.*

##### Col·laboració entre nodes
Els nodes col·laboren entre ells creant petits mapes a la xarxa per tal 
de poder decidir cap on adreçar els paquets que arriben des de diferents 
orígens a través de les seves interfícies de xarxa.
L'objectiu es segmentar la xarxa i crear mapes específics per a cada node 
per establir un conjunt de regles senzilles per al bon funcionament de fer 
arribar un paquet al seu destí.

##### Adreces i mascares
>Una màscara és una seqüència de bits de la mateixa mida que una adreça IP, 
> però organitzada de manera peculiar. Hi ha un nombre indeterminat de 
> bits més significatius (els que es troben més a l’esquerra de la 
> seqüència binària) que sempre prenen el valor 1, mentre que la resta de 
> bits, els menys significatius (que localitzem a la dreta del conjunt d’uns) 
> prenen sempre el valor 0.

*Ex1: El rang definit per 128.154.X.X es pot definir fent servir una adreça 
base com per exemple 128.154.0.0 i una màscara 255.255.0.0*

*Ex2: el rang definit per 144.134.12.X es pot definir fent servir una dreça
base com per exemple 144.134.12.11 i una màscara 255.255.255.0*

##### Adreces IP especials
el rang 127.0.0.0 pertany al propi dispositiu
També hi ah rangs d'adreces reservades a les xarxes locals com 192.168.0.0
També existeixen les conegudes com adreces *broadcast*, s'identifica per tenir 255
en una o més posicions.
*Ex: 248.147.118.255*
> broadcast: es la transmisión de datos que serán recibidos por todos los 
> dispositivos en una red.

//TODO: ctl+F
Finalment farem una menció específica a les adreces anomenades de multidifusió o multicast.

