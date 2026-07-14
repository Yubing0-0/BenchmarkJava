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

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * PMD 注释抑制示例
 * PMD 使用 // NOPMD 注释来抑制特定行的警告，或使用 @SuppressWarnings("PMD.规则名") 注解
 */
public class PMDSuppressionExample {

    // NOPMD 抑制所有 PMD 规则对这一行的检查
    public String unsafeQuery = "SELECT * FROM users WHERE id = " + System.getProperty("user.id"); // NOPMD

    // NOPMD: 特定规则抑制 - 抑制 AVOID_GET_UNSPECIFIED_DRIVER 规则
    public Connection getConnection() throws SQLException { // NOPMD: AVOID_GET_UNSPECIFIED_DRIVER
        return DriverManager.getConnection("jdbc:mysql://localhost/db");
    }

    // 使用 @SuppressWarnings 注解抑制整个方法的特定 PMD 规则
    @SuppressWarnings("PMD.SystemPrintln")
    public void printDebugInfo() {
        System.out.println("Debug information"); // 不会被 PMD 报告 SystemPrintln 规则
        System.out.println("Another debug line");
    }

    // 抑制多个 PMD 规则
    @SuppressWarnings({"PMD.DataflowAnomalyAnalysis", "PMD.UnusedFormalParameter"})
    public void unusedParameterMethod(String unusedParam) {
        int x = 0;
        System.out.println(x);
    }

    // 类级别抑制
    @SuppressWarnings("PMD.TooManyMethods")
    public class InnerClass {
        // 多个方法... 不会报告 TooManyMethods
        public void method1() {}
        public void method2() {}
        public void method3() {}
        public void method4() {}
        public void method5() {}
        public void method6() {}
        public void method7() {}
        public void method8() {}
        public void method9() {}
        public void method10() {}
    }

    public static void main(String[] args) throws IOException {
        PMDSuppressionExample example = new PMDSuppressionExample();
        // 这会触发长方法警告，但如果注解了就是会被抑制
        @SuppressWarnings("PMD.ExcessiveMethodLength")
        long methodWithManyLines() {
            long sum = 0;
            for (int i = 0; i < 100; i++) {
                sum += i;
            }
            return sum;
        }
    }
}
