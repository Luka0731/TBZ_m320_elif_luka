package ch.tbz.budgedbuddy.ui;

import ch.tbz.budgedbuddy.exception.*;
import ch.tbz.budgedbuddy.parsing.Command;
import ch.tbz.budgedbuddy.parsing.CommandDispatcher;
import ch.tbz.budgedbuddy.service.BudgetService;
import ch.tbz.budgedbuddy.service.ReportingService;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Client {
    private BudgetService budgetService;
    private ReportingService reportingService;

    public Client(BudgetService budgetService, ReportingService reportingService) {
        this.budgetService = budgetService;
        this.reportingService = reportingService;
    }

    public void run() {
        System.out.println("BudgetBuddy gestartet. Tippe 'help' oder 'exit'.");
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))) {
            while (true) {
                System.out.print("> ");
                String inputLine = bufferedReader.readLine();
                if (inputLine == null) {
                    break;
                }
                if (inputLine.isBlank()) {
                    continue;
                }
                if ("exit".equalsIgnoreCase(inputLine.trim())) {
                    System.out.println("Programm beendet.");
                    break;
                }
                try {
                    Command command = CommandDispatcher.parse(inputLine);
                    String resultText = command.execute(budgetService, reportingService);
                    System.out.println(resultText);
                } catch (InvalidCommandException invalidCommandException) {
                    System.out.println("Befehl nicht erkannt: " + invalidCommandException.getMessage());
                } catch (ParseException parseException) {
                    System.out.println("Fehler beim Lesen der Eingabe: " + parseException.getMessage());
                } catch (ValidationException validationException) {
                    System.out.println("Validierungsfehler: " + validationException.getMessage());
                } catch (BudgetExceededException budgetExceededException) {
                    System.out.println("Budgetwarnung: " + budgetExceededException.getMessage());
                } catch (StorageException storageException) {
                    System.out.println("Speicherfehler: " + storageException.getMessage());
                } catch (Exception unexpectedException) {
                    System.out.println("Unerwarteter Fehler: " + unexpectedException.getMessage());
                }
            }
        } catch (Exception cliException) {
            System.out.println("CLI-Fehler: " + cliException.getMessage());
        }
    }
}