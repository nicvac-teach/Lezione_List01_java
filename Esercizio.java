// LEGGERE LE ISTRUZIONI NEL FILE README.md

// Import di Classi Java necessarie al funzionamento del programma
import java.util.Scanner;

// Classe principale, con metodo main
class Esercizio {

    /* =====================
       Classe Nodo
       ===================== */
    static class Nodo {
        String dato;
        Nodo next;

        Nodo(String dato) {
            this.dato = dato;
            this.next = null;
        }
    }

    /* =====================
       Classe Lista Concatenata
       ===================== */
    static class Lista {
        private Nodo head;

        public Lista() {
            head = null;
        }

        public void aggiungiInTesta(String dato) {
            Nodo nuovo = new Nodo(dato);
            nuovo.next = head;
            head = nuovo;
        }

        public void aggiungiInCoda(String dato) {
            Nodo nuovo = new Nodo(dato);

            if (head == null) {
                head = nuovo;
                return;
            }

            Nodo corrente = head;
            while (corrente.next != null) {
                corrente = corrente.next;
            }
            corrente.next = nuovo;
        }

        public boolean contiene(String dato) {
            Nodo corrente = head;
            while (corrente != null) {
                if (corrente.dato.equals(dato)) {
                    return true;
                }
                corrente = corrente.next;
            }
            return false;
        }

        public int size() {
            int count = 0;
            Nodo corrente = head;
            while (corrente != null) {
                count++;
                corrente = corrente.next;
            }
            return count;
        }

        public void stampa() {
            Nodo corrente = head;
            while (corrente != null) {
                System.out.print(corrente.dato + " -> ");
                corrente = corrente.next;
            }
            System.out.println("NULL");
        }
    }

    // Il programma parte con una chiamata a main().
    public static void main(String args[]) {

        // Variabili del programma
        String nome;

        // Creo l'oggetto in per l'input da tastiera
        Scanner in = new Scanner(System.in);

        // Leggo l'input da tastiera
        System.out.print("Inserisci il tuo nome: ");
        nome = in.nextLine();

        // Output del nome acquisito da tastiera
        System.out.println("Ciao " + nome + "!");

        // =====================
        // Test Lista Concatenata
        // =====================
        Lista lista = new Lista();

        lista.aggiungiInTesta("A");
        lista.aggiungiInCoda("B");
        lista.aggiungiInCoda("C");

        System.out.println("\nContenuto della lista:");
        lista.stampa();

        System.out.println("Dimensione lista: " + lista.size());
        System.out.println("La lista contiene B? " + lista.contiene("B"));
        System.out.println("La lista contiene X? " + lista.contiene("X"));

        in.close();
    }
}

// LEGGERE LE ISTRUZIONI NEL FILE README.md
