# Git-Cheatsheet – NearbyFinder LK Informatik
> Albert-Einstein-Gymnasium Sankt Augustin

---

## Einmalig nach dem Klonen – eigenen Branch anlegen

```bash
# In den Projektordner wechseln
cd nearbyfinder

# Eigenen Branch anlegen (vornamename ersetzen!)
git checkout -b loesung-vornamename
```

Ab jetzt arbeitest du immer in deinem eigenen Branch.  
Dein Branch berührt nie den Code deiner Mitschüler.

---

## Täglicher Workflow – Fortschritt sichern

```bash
# 1. Alle geänderten Dateien zum nächsten Commit vormerken
git add src/

# 2. Prüfen was vorgemerkt ist (optional, aber empfohlen)
git status

# 3. Commit erstellen – kurze Beschreibung was du gemacht hast
git commit -m "Aufgabe 1.1 insert fertig"
```

> 💡 **Tipp:** Lieber oft committen als selten.  
> Jeder Commit ist ein Sicherungspunkt, zu dem du zurückkehren kannst.

---

## Updates vom Lehrer holen

Wenn der Lehrer das Grundgerüst aktualisiert hat:

```bash
# 1. Kurz auf main wechseln
git checkout main

# 2. Neueste Version holen
git pull

# 3. Zurück in deinen Branch
git checkout loesung-vornamename

# 4. Updates in deinen Branch übernehmen
git merge main
```

---

## Nützliche Befehle im Alltag

```bash
# Aktuellen Status anzeigen – was ist geändert?
git status

# Alle bisherigen Commits anzeigen
git log --oneline

# Welchen Branch bin ich gerade?
git branch

# Änderungen an einer Datei seit dem letzten Commit anzeigen
git diff src/kdbaum/LocationBST.java

# Einen alten Commit anschauen (ohne etwas zu verändern)
git checkout abc1234        # abc1234 = die kurze Commit-ID aus git log
git checkout loesung-vornamename   # danach wieder zurück
```

---

## Zu einem früheren Stand zurückkehren

```bash
# Alle lokalen Änderungen an einer Datei rückgängig machen
# ⚠️ Vorsicht: das kann nicht rückgängig gemacht werden!
git checkout -- src/kdbaum/LocationBST.java

# Zum Stand eines bestimmten Commits zurückgehen (sicher, nur anschauen)
git log --oneline           # Commit-ID heraussuchen
git checkout abc1234        # anschauen
git checkout loesung-vornamename   # wieder zurück zum eigenen Branch
```

---

## Auf main weitergearbeitet aber neue Änderungen benötigt

```bash
# 1. Aktuellen Branch umbenennen
# -m steht für "move/rename" – benennt den Branch lokal um,
# ohne die Commit-Historie zu verändern
git branch -m main feature

# 2. Alle Branches und Änderungen vom Remote holen,
# aber noch NICHT in den lokalen Branch einmergen (kein pull!)
# "origin" ist der Standardname für das Remote-Repository (z.B. auf GitHub)
# und wird beim "git clone" automatisch vergeben – es ist ein Alias für die
# hinterlegte URL (einsehbar mit: git remote -v)
git fetch origin

# 3. Neuen lokalen Branch "main" erstellen, der direkt auf
# origin/main zeigt (den gerade gefetchten Stand von GitHub)
# -b steht für "branch" – erstellt den Branch und wechselt sofort rein
# origin/main = der main-Branch so wie er auf dem Remote-Repository liegt
git checkout -b main origin/main

# 4. Zurück auf den feature-Branch wechseln
git checkout feature

# 5. Rebase: Nimmt deine Commits aus "feature" und setzt sie
# auf die Spitze des aktuellen "main" neu auf –
# so als hättest du sie erst NACH den neuen main-Commits gemacht
git rebase main
```

---

## Typische Fehlermeldungen

**„Please tell me who you are"**
```bash
git config --global user.email "deine@email.de"
git config --global user.name "Vorname Name"
```

**„fatal: not a git repository"**  
→ Du bist im falschen Ordner. Mit `cd nearbyfinder` in den Projektordner wechseln.

**„Your local changes would be overwritten"**  
→ Erst committen, dann pullen:
```bash
git add src/
git commit -m "Stand gesichert"
git pull
```

---

## Auf einen Blick – die 3 wichtigsten Befehle

| Was | Befehl |
|---|---|
| Änderungen sichern | `git add src/` → `git commit -m "..."` |
| Updates holen | `git checkout main` → `git pull` → `git checkout loesung-name` → `git merge main` |
| Status prüfen | `git status` |

---

*Bei Fragen: erst `git status` ausführen und die Ausgabe dem Lehrer zeigen – das hilft bei der Fehlersuche enorm!*
