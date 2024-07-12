package org.example.basicapm.api;

import org.example.basicapm.domain.Student;
import org.example.basicapm.service.StudentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/students")
public class StudentAPI {

  private final StudentService studentService;

  public StudentAPI(StudentService studentService) {
    this.studentService = studentService;
  }

  @GetMapping()
  public List<Student> findAll() {
    return studentService.findAll();
  }

  @GetMapping(value = "{id}")
  public Student findById(@PathVariable Long id) {
    return studentService.findById(id);
  }

  @PostMapping()
  public Student save(@RequestBody StudentDto student) {
    return studentService.save(student);
  }
}
