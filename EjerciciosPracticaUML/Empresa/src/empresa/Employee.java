package empresa;

public class Employee {
    // Atributos privados
    private int id;
    private String firstName;
    private String lastName;
    private int salary;

    // Constructor
    public Employee(int id, String firstName, String lastName, int salary) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.salary = salary;
    }

    // Métodos getters
    public int getID() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getName() {
        return firstName + " " + lastName;
    }

    public int getSalary() {
        return salary;
    }

    // Setter
    public void setSalary(int salary) {
        this.salary = salary;
    }

    // Método para calcular salario anual
    public int getAnnualSalary() {
        return salary * 12;
    }

    // Método para aumentar el salario
    public int raiseSalary(int percent) {
        salary += salary * percent / 100;
        return salary;
    }

    // Método toString
    @Override
    public String toString() {
        return "Employee[id=" + id + ", name=" + getName() + ", salary=" + salary + "]";
    }
}
