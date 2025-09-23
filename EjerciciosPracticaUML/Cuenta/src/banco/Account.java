package banco;

public class Account {
    // Atributos privados
    private String id;
    private String name;
    private int balance = 0;

    // Constructor con id y nombre
    public Account(String id, String name) {
        this.id = id;
        this.name = name;
    }

    // Constructor con id, nombre y saldo
    public Account(String id, String name, int balance) {
        this.id = id;
        this.name = name;
        this.balance = balance;
    }

    // Getters
    public String getID() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getBalance() {
        return balance;
    }

    // Añadir dinero a la cuenta
    public int credit(int amount) {
        balance += amount;
        return balance;
    }

    // Retirar dinero de la cuenta
    public int debit(int amount) {
        if (amount <= balance) {
            balance -= amount;
        } else {
            System.out.println("Amount exceeded balance");
        }
        return balance;
    }

    // Transferir dinero a otra cuenta
    public int transferTo(Account another, int amount) {
        if (amount <= balance) {
            this.balance -= amount;
            another.credit(amount);
        } else {
            System.out.println("Amount exceeded balance");
        }
        return balance;
    }

    // toString
    @Override
    public String toString() {
        return "Account[id=" + id + ",name=" + name + ",balance=" + balance + "]";
    }
}
