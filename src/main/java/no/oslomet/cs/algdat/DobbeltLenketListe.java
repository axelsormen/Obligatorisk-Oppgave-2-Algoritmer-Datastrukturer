package no.oslomet.cs.algdat;

import java.util.Comparator;
import java.util.Iterator;
import java.util.*;

public class DobbeltLenketListe<T> implements Liste<T> {
    // Innebygd (Trenger ikke endres)

    /**
     * Node class
     *
     * @param <T>
     */
    private static final class Node<T> {
        private T verdi;
        private Node<T> forrige, neste;

        private Node(T verdi, Node<T> forrige, Node<T> neste) {
            this.verdi = verdi; this.forrige = forrige; this.neste = neste;
        }
        private Node(T verdi) {this(verdi, null, null);}
    }

    private Node<T> hode;
    private Node<T> hale;
    private int antall;
    private int endringer;

    public void fraTilKontroll(int fra, int til) {
        if (fra < 0) throw new IndexOutOfBoundsException("fra("+fra+") er negativ.");
        if (til > antall) throw new IndexOutOfBoundsException("til("+til+") er større enn antall("+antall+")");
        if (fra > til) throw new IllegalArgumentException("fra("+fra+") er større enn til("+til+") - Ulovlig intervall.");
    }

    // Oppgave 0
    public static int gruppeMedlemmer() {
        return 1;
    }


    // Oppgave 1
        public DobbeltLenketListe() {
            hode = null;
            hale = null;
            antall = 0;
            endringer = 0;
        }

    public DobbeltLenketListe(T[] a) {
        Objects.requireNonNull(a, "Array a cannot be null");

        for (T element : a) {
            if (element != null) {
                Node<T> nyNode = new Node<>(element);
                if (hode == null) {
                    hode = nyNode;
                    hale = nyNode;
                } else {
                    hale.neste = nyNode;
                    nyNode.forrige = hale;
                    hale = nyNode;
                }
                antall++;
                endringer++;
            }
        }
    }

    @Override
    public int antall() {
        return antall;
    }

    @Override
    public boolean tom() {
        if(antall == 0){
            return true;
        }
        return false;
    }

    // Oppgave 2
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        Node<T> current = hode;

        while (current != null) {
            sb.append(current.verdi);
            current = current.neste;
            if (current != null) {
                sb.append(", ");
            }
        }

        sb.append("]");
        return sb.toString();
    }

    public String omvendtString() {
        StringBuilder sb = new StringBuilder("[");
        Node<T> current = hale;

        while (current != null) {
            sb.append(current.verdi);
            current = current.forrige;
            if (current != null) {
                sb.append(", ");
            }
        }

        sb.append("]");
        return sb.toString();
    }

    @Override
    public boolean leggInn(T verdi) {
        if (verdi == null) {
            throw new NullPointerException("Det er ikke tillatt å legge til null-verdier.");
        }

        Node<T> nyNode = new Node<>(verdi);

        if (antall == 0) {
            hode = nyNode;
            hale = nyNode;
        } else {
            nyNode.forrige = hale;
            hale.neste = nyNode;
            hale = nyNode;
        }

        antall++;
        endringer++;
        return true;
    }
    
    // Oppgave 3
    private Node<T> finnNode(int indeks) {
        if (indeks < 0 || indeks >= antall) {
            throw new IndexOutOfBoundsException("Ulovlig indeks: " + indeks);
        }

        if (indeks < antall / 2) {
            Node<T> current = hode;
            for (int i = 0; i < indeks; i++) {
                current = current.neste;
            }
            return current;
        } else {
            Node<T> current = hale;
            for (int i = antall - 1; i > indeks; i--) {
                current = current.forrige;
            }
            return current;
        }
    }

    @Override
    public T hent(int indeks) {
        indeksKontroll(indeks, false);

        Node<T> node = finnNode(indeks);
        return node.verdi;
    }

    @Override
    public T oppdater(int indeks, T nyverdi) {
            indeksKontroll(indeks, false); 

            if (nyverdi == null) {
                throw new NullPointerException("Det er ikke tillatt å oppdatere med en null-verdi.");
            }

            Node<T> node = finnNode(indeks);
            T gammelVerdi = node.verdi;
            node.verdi = nyverdi;
            endringer++;
            return gammelVerdi;
        }

    public Liste<T> subliste(int fra, int til) {
            fraTilKontroll(fra, til);
            DobbeltLenketListe<T> subliste = new DobbeltLenketListe<>();

            if (fra == til) {
                return subliste;
            }

            Node<T> current = finnNode(fra);

            for (int i = fra; i < til; i++) {
                subliste.leggInn(current.verdi);
                current = current.neste;
            }

            return subliste;

    }
    
    // Oppgave 4
    @Override
    public int indeksTil(T verdi) {
        Node<T> current = hode;
        int indeks = 0;

        while (current != null) {
            if (verdi == null ? current.verdi == null : verdi.equals(current.verdi)) {
                return indeks;
            }
            current = current.neste;
            indeks++;
        }

        return -1;
    }

    @Override
    public boolean inneholder(T verdi) {
        return indeksTil(verdi) != -1;
    }

    // Oppgave 5
    @Override
    public void leggInn(int indeks, T verdi) {
        indeksKontroll(indeks, true);
        Objects.requireNonNull(verdi, "Må legge inn en verdi");

        Node<T> nyNode = new Node<>(verdi);
        if (indeks == 0) {
            if (hode == null) {
                hode = nyNode;
                hale = nyNode;
            } 
            else {
                nyNode.neste = hode;
                hode.forrige = nyNode;
                hode = nyNode;
            }
        } 
        else if (indeks == antall()) {
            nyNode.forrige = hale;    
            hale.neste = nyNode;
            hale = nyNode;
        } 
        else {
            Node<T> currentNode = finnNode(indeks);
            Node<T> forrigeNode = currentNode.forrige;
            nyNode.forrige = forrigeNode;
            nyNode.neste = currentNode;
            forrigeNode.neste = nyNode;
            currentNode.forrige = nyNode;
        }
        antall++;
        endringer++;
    }

    // Oppgave 6
    @Override
    public T fjern(int indeks) {
        indeksKontroll(indeks, false);
        
        Node<T> fjernetNode;

        if (indeks == 0) {
            fjernetNode = hode;
            hode = hode.neste;
            if (hode != null) {
                hode.forrige = null;
            } 
            
            else {
                hale = null;
            }
        } 
            
        else if (indeks == antall() - 1) {
            fjernetNode = hale;
            hale = hale.forrige;
            hale.neste = null;
        } 
        else {
            Node<T> currentNode = finnNode(indeks);
            fjernetNode = currentNode;
            Node<T> forrigeNode = currentNode.forrige;
            Node<T> nesteNode = currentNode.neste;
            forrigeNode.neste = nesteNode;
            nesteNode.forrige = forrigeNode;
        }

        T fjernetVerdi = fjernetNode.verdi;
        fjernetNode.verdi = null;
        fjernetNode.forrige = null;
        fjernetNode.neste = null;

        antall--;
        endringer++;

        return fjernetVerdi;
    }

    @Override
    public boolean fjern(T verdi) {
        if (verdi == null) {
            return false;
        }

        Node<T> currentNode = hode;

        while (currentNode != null) {
            if (currentNode.verdi.equals(verdi)) {
                Node<T> forrigeNode = currentNode.forrige;
                Node<T> nesteNode = currentNode.neste;

                if (forrigeNode != null) {
                    forrigeNode.neste = nesteNode;
                }
                
                else {
                    hode = nesteNode;
                }

                if (nesteNode != null) {
                    nesteNode.forrige = forrigeNode;
                } 
            
                else {
                    hale = forrigeNode;
                }

                currentNode.verdi = null;
                currentNode.forrige = null;
                currentNode.neste = null;

                antall--;
                endringer++;

                return true;
            }
            currentNode = currentNode.neste;
        }

        return false;
    }
    
    // Oppgave 7
    @Override
    public void nullstill() {
        throw new UnsupportedOperationException();
    }

    // Oppgave 8
    private class DobbeltLenketListeIterator implements Iterator<T> {
        private Node<T> denne;
        private boolean kanFjerne;
        private int iteratorendringer;

        private DobbeltLenketListeIterator() {
            denne = hode;               
            kanFjerne = false;              
            iteratorendringer = endringer;  
        }
            
        private DobbeltLenketListeIterator(int indeks) {
            if (indeks < 0 || indeks >= antall) {
                throw new IndexOutOfBoundsException("Ugyldig indeks: " + indeks);
            }
            denne = finnNode(indeks);
            iteratorendringer = endringer;
        }
            
        @Override
        public boolean hasNext() {
            return denne != null;
        }

        @Override
        public T next() {
            if (iteratorendringer != endringer) {
                throw new ConcurrentModificationException("Endringer i listen oppdaget.");
            }

            if (!hasNext()) {
                throw new NoSuchElementException("Ingen flere elementer i listen.");
            }

            T verdi = denne.verdi;
            denne = denne.neste;
            kanFjerne = true;
            return verdi;
        }
    }

    // Oppgave 9
    public void remove() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator<T> iterator() {
        return new DobbeltLenketListeIterator();
    }

    public Iterator<T> iterator(int indeks) {
        if (indeks < 0 || indeks > antall) {
            throw new IndexOutOfBoundsException("Ugyldig indeks: " + indeks);
        }
        return new DobbeltLenketListeIterator(indeks);
    }

    // Oppgave 10
    public static <T> void sorter(Liste<T> liste, Comparator<? super T> c) {
        throw new UnsupportedOperationException();
    }
}
