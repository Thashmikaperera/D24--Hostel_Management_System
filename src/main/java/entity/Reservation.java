package entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.Data;


import java.sql.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Reservation {
    @Id
    private String resId; // Use an appropriate type for the primary key
    private Date date;
    private String status;

    @ToString.Exclude
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Student student;

    @ToString.Exclude
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Room room;
}
