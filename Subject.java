package com.example.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "subjects")
public class Subject implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "subject_name", nullable = false)
    private String subjectName;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "class_id", nullable = false)
    private StudentClass studentClass;
    
    // Default constructor
    public Subject() {}
    
    // Constructor with parameters
    public Subject(String subjectName, StudentClass studentClass) {
        this.subjectName = subjectName;
        this.studentClass = studentClass;
    }
    
    // Getters and Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getSubjectName() {
        return subjectName;
    }
    
    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }
    
    public StudentClass getStudentClass() {
        return studentClass;
    }
    
    public void setStudentClass(StudentClass studentClass) {
        this.studentClass = studentClass;
    }
    
    @Override
    public String toString() {
        return "Subject{" +
                "id=" + id +
                ", subjectName='" + subjectName + '\'' +
                ", studentClass=" + studentClass +
                '}';
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Subject subject = (Subject) o;
        return id != null ? id.equals(subject.id) : subject.id == null;
    }
    
    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
