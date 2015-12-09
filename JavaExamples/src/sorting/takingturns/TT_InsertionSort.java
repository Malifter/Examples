package sorting.takingturns;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Arrays;

// Probably the best O(n^2) sorting algorithm which is used as the baseline for
// most of the more complex sorting algorithms. Usually used once sub-arrays reach
// around 8 (min) to 24 (max) elements. Varies depending on system/environment.
// Stable (preserves order of duplicates)
// O(1) extra space
// O(n^2) comparisons
// O(n^2) swaps
// Adaptive: O(n) when nearly sorted
// Bonus: Very low overhead (super easy to implement)
public class TT_InsertionSort extends TT_SortMain {
	private static final long serialVersionUID = -6717914153788137989L;
	private Integer [] data = null;
	private int fin = 1;
	private int check = fin;
	
	public TT_InsertionSort(Integer [] data) {
		super(data);
		this.data = Arrays.copyOf(super.copyOfData(), super.copyOfData().length);
	}
	
	public void start() {
		super.start();
		this.data = Arrays.copyOf(super.copyOfData(), super.copyOfData().length);
		fin = 1;
		check = fin;
	}
	
	public void reset() {
		super.reset();
		this.data = Arrays.copyOf(super.copyOfData(), super.copyOfData().length);
		fin = 1;
		check = fin;
	}
	
	private void swap(int i, int j) {
		int tmp = data[i];
		data [i] = data [j];
		data [j] = tmp;
	}
	
	@Override
	public void update() {
		// for every slot
			// bubble back until in correct spot (by swapping)
		loop:
		if(super.isSorting()) {
			if(fin < data.length) {
				if(check > 0 && data[check] < data[check-1]) {
					swap(check, check-1);
					check--;
				} else {
					fin++;
					check = fin;
					break loop;
				}
			} else {
				super.finished();
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
				} else if(super.isSorting() && i < fin) {
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
