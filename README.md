# MoneyMinder

A **JavaFX** desktop application for personal-finance management, built as an
ESPRIT group project. It covers user accounts, transactions, savings objectives,
a stock portfolio, a community forum, assets, and a reclamation (support) module,
backed by a MySQL database.

## Tech stack

Java · JavaFX (FXML views) · Maven · MySQL (JDBC) · JavaMail

## Modules

User management · Transactions · Savings objectives · Stock portfolio · Forum ·
Assets · Reclamations.

## Project structure

```
src/main/java/
  Controllers/   JavaFX controllers (one per view)
  Models/        domain entities
  Services/      business logic and data access
  Interfaces/    service contracts
  Utils/         database connection, mail, helpers
src/main/resources/
  *.fxml         JavaFX views
```

## Prerequisites

- JDK 17+ and Maven
- A running MySQL server with a `moneyminderdb` database
  (import `moneyminderdb.sql` to create the schema)

## Configuration

Configuration is read from **environment variables** — no credentials are stored
in the code. Copy `.env.example` and set the values in your shell or your IDE's
run configuration:

| Variable | Purpose | Default |
| --- | --- | --- |
| `DB_URL` | MySQL JDBC URL | `jdbc:mysql://localhost:3306/moneyminderdb` |
| `DB_USER` | Database user | `root` |
| `DB_PASSWORD` | Database password | (empty) |
| `MAIL_USERNAME` | Gmail address used to send mail | — |
| `MAIL_APP_PASSWORD` | Gmail **App Password** (not your account password) | — |

## Run

```bash
mvn clean javafx:run
```

Or open the project in IntelliJ IDEA and run the main application class, with the
environment variables set in the run configuration.
