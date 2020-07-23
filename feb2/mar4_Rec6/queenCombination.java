package mar4;

public class queenCombination {

	// using formula ncr=npr/r!
	public static void qcomb(boolean[] boxes, int cq, int tq, int lqwpiwb, String asf) {
		// lqwpiwb= last queen was placed in which box
		if (cq > tq) {
			System.out.println(asf);
			return;
		}

		for (int cb = lqwpiwb + 1; cb < boxes.length; cb++) {
			if (boxes[cb] == false) {
				boxes[cb] = true;
				qcomb(boxes, cq + 1, tq, cb, asf + "q" + cq + "b" + cb + "+");
				boxes[cb] = false;
			}

		}
	}

	// using the concept of getsubsequence (har box k ps 2 choice h ki vo queen ko bithayga ya nhi)
	// using formula 2^n=nc0+nc1+nc2+nc3+.......+ncn 
	public static void qcomb1(int cb, int tb, int cq, int tq, String asf) {
		if (cb == tb) {
			if (cq == tq) 
				System.out.println(asf);
			return;
		}

		qcomb1(cb + 1, tb, cq + 1, tq, asf + "q" + cq + "b" + cb + "+");//if the box says yes to the queen
		qcomb1(cb + 1, tb, cq, tq, asf);// if the box says no to the queen
	}

	public static void main(String[] args) {
//		boolean[] boxes = new boolean[4];
//		qcomb(boxes, 1, 2, -1, "");
		qcomb1(0, 4, 0, 2,"");

	}

}
