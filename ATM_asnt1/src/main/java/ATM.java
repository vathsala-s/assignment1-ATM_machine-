import java.util.HashMap;
import java.util.Scanner;

public class ATM {
    private static HashMap<Integer, Double> accountBalances = new HashMap<>();
    private static HashMap<Integer, String> accountPINs = new HashMap<>();
    private static int currentAccountNumber;

    public static void main(String[] args) {
        System.out.println("Welcome to Automated Teller machine:) ");
        // Initialize some dummy accounts
        accountBalances.put(1234, 1000.0);
        accountPINs.put(1234, "1234");
        accountBalances.put(5678, 2000.0);
        accountPINs.put(5678, "5678");

        Scanner scanner = new Scanner(System.in);
        int choice = 0;
        boolean authenticated = false;

        do {
            if (!authenticated) {
                System.out.println("Enter your account number: ");
                int accountNumber = scanner.nextInt();
                System.out.println("Enter your PIN: ");
                String pin = scanner.next();

                if (verifyPIN(accountNumber, pin)) {
                    currentAccountNumber = accountNumber;
                    authenticated = true;
                } else {
                    System.out.println("Invalid account number or PIN. Please try again.");
                }
            } else {
                System.out.println("ATM System Menu:");
                System.out.println("1. Check Balance");
                System.out.println("2. Deposit Money");
                System.out.println("3. Withdraw Money");
                System.out.println("4. ChangePIN");
                System.out.println("5. Exit");
                System.out.print("Enter your choice: ");
                choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        checkBalance(currentAccountNumber);
                        break;
                    case 2:
                        depositMoney(currentAccountNumber, scanner);
                        break;
                    case 3:
                        withdrawMoney(currentAccountNumber, scanner);
                        break;
                    case 4:
                        ChangePIN(currentAccountNumber, scanner);
                        break;
                    case 5:
                        System.out.println("Exiting the system. Goodbye!");
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            }
        } while (true);
    }

    private static boolean verifyPIN(int accountNumber, String pin) {
        if (accountPINs.containsKey(accountNumber)) {
            String storedPin = accountPINs.get(accountNumber);
            return storedPin.equals(pin);
        }
        return false;
    }

    private static float checkBalance(int accountNumber) {
        if (accountBalances.containsKey(accountNumber)) {
            double balance = accountBalances.get(accountNumber);
            System.out.println("Your current balance is: $" + balance);
        } else {
            System.out.println("Account not found.");
        }
        return 0;
    }

    private static int depositMoney(int accountNumber, Scanner scanner) {
        System.out.print("Enter the amount you want to deposit: $");
        double amount = scanner.nextDouble();
        if (amount <= 0) {
            System.out.println("Invalid amount. Please enter a positive number.");
        } else {
            double currentBalance = accountBalances.get(accountNumber);
            currentBalance += amount;
            accountBalances.put(accountNumber, currentBalance);
            System.out.println("$" + amount + " deposited successfully.");
            System.out.println("Your new balance is: $" + currentBalance);
        }
        return accountNumber;
    }

    private static int withdrawMoney(int accountNumber, Scanner scanner) {
        System.out.print("Enter the amount you want to withdraw: $");
        double amount = scanner.nextDouble();
        if (amount <= 0 || !accountBalances.containsKey(accountNumber) || amount > accountBalances.get(accountNumber)) {
            System.out.println("Invalid amount or insufficient funds. Please try again.");
        } else {
            double currentBalance = accountBalances.get(accountNumber);
            currentBalance -= amount;
            accountBalances.put(accountNumber, currentBalance);
            System.out.println("$" + amount + " withdrawn successfully.");
            System.out.println("Your new balance is: $" + currentBalance);
        }
        return accountNumber;
    }
    private static int ChangePIN(int accountNumber, Scanner scanner) {
        System.out.println("Enter your current PIN: ");
        String currentPin = scanner.next();
        if (!verifyPIN(accountNumber, currentPin)) {
            System.out.println("Invalid PIN. PIN change failed.");
        }

        System.out.println("Enter your new PIN: ");
        String newPin = scanner.next();
        accountPINs.put(accountNumber, newPin);
        System.out.println("PIN changed successfully.");
        return accountNumber;
    }
}

