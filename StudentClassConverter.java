package com.example.converter;

import com.example.entity.StudentClass;
import com.example.service.StudentService;

import javax.ejb.EJB;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter("studentClassConverter")
public class StudentClassConverter implements Converter {
    
    @EJB
    private StudentService studentService;
    
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.trim().isEmpty()) {
            return null;
        }
        
        try {
            Long id = Long.valueOf(value);
            return studentService.findClassById(id);
        } catch (NumberFormatException e) {
            return null;
        }
    }
    
    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null) {
            return "";
        }
        
        if (value instanceof StudentClass) {
            StudentClass studentClass = (StudentClass) value;
            return studentClass.getId() != null ? studentClass.getId().toString() : "";
        }
        
        return "";
    }
}
