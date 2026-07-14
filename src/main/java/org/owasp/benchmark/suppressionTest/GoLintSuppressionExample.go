// Go 静态分析工具注释抑制示例
// 包含 golangci-lint, go vet, staticcheck 等常用工具的抑制方式
package suppressiontest

import (
	"fmt"
	"os"
	"unsafe"
)

//nolint:unused // 抑制整个文件的 unused 检查
// 在文件顶部使用 //nolint 可以抑制整个文件
// 常用格式: //nolint:<linter1>,<linter2> // 注释说明

// Go 中编译时未使用的变量示例
func UnusedVariableExample() {
	//nolint:staticcheck // 抑制 staticcheck 的 SA4006
	// 或者抑制特定规则
	//nolint:gosec // 抑制 gosec 安全检查
	var unusedVar = 42 // 不会被 lint 报告
	fmt.Println("Hello")
}

//nolint:gosec // 抑制 gosec 安全检查（G104 等）
func UnsafeUsageExample() {
	// 使用 unsafe 包 - 通常会被 staticcheck 和 gosec 警告
	var x int = 10
	ptr := unsafe.Pointer(&x) // 不会触发 gosec G103
	fmt.Println(ptr)
}

// 抑制多个 linter
//nolint:errcheck,gosec,staticcheck
func MultiLinterSuppression(filename string) {
	// errcheck: 忽略错误返回值
	file, _ := os.Open(filename) // 不会触发 errcheck
	file.Close()

	// gosec: 抑制 G204 (subprocess launched with variable)
	cmd := "echo hello"
	//nolint:gosec // 抑制 G204
	execCommand(cmd)
}

// 模拟执行命令函数
func execCommand(cmd string) {
	fmt.Println("Would execute:", cmd)
}

//nolint:gocritic // 抑制 gocritic 的检查建议
func GocriticSuppression() {
	// append 后没有赋值 - 通常 gocritic 会建议
	slice := []int{1, 2, 3}
	_ = append(slice, 4) // 不会触发 gocritic appendAssign
	fmt.Println(slice)
}

//nolint:unparam // 抑制 unparam (总是接收相同参数)
func UnparamSuppression(alwaysSame string) {
	fmt.Println(alwaysSame)
}

// 行内抑制：注释放在同一行末尾
func InlineSuppression() {
	// 行内抑制 errcheck
	os.Remove("/tmp/nonexistent") //nolint:errcheck

	// 行内抑制多个 linter
	_ = fmt.Sprintf("test %d", 42) //nolint:staticcheck,gosimple

	// 行内抑制带说明
	result := funcReturningInt() //nolint:staticcheck // intentionally ignoring result
	_ = result
}

func funcReturningInt() int {
	return 42
}

//nolint:funlen // 抑制函数过长检查
func LongFunctionExample() {
	// 这个函数故意写得很长，但被抑制了
	fmt.Println("Line 1")
	fmt.Println("Line 2")
	fmt.Println("Line 3")
	fmt.Println("Line 4")
	fmt.Println("Line 5")
	fmt.Println("Line 6")
	fmt.Println("Line 7")
	fmt.Println("Line 8")
	fmt.Println("Line 9")
	fmt.Println("Line 10")
}

//nolint:gocyclo // 抑制圈复杂度检查
func ComplexFunctionExample(n int) {
	if n == 0 {
		fmt.Println("zero")
	} else if n == 1 {
		fmt.Println("one")
	} else if n == 2 {
		fmt.Println("two")
	} else if n == 3 {
		fmt.Println("three")
	} else if n == 4 {
		fmt.Println("four")
	} else if n == 5 {
		fmt.Println("five")
	} else {
		fmt.Println("many")
	}
}

// 一些 golangci-lint 的 //nolint 使用说明：
// 1. //nolint - 抑制所有 linter 对当前行/块的检查
// 2. //nolint:gosec - 抑制特定 linter
// 3. //nolint:gosec,staticcheck - 抑制多个 linter
// 4. //nolint:gosec // 原因说明 - 带注释说明
// 5. 块抑制：在代码块前添加 //nolint 注释
// 6. 文件抑制：在文件顶部 package 声明前添加 //nolint 注释