[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-24ddc0f5d75046c5622901739e7c5dd533143b0c8e959d652212380cedb1ea36.svg)](https://classroom.github.com/a/k3TSQYDa)
# Obligatorisk Oppgave 2 i DATS2300 - Algoritmer og Datastrukturer

Denne oppgaven er en innlevering i DATS2300 - Algoritmer og datastrukturer. Den er innlevert av følgende studenter:
* s374926@oslomet.no

## Arbeidsfordeling
I oppgaven har vi hatt følgende arbeidsfordeling:
* Jeg har jobbet alene, og derfor løst alle oppgavene selv

## Oppgavebeskrivelser

# Oppgave 1
I oppgave 1 lagde jeg to konstruktører. En definerte hode, hale, antall og endringer på den dobbelt lenkede listen og returner en tom dobbelt lenket liste. Den andre tar inn verdier (ikke null-verdier) og generer listen. Metoden antall returner antall elementer i lista og metoden tom sjekker om antall er lik 0 og returner en boolean utifra svaret.

# Oppgave 2
I oppgave 2 lagde jeg en toString-metode ved hjelp av en StringBuilder. Ved hjelp av en while-løkke kjører den gjennom lista og legger på elementene helt til pekeren er forbi siste element i lista. Metoden omvendtString var det samme bare at man startet på halen og tok forrige element istedenfor neste element. Metoden leggInn lager en ny node og plasseren den som elementet etter halen (altså bakerest). Vi setter så halen til å være den nye noden.

# Oppgave 3
I oppgave 3 startet jeg med å gjøre noen tester i metoden finnNode. Vi sjekker så om indeksen er i første eller siste halvdel av listen, og utifra dette begynner vi enten i hode eller halen og jobber oss gjennom lista ved hjelp av en for-løkka for å finne noden på den innsendte indeksen. Metoden hent kaller på finnNode og returnerer verdien som er på den innsendte indeksen. Metoden oppdater kaller også på finnNode og returner verdien, i tillegg til å bytte ut verdien med den innsendte verdien. Metoden subliste returner elementene i et intervall ved hjelp av en for-løkke. For-løkken legger på noden i sublisten og går videre til neste node.

# Oppgave 4
I oppgave 4 kjører jeg gjennom en for-løkke i metoden indeksTil helt til jeg finner den første noden med innsendt verdi. Metoden returner så indeksen til noden. Går den gjennom for-løkken uten å finne en node med innsendt verdi vil den bare returnere -1. Metoden inneholder kaller på indeksTil, og returner true hvis svaret ikke er negativt og false hvis svaret er negativt. 

# Oppgave 5
I oppgave 5 lagde jeg metoden leggInn og starter med å opprette en ny node. Hvis den skal inn på indeks 0, må vi sjekke om vi har elementer i lista fra før av, og hvis nei, setter vi den til å være både hode og hale. Hvis ja, blir den nye verdien hode og den peker på det tidligere hodet. Det samme bare motsatt gjelder hvis vi skal sette den inn i listas siste indeks. Hvis vi skal plassere noden et sted mellom hode og hale, må vi definere en currentNode og forrigeNode, og deretter peke på den nye noden (forrige peker på nye, nye peker på forrige, neste peker på nye og nye peker på neste).

# Oppgave 6
I oppgave 6 lagde jeg metodene fjern. Den første metoden sjekker først for om hode eller halen skal fjernes. Hvis vi ikke skal fjerne hode eller halen, må vi gjøre slik at nodene før og etter den noden som blir fjernet peker på hverandre. Til slutt fjerner vi verdien, samt nodene før og etter den fjernede noden. For å fjerne første instans av en verdi benytter vi av oss en while-løkke som kjører gjennom lista. Vi fjerner så verdien, samt nodene før og etter den fjernede noden.

# Oppgave 8
I oppgave 8 lagde jeg metoden next ved å bruke if-setninger for å kaste ConcurrentModificationException eller NoSuchElementException. Iterator<T> iterator(int indeks) kaster en IndexOutOfBoundsException hvis indeksen er større enn 0 eller indeks er mindre enn antall. Ellers returnerer den DobbeltLenketListeIterator(indeks). Iterator<T> iterator() returnrer bare DobbeltLenketListeIterator.
