/**
 * Created by sparameshwaran on 8/28/17.
 */
public class MaxSubArray {

  public class MaxResult {
    int leftIndex;
    int rightIndex;
    int sum;

    public MaxResult(int leftIndex, int rightIndex, int sum) {
      this.leftIndex = leftIndex;
      this.rightIndex = rightIndex;
      this.sum = sum;
    }

    @Override
    public String toString(){
      return ("MaxResult:- Left-Index = " + leftIndex + "; Right-Index = " + rightIndex + "; Sum = " + sum + "\n");
    }
  }

  MaxResult getMaxSubArray(int[] array){
  return getMaxSubArray(array, 0, array.length-1);
  }

  MaxResult getMaxSubArray(int[] array, int low, int high ){

    // Base Case
    if (low == high){
      return new MaxResult(low, high, array[low]);
    }
    // Divide the problem into 2 sub problems: left and right sub arrays
    int mid = (int)Math.floor((low+high)/2);
    // Get the Max-sum and the indices from the 2 array sections
    MaxResult leftSubArrayResult = getMaxSubArray(array, low, mid);
    // Get Max-sum and indices of array containing the mid-point
    MaxResult rightSubArrayResult = getMaxSubArray(array, mid+1, high);
    // Get Max-sum that is contains the mid (spanning from the left to mid to right)
    MaxResult midSubArrayResult = getMaxSubArrayWithMidpoint(array, low, mid, high);
    // Get the max of the 3 results.
    // If left-side sum is the largest, return that along with indices
    if (leftSubArrayResult.sum > rightSubArrayResult.sum && leftSubArrayResult.sum > midSubArrayResult.sum){
      return leftSubArrayResult;
    }else if (rightSubArrayResult.sum > leftSubArrayResult.sum && rightSubArrayResult.sum > midSubArrayResult.sum){
      return rightSubArrayResult;
    }else {
      return midSubArrayResult;
    }
  }

  // O(n) scan the array to find the max sum and the indices
  private MaxResult getMaxSubArrayWithMidpoint(int[] array, int low, int mid, int high) {
    if (low == high){
      return new MaxResult(low, high, array[low]);
    }

    int leftSum = 0;
    int maxLeftSum = -Integer.MIN_VALUE;
    int leftIndex = low;
    for (int i = mid; i >= low; i--){ // NOTE: Start from mid and proceed towards low because the sum MUST include the mid.
      leftSum += array[i];
      if (maxLeftSum < leftSum){
        maxLeftSum = leftSum;
        leftIndex = i;
      }
    }

    int rightSum = 0;
    int maxRightSum = -Integer.MIN_VALUE;
    int rightIndex = high;
    for (int i = mid+1; i <= high; i++){
      rightSum += array[i];
      if(maxRightSum < rightSum){
        maxRightSum = rightSum;
        rightIndex = i;
      }
    }
    return  new MaxResult(leftIndex, rightIndex, maxLeftSum+maxRightSum);
  }

  public static void main(String args[]){
    MaxSubArray maxSubArray = new MaxSubArray();
    int[] input = new int[]{13,-3,-25,20,-3,-16,-23,18,20,-7,12,-5,-22,15,-4,7};
    System.out.println(maxSubArray.getMaxSubArray(input));
  }
}
