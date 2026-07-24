import java.util.HashSet;
import java.util.Set;

class Solution {
    public int uniqueXorTriplets(int[] nums) {
        // Step 1: Extract unique numbers from nums
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }

        int[] unique = new int[set.size()];
        int idx = 0;
        for (int num : set) {
            unique[idx++] = num;
        }

        // Max possible XOR result for elements <= 1500 is < 2048
        boolean[] seen = new boolean[2048];
        int count = 0;

        // Step 2: Every unique element x is achievable (via x ^ x ^ x or x ^ y ^ y)
        for (int x : unique) {
            if (!seen[x]) {
                seen[x] = true;
                count++;
            }
        }

        // Step 3: Check all distinct triplets (x, y, z)
        int n = unique.length;
        for (int i = 0; i < n; i++) {
            int x = unique[i];
            for (int j = i + 1; j < n; j++) {
                int xy = x ^ unique[j];
                for (int k = j + 1; k < n; k++) {
                    int xyz = xy ^ unique[k];
                    if (!seen[xyz]) {
                        seen[xyz] = true;
                        count++;
                        // Early exit if all possible XOR values in [0, 2047] are found
                        if (count == 2048) {
                            return 2048;
                        }
                    }
                }
            }
        }

        return count;
    }
}