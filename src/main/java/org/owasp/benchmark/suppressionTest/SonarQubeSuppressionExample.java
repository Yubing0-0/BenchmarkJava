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

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * SonarQube 注释抑制示例 SonarQube 使用以下方式抑制警告： 1. // NOSONAR 行尾注释 - 抑制当前行的所有问题 2. @SuppressWarnings 注解 -
 * 抑制特定规则 3. SonarCloud 不支持 // NOSONAR 匿名抑制，推荐使用 @SuppressWarnings("squid:规则名")
 */
@WebServlet(value = "/suppressionTest/SonarQubeSuppressionExample")
public class SonarQubeSuppressionExample extends HttpServlet {

    private static final long serialVersionUID = 1L;

    // NOSONAR 行尾抑制 - 抑制这一行的所有问题
    public String sqlInjectionQuery =
            "SELECT * FROM users WHERE id = " + System.getProperty("user.id"); // NOSONAR

    // 注解方式抑制特定的 SonarQube 规则
    @SuppressWarnings("squid:S1144")
    public void unusedPrivateMethod() {
        // 这个方法虽然未被使用，但不会触发 squid:S1144 (Unused private method)
        System.out.println("Unused but suppressed");
    }

    // 抑制多个规则
    @SuppressWarnings({"squid:S106", "squid:S1166"})
    public void multipleRuleSuppression() {
        System.out.println("Standard output"); // 抑制 squid:S106 (System.out usage)
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            // 抑制 squid:S1166 - 空 catch 块
        }
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // NOSONAR 可用于抑制硬编码密码等安全检查
        String password = "admin123"; // NOSONAR

        // 抑制安全热点
        @SuppressWarnings("squid:S2077")
        String query =
                "SELECT * FROM users WHERE name = '" + request.getParameter("username") + "'";

        response.setContentType("text/html;charset=UTF-8");
        response.getWriter().println("SonarQube suppression example");
    }

    @Override
    @SuppressWarnings("squid:S112")
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 抑制 squid:S112 - 不要抛出 RuntimeException
        String param = request.getParameter("param");
        if (param == null) {
            throw new RuntimeException("Parameter not provided"); // 被 @SuppressWarnings 抑制
        }
        response.getWriter().println("Received: " + param);
    }
}
