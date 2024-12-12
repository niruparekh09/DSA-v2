package Medium;

import java.util.HashMap;
import java.util.Map;

public class Number_Of_SubArrays_With_Sum_K {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, -3, 1, 1, 1, 4, 2, -3};
        int k = 3;
        int cnt = findAllSubArraysWithGivenSum(arr, k);
        System.out.println("The number of sub-arrays is: " + cnt);
    }

    /*
#### Iteration through the array

- **Iteration 0 (i = 0)**:

  - `preSum += arr[0]` → `preSum = 1`
  - `remove = preSum - k` → `remove = 1 - 3 = -2`
  - `cnt += mpp.getOrDefault(remove, 0)` → `cnt += mpp.getOrDefault(-2, 0)` → `cnt = 0`
  - `mpp.put(preSum, mpp.getOrDefault(preSum, 0) + 1)` → `mpp.put(1, 1)` → Map now: `{0=1, 1=1}`

- **Iteration 1 (i = 1)**:

  - `preSum += arr[1]` → `preSum = 3`
  - `remove = preSum - k` → `remove = 3 - 3 = 0`
  - `cnt += mpp.getOrDefault(remove, 0)` → `cnt += mpp.getOrDefault(0, 1)` → `cnt = 1`
  - `mpp.put(preSum, mpp.getOrDefault(preSum, 0) + 1)` → `mpp.put(3, 1)` → Map now: `{0=1, 1=1, 3=1}`

- **Iteration 2 (i = 2)**:

  - `preSum += arr[2]` → `preSum = 6`
  - `remove = preSum - k` → `remove = 6 - 3 = 3`
  - `cnt += mpp.getOrDefault(remove, 0)` → `cnt += mpp.getOrDefault(3, 1)` → `cnt = 2`
  - `mpp.put(preSum, mpp.getOrDefault(preSum, 0) + 1)` → `mpp.put(6, 1)` → Map now: `{0=1, 1=1, 3=1, 6=1}`

- **Iteration 3 (i = 3)**:

  - `preSum += arr[3]` → `preSum = 3`
  - `remove = preSum - k` → `remove = 3 - 3 = 0`
  - `cnt += mpp.getOrDefault(remove, 0)` → `cnt += mpp.getOrDefault(0, 1)` → `cnt = 3`
  - `mpp.put(preSum, mpp.getOrDefault(preSum, 0) + 1)` → `mpp.put(3, 2)` → Map now: `{0=1, 1=1, 3=2, 6=1}`

- **Iteration 4 (i = 4)**:

  - `preSum += arr[4]` → `preSum = 4`
  - `remove = preSum - k` → `remove = 4 - 3 = 1`
  - `cnt += mpp.getOrDefault(remove, 0)` → `cnt += mpp.getOrDefault(1, 1)` → `cnt = 4`
  - `mpp.put(preSum, mpp.getOrDefault(preSum, 0) + 1)` → `mpp.put(4, 1)` → Map now: `{0=1, 1=1, 3=2, 4=1, 6=1}`

- **Iteration 5 (i = 5)**:

  - `preSum += arr[5]` → `preSum = 5`
  - `remove = preSum - k` → `remove = 5 - 3 = 2`
  - `cnt += mpp.getOrDefault(remove, 0)` → `cnt += mpp.getOrDefault(2, 0)` → `cnt = 4`
  - `mpp.put(preSum, mpp.getOrDefault(preSum, 0) + 1)` → `mpp.put(5, 1)` → Map now: `{0=1, 1=1, 3=2, 4=1, 5=1, 6=1}`

- **Iteration 6 (i = 6)**:

  - `preSum += arr[6]` → `preSum = 6`
  - `remove = preSum - k` → `remove = 6 - 3 = 3`
  - `cnt += mpp.getOrDefault(remove, 0)` → `cnt += mpp.getOrDefault(3, 2)` → `cnt = 6`
  - `mpp.put(preSum, mpp.getOrDefault(preSum, 0) + 1)` → `mpp.put(6, 2)` → Map now: `{0=1, 1=1, 3=2, 4=1, 5=1, 6=2}`

- **Iteration 7 (i = 7)**:

  - `preSum += arr[7]` → `preSum = 10`
  - `remove = preSum - k` → `remove = 10 - 3 = 7`
  - `cnt += mpp.getOrDefault(remove, 0)` → `cnt += mpp.getOrDefault(7, 0)` → `cnt = 6`
  - `mpp.put(preSum, mpp.getOrDefault(preSum, 0) + 1)` → `mpp.put(10, 1)` → Map now: `{0=1, 1=1, 3=2, 4=1, 5=1, 6=2, 10=1}`

- **Iteration 8 (i = 8)**:
  - `preSum += arr[8]` → `preSum = 12`
  - `remove = preSum - k` → `remove = 12 - 3 = 9`
  - `cnt += mpp.getOrDefault(remove, 0)` → `cnt += mpp.getOrDefault(9, 0)` → `cnt = 6`
  - `mpp.put(preSum, mpp.getOrDefault(preSum, 0) + 1)` → `mpp.put(12, 1)` → Map now: `{0=1, 1=1, 3=2, 4=1, 5=1, 6=2, 10=1, 12=1}`

### Final Result
    The final value of cnt is 6, indicating there are 6 subarrays that sum to k = 3.
     */
    private static int findAllSubArraysWithGivenSum(int[] arr, int k) {
        int n = arr.length;
        Map<Integer, Integer> mpp = new HashMap<>();
        int preSum = 0, cnt = 0;
        mpp.put(0, 1);
        for (int i = 0; i < n; i++) {
            preSum += arr[i];
            int remove = preSum - k;
            cnt += mpp.getOrDefault(remove, 0);
            mpp.put(preSum, mpp.getOrDefault(preSum, 0) + 1);
        }
        return cnt;
    }
}
