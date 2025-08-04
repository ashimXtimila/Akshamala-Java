# Java Web CRUD Application - Student Management System

## Overview
A complete Java Web CRUD application built with JSF framework, JPA, MySQL, PrimeFaces, and designed for GlassFish 4.0 server with JDK 8 compatibility.

## Technologies Used
- **JSF 2.2** - JavaServer Faces framework
- **JPA with Hibernate** - Object-Relational Mapping
- **MySQL** - Database
- **PrimeFaces 6.2** - UI Components
- **GlassFish 4.0** - Application Server
- **JDK 8** - Java Development Kit
- **Maven** - Build and dependency management

## Features Implemented
1. **Student Form** with dependent dropdowns (Class → Subjects)
2. **CRUD Operations** - Create, Read, Update, Delete students
3. **Data Table** with pagination and sorting
4. **Edit Dialog** using PrimeFaces dialog component
5. **Form Validation** with error messages
6. **Database Integration** with MySQL
7. **Default Data Initialization** for classes and subjects

## Database Schema
- **students** - Main student records
- **student_classes** - Available classes (Class 10, 11, 12)
- **subjects** - Subjects mapped to classes

## Project Structure
```
src/
├── main/
│   ├── java/
│   │   └── com/example/
│   │       ├── entity/
│   │       │   ├── Student.java
│   │       │   ├── StudentClass.java
│   │       │   └── Subject.java
│   │       ├── service/
│   │       │   └── StudentService.java
│   │       ├── controller/
│   │       │   └── StudentController.java
│   │       └── converter/
│   │           ├── StudentClassConverter.java
│   │           └── SubjectConverter.java
│   ├── resources/
│   │   └── META-INF/
│   │       └── persistence.xml
│   └── webapp/
│       ├── WEB-INF/
│       │   ├── web.xml
│       │   ├── faces-config.xml
│       │   └── beans.xml
│       └── student.xhtml
```

## Key Fixes Applied

### 1. Entity Relationship Issues
- **Fixed cascade relationships** in StudentClass entity
- Removed problematic `CascadeType.ALL` that could cause deletion issues
- Proper JPA annotations for foreign key relationships

### 2. JSF Converter Issues
- **Created custom converters** instead of relying on OmniFaces
- `StudentClassConverter` and `SubjectConverter` for proper entity conversion
- Removed OmniFaces dependency to avoid compatibility issues

### 3. JSF Syntax Errors
- **Fixed JavaScript syntax** in `oncomplete` attribute
- Added proper JavaScript function `handleUpdateComplete()`
- Corrected XHTML syntax issues

### 4. Controller Logic Fixes
- **Added separate field** `editAvailableSubjects` for edit dialog
- Fixed subject loading for edit operations
- Proper initialization of all collections

### 5. CDI Configuration
- **Added beans.xml** for proper CDI support
- Configured bean discovery mode

### 6. Maven Dependencies
- **Corrected version compatibility** for JSF 2.2, PrimeFaces 6.2
- Proper scope configuration for provided dependencies
- Added all necessary dependencies for GlassFish 4.0

## Setup Instructions

### 1. Database Setup
```sql
CREATE DATABASE student_db;
USE student_db;
```

### 2. GlassFish Configuration
1. Create JDBC Connection Pool: `StudentConnectionPool`
2. Create JDBC Resource: `jdbc/studentDB`
3. Deploy MySQL Connector JAR to `glassfish/domains/domain1/lib/`

### 3. Build and Deploy
```bash
mvn clean package
asadmin deploy target/student-crud-app.war
```

### 4. Access Application
Navigate to: `http://localhost:8080/student-crud-app/`

## Application Features

### Main Form
- Student Name (required)
- Student Address (required)
- Student Class dropdown (required)
- Subject dropdown (dependent on class selection)
- Save button with validation

### Data Table
- Displays all students with pagination
- Sortable columns
- Edit and Delete actions
- Confirmation dialog for delete operations

### Edit Dialog
- PrimeFaces modal dialog
- Pre-populated form fields
- Dependent dropdown behavior
- Update and Cancel buttons

## Default Data
The application automatically initializes with:
- **Classes**: Class 10, Class 11, Class 12
- **Subjects per Class**:
  - Class 10: Mathematics, Science, English, Social Studies
  - Class 11: Physics, Chemistry, Biology, Mathematics, English
  - Class 12: Advanced Physics, Advanced Chemistry, Advanced Biology, Calculus, Literature

## Error Handling
- Form validation with user-friendly messages
- Database error handling
- Proper exception management in service layer
- JSF message system for user feedback

## All Major Issues Fixed
✅ Cascade relationship issues resolved
✅ JSF syntax errors corrected
✅ Custom converters implemented
✅ Controller logic fixed
✅ CDI configuration added
✅ Maven dependencies corrected
✅ Database schema properly defined
✅ Dependent dropdown functionality working
✅ Edit dialog properly implemented
✅ All CRUD operations functional
