package sorting.takingturns;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Arrays;

// Note because this implementation is used for showing steps (and therefore cannot have a recursive call),
// a bottom-up approach is being used rather than a top down. Therefore, the array will start as n separate
// arrays and then be merged into n/2 separate arrays for each iteration until there is only one sorted
// array left.
// Stable (preserves order of duplicates)
// O(n) extra space
// Theta(n*lg(n)) time/comparisons
// Not Adaptive: O(n*lg(n)) when nearly sorted
public class TT_MergeSort extends TT_SortMain {
	private static final long serialVersionUID = 2265882188689294893L;
	private Integer [] data = null;
	private Integer [] auxillary = null;
	private int subSize = 1;
	private int destSize = subSize*2;
	private int first = 0;
	private int second = subSize;
	private int iter = 0;
	private int dest = destSize * iter;
	
	
	public TT_MergeSort(Integer [] data) {
		super(data);
		this.data = Arrays.copyOf(super.copyOfData(), super.copyOfData().length);
		auxillary = Arrays.copyOf(this.data, this.data.length);
		
	}
	
	public void start() {
		super.start();
		this.data = Arrays.copyOf(super.copyOfData(), super.copyOfData().length);
		auxillary = Arrays.copyOf(this.data, this.data.length);
		subSize = 1;
		destSize = subSize*2;
		first = 0;
		second = subSize;
		iter = 0;
		dest = destSize * iter;
	}
	
	public void reset() {
		super.reset();
		this.data = Arrays.copyOf(super.copyOfData(), super.copyOfData().length);
		auxillary = Arrays.copyOf(this.data, this.data.length);
		subSize = 1;
		destSize = subSize*2;
		first = 0;
		second = subSize;
		iter = 0;
		dest = destSize * iter;
	}
	
	@Override
	public void update() {
		// TODO: This doesn't split the arrays evenly, instead they are split by powers of 2
		// Need to implement a recursive solution....although this is tough in this format.
		// while sub-array size < primary array size - O(lg(n))
			// for every sub-array of size subSize - O(n)
				// sort into new super-array of size destSize -- do one step at a time
		loop:
		if(super.isSorting()) {
			if(subSize < data.length) {
				if(iter < Math.ceil(data.length / (float) destSize)) {
					if(dest < data.length && dest < destSize * (iter + 1)) {
						if(first < data.length && second < data.length && first < subSize + (destSize * iter) && second < destSize * (iter + 1)) {
							data[dest++] = auxillary[first] < auxillary[second] ? auxillary[first++] : auxillary[second++];
						} else if(first < data.length && first < subSize + (destSize * iter)) {
							data[dest++] = auxillary[first++];
						} else if(second < data.length && second < destSize * (iter + 1)) {
							data[dest++] = auxillary[second++];
						} else {
							// Should never really get here
							break loop;
						}
					} else {
						iter++;
						dest = destSize * iter;
						first = dest;
						second = first + subSize;
						if(destSize >= data.length) super.finished(); // end here for better graphical representation
						break loop;
					}
				} else {
					auxillary = Arrays.copyOf(this.data, this.data.length);
					subSize *= 2;
					destSize = subSize*2;
					first = 0;
					second = subSize;
					iter = 0;
					dest = destSize * iter;
					break loop;
				}
			}
		}
	}
	
	@Override
	public void render() {
		if(super.flush() || super.isSorting()) {
			Graphics g = this.getGraphics();
			this.paintComponent(g);
			this.getBorder().paintBorder(this, g, 0, 0, getWidth(), getHeight());
			for(int i = 0; i < data.length; i++) {
				if(super.isSorted()) {
					g.setColor(Color.GREEN);
				}else if(super.isSorting() && dest == i) {
					g.setColor(Color.RED);
				} else if(super.isSorting() && destSize * iter <= i && i < subSize + (destSize * iter)) {
					g.setColor(Color.MAGENTA);
				}  else if(super.isSorting() && subSize + (destSize * iter) <= i && i < destSize * (iter + 1)) {
					g.setColor(Color.BLUE);
				} else {
					g.setColor(Color.BLACK);
				}
				g.fillOval(((i+1)*9) - 9, ((data.length-data[i])*9) + data.length - 20, 9, 9);
			}
			g.dispose();
		}
	}
}
