package bo.custom.impl;

import bo.custom.StudentBO;
import dao.DAOFactory;
import dao.custom.StudentDAO;
import dto.StudentDTO;
import entity.Student;

import java.util.ArrayList;
import java.util.List;

public class StudentBOImpl implements StudentBO {
    StudentDAO studentDAO=(StudentDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.STUDENT);

    @Override
    public String generateStudentNextId() {
        return studentDAO.generateNextId();
    }

    @Override
    public boolean saveStudent(StudentDTO studentDTO) {
        return studentDAO.save(new Student(studentDTO.getStudentId(),studentDTO.getStudentName(),studentDTO.getStudentAddress(),studentDTO.getStudentContact(),studentDTO.getDate(),studentDTO.getGender()));
    }

    @Override
    public boolean updateStudent(StudentDTO studentDTO) {
        return studentDAO.update(new Student(studentDTO.getStudentId(),studentDTO.getStudentName(),studentDTO.getStudentAddress(),studentDTO.getStudentContact(),studentDTO.getDate(),studentDTO.getGender()));
    }

    @Override
    public boolean deleteStudent(String studentId) {
        Student student = new Student();
        student.setStudentId(studentId);
        return studentDAO.delete(student);
    }

    @Override
    public List<StudentDTO> getAllStudent() {
        List<StudentDTO> customerDTOArrayList = new ArrayList<>();
        List<Student> customerArrayList = studentDAO.getAll();
        for (Student student:customerArrayList) {
            customerDTOArrayList.add(new StudentDTO(student.getStudentId(),student.getStudentName(), student.getStudentAddress(), student.getStudentContact(),student.getDate(),student.getGender()));
        }
        return customerDTOArrayList;
    }
}
