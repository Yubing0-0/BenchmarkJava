// Rust Clippy 注释抑制示例
// Clippy 使用 #[allow(...)] 属性来抑制 lint 警告
// 属性可以应用于 crate、模块、函数、语句等各种级别

/// 模块级别抑制 - 允许 dead_code 警告
#[allow(dead_code)]
mod clippy_suppression_examples {

    /// 函数级别抑制：允许 snake_case 命名（对包含大写的函数名）
    #[allow(non_snake_case)]
    fn ThisFunctionHasUpperCaseName() {
        println!("This function name won't trigger clippy::non_snake_case");
    }

    /// 抑制多个 lint 规则
    #[allow(clippy::needless_return, clippy::redundant_clone)]
    fn function_with_multiple_suppressions(x: String) -> String {
        let y = x.clone(); // clippy::redundant_clone 被抑制
        return y; // clippy::needless_return 被抑制
    }

    /// 抑制整个模块的特定 lint
    #[allow(clippy::all)]
    pub fn lenient_function() {
        let x = 3.0_f64;
        let y = 1.0_f64;
        // 抑制 clippy::float_cmp - 浮点数直接比较
        if x == y {
            println!("Floats are equal");
        }
    }

    /// 行内抑制：使用 #[allow(...)] 在语句或表达式级别
    fn inline_suppression_example() {
        let mut vec = vec![1, 2, 3];

        // 对特定语句抑制 clippy::unnecessary_operation
        #[allow(clippy::unnecessary_operation)]
        {
            vec[0] = vec[0]; // 无意义的赋值，被抑制
        }

        println!("vec: {:?}", vec);
    }

    /// 抑制 clippy::or_fun_call
    #[allow(clippy::or_fun_call)]
    fn or_fun_call_example() -> String {
        let opt: Option<String> = None;
        opt.unwrap_or(String::from("default")) // 不会触发 clippy::or_fun_call
    }

    /// 也可以使用 clippy:: 前缀来抑制标准 Rust lint
    #[allow(dead_code, unused_variables)]
    fn unused_example() {
        let unused_var = 42;
        let another_unused = "hello";
    }

    /// 使用 #[allow(clippy::all)] 抑制所有 Clippy 警告
    #[allow(clippy::all)]
    fn completely_ignored_by_clippy() {
        let x = 1;
        let y = 2;
        if x == y {
            // 浮点比较在这里也会被抑制
        }
        let mut v = vec![1, 2, 3];
        // 迭代器滥用也被抑制
        for i in 0..v.len() {
            println!("{}", v[i]);
        }
    }

    /// 函数参数过多时抑制 clippy::too_many_arguments
    #[allow(clippy::too_many_arguments)]
    fn many_arguments(a: i32, b: i32, c: i32, d: i32, e: i32, f: i32, g: i32, h: i32) -> i32 {
        a + b + c + d + e + f + g + h
    }

    /// 抑制 clippy::needless_collect
    #[allow(clippy::needless_collect)]
    fn collect_example() {
        let numbers = vec![1, 2, 3, 4, 5];
        let collected: Vec<i32> = numbers.iter().map(|x| x * 2).collect();
        for n in collected.iter() {
            println!("{}", n);
        }
    }
}

/// 主函数 - 展示不同级别的抑制
fn main() {
    // 对整个 crate 的抑制通常在 main.rs 或 lib.rs 顶部
    // #![allow(clippy::all)]

    // 使用条件编译抑制（仅在特定配置下）
    #[cfg_attr(feature = "nightly", allow(clippy::unstable_features))]
    fn nightly_only_function() {
        println!("This function is only compiled on nightly");
    }

    println!("Clippy suppression examples");
    println!("Run: cargo clippy -- -A clippy::all  # 命令行抑制所有警告");
    println!("Or use: cargo clippy -- -A clippy::specific_lint  # 抑制特定 lint");
}