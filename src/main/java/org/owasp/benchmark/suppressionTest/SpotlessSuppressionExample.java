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

/** Spotless 格式抑制示例 Spotless 使用特殊注释来禁用格式化 // spotless:off 和 // spotless:on 之间的代码不会被格式化 */
public class SpotlessSuppressionExample {

    // 常规代码会被 Spotless 格式化
    public String regularFormatting = "This line will be formatted normally";

    // spotless:off - 从这里开始禁用格式化
    // 这段代码保留了原始格式，不会被 Spotless 改变
    public String unformattedCode = "This has weird spacing that will be preserved";
    public int[][] matrix = {
        {1, 2, 3},
        {4, 5, 6},
        {7, 8, 9}
    };
    public String veryLongString =
            "This is a very long string that we don't want "
                    + "Spotless to split into multiple lines differently "
                    + "because we manually formatted it exactly how we want";

    // spotless:on - 从这里开始恢复格式化

    // 对于整个文件禁用格式化，可以在文件开头添加
    // /* spotless:off */
    // 或者在文件开头加上：
    // @formatter:off

    // 也可以使用 @formatter:off / @formatter:on 格式（兼容 IntelliJ/Eclipse 格式关闭约定）
    // spotless 也支持这种格式
    public void exampleWithFormatterOff() {
        int a = 1;
        int b = 2;

        // @formatter:off
        String json =
                "{\n"
                        + "  \"name\": \"test\",\n"
                        + "  \"value\": 123,\n"
                        + "  \"nested\": {\n"
                        + "    \"key\": \"value\"\n"
                        + "  }\n"
                        + "}";
        // @formatter:on

        System.out.println(a + b);
    }

    // 某些情况下禁用特定的检查
    // spotless:off:imports - 只禁用导入排序
    // 但通常使用完整的 off/on 更常见
}
