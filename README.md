# Da Array a Strutture Dati Dinamiche

## Rigidità degli Array

Gli array richiedono di **stabilire subito la dimensione** al momento della dichiarazione. Questo porta a un dilemma:

- **Pochi elementi allocati** ⟹ Rischio di non poter gestire tutti i dati necessari
- **Troppi elementi allocati** ⟹ Spreco di memoria

### Esempio: Prenotazioni Ristorante

Immaginiamo di allocare un array di 50 posti per gestire le prenotazioni giornaliere:
- Alcuni giorni le prenotazioni si esauriscono e perdiamo clienti
- Altri giorni ne arrivano poche e sprechiamo memoria

### Costo delle Operazioni

Le operazioni di **inserimento** e **cancellazione** in un array hanno costo **O(n)**: bisogna spostare tutti gli elementi successivi. Se le operazioni sono tante, il costo diventa molto elevato.

---

## Strutture Dinamiche

Le strutture dati dinamiche risolvono i problemi degli array:

1. **Allocano memoria solo quando serve**
2. **Inserimenti e cancellazioni molto efficienti**
3. **Non serve fare ipotesi sulla dimensione iniziale**

### Tipi di Strutture Dinamiche

Ognuna è adatta a problemi specifici:

- **Liste concatenate** (Linked List)
- **Pila** (Stack)
- **Coda** (Queue)
- **Alberi** (Tree)
- **Grafi** (Graph)

---

## Linked List (Lista Concatenata)

Una **Linked List** è una sequenza di **nodi**.

Ogni **nodo** contiene:
- Il **dato**
- Un **riferimento** (puntatore) al nodo successivo

### Struttura

```
  HEAD
    │
    ▼
┌───────┬───────┐     ┌───────┬───────┐     ┌───────┬───────┐
│ DATO  │ NEXT  │────▶│ DATO  │ NEXT  │────▶│ DATO  │ NULL  │
└───────┴───────┘     └───────┴───────┘     └───────┴───────┘
```

L'attributo **HEAD** è il punto di accesso alla lista: contiene il riferimento al primo nodo.

### Differenza con Array

Gli elementi **non sono contigui in memoria**: ogni nodo può trovarsi in qualsiasi posizione della RAM. Sono collegati tra loro solo tramite i riferimenti.

---

## Vantaggi vs Array

1. **Inserimento e cancellazione in O(1)**: basta spostare i puntatori, senza spostare fisicamente gli elementi in memoria

2. **Dimensioni flessibili**: la lista cresce e diminuisce senza sprechi né limiti prefissati

3. **No riallocazione**: quando un array si riempie, bisogna crearne uno nuovo più grande e copiare tutti gli elementi. Con le liste questo problema non esiste.

---

## Svantaggi

1. **Accesso a indice N richiede O(N)** invece di O(1): per raggiungere l'elemento N bisogna attraversare tutti i nodi precedenti

2. **Overhead di memoria**: ogni nodo deve memorizzare anche il riferimento al successivo, non solo il dato

3. **Scarso utilizzo della cache**: se i nodi sono sparsi in RAM, la CPU non può sfruttare la cache in modo efficiente (la cache funziona bene con dati contigui)

---

## Quando usare Array vs Linked List

| Usa **ARRAY** quando: | Usa **LINKED LIST** quando: |
|---|---|
| Serve accesso diretto per indice | Accesso solo in testa e coda |
| Dimensione nota o stabile | Dimensioni molto variabili |
| Pochi o nulli inserimenti/cancellazioni | Molti inserimenti/cancellazioni |

---

# Operazioni sulle Liste

Una lista concatenata deve fornire metodi per manipolare i dati. Si dividono in due categorie:

| **Metodi per Inserzione, Cancellazione e Ricerca** | **Altri Metodi** |
|---|---|
| Aggiunta di un oggetto in testa | Leggere l'oggetto di testa |
| Aggiunta di un oggetto in coda | Leggere l'oggetto in coda |
| Aggiunta di un oggetto in una posizione qualsiasi | Leggere un oggetto di posizione nota |
| Cancellazione di un oggetto noto | Stabilire se la lista è vuota o meno |
| Cancellazione di un oggetto di posizione nota | Conoscere la dimensione |
| Ricerca dell'esistenza di un oggetto noto | |
| Determinazione della posizione di un oggetto noto | |
| Aggiunta di una lista in coda a una lista data | |

---

## Aggiunta in testa

**Cosa fa**: Inserisce un nuovo nodo all'inizio della lista, che diventa la nuova testa.

**Prototipo**:
```java
void aggiungiInTesta(T dato)
```

**Come funziona**:

Prima:
```
  HEAD
    │
    ▼
┌───────┬───────┐     ┌───────┬───────┐
│   A   │ NEXT  │────▶│   B   │ NULL  │
└───────┴───────┘     └───────┴───────┘
```

1. Creo un nuovo nodo con il dato
2. Il NEXT del nuovo nodo punta al vecchio HEAD
3. HEAD punta al nuovo nodo

Dopo (inserisco X):
```
  HEAD
    │
    ▼
┌───────┬───────┐     ┌───────┬───────┐     ┌───────┬───────┐
│   X   │ NEXT  │────▶│   A   │ NEXT  │────▶│   B   │ NULL  │
└───────┴───────┘     └───────┴───────┘     └───────┴───────┘
```

**Costo**: O(1) - non dipende dalla lunghezza della lista.

---

## Aggiunta in coda

**Cosa fa**: Inserisce un nuovo nodo alla fine della lista.

**Prototipo**:
```java
void aggiungiInCoda(T dato)
```

**Come funziona**:

Prima:
```
  HEAD
    │
    ▼
┌───────┬───────┐     ┌───────┬───────┐
│   A   │ NEXT  │────▶│   B   │ NULL  │
└───────┴───────┘     └───────┴───────┘
```

1. Scorro la lista fino all'ultimo nodo (quello con NEXT = NULL)
2. Creo un nuovo nodo con il dato e NEXT = NULL
3. Il NEXT dell'ultimo nodo punta al nuovo nodo

Dopo (inserisco X):
```
  HEAD
    │
    ▼
┌───────┬───────┐     ┌───────┬───────┐     ┌───────┬───────┐
│   A   │ NEXT  │────▶│   B   │ NEXT  │────▶│   X   │ NULL  │
└───────┴───────┘     └───────┴───────┘     └───────┴───────┘
```

**Costo**: O(n) - devo scorrere tutta la lista per trovare l'ultimo nodo.

---

## Aggiunta in una posizione qualsiasi

**Cosa fa**: Inserisce un nuovo nodo in una posizione specifica della lista.

**Prototipo**:
```java
void aggiungiInPosizione(T dato, int posizione)
```

**Come funziona**:

Prima (voglio inserire X in posizione 1, cioè tra A e B):
```
  HEAD
    │
    ▼
┌───────┬───────┐     ┌───────┬───────┐
│   A   │ NEXT  │────▶│   B   │ NULL  │
└───────┴───────┘     └───────┴───────┘
```

1. Scorro la lista fino al nodo in posizione (posizione - 1)
2. Creo un nuovo nodo con il dato
3. Il NEXT del nuovo nodo punta al nodo successivo
4. Il NEXT del nodo precedente punta al nuovo nodo

Dopo:
```
  HEAD
    │
    ▼
┌───────┬───────┐     ┌───────┬───────┐     ┌───────┬───────┐
│   A   │ NEXT  │────▶│   X   │ NEXT  │────▶│   B   │ NULL  │
└───────┴───────┘     └───────┴───────┘     └───────┴───────┘
```

**Costo**: O(n) - nel caso peggiore devo scorrere tutta la lista.

---

## Cancellazione di un oggetto noto

**Cosa fa**: Rimuove dalla lista il primo nodo che contiene un dato specifico.

**Prototipo**:
```java
boolean cancella(T dato)
```

**Come funziona**:

Prima (voglio cancellare B):
```
  HEAD
    │
    ▼
┌───────┬───────┐     ┌───────┬───────┐     ┌───────┬───────┐
│   A   │ NEXT  │────▶│   B   │ NEXT  │────▶│   C   │ NULL  │
└───────┴───────┘     └───────┴───────┘     └───────┴───────┘
```

1. Scorro la lista cercando il nodo con il dato, tenendo traccia del nodo precedente
2. Il NEXT del nodo precedente punta al nodo successivo a quello da cancellare
3. Il nodo da cancellare viene rimosso

Dopo:
```
  HEAD
    │
    ▼
┌───────┬───────┐     ┌───────┬───────┐
│   A   │ NEXT  │────▶│   C   │ NULL  │
└───────┴───────┘     └───────┴───────┘
```

**Costo**: O(n) - devo cercare il nodo da cancellare.

---

## Cancellazione di un oggetto di posizione nota

**Cosa fa**: Rimuove il nodo che si trova in una posizione specifica.

**Prototipo**:
```java
T cancellaInPosizione(int posizione)
```

**Come funziona**:

Prima (voglio cancellare la posizione 1, cioè B):
```
  HEAD
    │
    ▼
┌───────┬───────┐     ┌───────┬───────┐     ┌───────┬───────┐
│   A   │ NEXT  │────▶│   B   │ NEXT  │────▶│   C   │ NULL  │
└───────┴───────┘     └───────┴───────┘     └───────┴───────┘
```

1. Scorro la lista fino al nodo in posizione (posizione - 1)
2. Il NEXT del nodo precedente punta al nodo successivo a quello da cancellare
3. Restituisco il dato del nodo cancellato

Dopo:
```
  HEAD
    │
    ▼
┌───────┬───────┐     ┌───────┬───────┐
│   A   │ NEXT  │────▶│   C   │ NULL  │
└───────┴───────┘     └───────┴───────┘
```

**Costo**: O(n) - devo raggiungere la posizione specificata.

---

## Ricerca dell'esistenza di un oggetto noto

**Cosa fa**: Verifica se un dato è presente nella lista.

**Prototipo**:
```java
boolean contiene(T dato)
```

**Come funziona**:

```
  HEAD
    │
    ▼
┌───────┬───────┐     ┌───────┬───────┐     ┌───────┬───────┐
│   A   │ NEXT  │────▶│   B   │ NEXT  │────▶│   C   │ NULL  │
└───────┴───────┘     └───────┴───────┘     └───────┴───────┘
                            ▲
                            │
                      Cerco "B"?
                       Trovato!
```

1. Parto da HEAD
2. Confronto il dato di ogni nodo con quello cercato
3. Se trovo una corrispondenza, restituisco true
4. Se arrivo a NULL senza trovare, restituisco false

**Costo**: O(n) - nel caso peggiore devo scorrere tutta la lista.

---

## Determinazione della posizione di un oggetto noto

**Cosa fa**: Restituisce l'indice (posizione) di un dato nella lista.

**Prototipo**:
```java
int indiceDi(T dato)
```

**Come funziona**:

```
  HEAD
    │
    ▼
┌───────┬───────┐     ┌───────┬───────┐     ┌───────┬───────┐
│   A   │ NEXT  │────▶│   B   │ NEXT  │────▶│   C   │ NULL  │
└───────┴───────┘     └───────┴───────┘     └───────┴───────┘
  pos=0                 pos=1                 pos=2
```

1. Parto da HEAD con un contatore a 0
2. Confronto il dato di ogni nodo con quello cercato
3. Se trovo una corrispondenza, restituisco il contatore
4. Altrimenti incremento il contatore e passo al nodo successivo
5. Se arrivo a NULL, restituisco -1 (non trovato)

**Costo**: O(n) - nel caso peggiore devo scorrere tutta la lista.

---

## Aggiunta di una lista in coda a una lista data

**Cosa fa**: Concatena una seconda lista alla fine della prima.

**Prototipo**:
```java
void concatena(Lista<T> altraLista)
```

**Come funziona**:

Prima:
```
Lista 1:
  HEAD1
    │
    ▼
┌───────┬───────┐     ┌───────┬───────┐
│   A   │ NEXT  │────▶│   B   │ NULL  │
└───────┴───────┘     └───────┴───────┘

Lista 2:
  HEAD2
    │
    ▼
┌───────┬───────┐     ┌───────┬───────┐
│   X   │ NEXT  │────▶│   Y   │ NULL  │
└───────┴───────┘     └───────┴───────┘
```

1. Scorro la lista 1 fino all'ultimo nodo
2. Il NEXT dell'ultimo nodo punta a HEAD2

Dopo:
```
  HEAD1
    │
    ▼
┌───────┬───────┐     ┌───────┬───────┐
│   A   │ NEXT  │────▶│   B   │ NEXT  │───┐
└───────┴───────┘     └───────┴───────┘   │
                                          │
                ┌─────────────────────────┘
                │
                ▼
          ┌───────┬───────┐     ┌───────┬───────┐
          │   X   │ NEXT  │────▶│   Y   │ NULL  │
          └───────┴───────┘     └───────┴───────┘
```

**Costo**: O(n) - devo raggiungere l'ultimo nodo della prima lista.

---

## Leggere l'oggetto di testa

**Cosa fa**: Restituisce il dato del primo nodo senza rimuoverlo.

**Prototipo**:
```java
T leggiTesta()
```

**Come funziona**:

```
  HEAD
    │
    ▼
┌───────┬───────┐     ┌───────┬───────┐
│   A   │ NEXT  │────▶│   B   │ NULL  │
└───────┴───────┘     └───────┴───────┘
    │
    └──▶ Restituisco "A"
```

1. Restituisco il dato contenuto nel nodo puntato da HEAD

**Costo**: O(1) - accesso diretto tramite HEAD.

---

## Leggere l'oggetto in coda

**Cosa fa**: Restituisce il dato dell'ultimo nodo senza rimuoverlo.

**Prototipo**:
```java
T leggiCoda()
```

**Come funziona**:

```
  HEAD
    │
    ▼
┌───────┬───────┐     ┌───────┬───────┐
│   A   │ NEXT  │────▶│   B   │ NULL  │
└───────┴───────┘     └───────┴───────┘
                            │
                            └──▶ Restituisco "B"
```

1. Scorro la lista fino all'ultimo nodo (NEXT = NULL)
2. Restituisco il dato contenuto

**Costo**: O(n) - devo scorrere tutta la lista.

---

## Leggere un oggetto di posizione nota

**Cosa fa**: Restituisce il dato del nodo in una posizione specifica.

**Prototipo**:
```java
T leggiInPosizione(int posizione)
```

**Come funziona**:

```
  HEAD
    │
    ▼
┌───────┬───────┐     ┌───────┬───────┐     ┌───────┬───────┐
│   A   │ NEXT  │────▶│   B   │ NEXT  │────▶│   C   │ NULL  │
└───────┴───────┘     └───────┴───────┘     └───────┴───────┘
  pos=0                 pos=1                 pos=2
                          │
                          └──▶ leggiInPosizione(1) restituisce "B"
```

1. Scorro la lista contando i nodi
2. Quando raggiungo la posizione desiderata, restituisco il dato

**Costo**: O(n) - devo raggiungere la posizione specificata.

---

## Stabilire se la lista è vuota

**Cosa fa**: Verifica se la lista non contiene nodi.

**Prototipo**:
```java
boolean isEmpty()
```

**Come funziona**:

Lista vuota:
```
  HEAD
    │
    ▼
  NULL
```

Lista non vuota:
```
  HEAD
    │
    ▼
┌───────┬───────┐
│   A   │ NULL  │
└───────┴───────┘
```

1. Se HEAD == NULL, la lista è vuota → restituisco true
2. Altrimenti → restituisco false

**Costo**: O(1) - basta controllare HEAD.

---

## Conoscere la dimensione

**Cosa fa**: Restituisce il numero di nodi nella lista.

**Prototipo**:
```java
int size()
```

**Come funziona**:

```
  HEAD
    │
    ▼
┌───────┬───────┐     ┌───────┬───────┐     ┌───────┬───────┐
│   A   │ NEXT  │────▶│   B   │ NEXT  │────▶│   C   │ NULL  │
└───────┴───────┘     └───────┴───────┘     └───────┴───────┘
  cnt=1                 cnt=2                 cnt=3

Restituisco 3
```

1. Parto da HEAD con un contatore a 0
2. Per ogni nodo, incremento il contatore
3. Quando arrivo a NULL, restituisco il contatore

**Costo**: O(n) - devo scorrere tutta la lista.

---
