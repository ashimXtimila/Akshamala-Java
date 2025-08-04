package com.example.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "students")
public class Student implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "student_name", nullable = false)
    private String studentName;
    
    @Column(name = "student_address", nullable = false)
    private String studentAddress;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "class_id", nullable = false)
    private StudentClass studentClass;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "subject_id", nullable = false)
    private Subject subject;
    
    // Default constructor
    public Student() {}
    
    // Constructor with parameters
    public Student(String studentName, String studentAddress, StudentClass studentClass, Subject subject) {
        this.studentName = studentName;
        this.studentAddress = studentAddress;
        this.studentClass = studentClass;
        this.subject = subject;
    }
    
    // Getters and Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getStudentName() {
        return studentName;
    }
    
    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }
    
    public String getStudentAddress() {
        return studentAddress;
    }
    
    public void setStudentAddress(String studentAddress) {
        this.studentAddress = studentAddress;
    }
    
    public StudentClass getStudentClass() {
        return studentClass;
    }
    
    public void setStudentClass(StudentClass studentClass) {
        this.studentClass = studentClass;
    }
    
    public Subject getSubject() {
        return subject;
    }
    
    public void setSubject(Subject subject) {
        this.subject = subject;
    }
    
    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", studentName='" + studentName + '\'' +
                ", studentAddress='" + studentAddress + '\'' +
                ", studentClass=" + studentClass +
                ", subject=" + subject +
                '}';
    }
}
