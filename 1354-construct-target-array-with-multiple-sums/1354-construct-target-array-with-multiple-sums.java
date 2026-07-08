import java.util.PriorityQueue;

class Solution {
    public boolean isPossible(int[] target) {

        PriorityQueue<Integer> maxHeap =
                new PriorityQueue<>((a, b) -> b - a);

        long sum = 0;

        for (int num : target) {
            maxHeap.offer(num);
            sum += num;
        }

        while (true) {

            int largest = maxHeap.poll();
            long rest = sum - largest;

            if (largest == 1 || rest == 1)
                return true;

            if (rest == 0 || largest < rest || largest % rest == 0)
                return false;

            int previous = (int) (largest % rest);

            sum = rest + previous;
            maxHeap.offer(previous);
        }
    }
}