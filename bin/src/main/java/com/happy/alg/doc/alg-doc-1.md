# 1. 在一个数组中，如何判断相同的数字，或者元素
~~~java
// i must be greater than first initial value, and nums[i] must be equal to nums[i - 1], 
// then we can skip the same element to avoid duplicate quadruplets.
if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
    }

// m 的初始值已经被使用了，就不添加条件了
 m++;
// skip same elements
while (m < n && nums[m] == nums[m - 1]){
     m++;
}
~~~

# 2. 如何判断 overflow
LeetCode 中处理溢出的核心方案确实可以归纳为这两大类：
## 2.1 「降维打击」法：使用更大范围的类型
这种方法最直观，通过将计算过程提升到一个更高精度的容器中，让原本会溢出的数据能够被安全存储，计算后再检查是否超标。 [1, 2] 

* 实现方式：
* C++：使用 long long 来处理 int 的运算。
   * Java：使用 long 来处理 int 的运算。
* 判断逻辑：

long long res = (long long)a + b;if (res > INT_MAX || res < INT_MIN) return ERROR;

* 局限性：如果题目本身要求处理的是该语言中最大的类型（例如在 C++ 中处理 long long 溢出），就没有更高级别的类型可用了（除非使用 BigInt 等大数类）。 [3, 4] 

## 2.2 「未雨绸缪」法：利用逆运算预判
这种方法不需要额外的空间，通过数学变形，在溢出发生之前进行逻辑拦截。 [4, 5, 6] 
* 加法/减法预判：
* 判断 a + b 是否溢出，转化为判断 a > INT_MAX - b（假设 b > 0）。
* 乘法/除法预判：
* 判断 ans * 10 + digit 是否溢出，转化为判断 ans > (INT_MAX - digit) / 10。
* 中点计算：
* 防止 (low + high) / 2 溢出，改为 low + (high - low) / 2。 [6, 7] 

## 2.3 剪枝提前进行判断
在某些特定问题中，可以通过对输入数据进行排序和分析，提前判断某些组合是否可能达到目标值，从而避免不必要的计算。例如，在四数之和问题中，如果当前的最小组合已经大于目标值，或者最大组合已经小于目标值，就可以直接跳过后续的计算。 例如：

```java
// 在四数之和问题中，如果当前的最小组合已经大于目标值
if ((long) nums[i] + nums[i + 1] + nums[i + 2] + nums[i + 3] > target) {
                    break;
}
if ((long) nums[i] + nums[length - 3] + nums[length - 2] + nums[length - 1] < target) {
                    continue; // prevent integer overflow
}
```    

## 补充：面试中的「潜规则」
在 LeetCode 的环境下，虽然第一种方法（使用 long）写起来更简单，但面试官往往更倾向于看到第二种方案。因为第二种方案不依赖于更大范围的数据类型，展现了你对数值边界和数学逻辑的严谨控制能力，这在嵌入式或内存极度受限的环境中是必不可少的。
