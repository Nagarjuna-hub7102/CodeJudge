const problems = [
  {
    id: 1,
    name: "Two Sum",
    difficulty: "Easy",
    category: "Arrays",
    description: "Given an array of integers nums and an integer target, return indices of the two numbers such that they add up to target."
  },
  {
    id: 2,
    name: "Longest Substring Without Repeating Characters",
    difficulty: "Medium",
    category: "Strings",
    description: "Given a string s, find the length of the longest substring without repeating characters."
  },
  {
    id: 3,
    name: "Merge K Sorted Lists",
    difficulty: "Hard",
    category: "Linked Lists",
    description: "You are given an array of k linked-lists lists, each linked-list is sorted in ascending order. Merge all the linked-lists into one sorted linked-list and return it."
  },
  {
    id: 4,
    name: "Valid Parentheses",
    difficulty: "Easy",
    category: "Strings",
    description: "Given a string s containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid."
  },
  {
    id: 5,
    name: "Maximum Subarray",
    difficulty: "Medium",
    category: "Arrays",
    description: "Given an integer array nums, find the contiguous subarray (containing at least one number) which has the largest sum and return its sum."
  },
  {
    id: 6,
    name: "Binary Tree Level Order Traversal",
    difficulty: "Medium",
    category: "Trees",
    description: "Given the root of a binary tree, return the level order traversal of its nodes' values. (i.e., from left to right, level by level)."
  },
  {
    id: 7,
    name: "Word Search",
    difficulty: "Medium",
    category: "Graphs",
    description: "Given an m x n grid of characters board and a string word, return true if word exists in the grid."
  },
  {
    id: 8,
    name: "Median of Two Sorted Arrays",
    difficulty: "Hard",
    category: "Arrays",
    description: "Given two sorted arrays nums1 and nums2 of size m and n respectively, return the median of the two sorted arrays."
  },
  {
    id: 9,
    name: "Climbing Stairs",
    difficulty: "Easy",
    category: "Dynamic Programming",
    description: "You are climbing a staircase. It takes n steps to reach the top. Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?"
  },
  {
    id: 10,
    name: "Sudoku Solver",
    difficulty: "Hard",
    category: "Backtracking",
    description: "Write a program to solve a Sudoku puzzle by filling the empty cells."
  },
  {
    id: 11,
    name: "Best Time to Buy and Sell Stock",
    difficulty: "Easy",
    category: "Dynamic Programming",
    description: "You are given an array prices where prices[i] is the price of a given stock on the ith day. Maximize your profit."
  },
  {
    id: 12,
    name: "LRU Cache",
    difficulty: "Medium",
    category: "Design",
    description: "Design a data structure that follows the constraints of a Least Recently Used (LRU) cache."
  }
];

export default problems;



 export const problemData = {
    title: "Two Sum",
    category: "Array",
    difficulty: "Easy",
    description: "Given an array of integers nums and an integer target, return indices of the two numbers such that they add up to target. You may assume that each input would have exactly one solution, and you may not use the same element twice. You can return the answer in any order.",
    inputFormat: "The first line contains an integer n, the size of the array. The second line contains n space-separated integers representing the array nums. The third line contains an integer target.",
    outputFormat: "Return an array of two integers representing the indices of the two numbers that add up to target.",
    constraints: [
      "2 ≤ nums.length ≤ 10⁴",
      "-10⁹ ≤ nums[i] ≤ 10⁹",
      "-10⁹ ≤ target ≤ 10⁹",
      "Only one valid answer exists."
    ],
    examples: [
      {
        input: "nums = [2,7,11,15], target = 9",
        output: "[0,1]",
        explanation: "Because nums[0] + nums[1] == 9, we return [0, 1]."
      },
      {
        input: "nums = [3,2,4], target = 6",
        output: "[1,2]",
        explanation: "Because nums[1] + nums[2] == 6, we return [1, 2]."
      }
    ]
  };


  export const progresses = [
  {
    id: 1,
    problemName: "Two Sum",
    language: "JavaScript",
    code: `function twoSum(nums, target) {
  const map = new Map();
  for(let i = 0; i < nums.length; i++) {
    const complement = target - nums[i];
    if(map.has(complement)) {
      return [map.get(complement), i];
    }
    map.set(nums[i], i);
  }
}`
  },
  {
    id: 2,
    problemName: "Valid Parentheses",
    language: "Java",
    code: `public boolean isValid(String s) {
  Stack<Character> stack = new Stack<>();
  for (char c : s.toCharArray()) {
    if (c == '(') stack.push(')');
    else if (c == '[') stack.push(']');
    else if (c == '{') stack.push('}');
    else if (stack.isEmpty() || stack.pop() != c) return false;
  }
  return stack.isEmpty();
}`
  },
  {
    id: 3,
    problemName: "Merge Two Sorted Lists",
    language: "Python",
    code: `def mergeTwoLists(l1, l2):
  if not l1 or not l2:
    return l1 or l2
  if l1.val < l2.val:
    l1.next = mergeTwoLists(l1.next, l2)
    return l1
  else:
    l2.next = mergeTwoLists(l1, l2.next)
    return l2`
  }
];

