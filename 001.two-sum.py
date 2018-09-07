# Given an array of integers, return indices of the two numbers such that they add up to a specific target.

# You may assume that each input would have exactly one solution, and you may not use the same element twice.

# Example:

# Given nums = [2, 7, 11, 15], target = 9,
# Because nums[0] + nums[1] = 2 + 7 = 9,
# return [0, 1].


class Solution:
    def twoSum1(self, nums, target):
        """
        :type nums: List[int]
        :type target: int
        :rtype: List[int]
        """
        result = {}
        for i, num in enumerate(nums):
            if target - num in result:
                return [result[target - num], i]
            result[num] = i

    def twoSum2(self, nums, target):
        num_map = {}
        for i in range(len(nums)):
            result = target - nums[i]
            if result in num_map:
                return [num_map[result], i]
            num_map[nums[i]] = i

    def twoSum3(self,nums, target):
        result = []
        for i in range(len(nums)):
            for j in range(i + 1, len(nums)):
                if nums[i] + nums[j] == target:
                    result.append(i)
                    result.append(j)
                    return result

if __name__ == '__main__':
    # print(Solution().twoSum1((2, 7, 11, 15), 9))
    print(Solution().twoSum2((2, 7, 11, 15), 9))
    # print(Solution().twoSum3((2, 7, 11, 15), 9))


"""
主要说明第二种方法：
使用字典，键(key)是target与nums[i]的差，值(value)是所给定数组元素的索引。
1、num_map = {}, i = 0, result = target - nums[i] = 9 - nums[0] = 9 - 2 = 7, 
因为7不在字典num_map中，所以执行num_map[nums[i]] = i,也就是num_map[2] = 0,即num_map = {'2': 0}
2、现在字典num_map = {'2': 0}, i = 1,result = target - nums[i] = 9 - nums[1] = 9 - 7 = 2,
因为2在字典num_map中,所以执行return语句,直接返回[num_map[result], i],也就是[num_map[2], 1],即[0,1]
"""

"""
enumerate() 函数用于将一个可遍历的数据对象(如列表、元组或字符串)组合为一个索引序列，
同时列出数据和数据下标，一般用在 for 循环当中。
a = ['one', 2, 'three', 'four']
for i, elements in enumerate(a):
    print(i, elements)
"""
