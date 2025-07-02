  const submissions = [
    {
      id: 123,
      problemName: "Two Sum",
      status: "Passed",
      time: "2025-06-17 13:42",
      code: `function twoSum(nums, target) {
    const map = new Map();
    
    for (let i = 0; i < nums.length; i++) {
        const complement = target - nums[i];
        
        if (map.has(complement)) {
            return [map.get(complement), i];
        }
        
        map.set(nums[i], i);
    }
    
    return [];
}`
    },
    {
      id: 124,
      problemName: "Reverse String",
      status: "Failed",
      time: "2025-06-17 14:15",
      code: `function reverseString(s) {
    // This implementation has a bug
    return s.reverse(); // Error: strings don't have reverse method
}`
    },
    {
      id: 125,
      problemName: "Valid Parentheses",
      status: "Passed",
      time: "2025-06-17 15:30",
      code: `function isValid(s) {
    const stack = [];
    const mapping = {
        ')': '(',
        '}': '{',
        ']': '['
    };
    
    for (let char of s) {
        if (char in mapping) {
            const topElement = stack.length ? stack.pop() : '#';
            if (mapping[char] !== topElement) {
                return false;
            }
        } else {
            stack.push(char);
        }
    }
    
    return stack.length === 0;
}`
    },
    {
      id: 126,
      problemName: "Merge Two Sorted Lists",
      status: "Passed",
      time: "2025-06-17 16:45",
      code: `function mergeTwoLists(list1, list2) {
    const dummy = new ListNode(0);
    let current = dummy;
    
    while (list1 && list2) {
        if (list1.val <= list2.val) {
            current.next = list1;
            list1 = list1.next;
        } else {
            current.next = list2;
            list2 = list2.next;
        }
        current = current.next;
    }
    
    current.next = list1 || list2;
    return dummy.next;
}`
    },
    {
      id: 127,
      problemName: "Binary Search",
      status: "Failed",
      time: "2025-06-17 17:20",
      code: `function search(nums, target) {
    let left = 0;
    let right = nums.length; // Bug: should be nums.length - 1
    
    while (left < right) {
        const mid = Math.floor((left + right) / 2);
        
        if (nums[mid] === target) {
            return mid;
        } else if (nums[mid] < target) {
            left = mid + 1;
        } else {
            right = mid - 1;
        }
    }
    
    return -1;
}`
    }
  ];

  export default submissions;