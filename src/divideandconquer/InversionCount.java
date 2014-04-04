package divideandconquer;

import java.util.ArrayList;
import java.util.List;

/**
 * Given an array, it finds the number of inversions 
 * @author Kavi
 *
 */
public class InversionCount 
{	
	/* Count inversions while merging */
	private static int merge(int[] a, int start, int mid, int end)
	{
		int leftStart = start;
		int rightStart = mid;
		int count = 0;
		List<Integer> temp = new ArrayList<Integer>();
		
		while( (leftStart <= mid) && (rightStart <= end) )
		{
			if(a[leftStart] <= a[rightStart])
			{
				temp.add(a[leftStart++]);
			}
			else
			{
				temp.add(a[rightStart++]);
				
				/* Since the rest of the elements in the left array 
				 * counts for inversions */
				count += mid - leftStart;
			}
		}
		
		/* Copy the rest of the elements */
		while(leftStart <= mid)
		{
			temp.add(a[leftStart++]);
		}
		
		while(rightStart <= end)
		{
			temp.add(a[rightStart++]);
		}
		
		/* Copy the sorted elements to the real array */
		int k = 0;
		for(int i = start; i <= end; i++)
		{
			a[i] = temp.get(k++);
		}
		return count;
	}
	
	/* Apply merge sort */
	private static int getInversionCount(int[] input, int start, int end)
	{
		if(start >= end)
		{
			return 0;
		}
		int mid = start + ( end - start ) / 2;
		int count = getInversionCount(input, start, mid);
		count += getInversionCount(input, mid + 1, end);
		count += merge(input, start, mid+1, end);
		return count;
	}
	
	public static void main(String args[])
	{
		int a[] = {1,2,3,4,6,5};
		System.out.println(getInversionCount(a, 0, a.length - 1));
	}
}
