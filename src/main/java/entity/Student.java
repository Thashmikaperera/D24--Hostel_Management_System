package entity;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString

@NamedQuery(
        name = "Student.findLatestUserId",
        query = "SELECT s.studentId FROM Student s ORDER BY s.studentId DESC"
)
public class Student {
    @Id
    private String studentId;
    private String studentName;
    private String studentAddress;
    private String studentContact;
    private Date date;
    private String gender;
    @ToString.Exclude
    @OneToMany(targetEntity = Reservation.class, mappedBy = "student", cascade = CascadeType.ALL)
    private List<Reservation> reservationList = new ArrayList<>();

    public Student(String studentId, String name, String address, String contactNo, Date date, String gender) {
        this.studentId = studentId;
        this. studentName= name;
        this.studentAddress = address;
        this.studentContact = contactNo;
        this.date = date;
        this.gender = gender;
    }
}
