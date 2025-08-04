package com.example.service;

import com.example.entity.Student;
import com.example.entity.StudentClass;
import com.example.entity.Subject;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Stateless
public class StudentService {
    
    @PersistenceContext(unitName = "studentPU")
    private EntityManager entityManager;
    
    // Student CRUD operations
    public void saveStudent(Student student) {
        entityManager.persist(student);
    }
    
    public void updateStudent(Student student) {
        entityManager.merge(student);
    }
    
    public void deleteStudent(Long id) {
        Student student = entityManager.find(Student.class, id);
        if (student != null) {
            entityManager.remove(student);
        }
    }
    
    public Student findStudentById(Long id) {
        return entityManager.find(Student.class, id);
    }
    
    public List<Student> findAllStudents() {
        TypedQuery<Student> query = entityManager.createQuery(
            "SELECT s FROM Student s ORDER BY s.id DESC", Student.class);
        return query.getResultList();
    }
    
    // StudentClass operations
    public List<StudentClass> findAllClasses() {
        TypedQuery<StudentClass> query = entityManager.createQuery(
            "SELECT sc FROM StudentClass sc ORDER BY sc.className", StudentClass.class);
        return query.getResultList();
    }
    
    public StudentClass findClassById(Long id) {
        return entityManager.find(StudentClass.class, id);
    }
    
    public void saveClass(StudentClass studentClass) {
        entityManager.persist(studentClass);
    }
    
    // Subject operations
    public List<Subject> findAllSubjects() {
        TypedQuery<Subject> query = entityManager.createQuery(
            "SELECT s FROM Subject s ORDER BY s.subjectName", Subject.class);
        return query.getResultList();
    }
    
    public List<Subject> findSubjectsByClassId(Long classId) {
        TypedQuery<Subject> query = entityManager.createQuery(
            "SELECT s FROM Subject s WHERE s.studentClass.id = :classId ORDER BY s.subjectName", 
            Subject.class);
        query.setParameter("classId", classId);
        return query.getResultList();
    }
    
    public Subject findSubjectById(Long id) {
        return entityManager.find(Subject.class, id);
    }
    
    public void saveSubject(Subject subject) {
        entityManager.persist(subject);
    }
    
    // Initialize default data
    public void initializeDefaultData() {
        // Check if data already exists
        List<StudentClass> existingClasses = findAllClasses();
        if (!existingClasses.isEmpty()) {
            return; // Data already initialized
        }
        
        // Create default classes
        StudentClass class10 = new StudentClass("Class 10");
        StudentClass class11 = new StudentClass("Class 11");
        StudentClass class12 = new StudentClass("Class 12");
        
        saveClass(class10);
        saveClass(class11);
        saveClass(class12);
        
        // Flush to get IDs
        entityManager.flush();
        
        // Create subjects for Class 10
        saveSubject(new Subject("Mathematics", class10));
        saveSubject(new Subject("Science", class10));
        saveSubject(new Subject("English", class10));
        saveSubject(new Subject("Social Studies", class10));
        
        // Create subjects for Class 11
        saveSubject(new Subject("Physics", class11));
        saveSubject(new Subject("Chemistry", class11));
        saveSubject(new Subject("Biology", class11));
        saveSubject(new Subject("Mathematics", class11));
        saveSubject(new Subject("English", class11));
        
        // Create subjects for Class 12
        saveSubject(new Subject("Advanced Physics", class12));
        saveSubject(new Subject("Advanced Chemistry", class12));
        saveSubject(new Subject("Advanced Biology", class12));
        saveSubject(new Subject("Calculus", class12));
        saveSubject(new Subject("Literature", class12));
    }
}
