package util;

import java.util.List;

public class PrimaryData {
	int[] numbers = new int[]{3, 5, 1};
	int[] key = new int[]{0, 2, 1, 0, 0, 1};
	int[] prime = new int[]{2, 3, 5};
	int random = 3;
	public long getValue(long pan){
		int prim = ((int) pan) % prime[random];
		return numbers[prim];
	}
	
	public long getKey(int value){
		return key[value];
	}
}
