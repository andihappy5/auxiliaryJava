package com.happy.alg.leetcode;

public class LeetCode584 {
    /**

     Table: Customer
     +-------------+---------+
     | Column Name | Type    |
     +-------------+---------+
     | id          | int     |
     | name        | varchar |
     | referee_id  | int     |
     +-------------+---------+
     In SQL, id is the primary key column for this table.
     Each row of this table indicates the id of a customer, their name,
     and the id of the customer who referred them.

     Find the names of the customer that are either:

     referred by any customer with id != 2.
     not referred by any customer.
     Return the result table in any order.

     The result format is in the following example.



     Example 1:

     Input:
     Customer table:
     +----+------+------------+
     | id | name | referee_id |
     +----+------+------------+
     | 1  | Will | null       |
     | 2  | Jane | null       |
     | 3  | Alex | 2          |
     | 4  | Bill | null       |
     | 5  | Zack | 1          |
     | 6  | Mark | 2          |
     +----+------+------------+
     Output:
     +------+
     | name |
     +------+
     | Will |
     | Jane |
     | Bill |
     | Zack |
     +------+
     * */

    /***
     *
     算法
     MySQL 使用三值逻辑 —— TRUE, FALSE 和 UNKNOWN。任何与 NULL 值进行的比较都会与第三种值 UNKNOWN 做比较。
     这个“任何值”包括 NULL 本身！这就是为什么 MySQL 提供 IS NULL 和 IS NOT NULL 两种操作来对 NULL 特殊判断。
     因此，在 WHERE 语句中我们需要做一个额外的条件判断 `referee_id IS NULL'。
     * */

//    select name from Customer where referee_id != 2 or referee_id is null;
}
