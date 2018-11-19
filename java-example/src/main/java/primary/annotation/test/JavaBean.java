package primary.annotation.test;

import primary.annotation.anno.Constraints;
import primary.annotation.anno.DBTable;
import primary.annotation.anno.SQLInteger;
import primary.annotation.anno.SQLString;

@DBTable(name = "table1")
public class JavaBean {
    @SQLString(30) String firstName;
    @SQLString(50) String lastName;
    @SQLInteger Integer age;
    @SQLString(value = 30,constraints = @Constraints(primaryKey = true)) String handle;
    static int memberCount;
}
