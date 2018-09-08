# Given a 32-bit signed integer, reverse digits of an integer.

# Example 1:
# Input: 123
# Output: 321

# Example 2:
# Input: -123
# Output: -321

# Example 3:
# Input: 120
# Output: 21

# Note:
# Assume we are dealing with an environment which could only store integers within the 32-bit signed integer range: [−2^31,  2^31 − 1]. 
# For the purpose of this problem, assume that your function returns 0 when the reversed integer overflows.

class Solution:
    def reverse1(self, x):
        """
        :type x: int
        :rtype: int
        """
        if x < 0:
            # a = str(x)[::-1][-1]
            # b = str(x)[::-1][:-1]
            # c = a + b
            # return int(c)
            return int(str(x)[::-1][-1] + str(x)[::-1][:-1])
        else:
            return int(str(x)[::-1])
        # if abs(x) > 0x7FFFFFFF:
        #     return 0
        # else:
        #     return x
        return 0 if abs(x) > 0x7FFFFFFF else x

    def reverse2(self, x):
        sign = [-1, 1][x > 0]  # 若给定的x大于0，则将sign置为1
        result = sign * int(str(abs(x))[::-1])
        return result if -2**31 < result < 2**31 -1 else 0

if __name__ == '__main__':
    print(Solution().reverse2(120))


"""
这里学到了两个新的用法：
1、sign = [-1, 1][x > 0] 这个用法之前没有见过，其中[-1,1]是一个列表，里面有两个元素，后面是用来判断给定的x是否大于0。
若大于0，则将sign置为1，否则值为-1。
2、return result if -2**31 < result < 2**31 -1 else 0  这个之前没见过，其实这是Python支持的一种语法，允许只使用一个return语句。
"""