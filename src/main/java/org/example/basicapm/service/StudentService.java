package org.example.basicapm.service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.example.basicapm.api.StudentDto;
import org.example.basicapm.domain.Student;
import org.example.basicapm.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

  private final StudentRepository studentRepository;

  public StudentService(StudentRepository studentRepository) {
    this.studentRepository = studentRepository;
  }

  public List<Student> findAll() {
    return studentRepository.findAll();
  }

  public Student findById(Long id) {
    return studentRepository
        .findById(id)
        .orElseThrow(
            () -> new EntityNotFoundException("Student with ID [%d] not found".formatted(id)));
  }

  @Transactional
  public Student save(StudentDto student) {
    int totalMarks = student.totalMarks();

    if (totalMarks > 1000) {
      throw new IllegalArgumentException("Total marks can not be more than 1000");
    }
    Student optionalStudent = null;

    if (student.id() != null) {
      optionalStudent = findById(student.id());
    }

    if (optionalStudent == null) {
      optionalStudent = new Student(student.name(), student.grade());
    }

    optionalStudent.setTotalMarks(totalMarks);
    optionalStudent.setPassed(totalMarks >= 400);

    return studentRepository.save(optionalStudent);
  }
}
