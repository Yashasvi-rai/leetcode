class Solution {
    public int removeElement(int[] nums, int val) {
        int i = 0; // Pointer for the next valid position
        
        for (int j = 0; j < nums.length; j++) {
            if (nums[j] != val) {
                nums[i] = nums[j];
                i++;
            }
        }
        
        return i; // k elements are not equal to val
    }
}