Doppelkopf ist ein Teamspiel für 4 Spieler, bei dem die Teams zwar immer feststehen, aber niemand weiß in welchem Team die Mitspieler sind.
Grundsätzlich besteht das Re-Team aus den Spielern mit den Kreuz Damen (auch Re Damen oder "Alte" genannt), die Kontra-Partei sind die restlichen Spieler.

Das Spiel besteht aus Stichen, die vom Stich-Gewinner eingesammelt werden. Die Stiche geben bei der Auswertung Augen die über die Sieger-Partei entscheiden.
Die Re-Partei braucht zum Sieg 121 Augen, die Kontra-Partei 120 Augen.
Asse geben 11 Augen, 10en geben 10 Augen, Könige 4, Damen 3, Buben 2. Neunen haben keinen Wert.

Basierend auf den Augen werden die Punkte ausgezählt: Der Sieger erhält einen Punkt und einen Zusätzlichen für jede der folgenden Stufen:
150 Augen - "keine 90" vom Gegner
180 Augen - "keine 60" vom Gegner
210 Augen - "keine 30" vom Gegner
240 Augen - den Gegner "schwarz" gespielt

Kontra gewinnt bei einem Sieg "gegen die ALten" (also bei 120 Augen) einen Punkt dazu.
Spieler der Sieger-Partei können außerdem während des Spiel gewonnenen Zusatzpunkte ihrer Partei anrechnen.


Spielvorbereitung:
Ein Doppelkopf-Deck wird gleichmäßig an 4 Spieler verteilt.
Wenn ein Spieler 5 der schlechtesten Karte hat ("krank" ist) wird neu gemischt, vorausgesetzt dass man mit jener Regel spielt.
Da der Computer austeilt startet ein zufälliger Spieler. Anschließend beginnt der Gewinner des vorherigen Stichs.


Spielablauf:
Der erste Spieler spielt eine seiner Handkarten aus. Danach besteht für die anderen Spieler Bedienpflicht.
Unterschieden wird zwischen Trumpf und Fehl (-Farben).
Wurde Trumpf aufgespielt muss nach Möglichkeit Trumpf bedient werden, war das Aufspiel Fehl muss die entsprechende Farbe bedient werden.
Wenn alle Spieler eine Karte abgelegt haben wird der Sieger ermittelt.

Standardmäßig sind folgende Karten in folgender Reihenfolge Trumpf: Herz 10, alle Damen und Buben, sowie die restlichen Karo-Karten.
Eine Kreuz Dame ist höher als Pik. Darauf folgen Herz und Karo. (Gilt natürlich auch für Buben).
Die Karo-Karten werden so wie die Fehlfarben angeordnet: Ass übertrifft 10en, welche wiederum König überbieten. Neunen sind ausnahmslos die niedrigste Karte.
Es gewinnt immer die höchste Trumpf-Karte oder, sollte keine gespielt worden sein, die höchste Fehlkarte welche die richtige Farbe bedient.
Bei 2 identischen Karten gewinnt die zuerst gespielte. Ausnahme ist die Herz 10 - da kann, je nach Extra-Regel, die 2te die Erste übertrumpfen.
Der Sieger sammelt alle Karten des Stiches ein.

Im letzten Stich kann man einen Extra-Punkt dafür bekommen, mit dem Kreuz Buben ("Karlchen"/"Charly") zu gewinnen.
Sollte ein Karo Ass ("Fuchs") "gefangen" werden (die Gegenpartei gewinnt einen Stich mit besagter Karte der Eigenpartei) erhält der Sieger ebenfalls einen Extra-Punkt.


Auswertung:
Die Parteien zählen ihre Augen zusammen und berechnen danach die Punkte. Die Sieger bekommen die Punkte angerechnet, die Verlierer abgezogen.



Extra-Regeln:
- ohne Neunen spielen
- 2 Karo Asse sind der höchste Trumpf, wenn sie vom selben Spieler bessen werden.
- 2te "Dulle" sticht erste (Herz 10)
- 5 Neunen sorgt für Neuausteilung (oder König wenn man ohne Neunen spielt)

Diese Extra-Regeln sind in der GameManager-Klasse.


Soli:
Bevor die erste Karte ausgespielt wird muss jeder Spieler eine Vorbehaltsansage abgeben. Sollte kein Vorbehalt vorliegen wird "gesund" angesagt.
Mögliche Vorbehalte sind:
- Damen-Solo (nur Damen sind Trumpf)
- Buben-Solo (nur Buben sind Trumpf)
- fleischlos/Ass-Solo (keine Trümpfe)
- Farb-Solo (die angesagte Farbe ersetzt die Karo-Karten in der Trumpfreihenfolge)
    Bei einem Karo-Solo ändert sich die Trumpfreihenfolge nicht. Bei einem Herz-Solo bleibt die Herz 10 der höchste Trumpf.

Sagt man einen Vorbehalt an spielt man mit den neuen Trümpfen alleine die Re-Partei & ausgenommen beim Karo-Solo sind keine Schweinchen mehr möglich. 


Hat ein Spieler die beiden Alten kann er "gesund" sagen und ein Karo-Solo spielen oder "Hochzeit" ansagen und einen Mitspieler erhalten.
Sticht der Heiratende die ersten 3 Stiche selbst spielt er trotzdem alleine.
