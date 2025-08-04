# GlassFish 4.0 Setup Instructions

## Prerequisites
- JDK 8 installed
- MySQL Server running
- GlassFish 4.0 Server

## Database Configuration

### 1. Create MySQL Database
```sql
CREATE DATABASE student_db;
USE student_db;
```

### 2. Configure JDBC Connection Pool in GlassFish

1. Start GlassFish Server:
   ```bash
   asadmin start-domain
   ```

2. Access GlassFish Admin Console: http://localhost:4848

3. Navigate to: Resources → JDBC → JDBC Connection Pools

4. Click "New" and create a connection pool with these settings:
   - Pool Name: `StudentConnectionPool`
   - Resource Type: `javax.sql.DataSource`
   - Database Driver Vendor: `MySQL`

5. In Additional Properties, add:
   - `serverName`: `localhost`
   - `portNumber`: `3306`
   - `databaseName`: `student_db`
   - `user`: `root` (or your MySQL username)
   - `password`: `your_mysql_password`
   - `URL`: `jdbc:mysql://localhost:3306/student_db`
   - `driverClass`: `com.mysql.jdbc.Driver`

6. Test the connection and save.

### 3. Create JDBC Resource

1. Navigate to: Resources → JDBC → JDBC Resources

2. Click "New" and create:
   - JNDI Name: `jdbc/studentDB`
   - Pool Name: `StudentConnectionPool`

3. Save the resource.

### 4. Deploy MySQL Connector

1. Download MySQL Connector/J 5.1.47
2. Copy `mysql-connector-java-5.1.47.jar` to `glassfish/domains/domain1/lib/`
3. Restart GlassFish server

## Application Deployment

### 1. Build the Application
```bash
mvn clean package
```

### 2. Deploy to GlassFish
```bash
asadmin deploy target/student-crud-app.war
```

Or use the Admin Console:
1. Go to Applications
2. Click "Deploy"
3. Select the WAR file
4. Deploy

### 3. Access the Application
Open browser and navigate to: http://localhost:8080/student-crud-app/

## Troubleshooting

### Common Issues:

1. **ClassNotFoundException for MySQL Driver**
   - Ensure MySQL connector JAR is in `glassfish/domains/domain1/lib/`
   - Restart GlassFish after adding the JAR

2. **Connection Pool Test Fails**
   - Check MySQL server is running
   - Verify database credentials
   - Ensure database `student_db` exists

3. **JSF Pages Not Loading**
   - Check if JSF libraries are properly included
   - Verify web.xml configuration
   - Check server logs for errors

4. **PrimeFaces Components Not Working**
   - Ensure PrimeFaces JAR is in WEB-INF/lib
   - Check theme configuration in web.xml

### Server Logs Location:
- `glassfish/domains/domain1/logs/server.log`

### Useful Commands:
```bash
# Start domain
asadmin start-domain

# Stop domain
asadmin stop-domain

# List applications
asadmin list-applications

# Undeploy application
asadmin undeploy student-crud-app

# Redeploy
asadmin redeploy target/student-crud-app.war
```
