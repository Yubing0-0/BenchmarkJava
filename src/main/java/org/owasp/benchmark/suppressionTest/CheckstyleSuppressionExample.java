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

/**
 * Checkstyle 注释抑制示例 Checkstyle 支持多种抑制方式： 1. 行内注释 // CHECKSTYLE:OFF 和 // CHECKSTYLE:ON 2. 行尾注释 //
 * SUPPRESS CHECKSTYLE <规则名> 3. @SuppressWarnings("checkstyle:规则名") 注解 4. 外部 suppression XML 文件
 */
public class CheckstyleSuppressionExample {

    // 行尾抑制: 抑制 MagicNumber 规则
    public static final int MAX_ITEMS = 100; // SUPPRESS CHECKSTYLE MagicNumber

    // 行尾抑制: 抑制 FileTabCharacter 规则
    public void methodWithTabs() { // SUPPRESS CHECKSTYLE FileTabCharacter
        System.out.println("This method has tabs in it");
    }

    // CHECKSTYLE:OFF - 从这里开始禁用所有检查
    // 这段代码完全跳过 Checkstyle 检查
    public void completelyUncheckedBlock() {
        String
                veryLongVariableNameThatExceedsTheLineLengthLimitAndWouldNormallyBeFlaggedByCheckstyle =
                        "test";
        int x = 1;
        int y = 2;
        int z = 3;
        System.out.println(
                x
                        + y
                        + z
                        + veryLongVariableNameThatExceedsTheLineLengthLimitAndWouldNormallyBeFlaggedByCheckstyle);
    }

    // CHECKSTYLE:ON - 从这里开始恢复检查

    // 注解方式抑制：抑制整个类或方法的检查
    @SuppressWarnings("checkstyle:MissingJavadocMethod")
    public void methodWithoutJavadoc() {
        System.out.println("This method has no Javadoc");
    }

    // 抑制多个规则
    @SuppressWarnings({"checkstyle:LineLength", "checkstyle:MagicNumber"})
    public void methodWithMultipleSuppressions() {
        int magicNumber = 42;
        String veryLongString =
                "This is a very long string that would normally trigger the LineLength check in Checkstyle because it exceeds the default limit of 80 or 120 characters";
        System.out.println(magicNumber + " " + veryLongString);
    }

    // 关闭特定规则：只关闭特定检查（如 JavadocMethod）
    // CHECKSTYLE:OFF: JavadocMethod
    public void methodWithNoJavadocAndNoCheckstyle() {
        int x = 5;
        System.out.println(x);
    }
    // CHECKSTYLE:ON: JavadocMethod
}
