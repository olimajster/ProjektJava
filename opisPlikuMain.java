Ten kod w Javie to **prosty system zarządzania zadaniami w konsoli** – coś w rodzaju mini aplikacji „to-do list”.
Pozwala użytkownikowi tworzyć, edytować, usuwać i przeglądać **zadania zwykłe** oraz **zadania terminowe** (czyli z datą wykonania).
Poniżej szczegółowe omówienie, krok po kroku 👇

---

## 🔹 Struktura programu

* Główna klasa: `Main`
* Główna metoda: `public static void main(String[] args)`
* Wykorzystuje inne klasy z pakietu `Zadania.*`, m.in.:

  * `Zadania` – zwykłe zadania,
  * `ZadaniaTerminowe` – zadania z datą wykonania,
  * `Status` – enum ze statusami (`DONE`, `IN_PROGRESS`, `TODO`, `OVERDUE`),
  * `Table` i `TableTermin` – klasy do wyświetlania tabel z zadaniami,
  * `ConsoleColors` – klasa do kolorowania tekstu w konsoli.

---

## 🔹 Co robi program?

### 1. Inicjalizacja

Tworzone są dwie listy:

```java
List<Zadania> tasks = new ArrayList<>();
List<ZadaniaTerminowe> tasks_ter = new ArrayList<>();
```

oraz obiekt `Scanner` do wczytywania danych z klawiatury.

---

### 2. Główne menu

Program działa w pętli `while`, dopóki użytkownik nie wybierze opcji „Wyjdź”.

Wyświetlane są opcje:

```
[1] Pokaz zadania
[2] Pokaz zadania terminowe
[3] Wyjdz
```

Użytkownik wybiera numer i program przechodzi do odpowiedniego podmenu.

---

### 3. Menu „Zadania zwykłe”

Jeśli użytkownik wybierze `1`, program pokazuje listę zadań (przez `Table.printTable(tasks)`)
i wyświetla kolejne opcje:

```
[1] Dodaj zadania
[2] Usun zadania
[3] Edytuj zadania
[4] Zmień status
[5] Wyjdz
```

Każda z opcji uruchamia odpowiednią metodę pomocniczą:

| Funkcja        | Metoda                                       | Opis                                                                   |
| -------------- | -------------------------------------------- | ---------------------------------------------------------------------- |
| Dodaj zadanie  | `creatNew()`                                 | Pyta o nazwę i dodaje nowe zadanie z domyślnym statusem `IN_PROGRESS`. |
| Usuń zadanie   | `deleta()`                                   | Pyta o ID i usuwa odpowiedni obiekt z listy.                           |
| Edytuj zadanie | `editTask()`                                 | Pozwala zmienić nazwę, status i oznaczenie zakończenia zadania.        |
| Zmień status   | `editStatusTask()`                           | Pozwala zmienić tylko status po ID.                                    |
| Wyjście        | ustawia `j = false`, wraca do menu głównego. |                                                                        |

---

### 4. Menu „Zadania terminowe”

Działa podobnie do menu powyżej, ale operuje na liście `tasks_ter` (czyli zadaniach z datą).

Dostępne akcje:

```
[1] Dodaj zadania
[2] Usun zadania
[3] Edytuj zadania
[4] Zmień status
[5] Wyjdz
```

Odpowiadające metody:

| Funkcja          | Metoda                   | Opis                                                |
| ---------------- | ------------------------ | --------------------------------------------------- |
| Dodaj terminowe  | `creatNewTermin()`       | Pyta o nazwę i datę wykonania, dodaje nowe zadanie. |
| Usuń terminowe   | `deletaTermin()`         | Usuwa zadanie po ID.                                |
| Edytuj terminowe | `editTaskTermin()`       | Zmienia nazwę, status, informację o ukończeniu.     |
| Zmień status     | `editStatusTaskTermin()` | Zmienia tylko status po ID.                         |

---

### 5. Wyjście

Opcja `3` w głównym menu kończy pętlę `while` i zamyka program (`scan.close()`).

---

## 🔹 Wady / błędy w kodzie

1. **Porównanie stringów przy użyciu `==`**
   W kilku miejscach (np. `if (numer_2 == "5")`) użyto `==` zamiast `equals()`.
   W Javie należy używać:

   ```java
   if (numer_2.equals("5"))
   ```

   inaczej warunek może nie działać poprawnie.

2. **Błąd w wyszukiwaniu po ID**
   Jeśli zadanie o danym ID nie istnieje, komunikat o błędzie pojawia się dla każdego elementu pętli.
   Powinien pojawić się dopiero po zakończeniu pętli.

3. **Brak obsługi błędów / wyjątków**
   Program nie sprawdza poprawności danych wejściowych – np. wpisanie tekstu zamiast liczby spowoduje `InputMismatchException`.

4. **Niewyraźne typy pól w `editTask()`**
   W metodach edycji jest linijka:

   ```java
   System.out.println("Podaj nową date wwykonania zadania");
   boolean ukonczenie = scan.nextBoolean();
   ```

   Wygląda, jakby miała wczytywać datę, ale używa `boolean`. To prawdopodobnie pomyłka.

---

## 🔹 Podsumowanie działania

Program to **konsolowy menedżer zadań**, który umożliwia:

* przeglądanie listy zadań i zadań terminowych,
* dodawanie nowych zadań,
* usuwanie istniejących,
* edytowanie ich zawartości i statusu.

Wymaga klas pomocniczych (`Zadania`, `ZadaniaTerminowe`, `Table`, `Status` itd.), które nie są pokazane w kodzie, ale są kluczowe do działania.

---

Chcesz, żebym pokazał przykładowy wygląd klas `Zadania` i `ZadaniaTerminowe`, żeby lepiej zrozumieć, jak to wszystko współpracuje?
