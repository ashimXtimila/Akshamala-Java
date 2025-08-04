package com.example.controller;

import com.example.entity.Student;
import com.example.entity.StudentClass;
import com.example.entity.Subject;
import com.example.service.StudentService;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@ManagedBean(name = "studentController")
@ViewScoped
public class StudentController implements Serializable {
    
    @EJB
    private StudentService studentService;
    
    private Student student;
    private Student selectedStudent;
    private List<Student> students;
    private List<StudentClass> classes;
    private List<Subject> subjects;
    private List<Subject> availableSubjects;
    private List<Subject> editAvailableSubjects;
    
    @PostConstruct
    public void init() {
        student = new Student();
        selectedStudent = new Student();
        students = new ArrayList<>();
        classes = new ArrayList<>();
        subjects = new ArrayList<>();
        availableSubjects = new ArrayList<>();
        editAvailableSubjects = new ArrayList<>();
        
        // Initialize default data
        studentService.initializeDefaultData();
        
        // Load data
        loadStudents();
        loadClasses();
        loadSubjects();
    }
    
    public void loadStudents() {
        students = studentService.findAllStudents();
    }
    
    public void loadClasses() {
        classes = studentService.findAllClasses();
    }
    
    public void loadSubjects() {
        subjects = studentService.findAllSubjects();
    }
    
    public void onClassChange() {
        availableSubjects.clear();
        if (student.getStudentClass() != null && student.getStudentClass().getId() != null) {
            availableSubjects = studentService.findSubjectsByClassId(student.getStudentClass().getId());
        }
        student.setSubject(null); // Reset subject selection
    }
    
    public void onEditClassChange() {
        editAvailableSubjects.clear();
        if (selectedStudent.getStudentClass() != null && selectedStudent.getStudentClass().getId() != null) {
            editAvailableSubjects = studentService.findSubjectsByClassId(selectedStudent.getStudentClass().getId());
        }
        selectedStudent.setSubject(null); // Reset subject selection
    }
    
    public void saveStudent() {
        try {
            if (student.getStudentName() == null || student.getStudentName().trim().isEmpty()) {
                addMessage(FacesMessage.SEVERITY_ERROR, "Error", "Student name is required");
                return;
            }
            
            if (student.getStudentAddress() == null || student.getStudentAddress().trim().isEmpty()) {
                addMessage(FacesMessage.SEVERITY_ERROR, "Error", "Student address is required");
                return;
            }
            
            if (student.getStudentClass() == null || student.getStudentClass().getId() == null) {
                addMessage(FacesMessage.SEVERITY_ERROR, "Error", "Please select a class");
                return;
            }
            
            if (student.getSubject() == null || student.getSubject().getId() == null) {
                addMessage(FacesMessage.SEVERITY_ERROR, "Error", "Please select a subject");
                return;
            }
            
            // Fetch the actual entities from database
            StudentClass actualClass = studentService.findClassById(student.getStudentClass().getId());
            Subject actualSubject = studentService.findSubjectById(student.getSubject().getId());
            
            student.setStudentClass(actualClass);
            student.setSubject(actualSubject);
            
            studentService.saveStudent(student);
            
            addMessage(FacesMessage.SEVERITY_INFO, "Success", "Student saved successfully");
            
            // Reset form
            student = new Student();
            availableSubjects.clear();
            
            // Reload students
            loadStudents();
            
        } catch (Exception e) {
            addMessage(FacesMessage.SEVERITY_ERROR, "Error", "Failed to save student: " + e.getMessage());
        }
    }
    
    public void editStudent(Student studentToEdit) {
        selectedStudent = new Student();
        selectedStudent.setId(studentToEdit.getId());
        selectedStudent.setStudentName(studentToEdit.getStudentName());
        selectedStudent.setStudentAddress(studentToEdit.getStudentAddress());
        selectedStudent.setStudentClass(studentToEdit.getStudentClass());
        selectedStudent.setSubject(studentToEdit.getSubject());
        
        // Load available subjects for the selected class
        if (selectedStudent.getStudentClass() != null) {
            editAvailableSubjects = studentService.findSubjectsByClassId(selectedStudent.getStudentClass().getId());
        }
    }
    
    public void updateStudent() {
        try {
            if (selectedStudent.getStudentName() == null || selectedStudent.getStudentName().trim().isEmpty()) {
                addMessage(FacesMessage.SEVERITY_ERROR, "Error", "Student name is required");
                return;
            }
            
            if (selectedStudent.getStudentAddress() == null || selectedStudent.getStudentAddress().trim().isEmpty()) {
                addMessage(FacesMessage.SEVERITY_ERROR, "Error", "Student address is required");
                return;
            }
            
            if (selectedStudent.getStudentClass() == null || selectedStudent.getStudentClass().getId() == null) {
                addMessage(FacesMessage.SEVERITY_ERROR, "Error", "Please select a class");
                return;
            }
            
            if (selectedStudent.getSubject() == null || selectedStudent.getSubject().getId() == null) {
                addMessage(FacesMessage.SEVERITY_ERROR, "Error", "Please select a subject");
                return;
            }
            
            // Fetch the actual entities from database
            StudentClass actualClass = studentService.findClassById(selectedStudent.getStudentClass().getId());
            Subject actualSubject = studentService.findSubjectById(selectedStudent.getSubject().getId());
            
            selectedStudent.setStudentClass(actualClass);
            selectedStudent.setSubject(actualSubject);
            
            studentService.updateStudent(selectedStudent);
            
            addMessage(FacesMessage.SEVERITY_INFO, "Success", "Student updated successfully");
            
            // Reload students
            loadStudents();
            
        } catch (Exception e) {
            addMessage(FacesMessage.SEVERITY_ERROR, "Error", "Failed to update student: " + e.getMessage());
        }
    }
    
    public void deleteStudent(Student studentToDelete) {
        try {
            studentService.deleteStudent(studentToDelete.getId());
            addMessage(FacesMessage.SEVERITY_INFO, "Success", "Student deleted successfully");
            loadStudents();
        } catch (Exception e) {
            addMessage(FacesMessage.SEVERITY_ERROR, "Error", "Failed to delete student: " + e.getMessage());
        }
    }
    
    private void addMessage(FacesMessage.Severity severity, String summary, String detail) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(severity, summary, detail));
    }
    
    // Getters and Setters
    public Student getStudent() {
        return student;
    }
    
    public void setStudent(Student student) {
        this.student = student;
    }
    
    public Student getSelectedStudent() {
        return selectedStudent;
    }
    
    public void setSelectedStudent(Student selectedStudent) {
        this.selectedStudent = selectedStudent;
    }
    
    public List<Student> getStudents() {
        return students;
    }
    
    public void setStudents(List<Student> students) {
        this.students = students;
    }
    
    public List<StudentClass> getClasses() {
        return classes;
    }
    
    public void setClasses(List<StudentClass> classes) {
        this.classes = classes;
    }
    
    public List<Subject> getSubjects() {
        return subjects;
    }
    
    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
    }
    
    public List<Subject> getAvailableSubjects() {
        return availableSubjects;
    }
    
    public void setAvailableSubjects(List<Subject> availableSubjects) {
        this.availableSubjects = availableSubjects;
    }
    
    public List<Subject> getEditAvailableSubjects() {
        return editAvailableSubjects;
    }
    
    public void setEditAvailableSubjects(List<Subject> editAvailableSubjects) {
        this.editAvailableSubjects = editAvailableSubjects;
    }
}
