/**
 * OWASP Benchmark v1.2
 *
 * <p>This file is part of the Open Web Application Security Project (OWASP) Benchmark Project. For
 * details, please see <a
 * href="https://owasp.org/www-project-benchmark/">https://owasp.org/www-project-benchmark/</a>.
 *
 * <p>The OWASP Benchmark is free software: you can redistribute it and/or modify it under the terms
 * of the GNU General Public License as published by the Free Software Foundation, version 2.
 *
 * <p>The OWASP Benchmark is distributed in the hope that it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR
 * PURPOSE. See the GNU General Public License for more details.
 *
 * @author OWASP Benchmark Team
 * @created 2025
 */
package org.owasp.benchmark.suppressionTest;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * SpotBugs (FindBugs) 注释抑制示例 SpotBugs 使用 @SuppressFBWarnings 注解来抑制检测
 * 也支持 @SuppressWarnings("findbugs:规则名") 和 Filter 文件
 */
public class SpotBugsSuppressionExample implements Serializable {

    private static final long serialVersionUID = 1L;

    // SpotBugs: 抑制单个规则 - 方法级别
    @SuppressFBWarnings("SQL_INJECTION")
    public void executeUnsafeQuery(String userInput) throws Exception {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            conn = java.sql.DriverManager.getConnection("jdbc:hsqldb:mem:test");
            stmt = conn.createStatement();
            // 这行 SQL 注入代码不会被 SpotBugs 报告
            rs = stmt.executeQuery("SELECT * FROM users WHERE id = " + userInput);
        } finally {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
            if (conn != null) conn.close();
        }
    }

    // SpotBugs: 抑制多个规则
    @SuppressFBWarnings({"PATH_TRAVERSAL_IN", "SQL_INJECTION"})
    public void readUserFile(String fileName) throws IOException {
        File file = new File("/home/user/" + fileName);
        FileInputStream fis = new FileInputStream(file);
        byte[] data = new byte[1024];
        fis.read(data);
        fis.close();
    }

    // SpotBugs: 字段级别抑制
    @SuppressFBWarnings("MS_MUTABLE_ARRAY")
    public static final String[] IMMUTABLE_ARRAY = new String[] {"a", "b", "c"};

    // SpotBugs: 类级别抑制
    @SuppressFBWarnings("EI_EXPOSE_REP")
    public int[] getScores() {
        int[] scores = {1, 2, 3};
        return scores.clone();
    }

    // SpotBugs: 使用 @SuppressWarnings 注解抑制
    @SuppressWarnings("findbugs:UUF_UNUSED_FIELD")
    private String unusedField = "This field is kept for future use";

    // SpotBugs: 抑制通过自定义 severity 降低
    @SuppressFBWarnings(
            value = "RCN_REDUNDANT_NULLCHECK_WOULD_HAVE_BEEN_A_NPE",
            justification = "This nullcheck is for defensive programming")
    public void defensiveNullCheck(String input) {
        if (input != null) {
            System.out.println(input.trim());
        }
        if (input != null) {
            System.out.println(input.toUpperCase());
        }
    }

    public static void main(String[] args) {
        SpotBugsSuppressionExample example = new SpotBugsSuppressionExample();
        System.out.println("SpotBugs suppression example");
    }
}
