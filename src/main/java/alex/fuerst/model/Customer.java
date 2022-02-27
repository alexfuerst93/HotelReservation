package alex.fuerst.model;

import java.util.regex.Pattern;

public class Customer {

    private String firstName;
    private String lastName;
    private final String email;

    public Customer(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;

        String validateEmail = "^(.+)@(.+).com$";
        Pattern pattern = Pattern.compile(validateEmail);
        if (!pattern.matcher(this.email).matches()) {
            throw new IllegalArgumentException("Please enter a valid email, like 'hans.meyer@gmail.com'.");
        }

    }

    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getEmail() { return email; }


    @Override
    public String toString() {
        return "First Name: " + firstName + " Last Name: " + lastName + " Email: " + email;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) { return true; }
        if (!(o instanceof Customer)) { return false; }
        Customer other = (Customer) o;
        boolean emailEquals = (this.email == null && other.email == null) || (this.email != null && this.email.equals(other.email));
        return emailEquals;
    }

    @Override
    public final int hashCode() {
        int result = 15;
        if (email != null) { result = 10 * result + email.hashCode(); }
        return result;
    }

}
