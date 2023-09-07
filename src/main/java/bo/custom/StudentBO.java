package bo.custom;

import bo.SuperBO;
import dto.StudentDTO;

import java.util.List;

public interface StudentBO extends SuperBO {
    String generateStudentNextId();

    boolean saveStudent(StudentDTO studentDTO);

    boolean updateStudent(StudentDTO studentDTO);

    boolean deleteStudent(String studentId);

    List<StudentDTO> getAllStudent();
}
