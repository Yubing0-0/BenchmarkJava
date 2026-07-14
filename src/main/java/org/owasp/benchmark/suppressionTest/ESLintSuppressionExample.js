/**
 * JavaScript / TypeScript / ESLint 注释抑制示例
 * 包含 ESLint, JSHint, TSLint 等工具的抑制方式
 *
 * 通用抑制语法：
 * - eslint-disable / eslint-enable
 * - eslint-disable-next-line
 * - eslint-disable-line
 * - // @ts-ignore (TypeScript)
 * - // @ts-expect-error (TypeScript, 推荐)
 */

// eslint-disable-next-line no-unused-vars
const unusedVariable = 'This is not used, but ESLint is suppressed';

// TypeScript: @ts-ignore 抑制类型错误
// @ts-ignore
const wrongType: number = 'this is a string not a number';

// TypeScript: @ts-expect-error 更推荐 - 当下一行没有错误时会报错
// @ts-expect-error - We know this is wrong, testing purposes
const anotherWrong: number = 'another string';

// eslint-disable-next-line no-console
console.log('This console.log is allowed');

// 块抑制：在一个代码块中禁用多个规则
/* eslint-disable no-alert, no-console */
alert('This is an alert');
console.log('Multiple console logs');
console.log('Without ESLint complaining');
/* eslint-enable no-alert, no-console */

// 块抑制：禁用所有 ESLint 规则
/* eslint-disable */
function completelyUnchecked() {
    var oldStyleVar = 'using var instead of let/const';
    console.log('This runs without any ESLint checks');
    debugger; // 甚至 debugger 语句也不会被报告
    eval('console.log("eval is allowed here")');
}
/* eslint-enable */

// 行内抑制：单行注释
function inlineSuppression() {
    const x = 1;
    // eslint-disable-next-line no-debugger
    debugger; // 仅抑制下一行的 debugger 规则

    // eslint-disable-next-line no-eval
    eval('console.log("eval is suppressed")'); // 仅抑制下一行的 eval 规则

    // 多规则抑制（下一行）
    // eslint-disable-next-line no-alert, no-console
    alert('This alert is suppressed');

    // 行尾抑制
    const unused = 'test'; // eslint-disable-line no-unused-vars
}

// TypeScript: @ts-nocheck 抑制整个文件的类型检查
// 放在文件顶部：// @ts-nocheck

// JSHint 抑制（老式方式）
/* jshint ignore:start */
function oldJSHintStyle() {
    var x = 1;
    // 这里的代码不会被 JSHint 检查
}
/* jshint ignore:end */

// 抑制特定 ESLint 插件规则
// eslint-disable-next-line react-hooks/exhaustive-deps
// useEffect(() => {}, []); // React hooks 依赖检查被抑制

// 抑制 import 相关规则
// eslint-disable-next-line import/no-unresolved
// import something from 'nonexistent-module';

// 抑制 prettier 格式化（与 ESLint 配合）
// prettier-ignore
const matrix = [
    [1, 2, 3],
    [4, 5, 6],
    [7, 8, 9]
];

// 抑制 TypeScript 特定规则
// @typescript-eslint/no-explicit-any
const anyValue: any = 'this could be anything';

// 导出示例
export { unusedVariable, inlineSuppression, completelyUnchecked };