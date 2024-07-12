package org.example.basicapm.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "student")
public class Student {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;

  private int grade;

  @Column(name = "total_marks")
  private int totalMarks;

  private boolean passed;

  public Student() {}

  public Student(String name, int grade) {
    this.name = name;
    this.grade = grade;
  }

  public Long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public int getGrade() {
    return grade;
  }

  public int getTotalMarks() {
    return totalMarks;
  }

  public boolean isPassed() {
    return passed;
  }

  public void setTotalMarks(int totalMarks) {
    this.totalMarks = totalMarks;
  }

  public void setPassed(boolean passed) {
    this.passed = passed;
  }
}
