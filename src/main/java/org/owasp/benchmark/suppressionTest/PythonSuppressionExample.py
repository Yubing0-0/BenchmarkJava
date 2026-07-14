# Python 代码检查工具注释抑制示例
# 包含 pylint, flake8, mypy, bandit, ruff 等常用工具的抑制方式

import os
import subprocess
import json


class SuppressionExample:
    """Python 代码检查工具抑制示例"""

    # pylint: disable=too-many-instance-attributes
    # 抑制警告：属性过多
    def __init__(self):
        self.name = "test"
        self.value = 42
        self.data = {}
        self.cache = {}
        self.logger = None
        self.config = {}
        self.status = "active"
        self.count = 0
        self.version = "1.0"
        self.author = "unknown"
    # pylint: enable=too-many-instance-attributes

    # pylint 行内抑制：抑制特定行的特定警告
    def unsafe_exec(self, user_input):  # pylint: disable=exec-used
        # pylint 会警告 exec() 的使用，但这里通过行内注释抑制
        exec(user_input)  # nosec  # bandit 的 B102 抑制

    # flake8 抑制：抑制 E501 行过长
    # noqa: E501
    this_is_a_very_long_variable_name_that_would_trigger_flake8_line_length_check = "test value"  # noqa: E501

    # flake8 抑制：抑制整个文件的所有检查
    # 在文件顶部添加: # flake8: noqa

    # mypy 类型检查抑制
    def mypy_suppression(self):
        # type: ignore 抑制当前行的类型检查
        x: int = "this is a string"  # type: ignore[assignment]

        # 抑制多个 mypy 错误
        result = 1 + "2"  # type: ignore[operator, return-value]

    # bandit 安全扫描抑制
    def bandit_suppression(self, command):
        # nosec 抑制 bandit 的 B602 和 B604 警告
        subprocess.call(command, shell=True)  # nosec B602 B604

        # 抑制所有 bandit 问题
        # nosec 可以单独使用，抑制当前行所有 bandit 问题
        os.system(command)  # nosec

    # ruff 抑制（Ruff 是新的 Python linter，支持 flake8 兼容的抑制）
    # ruff 支持: # noqa: F401, E501 - 与 flake8 兼容
    # 也支持: # noqa: RUF001 - ruff 特有的规则
    def ruff_suppression(self):
        import unused_module  # noqa: F401

        # ruff 特有的规则抑制
        # 抑制 RUF001: 字符串包含模糊 Unicode 字符
        file_name = "test_file.txt"  # noqa: RUF001, RUF003

    # pylint 块抑制：禁用/启用多个规则
    # pylint: disable=unused-argument,unused-variable
    def block_suppression(self, unused_arg):
        unused_variable = "test"
        return "something"
    # pylint: enable=unused-argument,unused-variable

    # 抑制所有 pylint 警告
    # pylint: disable=all
    def completely_ignored_by_pylint(self):
        x = 1  # noqa
        y = 2  # noqa
        z = "bad"  # type: ignore
        exec("print('hello')")  # nosec
        return x + y + z  # noqa
    # pylint: enable=all


# 文件级别抑制示例
# 如果整个文件需要抑制某个规则，可以将 pylint 注释放在文件最顶部
# 例如： # pylint: disable=missing-module-docstring,missing-class-docstring

if __name__ == "__main__":
    example = SuppressionExample()
    # bandit: 抑制 B104 - 绑定到所有接口
    # 这种方式在配置文件或命令行中抑制更常见
    print("Python suppression examples - run linting tools to verify")






    # pylint 行内抑制：抑制特定行的特定警告
    def unsafe_exec111111111111111111111111111111111111111111111111111111111111111111111(self, user_input):  # pylint: disable=exec-used
        # pylint 会警告 exec() 的使用，但这里通过行内注释抑制
        exec(user_input)  # nosec  # bandit 的 B102 抑制
