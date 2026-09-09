import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // ================= LOGIN =================

        System.out.println("=================================");
        System.out.println("       BANKING SYSTEM LOGIN");
        System.out.println("=================================");

        System.out.print("Username: ");
        String username = sc.nextLine();

        System.out.print("Password: ");
        String password = sc.nextLine();

        // Simple login details
        if (!username.equals("admin") || !password.equals("1234")) {

            System.out.println("\nInvalid username or password!");
            System.out.println("Login failed.");

            sc.close();
            return;
        }

        System.out.println("\nLogin successful!");
        System.out.println("Welcome, " + username + "!");

        // ================= BANKING SERVICE =================

        BankingService banking = new BankingServiceImpl();

        int choice;

        do {

            System.out.println("\n=================================");
            System.out.println("          BANKING SYSTEM");
            System.out.println("=================================");

            System.out.println("1. Add Customer");
            System.out.println("2. Create Account");
            System.out.println("3. Add Beneficiary");
            System.out.println("4. Deposit Money");
            System.out.println("5. Withdraw Money");
            System.out.println("6. Transfer Money");
            System.out.println("7. Check Balance");
            System.out.println("8. Show Customers");
            System.out.println("9. Show Accounts");
            System.out.println("10. Show Transactions");
            System.out.println("11. Show Beneficiaries");
            System.out.println("0. Logout");

            System.out.print("\nEnter your choice: ");
            choice = sc.nextInt();

            switch (choice) {

                // ========== ADD CUSTOMER ==========

                case 1:

                    System.out.print("Customer ID: ");
                    int customerID = sc.nextInt();

                    sc.nextLine();

                    System.out.print("Name: ");
                    String name = sc.nextLine();

                    System.out.print("Address: ");
                    String address = sc.nextLine();

                    System.out.print("Contact: ");
                    String contact = sc.nextLine();

                    Customer customer = new Customer(
                            customerID,
                            name,
                            address,
                            contact
                    );

                    banking.addCustomer(customer);

                    break;

                // ========== CREATE ACCOUNT ==========

                case 2:

                    System.out.print("Account ID: ");
                    int accountID = sc.nextInt();

                    System.out.print("Customer ID: ");
                    int custID = sc.nextInt();

                    sc.nextLine();

                    System.out.print("Account Type: ");
                    String type = sc.nextLine();

                    System.out.print("Initial Balance: ");
                    double balance = sc.nextDouble();

                    Account account = new Account(
                            accountID,
                            custID,
                            type,
                            balance
                    );

                    banking.createAccount(account);

                    break;

                // ========== ADD BENEFICIARY ==========

                case 3:

                    System.out.print("Beneficiary ID: ");
                    int beneficiaryID = sc.nextInt();

                    System.out.print("Customer ID: ");
                    int beneficiaryCustomerID = sc.nextInt();

                    sc.nextLine();

                    System.out.print("Beneficiary Name: ");
                    String beneficiaryName = sc.nextLine();

                    System.out.print("Account Number: ");
                    String accountNumber = sc.nextLine();

                    System.out.print("Bank Details: ");
                    String bankDetails = sc.nextLine();

                    Beneficiary beneficiary = new Beneficiary(
                            beneficiaryID,
                            beneficiaryCustomerID,
                            beneficiaryName,
                            accountNumber,
                            bankDetails
                    );

                    banking.addBeneficiary(beneficiary);

                    break;

                // ========== DEPOSIT ==========

                case 4:

                    System.out.print("Account ID: ");
                    int depositAccount = sc.nextInt();

                    System.out.print("Amount: ");
                    double depositAmount = sc.nextDouble();

                    banking.deposit(
                            depositAccount,
                            depositAmount
                    );

                    break;

                // ========== WITHDRAW ==========

                case 5:

                    System.out.print("Account ID: ");
                    int withdrawAccount = sc.nextInt();

                    System.out.print("Amount: ");
                    double withdrawAmount = sc.nextDouble();

                    banking.withdraw(
                            withdrawAccount,
                            withdrawAmount
                    );

                    break;

                // ========== TRANSFER ==========

                case 6:

                    System.out.print("From Account ID: ");
                    int fromAccount = sc.nextInt();

                    System.out.print("To Account ID: ");
                    int toAccount = sc.nextInt();

                    System.out.print("Amount: ");
                    double transferAmount = sc.nextDouble();

                    banking.transfer(
                            fromAccount,
                            toAccount,
                            transferAmount
                    );

                    break;

                // ========== CHECK BALANCE ==========

                case 7:

                    System.out.print("Account ID: ");
                    int checkAccount = sc.nextInt();

                    banking.checkBalance(checkAccount);

                    break;

                // ========== SHOW CUSTOMERS ==========

                case 8:

                    banking.showCustomers();

                    break;

                // ========== SHOW ACCOUNTS ==========

                case 9:

                    banking.showAccounts();

                    break;

                // ========== SHOW TRANSACTIONS ==========

                case 10:

                    banking.showTransactions();

                    break;

                // ========== SHOW BENEFICIARIES ==========

                case 11:

                    banking.showBeneficiaries();

                    break;

                // ========== LOGOUT ==========

                case 0:

                    System.out.println("\nYou have been logged out.");
                    System.out.println("Thank you for using Banking System!");

                    break;

                default:

                    System.out.println("\nInvalid choice!");

            }

        } while (choice != 0);

        sc.close();
    }
}