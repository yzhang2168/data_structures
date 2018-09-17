package data_structures;

import java.util.Comparator;


class ListNodeComparator implements Comparator<ListNode>{
	@Override
	public int compare(ListNode o1, ListNode o2) {
		if (o1.value < o2.value) {
			return -1;
		} else if (o1.value > o2.value) {
			return 1;
		} else {
			return 0;
		}
	}
	
}
