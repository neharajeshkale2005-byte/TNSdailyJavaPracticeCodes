public class Customer extends Person {

    private int customerID;

    public Customer(int customerID, String name,
                    String address, String contact) {

        super(name, address, contact);

        this.customerID = customerID;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    @Override
    public String toString() {

        return "Customer ID: " + customerID +
               ", Name: " + getName() +
               ", Address: " + getAddress() +
               ", Contact: " + getContact();
    }
}