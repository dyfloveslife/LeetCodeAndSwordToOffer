# Determine whether an integer is a palindrome(回文). An integer is a palindrome when it reads the same backward as forward.

# Example 1:
# Input: 121
# Output: true

# Example 2:
# Input: -121
# Output: false
# Explanation: From left to right, it reads -121. From right to left, it becomes 121-. Therefore it is not a palindrome.

# Example 3:
# Input: 10
# Output: false
# Explanation: Reads 01 from right to left. Therefore it is not a palindrome.

class Solution(object):
    def isPalindrome1(self, x):
        """
        :type x: int
        :rtype: bool
        """
        # if x < 0:
        #     return False
        # str_x = str(x)
        # if str_x == str_x[::-1]:
        #     return True
        # return False

    def isPalindrome2(self, x):
        return str(x) == str(x)[::-1]

    def isPalindrome3(self, x):
        str_x = str(x)
        str_x_re = list(str_x)[::-1]
        str_x_re = ''.join(str_x_re)
        return str_x == str_x_re

if __name__ == '__main__':
    print(Solution().isPalindrome3(123321))


"""
方法join()使用指定字符连接多个字符串，它需要一个包含字符串元素的 列表 作为输入然后连接列表内的字符串元素。
"""