package feb24;

public class difficultArrayQuestions {

	public static int fi(int[] arr, int sp, int data) {
		if (sp == arr.length)
			return -1;
		if (arr[sp] == data)
			return sp;
		int result = fi(arr, sp + 1, data);
		return result;
	}

	public static int li(int[] arr, int sp, int data) {
		if (sp == arr.length)
			return -1;
		int result = li(arr, sp + 1, data);
		if (result != -1)
			return result;
		else if (arr[sp] == data)
			return sp;
		else
			return -1;
	}

	// allindices(arr,0,9)=arr of indices from 0 to end
	// allindices(arr,1,9)=arr of indices from 1 to end
	public static int[] allindices(int[] arr, int sp, int data,int count) {
		if (sp == arr.length)
			return new int[count];
		if (arr[sp] == data)
			count++;
		int[] result = allindices(arr, sp + 1, data,count);
		if (arr[sp] == data) {
			result[count-1] = sp;
		}

		return result;

	}

	public static void main(String[] args) {
		int[] arr = { 2, 11, 3, 11, 11, 9, 7, 4, 11 };
		// System.out.println(fi(arr,0,11));
		 System.out.println(li(arr,0,11));
		int[] res = allindices(arr, 0, 11,0);
		for (int i = 0; i < res.length; i++)
			System.out.print(res[i] + " ");
	}

}
