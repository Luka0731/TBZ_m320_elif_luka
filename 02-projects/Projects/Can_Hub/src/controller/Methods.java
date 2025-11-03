package controller;

import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

public class Methods {
    private static final String BASE_PATH = "C:\\Users\\elifb\\OneDrive\\Dokumente\\GitHub\\TBZ_m320_elif_luka\\02-projects\\Projects\\Can_Hub\\src";
    private static final Path HUB_PATH = Paths.get(BASE_PATH, "hub");
    private static final Path BEF_CHECK_PATH = Paths.get(BASE_PATH, "bef_check");
    private final Scanner sc = new Scanner(System.in);

    public void create_user() {
        System.out.println("Creating user...");
        System.out.print("Enter username: ");
        String name = sc.next();

        System.out.print("Enter email: ");
        String receiver = sc.next();

        Random rand = new Random();
        int zahl = rand.nextInt(1000);

        final String senderEmail = "fortnermontag@gmail.com";
        final String senderPassword = "wvsc mwuo hbsg wmhl"; // App-Passwort

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new jakarta.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(senderEmail, senderPassword);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(senderEmail));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(receiver));
            message.setSubject("Thank you for your registration");
            message.setText("Hello " + name + ",\n\nYour registration code is: " + zahl + "\n\nPlease enter this code to complete registration.");
            Transport.send(message);
            System.out.println("Email sent successfully!");
        } catch (MessagingException e) {
            e.printStackTrace();
            return;
        }

        System.out.print("Enter the code you received: ");
        int code = sc.nextInt();

        if (zahl == code) {
            System.out.println("Verification successful!");
            System.out.print("Enter password: ");
            String password = sc.next();

            String hashedPassword = hashPassword(password);

            String path = "C:\\Users\\elifb\\OneDrive\\Dokumente\\GitHub\\TBZ_m320_elif_luka\\02-projects\\Projects\\Can_Hub\\src\\user_dd\\"
                    + name + "_cv.txt";

            try (FileWriter writer = new FileWriter(path)) {
                writer.write("Username: " + name + "\n");
                writer.write("Email: " + receiver + "\n");
                writer.write("Password (SHA-256): " + hashedPassword + "\n");
                writer.write("Registration code: " + zahl + "\n");
                writer.write("Status: Verified\n");
                System.out.println("User saved successfully at:\n" + path);
            } catch (IOException e) {
                System.out.println("Error saving user file:");
                e.printStackTrace();
            }

        } else {
            System.out.println("Incorrect code. Registration failed.");
        }
    }

    public static String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(password.getBytes());
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }


    public void delete_user() {
        System.out.println("Deleting user...");
        System.out.print("Enter username to delete: ");
        String name = sc.next();

        String path = "C:\\Users\\elifb\\OneDrive\\Dokumente\\GitHub\\TBZ_m320_elif_luka\\02-projects\\Projects\\Can_Hub\\src\\user_dd\\"
                + name + "_cv.txt";

        File file = new File(path);

        if (!file.exists()) {
            System.out.println("❌ User file not found: " + path);
            return;
        }

        System.out.print("Are you sure you want to delete this user? (yes/no): ");
        String confirm = sc.next();

        if (confirm.equalsIgnoreCase("yes")) {
            if (file.delete()) {
                System.out.println("✅ User '" + name + "' deleted successfully.");
            } else {
                System.out.println("⚠️ Could not delete the user file.");
            }
        } else {
            System.out.println("Deletion cancelled.");
        }
    }

    public void createBranch(String branchName) {
        try {
            Path hubBranch = HUB_PATH.resolve(branchName);
            Path befBranch = BEF_CHECK_PATH.resolve(branchName);

            if (Files.exists(hubBranch) || Files.exists(befBranch)) {
                System.out.println("Branch '" + branchName + "' existiert bereits.");
                return;
            }

            Files.createDirectories(hubBranch);
            Files.createDirectories(befBranch);
            System.out.println("Branch '" + branchName + "' wurde erstellt.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Führt einen "Commit" aus: neue Dateien aus bef_check/<branch> werden in hub/<branch> verschoben.
     */
    public void commitBranch(String branchName) {
        Path branchBef = BEF_CHECK_PATH.resolve(branchName);
        Path branchHub = HUB_PATH.resolve(branchName);

        if (!Files.exists(branchBef) || !Files.exists(branchHub)) {
            System.out.println("Branch '" + branchName + "' existiert nicht. Erstelle ihn zuerst.");
            return;
        }

        File[] files = branchBef.toFile().listFiles();
        if (files == null || files.length == 0) {
            System.out.println("Keine neuen Dateien in " + branchBef);
            return;
        }

        System.out.println("Neue Dateien im Branch '" + branchName + "':");
        List<File> fileList = new ArrayList<>(Arrays.asList(files));

        for (int i = 0; i < fileList.size(); i++) {
            System.out.println((i + 1) + ") " + fileList.get(i).getName());
        }

        System.out.println("Gib die Nummern der Dateien ein, die du committen möchtest (z. B. 1,3,4 oder 'alle'): ");
        String input = sc.nextLine().trim();

        List<File> toCommit = new ArrayList<>();

        if (input.equalsIgnoreCase("alle")) {
            toCommit.addAll(fileList);
        } else {
            String[] parts = input.split(",");
            for (String p : parts) {
                try {
                    int index = Integer.parseInt(p.trim()) - 1;
                    if (index >= 0 && index < fileList.size()) {
                        toCommit.add(fileList.get(index));
                    }
                } catch (NumberFormatException ignored) {
                }
            }
        }

        if (toCommit.isEmpty()) {
            System.out.println("Keine Dateien ausgewählt.");
            return;
        }

        System.out.println("Commit-Message: ");
        String msg = sc.nextLine();

        for (File f : toCommit) {
            try {
                Path src = f.toPath();
                Path dest = branchHub.resolve(f.getName());
                Files.move(src, dest, StandardCopyOption.REPLACE_EXISTING);
                System.out.println("Committed: " + f.getName());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // Commit-Log speichern
        Path logFile = branchHub.resolve("commits.log");
        try {
            String logEntry = String.format("[%s] %s: %d Datei(en) → %s%n",
                    new Date(), branchName, toCommit.size(), msg);
            Files.writeString(logFile, logEntry, StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Commit abgeschlossen und Log gespeichert in " + logFile);
    }

    /**
     * Zeigt Commits für einen Branch
     */
    public void showLog(String branchName) {
        Path logFile = HUB_PATH.resolve(branchName).resolve("commits.log");
        if (!Files.exists(logFile)) {
            System.out.println("Keine Commits gefunden für Branch '" + branchName + "'.");
            return;
        }

        try {
            System.out.println("Commits für " + branchName + ":");
            Files.lines(logFile).forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void deleteBranch(String branchName) {
        try {
            deleteFolder(HUB_PATH.resolve(branchName));
            deleteFolder(BEF_CHECK_PATH.resolve(branchName));
            System.out.println("Branch '" + branchName + "' wurde gelöscht.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void deleteFolder(Path folder) throws IOException {
        if (!Files.exists(folder)) return;
        Files.walk(folder)
                .sorted(Comparator.reverseOrder())
                .map(Path::toFile)
                .forEach(File::delete);
    }


}
