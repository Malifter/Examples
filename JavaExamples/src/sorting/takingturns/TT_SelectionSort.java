package sorting.takingturns;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Arrays;

// A very basic sorting algorithm guaranteed Theta(n^2) but can minimize swaps (unlike bubble sort)
// Not Stable (doesn't preserve order of duplicates)
// O(1) extra space
// O(n^2) comparisons
// O(n) swaps
// Not Adaptive: O(n^2) when nearly sorted
public class TT_SelectionSort extends TT_SortMain {
	private static final long serialVersionUID = 7938807170556653925L;
	private Integer [] data = null;
	private int fin = 0;
	private int check = fin + 1;
	private int min = fin;

	public TT_SelectionSort(Integer[] data) {
		super(data);
		this.data = Arrays.copyOf(super.copyOfData(), super.copyOfData().length);
	}
	
	public void start() {
		super.start();
		this.data = Arrays.copyOf(super.copyOfData(), super.copyOfData().length);
		fin = 0;
		check = fin + 1;
		min = fin;
	}
	
	public void reset() {
		super.reset();
		this.data = Arrays.copyOf(super.copyOfData(), super.copyOfData().length);
		fin = 0;
		check = fin + 1;
		min = fin;
	}
	
	private void swap(int i, int j) {
		int tmp = data[i];
		data [i] = data [j];
		data [j] = tmp;
	}

	@Override
	public void update() {
		// for every slot
			// find min for every slot after this one
			// swap this with min
		
		loop:
		if(super.isSorting()) {
			if(fin < data.length) { // outer loop to iterate through all n
				if(check < data.length) { // inner loop to find min
					if(data[check] < data[min]) min = check;
					check++;
				} else {
					if(fin != min) swap(fin, min);
					fin++;
					check = fin+1;
					min = fin;
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
				if(super.isSorted() || i < fin) {
					g.setColor(Color.GREEN);
				} else if(super.isSorting() && check == i) {
					g.setColor(Color.RED);
				} else if(super.isSorting() && min == i) {
					g.setColor(Color.MAGENTA);
				}else {
					g.setColor(Color.BLACK);
				}
				g.fillOval(((i+1)*9) - 9, ((data.length-data[i])*9) + data.length - 20, 9, 9);
			}
			g.dispose();
		}
	}
}
