DROP TABLE PRENOTAZIONE;
DROP TABLE SPETTACOLO;
DROP TABLE FILM;
DROP TABLE GENERE;
DROP TABLE POSTO;
DROP TABLE PREZZO;
DROP TABLE UTENTE;
DROP TABLE RUOLO;
DROP TABLE SALA;

CREATE TABLE FILM
(
  ID_FILM INT PRIMARY KEY NOT NULL,
  TITOLO VARCHAR(100),
  ID_GENERE INT,
  URL_TRAILER VARCHAR(255),
  DURATA INT,
  TRAMA VARCHAR(1000),
  URI_LOCANDINA VARCHAR(255)
);
CREATE TABLE GENERE
(
  ID_GENERE INT PRIMARY KEY NOT NULL,
  DESCRIZIONE VARCHAR(50)
);
CREATE TABLE POSTO
(
  ID_POSTO INT PRIMARY KEY NOT NULL,
  ID_SALA INT,
  RIGA INT,
  COLONNA INT,
  ESISTE SMALLINT
);
CREATE TABLE PRENOTAZIONE
(
  ID_PRENOTAZIONE INT PRIMARY KEY NOT NULL,
  ID_UTENTE INT,
  ID_SPETTACOLO INT,
  ID_PREZZO INT,
  ID_POSTO INT,
  DATA_ORA_OPERAZIONE TIMESTAMP
);
CREATE TABLE PREZZO
(
  ID_PREZZO INT PRIMARY KEY NOT NULL,
  TIPO VARCHAR(50),
  PREZZO FLOAT(52)
);
CREATE TABLE RUOLO
(
  ID_RUOLO INT PRIMARY KEY NOT NULL,
  RUOLO VARCHAR(50)
);
CREATE TABLE SALA
(
  ID_SALA INT PRIMARY KEY NOT NULL,
  DESCRIZIONE VARCHAR(50)
);
CREATE TABLE SPETTACOLO
(
  ID_SPETTACOLO INT PRIMARY KEY NOT NULL,
  ID_FILM INT,
  DATA_ORA TIMESTAMP,
  ID_SALA INT
);
CREATE TABLE UTENTE
(
  ID_UTENTE INT PRIMARY KEY NOT NULL,
  EMAIL VARCHAR(50),
  PASSWORD VARCHAR(50),
  CREDITO FLOAT(52) DEFAULT 0 NOT NULL,
  ID_RUOLO INT
);
ALTER TABLE FILM ADD FOREIGN KEY (ID_GENERE) REFERENCES GENERE (ID_GENERE);
ALTER TABLE POSTO ADD FOREIGN KEY (ID_SALA) REFERENCES SALA (ID_SALA);
ALTER TABLE PRENOTAZIONE ADD FOREIGN KEY (ID_POSTO) REFERENCES POSTO (ID_POSTO);
ALTER TABLE PRENOTAZIONE ADD FOREIGN KEY (ID_PREZZO) REFERENCES PREZZO (ID_PREZZO);
ALTER TABLE PRENOTAZIONE ADD FOREIGN KEY (ID_SPETTACOLO) REFERENCES SPETTACOLO (ID_SPETTACOLO);
ALTER TABLE PRENOTAZIONE ADD FOREIGN KEY (ID_UTENTE) REFERENCES UTENTE (ID_UTENTE);
ALTER TABLE SPETTACOLO ADD FOREIGN KEY (ID_FILM) REFERENCES FILM (ID_FILM);
ALTER TABLE SPETTACOLO ADD FOREIGN KEY (ID_SALA) REFERENCES SALA (ID_SALA);
ALTER TABLE UTENTE ADD FOREIGN KEY (ID_RUOLO) REFERENCES RUOLO (ID_RUOLO);

INSERT INTO APP.GENERE (ID_GENERE, DESCRIZIONE) VALUES (1, 'Azione');
INSERT INTO APP.GENERE (ID_GENERE, DESCRIZIONE) VALUES (2, 'Fantascienza');
INSERT INTO APP.GENERE (ID_GENERE, DESCRIZIONE) VALUES (3, 'Commedia');
INSERT INTO APP.GENERE (ID_GENERE, DESCRIZIONE) VALUES (4, 'Drammatico');
INSERT INTO APP.GENERE (ID_GENERE, DESCRIZIONE) VALUES (5, 'Famiglia');
INSERT INTO APP.GENERE (ID_GENERE, DESCRIZIONE) VALUES (6, 'Animazione');
INSERT INTO APP.GENERE (ID_GENERE, DESCRIZIONE) VALUES (7, 'Horror');
INSERT INTO APP.GENERE (ID_GENERE, DESCRIZIONE) VALUES (8, 'Thriller');
INSERT INTO APP.GENERE (ID_GENERE, DESCRIZIONE) VALUES (9, 'Biografico');

INSERT INTO APP.SALA (ID_SALA, DESCRIZIONE) VALUES (1, 'Sala 1');
INSERT INTO APP.SALA (ID_SALA, DESCRIZIONE) VALUES (2, 'Sala 2');
INSERT INTO APP.SALA (ID_SALA, DESCRIZIONE) VALUES (3, 'Sala 3');
INSERT INTO APP.SALA (ID_SALA, DESCRIZIONE) VALUES (4, 'Sala 4');
INSERT INTO APP.SALA (ID_SALA, DESCRIZIONE) VALUES (5, 'Sala 5');

INSERT INTO APP.RUOLO (ID_RUOLO, RUOLO) VALUES (1, 'admin');
INSERT INTO APP.RUOLO (ID_RUOLO, RUOLO) VALUES (2, 'user');

INSERT INTO APP.PREZZO (ID_PREZZO, TIPO, PREZZO) VALUES (1, 'Normale', 7);
INSERT INTO APP.PREZZO (ID_PREZZO, TIPO, PREZZO) VALUES (2, 'Ridotto', 4);

INSERT INTO APP.FILM (ID_FILM, TITOLO, ID_GENERE, URL_TRAILER, DURATA, TRAMA, URI_LOCANDINA) VALUES (1, 'Star Wars: Episodio VII – Il risveglio della Forza', 2, 'https://www.youtube.com/embed/SxeTeSyMvXI', 136, 'Con l’Episodio 7 di Star Wars, Lucasfilm e il regista visionario J.J. Abrams hanno unito le forze per riportare il pubblico nella galassia lontana lontana di Star Wars, con l’arrivo sul grande schermo di Star Wars: Il Risveglio della Forza. Questo primo episodio della nuova trilogia di Guerre Stellari dovrebbe essere ambientato 30 anni dopo Il ritorno del Jedi.', 'http://blog.screenweek.it/wp-content/uploads/2015/10/star-wars-7-poster-italiano-717x1024.jpg');
INSERT INTO APP.FILM (ID_FILM, TITOLO, ID_GENERE, URL_TRAILER, DURATA, TRAMA, URI_LOCANDINA) VALUES (2, 'Natale col boss ', 3, 'https://www.youtube.com/embed/iN3ap2awDUc', 94, 'Il film racconta la storia di Alex e Dino (Lillo e Greg), due affermati chirurghi plastici abituati a cambiare i connotati dei loro pazienti con pochi e delicati colpi di bisturi. Leo e Cosimo (Paolo Ruffini e Francesco Mandelli) invece sono due maldestri poliziotti sulle tracce di un pericoloso e potente boss di cui nessuno conosce il volto. Alex, Dino, Leo, Cosimo e il Boss inciamperanno l’uno nella vita dell’altro, in una commedia piena di equivoci, colpi di scena e grandi risate, in cui ognuno alla fine cercherà di…salvare la ‘faccia’.', 'http://th.cineblog.it/pwUtTplQ4x-956IV_XizPxPwIE4=/fit-in/655x437/http://media.cineblog.it/2/27c/natale-col-boss-trailer-poster-e-foto-della-commedia-natalizia-con-lillo-amp-greg-1.jpg');
INSERT INTO APP.FILM (ID_FILM, TITOLO, ID_GENERE, URL_TRAILER, DURATA, TRAMA, URI_LOCANDINA) VALUES (3, 'Irrational Man ', 4, 'https://www.youtube.com/embed/GRQ65dRLm1k', 96, 'Abe Lucas, professore di Filosofia, è emotivamente provato ed incapace di dare un significato alla sua vita. Poco dopo il suo arrivo come nuovo insegnante presso il college di una piccola città, Abe si ritrova coinvolto nella vita di due donne: Rita Richards, professoressa solitaria che spera che lui la salvi dal suo matrimonio infelice, e Jill Pollard, la sua migliore allieva che è anche la sua migliore amica. Il caso spariglia le carte quando Abe e Jill si trovano ad origliare la conversazione di un estraneo, rimanendone invischiati…', 'http://pad.mymovies.it/cinemanews/2015/122300/locandina1300.jpg');
INSERT INTO APP.FILM (ID_FILM, TITOLO, ID_GENERE, URL_TRAILER, DURATA, TRAMA, URI_LOCANDINA) VALUES (4, 'Il ponte delle spie', 8, 'https://www.youtube.com/embed/8JICEhUw9C0', 141, 'Il titolo del film, Il ponte delle spie, fa riferimento a un ponte realmente esistente a Berlino, che un tempo univa la zona est e quella ovest, oggi noto come Ponte di Glienicke. Il soprannome gli viene dal fatto di essere stato spesso teatro di scambi di prigionieri tra i servizi segreti americani e quelli della Germania Est. Il ponte delle spie racconta la storia di James Donovan (Tom Hanks), un famoso avvocato di Brooklyn che si ritrova al centro della Guerra Fredda quando la CIA lo ingaggia per un compito quasi impossibile: la negoziazione per il rilascio di un pilota statunitense, Francis Gary Powers, abbattuto nei cieli dell’Unione Sovietica mentre volava a bordo di un aereo spia U2.', 'http://i0.wp.com/newsgate.it/wp-content/uploads/2015/10/bridge-of-spies-il-ponte-delle-spie-news-gate-steven-spielberg.jpg');
INSERT INTO APP.FILM (ID_FILM, TITOLO, ID_GENERE, URL_TRAILER, DURATA, TRAMA, URI_LOCANDINA) VALUES (5, 'Vacanze ai Caraibi', 3, 'https://www.youtube.com/embed/vGuxL9JLL6A', 120, 'Siete pronti a partire per i Caraibi? Quest’anno a Natale si festeggia con Neri Parenti il ritorno del cinepanettone doc con “Vacanze ai Caraibi”. Nel cast, ovviamente, non possono mancare Christian De Sica e Massimo Ghini. Con loro anche Angela Finocchiaro, Luca Argentero e Ilaria Spada.', 'http://www.cinemadelsilenzio.it/images/film/poster/11088_big.jpg');
INSERT INTO APP.FILM (ID_FILM, TITOLO, ID_GENERE, URL_TRAILER, DURATA, TRAMA, URI_LOCANDINA) VALUES (6, 'Il professor Cenerentolo', 3, 'https://www.youtube.com/embed/NRWwVWJEBPo', 93, 'Umberto, per evitare il fallimento della sua disastrata ditta di costruzioni, insieme a un suo dipendente ha tentato un maldestro colpo in banca che gli ha fruttato, però, solo quattro anni di reclusione nella prigione di una bellissima isola italiana: Ventotene. Giunto alla fine della pena, Umberto di giorno lavora nella biblioteca del paese. Una sera, durante un dibattito aperto al pubblico in carcere, Umberto conosce Morgana, una donna affascinante, un po’ folle e un po’ bambina. Morgana crede che lui lavori nel carcere e che non sia un detenuto. Umberto, approfittando dell’equivoco, inizia a frequentarla durante l’orario di lavoro in biblioteca. Ma ogni giorno entro la mezzanotte, proprio come Cenerentola, deve rientrare di corsa nella struttura per evitare che il direttore del carcere scopra il tutto e gli revochi il permesso di lavoro in esterno.', 'http://static.screenweek.it/2015/11/9/Il_Professor_Cenerentolo_Poster_Italia_01_mid.jpg');
INSERT INTO APP.FILM (ID_FILM, TITOLO, ID_GENERE, URL_TRAILER, DURATA, TRAMA, URI_LOCANDINA) VALUES (7, 'Belle e Sebastien', 5, 'https://www.youtube.com/embed/yq0jFSjQuJo', 120, 'Dopo lo straordinario successo del primo capitolo, tornano Belle e Sebastien in un’avventura con più azione, nuovi importantissimi personaggi e sempre tante, tantissime emozioni. Sebastien attende con ansia il ritorno di Angelina che è in procinto di tornare a casa con tutti gli onori: è infatti stata insignita di una medaglia al valore per i servizi resi nel corso della guerra. Il giorno tanto atteso arriva ma Angelina rimane vittima di un terribile incidente aereo e data per morta dalle autorità locali. Sebastien però non si rassegna all’idea di averla perduta e decide di andare a cercarla insieme al nonno e al suo inseparabile amico a quattro zampe. Nel corso della spedizione di salvataggio Sebastien si troverà di fronte ad una grande scoperta che cambierà la sua vita.', 'http://ftv01.stbm.it/imgbank/GALLERYXL/R201511/Belle_Seastien_Prato140x200.jpg');
INSERT INTO APP.FILM (ID_FILM, TITOLO, ID_GENERE, URL_TRAILER, DURATA, TRAMA, URI_LOCANDINA) VALUES (8, 'Chiamatemi Francesco - Il papa della gente', 9, 'https://www.youtube.com/embed/vD7vYmPmHCE', 120, 'La vicenda umana e pastorale di Jorge Mario Bergoglio dalla sua gioventù fino all’elezione al soglio pontificio come Papa Francesco nel 2013, attraversando le sue esperienze di vita: professore di scuola superiore, giovane Padre Provinciale dei Gesuiti argentini durante gli anni bui della dittatura militare, Arcivescovo di Buenos Aires durante la drammatica crisi economica che ha colpito l’Argentina negli ultimi decenni.', 'http://images.movieplayer.it/images/2015/10/29/chiamatemi-francesco-locandina.jpg');
INSERT INTO APP.FILM (ID_FILM, TITOLO, ID_GENERE, URL_TRAILER, DURATA, TRAMA, URI_LOCANDINA) VALUES (9, 'Revenant - Redivivo', 4, 'https://www.youtube.com/embed/xrctuMnFDc4', 156, 'Tratto da una storia vera, Revenant racconta l''epica avventura di un uomo che cerca di sopravvivere grazie alla straordinaria forza del proprio spirito. In una spedizione nelle vergini terre americane, l''esploratore Hugh Glass (Leonardo Di Caprio) viene brutalmente attaccato da un orso e dato per morto dai membri del suo stesso gruppo di cacciatori. Nella sua lotta per la sopravvivenza, Glass sopporta inimmaginabili sofferenze, tra cui anche il tradimento del suo compagno John Fitzgerald (Tom Hardy). Mosso da una profonda determinazione e dall''amore per la sua famiglia, Glass dovrà superare un duro inverno nell''implacabile tentativo di sopravvivere e di trovare la sua redenzione.', 'http://www.filmscoop.it/locandine/revenant-redivivo.jpg');
INSERT INTO APP.FILM (ID_FILM, TITOLO, ID_GENERE, URL_TRAILER, DURATA, TRAMA, URI_LOCANDINA) VALUES (10, 'Creed - nato per combattere', 1, 'https://www.youtube.com/embed/my81HQ4UpQs', 133, 'Adonis Johnson non ha mai conosciuto il suo celebre padre, il campione del mondo dei pesi massimi Apollo Creed, morto prima della sua nascita. Nonostante tutto, non c''è modo di negare che la boxe scorra nelle sue vene, quindi Adonis va a Philadelphia, luogo del leggendario incontro tra Apollo Creed e lo sfidante Rocky Balboa. Una volta arrivato in città, Adonis rintraccia Rocky e gli chiede di essere il suo allenatore. Nonostante l''insistenza nello spiegare al giovane che lui ormai è fuori dal giro da parecchio tempo, Rocky vede in Adonis la stessa forza e determinazione caratteristiche di Apollo, il fiero rivale che diventò anche l''amico più stretto.', 'http://pad.mymovies.it/filmclub/2015/04/113/locandinapg1.jpg');

INSERT INTO APP.SPETTACOLO (ID_SPETTACOLO, ID_FILM, DATA_ORA, ID_SALA) VALUES (1, 1, '2015-12-19 17:47:12.165000000', 1);
INSERT INTO APP.SPETTACOLO (ID_SPETTACOLO, ID_FILM, DATA_ORA, ID_SALA) VALUES (2, 2, '2015-12-19 17:47:12.165000000', 2);
INSERT INTO APP.SPETTACOLO (ID_SPETTACOLO, ID_FILM, DATA_ORA, ID_SALA) VALUES (3, 3, '2015-12-19 17:47:12.165000000', 3);
INSERT INTO APP.SPETTACOLO (ID_SPETTACOLO, ID_FILM, DATA_ORA, ID_SALA) VALUES (4, 4, '2015-12-19 17:47:12.165000000', 4);
INSERT INTO APP.SPETTACOLO (ID_SPETTACOLO, ID_FILM, DATA_ORA, ID_SALA) VALUES (5, 5, '2015-12-19 17:47:12.165000000', 5);

INSERT INTO APP.UTENTE (ID_UTENTE, ID_RUOLO, EMAIL, PASSWORD, CREDITO) VALUES (1, 1, 'matteogabburo@gmail.com', 'ciao', 0);
INSERT INTO APP.UTENTE (ID_UTENTE, ID_RUOLO, EMAIL, PASSWORD, CREDITO) VALUES (2, 2, 'matteogabburo.unitn@gmail.com', 'ciao', 0);
INSERT INTO APP.UTENTE (ID_UTENTE, ID_RUOLO, EMAIL, PASSWORD, CREDITO) VALUES (3, 2, 'user1@asdaipaifpa.com', 'ciao', 0);
INSERT INTO APP.UTENTE (ID_UTENTE, ID_RUOLO, EMAIL, PASSWORD, CREDITO) VALUES (4, 2, 'user2@asdaipaifpa.com', 'ciao', 0);
INSERT INTO APP.UTENTE (ID_UTENTE, ID_RUOLO, EMAIL, PASSWORD, CREDITO) VALUES (5, 2, 'user3@asdaipaifpa.com', 'ciao', 0);
INSERT INTO APP.UTENTE (ID_UTENTE, ID_RUOLO, EMAIL, PASSWORD, CREDITO) VALUES (6, 2, 'user4@asdaipaifpa.com', 'ciao', 0);
INSERT INTO APP.UTENTE (ID_UTENTE, ID_RUOLO, EMAIL, PASSWORD, CREDITO) VALUES (7, 2, 'user5@asdaipaifpa.com', 'ciao', 0);
INSERT INTO APP.UTENTE (ID_UTENTE, ID_RUOLO, EMAIL, PASSWORD, CREDITO) VALUES (8, 2, 'user6@asdaipaifpa.com', 'ciao', 0);
INSERT INTO APP.UTENTE (ID_UTENTE, ID_RUOLO, EMAIL, PASSWORD, CREDITO) VALUES (9, 2, 'user7@asdaipaifpa.com', 'ciao', 0);
INSERT INTO APP.UTENTE (ID_UTENTE, ID_RUOLO, EMAIL, PASSWORD, CREDITO) VALUES (10,2, 'user8@asdaipaifpa.com', 'ciao', 0);