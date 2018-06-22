# flywaydb example (MySQL)

**1. Prerequisite**

```bash
Database name : flywaydb
Username : flywaydb
Password : flywaydb

```

**2. Run maven for the first time**

Command:

```bash
mvn flywaydb:info

```
Result:

```bash
[INFO] Scanning for projects...
[INFO]
[INFO] ------------------------------------------------------------------------
[INFO] Building flywaydb-example 0.0.1-SNAPSHOT
[INFO] ------------------------------------------------------------------------
[INFO]
[INFO] --- flyway-maven-plugin:5.1.3:info (default-cli) @ flywaydb-example ---
[INFO] Flyway Community Edition 5.1.3 by Boxfuse
[INFO] Database: jdbc:mysql://localhost:3306/flywaydb (MySQL 5.5)
[INFO] Schema version: << Empty Schema >>
[INFO]
[INFO] +-----------+---------+---------------------+------+--------------+---------+
| Category  | Version | Description         | Type | Installed On | State   |
+-----------+---------+---------------------+------+--------------+---------+
| Versioned | 1       | Create person table | SQL  |              | Pending |
| Versioned | 2       | Add people          | SQL  |              | Pending |
| Versioned | 3       | Add people          | SQL  |              | Pending |
| Versioned | 4       | Create person table | SQL  |              | Pending |
| Versioned | 5       | Create person table | SQL  |              | Pending |
+-----------+---------+---------------------+------+--------------+---------+

[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time: 0.847 s
[INFO] Finished at: 2018-06-23T03:22:40+07:00
[INFO] Final Memory: 12M/150M
[INFO] ------------------------------------------------------------------------
```

**3. Run maven to execute database migration**

Command:

```bash
mvn flyway:migrate

```
Result:

```bash
[INFO] Scanning for projects...
[INFO]
[INFO] ------------------------------------------------------------------------
[INFO] Building flywaydb-example 0.0.1-SNAPSHOT
[INFO] ------------------------------------------------------------------------
[INFO]
[INFO] --- flyway-maven-plugin:5.1.3:migrate (default-cli) @ flywaydb-example ---
[INFO] Flyway Community Edition 5.1.3 by Boxfuse
[INFO] Database: jdbc:mysql://localhost:3306/flywaydb (MySQL 5.5)
[INFO] Successfully validated 5 migrations (execution time 00:00.012s)
[INFO] Creating Schema History table: `flywaydb`.`flyway_schema_history`
[INFO] Current version of schema `flywaydb`: << Empty Schema >>
[INFO] Migrating schema `flywaydb` to version 1 - Create person table
[INFO] Migrating schema `flywaydb` to version 2 - Add people
[INFO] Migrating schema `flywaydb` to version 3 - Add people
[INFO] Migrating schema `flywaydb` to version 4 - Create person table
[WARNING] DB: Table 'person' already exists (SQL State: 42S01 - Error Code: 1050)
[ERROR] Migration of schema `flywaydb` to version 4 - Create person table failed! Please restore backups and roll back database and code!
[INFO] ------------------------------------------------------------------------
[INFO] BUILD FAILURE
[INFO] ------------------------------------------------------------------------
[INFO] Total time: 1.762 s
[INFO] Finished at: 2018-06-23T03:23:50+07:00
[INFO] Final Memory: 9M/150M
[INFO] ------------------------------------------------------------------------
[ERROR] Failed to execute goal org.flywaydb:flyway-maven-plugin:5.1.3:migrate (default-cli) on project flywaydb-example: org.flywaydb.core.internal.command.DbMigrate$FlywayMigrateException:
[ERROR] Migration V4__Create_person_table.sql failed
[ERROR] --------------------------------------------
[ERROR] SQL State  : 42S01
[ERROR] Error Code : 1050
[ERROR] Message    : Table 'person' already exists
[ERROR] Location   : db/migration/sql/ddl/V4__Create_person_table.sql (D:\harvan\programming\java\github.com\harvanir\example\flywaydb-example\target\classes\db\migration\sql\ddl\V4__Create_person_table.sql)
[ERROR] Line       : 1
[ERROR] Statement  : create table PERSON (
[ERROR]     ID int not null,
[ERROR]     NAME varchar(100) not null
[ERROR] )
[ERROR]
[ERROR] -> [Help 1]
[ERROR]
[ERROR] To see the full stack trace of the errors, re-run Maven with the -e switch.
[ERROR] Re-run Maven using the -X switch to enable full debug logging.
[ERROR]
[ERROR] For more information about the errors and possible solutions, please read the following articles:
[ERROR] [Help 1] http://cwiki.apache.org/confluence/display/MAVEN/MojoExecutionException

```

**4. Your process will return an error because there are 2 file conflict**

```bash
Existing valid script: "db/migration/sql/ddl/V1__Create_person_table.sql"
Invalid/duplicate script: "db/migration/sql/ddl/V4__Create_person_table.sql, db/migration/sql/ddl/V5__Create_person_table.sql"
```

**5. Check unsuccessful process**

Command:

```bash
mvn flywaydb:info
```

Result:

```bash
[INFO] Scanning for projects...
[INFO]
[INFO] ------------------------------------------------------------------------
[INFO] Building flywaydb-example 0.0.1-SNAPSHOT
[INFO] ------------------------------------------------------------------------
[INFO]
[INFO] --- flyway-maven-plugin:5.1.3:info (default-cli) @ flywaydb-example ---
[INFO] Flyway Community Edition 5.1.3 by Boxfuse
[INFO] Database: jdbc:mysql://localhost:3306/flywaydb (MySQL 5.5)
[INFO] Schema version: 4
[INFO]
[INFO] +-----------+---------+---------------------+------+---------------------+---------+
| Category  | Version | Description         | Type | Installed On        | State   |
+-----------+---------+---------------------+------+---------------------+---------+
| Versioned | 1       | Create person table | SQL  | 2018-06-23 03:23:50 | Success |
| Versioned | 2       | Add people          | SQL  | 2018-06-23 03:23:50 | Success |
| Versioned | 3       | Add people          | SQL  | 2018-06-23 03:23:50 | Success |
| Versioned | 4       | Create person table | SQL  | 2018-06-23 03:23:50 | Failed  |
| Versioned | 5       | Create person table | SQL  |                     | Pending |
+-----------+---------+---------------------+------+---------------------+---------+

[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time: 1.046 s
[INFO] Finished at: 2018-06-23T03:26:09+07:00
[INFO] Final Memory: 9M/150M
[INFO] ------------------------------------------------------------------------
```

**6. To continue your next migrating process, do 2 steps bellow**

(1.) Repair command:

```bash
mvn flywaydb:repair
```

Result:

```bash
[INFO] Scanning for projects...
[INFO]
[INFO] ------------------------------------------------------------------------
[INFO] Building flywaydb-example 0.0.1-SNAPSHOT
[INFO] ------------------------------------------------------------------------
[INFO]
[INFO] --- flyway-maven-plugin:5.1.3:repair (default-cli) @ flywaydb-example ---
[INFO] Flyway Community Edition 5.1.3 by Boxfuse
[INFO] Database: jdbc:mysql://localhost:3306/flywaydb (MySQL 5.5)
[INFO] Successfully repaired schema history table `flywaydb`.`flyway_schema_history` (execution time 00:00.154s).
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time: 1.124 s
[INFO] Finished at: 2018-06-23T03:34:59+07:00
[INFO] Final Memory: 11M/150M
[INFO] ------------------------------------------------------------------------
```

(2.) Delete 2 invalid/conflict file:

```bash
db/migration/sql/ddl/V4__Create_person_table.sql
db/migration/sql/ddl/V5__Create_person_table.sql
```

**7. Check your latest migration status**

Command:

```bash
mvn flywaydb:info
```

Result:

```bash
[INFO] Scanning for projects...
[INFO]
[INFO] ------------------------------------------------------------------------
[INFO] Building flywaydb-example 0.0.1-SNAPSHOT
[INFO] ------------------------------------------------------------------------
[INFO]
[INFO] --- flyway-maven-plugin:5.1.3:info (default-cli) @ flywaydb-example ---
[INFO] Flyway Community Edition 5.1.3 by Boxfuse
[INFO] Database: jdbc:mysql://localhost:3306/flywaydb (MySQL 5.5)
[INFO] Schema version: 3
[INFO]
[INFO] +-----------+---------+---------------------+------+---------------------+---------+
| Category  | Version | Description         | Type | Installed On        | State   |
+-----------+---------+---------------------+------+---------------------+---------+
| Versioned | 1       | Create person table | SQL  | 2018-06-23 03:23:50 | Success |
| Versioned | 2       | Add people          | SQL  | 2018-06-23 03:23:50 | Success |
| Versioned | 3       | Add people          | SQL  | 2018-06-23 03:23:50 | Success |
+-----------+---------+---------------------+------+---------------------+---------+

[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time: 1.513 s
[INFO] Finished at: 2018-06-23T03:41:20+07:00
[INFO] Final Memory: 11M/150M
[INFO] ------------------------------------------------------------------------
```