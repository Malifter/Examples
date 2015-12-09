package sorting.takingturns;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Arrays;

// Takes advantage of using gaps to sort long distance items first in lg(n) steps
// then it goes through the final form with gap=1 and performs a normal insertion
// sort over all of the data (roughly O(n))
// Not Stable (doesn't preserve order of duplicates)
// O(1) extra space
// O(n^3/2) comparisons
// O(n^3/2) swaps
// Adaptive: O(n*lg(n)) when nearly sorted
// Additional: Uses Insertion Sort for baseline sort.
public class TT_ShellSort extends TT_SortMain{
	private static final long serialVersionUID = -7948103432731702496L;
	private Integer [] data = null;
	private int gap = 0;
	private int interval = 0;
	private int fin = 1;
	private int check = fin;
	
	public TT_ShellSort(Integer [] data) {
		super(data);
		this.data = Arrays.copyOf(super.copyOfData(), super.copyOfData().length);
		while(gap < data.length) {
			gap = (3*gap) + 1;
		}
		gap = gap / 3;
		fin = gap + interval;
		check = fin;
	}
	
	public void start() {
		super.start();
		this.data = Arrays.copyOf(super.copyOfData(), super.copyOfData().length);
		gap = 0;
		while(gap < data.length) {
			gap = (3*gap) + 1;
		}
		gap = gap / 3;
		interval = 0;
		fin = gap + interval;
		check = fin;
	}
	
	public void reset() {
		super.reset();
		this.data = Arrays.copyOf(super.copyOfData(), super.copyOfData().length);
		gap = 0;
		while(gap < data.length) {
			gap = (3*gap) + 1;
		}
		gap = gap / 3;
		interval = 0;
		fin = gap + interval;
		check = fin;
	}
	
	private void swap(int i, int j) {
		int tmp = data[i];
		data [i] = data [j];
		data [j] = tmp;
	}
	
	@Override
	public void update() {
		// start at the third gap (changing gaps effects performance)
		// while still have gaps
			// move to start of next largest gap
			// for every slot after gap
				// bubble back until in correct spot (by swapping)
		
		loop:
		if(super.isSorting()) {
			if(gap > 0) {
				if(interval < gap) {
					// insertion sort on sub-arrays
					if(fin < data.length) {
						if(check >= gap && data[check] < data[check-gap]) {
							swap(check, check-gap);
							check -= gap;
						} else {
							fin += gap;
							check = fin;
							if(fin >= data.length && gap == 1 && interval == 0) { // so that rendering looks nicer
								super.finished();
							}
							break loop;
						}
					} else {
						interval++;
						fin = gap + interval;
						check = fin;
						break loop;
					}
				} else {
					gap = gap / 3;
					interval = 0;
					fin = gap + interval;
					check = fin;
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
				}else if(super.isSorting() && check == i) {
					g.setColor(Color.RED);
				} else if(super.isSorting() && i <= fin && gap > 0 &&  i % gap == interval) {
					g.setColor(Color.MAGENTA);
				} else {
					g.setColor(Color.BLACK);
				}
				g.fillOval(((i+1)*9) - 9, ((data.length-data[i])*9) + data.length - 20, 9, 9);
			}
			g.dispose();
		}
	}
}
