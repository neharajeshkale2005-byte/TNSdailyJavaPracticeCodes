import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BankingServiceImpl implements BankingService {

    private List<Customer> customers = new ArrayList<>();
    private List<Account> accounts = new ArrayList<>();
    private List<Transaction> transactions = new ArrayList<>();
    private List<Beneficiary> beneficiaries = new ArrayList<>();

    private int transactionID = 1;

    // ================= CONSTRUCTOR =================

    public BankingServiceImpl() {

        loadCustomers();
        loadAccounts();
        loadBeneficiaries();
        loadTransactions();
    }

    // ================= CUSTOMER =================

    @Override
    public void addCustomer(Customer customer) {

        customers.add(customer);

        try {

            FileWriter writer =
                    new FileWriter("data/customers.txt", true);

            writer.write(
                    customer.getCustomerID() + "," +
                    customer.getName() + "," +
                    customer.getAddress() + "," +
                    customer.getContact() + "\n"
            );

            writer.close();

            System.out.println("Customer saved successfully!");

        } catch (IOException e) {

            System.out.println("Error saving customer!");
        }
    }

    // ================= ACCOUNT =================

    @Override
    public void createAccount(Account account) {

        accounts.add(account);

        saveAccounts();

        System.out.println("Account created successfully!");
    }

    // ================= BENEFICIARY =================

    @Override
    public void addBeneficiary(Beneficiary beneficiary) {

        beneficiaries.add(beneficiary);

        saveBeneficiaries();

        System.out.println("Beneficiary saved successfully!");
    }

    // ================= DEPOSIT =================

    @Override
    public void deposit(int accountID, double amount) {

        for (Account account : accounts) {

            if (account.getAccountID() == accountID) {

                account.setBalance(
                        account.getBalance() + amount
                );

                saveAccounts();

                Transaction transaction =
                        new Transaction(
                                transactionID++,
                                accountID,
                                "Deposit",
                                amount
                        );

                transactions.add(transaction);

                saveTransaction(transaction);

                System.out.println(
                        "Amount deposited successfully!"
                );

                return;
            }
        }

        System.out.println("Account not found!");
    }

    // ================= WITHDRAW =================

    @Override
    public void withdraw(int accountID, double amount) {

        for (Account account : accounts) {

            if (account.getAccountID() == accountID) {

                if (account.getBalance() >= amount) {

                    account.setBalance(
                            account.getBalance() - amount
                    );

                    saveAccounts();

                    Transaction transaction =
                            new Transaction(
                                    transactionID++,
                                    accountID,
                                    "Withdraw",
                                    amount
                            );

                    transactions.add(transaction);

                    saveTransaction(transaction);

                    System.out.println(
                            "Amount withdrawn successfully!"
                    );

                } else {

                    System.out.println(
                            "Insufficient balance!"
                    );
                }

                return;
            }
        }

        System.out.println("Account not found!");
    }

    // ================= TRANSFER =================

    @Override
    public void transfer(
            int fromAccountID,
            int toAccountID,
            double amount) {

        Account fromAccount = null;
        Account toAccount = null;

        for (Account account : accounts) {

            if (account.getAccountID() == fromAccountID) {

                fromAccount = account;
            }

            if (account.getAccountID() == toAccountID) {

                toAccount = account;
            }
        }

        if (fromAccount == null || toAccount == null) {

            System.out.println("Account not found!");

            return;
        }

        if (fromAccount.getBalance() < amount) {

            System.out.println(
                    "Insufficient balance!"
            );

            return;
        }

        fromAccount.setBalance(
                fromAccount.getBalance() - amount
        );

        toAccount.setBalance(
                toAccount.getBalance() + amount
        );

        saveAccounts();

        Transaction transaction =
                new Transaction(
                        transactionID++,
                        fromAccountID,
                        "Transfer",
                        amount
                );

        transactions.add(transaction);

        saveTransaction(transaction);

        System.out.println(
                "Money transferred successfully!"
        );
    }

    // ================= CHECK BALANCE =================

    @Override
    public void checkBalance(int accountID) {

        for (Account account : accounts) {

            if (account.getAccountID() == accountID) {

                System.out.println(
                        "Current Balance: ₹" +
                        account.getBalance()
                );

                return;
            }
        }

        System.out.println("Account not found!");
    }

    // ================= SHOW CUSTOMERS =================

    @Override
    public void showCustomers() {

        System.out.println("\n--- CUSTOMERS ---");

        for (Customer customer : customers) {

            System.out.println(customer);
        }
    }

    // ================= SHOW ACCOUNTS =================

    @Override
    public void showAccounts() {

        System.out.println("\n--- ACCOUNTS ---");

        for (Account account : accounts) {

            System.out.println(account);
        }
    }

    // ================= SHOW TRANSACTIONS =================

    @Override
    public void showTransactions() {

        System.out.println("\n--- TRANSACTIONS ---");

        for (Transaction transaction : transactions) {

            System.out.println(transaction);
        }
    }

    // ================= SHOW BENEFICIARIES =================

    @Override
    public void showBeneficiaries() {

        System.out.println("\n--- BENEFICIARIES ---");

        for (Beneficiary beneficiary : beneficiaries) {

            System.out.println(beneficiary);
        }
    }

    // =====================================================
    // ================= SAVE ACCOUNTS =====================
    // =====================================================

    private void saveAccounts() {

        try {

            FileWriter writer =
                    new FileWriter("data/accounts.txt");

            for (Account account : accounts) {

                writer.write(
                        account.getAccountID() + "," +
                        account.getCustomerID() + "," +
                        account.getType() + "," +
                        account.getBalance() + "\n"
                );
            }

            writer.close();

        } catch (IOException e) {

            System.out.println(
                    "Error saving accounts!"
            );
        }
    }

    // =====================================================
    // ================= LOAD ACCOUNTS =====================
    // =====================================================

    private void loadAccounts() {

        try {

            BufferedReader reader =
                    new BufferedReader(
                            new FileReader(
                                    "data/accounts.txt"
                            )
                    );

            String line;

            while ((line = reader.readLine()) != null) {

                String[] data = line.split(",");

                if (data.length >= 4) {

                    int accountID =
                            Integer.parseInt(data[0]);

                    int customerID =
                            Integer.parseInt(data[1]);

                    String type =
                            data[2];

                    double balance =
                            Double.parseDouble(data[3]);

                    Account account =
                            new Account(
                                    accountID,
                                    customerID,
                                    type,
                                    balance
                            );

                    accounts.add(account);
                }
            }

            reader.close();

        } catch (IOException e) {

            // File may not exist on first run.

        } catch (Exception e) {

            System.out.println(
                    "Error loading accounts!"
            );
        }
    }

    // =====================================================
    // ================ SAVE BENEFICIARIES ================
    // =====================================================

    private void saveBeneficiaries() {

        try {

            FileWriter writer =
                    new FileWriter(
                            "data/beneficiaries.txt"
                    );

            for (Beneficiary beneficiary :
                    beneficiaries) {

                writer.write(
                        beneficiary.getBeneficiaryID() + "," +
                        beneficiary.getCustomerID() + "," +
                        beneficiary.getName() + "," +
                        beneficiary.getAccountNumber() + "," +
                        beneficiary.getBankDetails() + "\n"
                );
            }

            writer.close();

        } catch (IOException e) {

            System.out.println(
                    "Error saving beneficiaries!"
            );
        }
    }

    // =====================================================
    // ================ LOAD BENEFICIARIES ================
    // =====================================================

    private void loadBeneficiaries() {

        try {

            BufferedReader reader =
                    new BufferedReader(
                            new FileReader(
                                    "data/beneficiaries.txt"
                            )
                    );

            String line;

            while ((line = reader.readLine()) != null) {

                String[] data = line.split(",");

                if (data.length >= 5) {

                    int beneficiaryID =
                            Integer.parseInt(data[0]);

                    int customerID =
                            Integer.parseInt(data[1]);

                    String name =
                            data[2];

                    String accountNumber =
                            data[3];

                    String bankDetails =
                            data[4];

                    Beneficiary beneficiary =
                            new Beneficiary(
                                    beneficiaryID,
                                    customerID,
                                    name,
                                    accountNumber,
                                    bankDetails
                            );

                    beneficiaries.add(beneficiary);
                }
            }

            reader.close();

        } catch (IOException e) {

            // File may not exist on first run.

        } catch (Exception e) {

            System.out.println(
                    "Error loading beneficiaries!"
            );
        }
    }

    // =====================================================
    // ================= SAVE TRANSACTION ==================
    // =====================================================

    private void saveTransaction(
            Transaction transaction) {

        try {

            FileWriter writer =
                    new FileWriter(
                            "data/transactions.txt",
                            true
                    );

            writer.write(
                    transaction.getTransactionID() + "," +
                    transaction.getAccountID() + "," +
                    transaction.getType() + "," +
                    transaction.getAmount() + "," +
                    transaction.getTimestamp() + "\n"
            );

            writer.close();

        } catch (IOException e) {

            System.out.println(
                    "Error saving transaction!"
            );
        }
    }

    // =====================================================
    // ================= LOAD TRANSACTIONS =================
    // =====================================================

    private void loadTransactions() {

        try {

            BufferedReader reader =
                    new BufferedReader(
                            new FileReader(
                                    "data/transactions.txt"
                            )
                    );

            String line;

            while ((line = reader.readLine()) != null) {

                String[] data = line.split(",");

                if (data.length >= 4) {

                    int id =
                            Integer.parseInt(data[0]);

                    int accountID =
                            Integer.parseInt(data[1]);

                    String type =
                            data[2];

                    double amount =
                            Double.parseDouble(data[3]);

                    Transaction transaction =
                            new Transaction(
                                    id,
                                    accountID,
                                    type,
                                    amount
                            );

                    transactions.add(transaction);

                    if (id >= transactionID) {

                        transactionID = id + 1;
                    }
                }
            }

            reader.close();

        } catch (IOException e) {

            // File may not exist on first run.

        } catch (Exception e) {

            System.out.println(
                    "Error loading transactions!"
            );
        }
    }

    // =====================================================
    // ================= LOAD CUSTOMERS ====================
    // =====================================================

    private void loadCustomers() {

        try {

            BufferedReader reader =
                    new BufferedReader(
                            new FileReader(
                                    "data/customers.txt"
                            )
                    );

            String line;

            while ((line = reader.readLine()) != null) {

                String[] data = line.split(",");

                if (data.length >= 4) {

                    int customerID =
                            Integer.parseInt(data[0]);

                    String name =
                            data[1];

                    String address =
                            data[2];

                    String contact =
                            data[3];

                    Customer customer =
                            new Customer(
                                    customerID,
                                    name,
                                    address,
                                    contact
                            );

                    customers.add(customer);
                }
            }

            reader.close();

        } catch (IOException e) {

            // File may not exist on first run.

        } catch (Exception e) {

            System.out.println(
                    "Error loading customers!"
            );
        }
    }
}