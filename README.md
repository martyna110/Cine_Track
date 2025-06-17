**CineTrack** è un progetto sviluppato da Acanfora Martina, studentessa del corso di studi Ingegneria informatica e scienze informatiche per la cybersecurity. 
Questa è un'app Android sviluppata in Kotlin che mostra un catalogo di film. L'utente può mettere "Mi piace" ai film preferiti tramite una stella e filtrare l'elenco per visualizzare solo quelli che ha apprezzato.

**Funzionalità principali:**
- Lista di film con immagine e titolo
- Pulsante "like" per salvare i preferiti
- Interruttore per mostrare solo i film piaciuti
  
**Tecnologie utilizzate:**

- Kotlin (Linguaggio principale dell'app)
- Retrofit (Per scaricare i dati da un'API online)
- RecyclerView (Per visualizzare in modo efficiente una lista di elementi)
- Synthetic Accessors (Per accedere facilmente agli elementi grafici nel codice (tramite ID))

**Come funziona:**

Quando l'app viene avviata, si collega automaticamente all'API pubblica del sito The Movie Database (TMDb) tramite Retrofit per scaricare l'elenco dei film più popolari.
I film vengono mostrati in una lista verticale usando RecyclerView. Si può indicare, per ogni film, se è piaciuto tramite una stella cliccabile. 
È presente uno switch in alto che permette di vedere solo i film che hanno ricevuto un like (una stella). 
Per far funzionare questo switch ho creato all'interno del file MovieAdapter.kt un metodo che cambia il contenuto della lista, cioè sostituisce la lista movies con newMovies (cioè l’elenco aggiornato, filtrato o meno),
e chiama notifyDataSetChanged() che dice al sistema che la lista è cambiata, e quindi la RecyclerView deve ridisegnare gli elementi visibili, senza ricaricare tutta l'app.

**Struttura del progetto**
- Movie.kt – È la data class che rappresenta il modello di un film. Contiene i campi id, title e poster, che vengono riempiti automaticamente dai dati JSON ricevuti dall’API.
- MovieApiInterface.kt – Definizione dell’interfaccia Retrofit
  - È un’interfaccia Retrofit dove si definisce la richiesta HTTP da eseguire.
  - Contiene una funzione getMovieList() annotata con @GET(...) per scaricare la lista dei film popolari da TMDb.
  - La funzione restituisce un oggetto Call<MovieResponse> che Retrofit userà per gestire la risposta.
- MovieApiService.kt – Qui viene creato e configurato l’oggetto Retrofit.
  Specifica l’URL base dell’API (https://api.themoviedb.org) e indica che i dati ricevuti vanno convertiti con Gson.
  Contiene un metodo getInstance() che restituisce il client Retrofit pronto all’uso in altre parti dell’app.
- MovieAdapter.kt – È l’adapter che collega i dati (cioè la lista dei film) alla RecyclerView. Si occupa di:
  - disegnare ogni singola riga (film) nella lista
  - mostrare immagine e titolo
  - gestire il clic sulla stella per aggiungere o rimuovere un "Mi piace"
  - filtrare i film usando il metodo updateData(...) quando lo switch viene attivato
- MainActivity.kt – È l’Activity principale, cioè la schermata principale dell’app. Gestisce:
  - l’avvio dell’app e il caricamento dei dati tramite Retrofit
  - l’inizializzazione della RecyclerView con l’adapter
  - il comportamento dello switch per filtrare i film piaciuti
  - l’aggiornamento della UI in base alle interazioni dell’utente
 
