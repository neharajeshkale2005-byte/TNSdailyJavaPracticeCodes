import java.util.List;

public interface BankingService {

    void addCustomer(Customer customer);

    void createAccount(Account account);

    void addBeneficiary(Beneficiary beneficiary);

    void deposit(int accountID, double amount);

    void withdraw(int accountID, double amount);

    void transfer(int fromAccountID, int toAccountID, double amount);

    void checkBalance(int accountID);

    void showCustomers();

    void showAccounts();

    void showTransactions();

    void showBeneficiaries();
}