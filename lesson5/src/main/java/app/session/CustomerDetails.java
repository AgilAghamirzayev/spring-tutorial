package app.session;

import lombok.Data;

@Data
public class CustomerDetails {
    public static final String ATTR = "cd";
    // booking
    private String seat;
    // customer
    private String firstname;
    private String lastname;
    // payment
    private String cardno;
}
