package sorting.takingturns;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Arrays;

// The most basic sorting algorithm
// Stable (preserves order of duplicates)
// O(1) extra space
// O(n^2) comparisons
// O(n^2) swaps
// Adaptive: O(n) when nearly sorted
public class TT_BubbleSort extends TT_SortMain {
	private static final long serialVersionUID = 7573954993050834179L;
	private Integer [] data = null;
	private int fin = 0;
	private int check = 0;
	private boolean swapped = false;
	
	public TT_BubbleSort(Integer [] data) {
		super(data);
		this.data = Arrays.copyOf(super.copyOfData(), super.copyOfData().length);
	}
	
	public void start() {
		super.start();
		this.data = Arrays.copyOf(super.copyOfData(), super.copyOfData().length);
		fin = 0;
		check = data.length-1;
		swapped = false;
	}
	
	public void reset() {
		super.reset();
		this.data = Arrays.copyOf(super.copyOfData(), super.copyOfData().length);
		fin = 0;
		check = data.length-1;
		swapped = false;
	}
	
	private void swap(int i, int j) {
		int tmp = data[i];
		data [i] = data [j];
		data [j] = tmp;
	}
	
	@Override
	public void update() {
		// for every slot
			// for every pair
				// if out of order, swap
			// if no swaps, finished
		loop:
		if(super.isSorting()) {
			if(fin < data.length) {
				if(check > fin) {
					if(data[check] < data[check-1]) {
						swap(check, check-1);
						swapped = true;
					}
					check--;
				} else {
					if(!swapped) {
						super.finished();
					}
					fin++;
					check = data.length-1;
					swapped = false;
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
				if(super.isSorted() || i < fin) {
					g.setColor(Color.GREEN);
				} else if(super.isSorting() && check == i) {
					g.setColor(Color.RED);
				} else if(super.isSorting() && check == i-1) {
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
