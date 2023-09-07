package dto;

import lombok.*;

import java.sql.Date;
    @AllArgsConstructor
    @NoArgsConstructor
    @Setter
    @Getter
    @ToString
    public class StudentDTO {
        private String studentId;
        private String studentName;
        private String studentAddress;
        private String studentContact;
        private Date date;
        private String gender;
    }
