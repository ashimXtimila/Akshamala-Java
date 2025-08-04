package com.example.converter;

import com.example.entity.Subject;
import com.example.service.StudentService;

import javax.ejb.EJB;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter("subjectConverter")
public class SubjectConverter implements Converter {
    
    @EJB
    private StudentService studentService;
    
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.trim().isEmpty()) {
            return null;
        }
        
        try {
            Long id = Long.valueOf(value);
            return studentService.findSubjectById(id);
        } catch (NumberFormatException e) {
            return null;
        }
    }
    
    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null) {
            return "";
        }
        
        if (value instanceof Subject) {
            Subject subject = (Subject) value;
            return subject.getId() != null ? subject.getId().toString() : "";
        }
        
        return "";
    }
}
