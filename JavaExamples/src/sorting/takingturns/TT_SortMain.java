package sorting.takingturns;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Arrays;

import javax.swing.JPanel;

public abstract class TT_SortMain extends JPanel {
	private static final long serialVersionUID = 3466510534837040638L;
	private final Integer [] immutableData;
	private boolean sorting = false;
	private boolean sorted = false;
	private boolean flush = true;
	
	protected TT_SortMain(Integer [] data) {
		this.setDoubleBuffered(true);
		immutableData = Arrays.copyOf(data, data.length);
		
		this.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent arg0) {
				// do nothing
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// do nothing
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// do nothing
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				start();
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// do nothing
			}
			
		});
	}
	
	protected Integer [] copyOfData() {
		return immutableData;
	}
	
	public void start() {
		sorting = true;
		sorted = false;
		flush = true;
	}
	
	public void reset() {
		sorting = false;
		sorted = false;
		flush = true;
	}
	
	public void finished() {
		sorting = false;
		sorted = true;
		flush = true;
	}
	
	public boolean flush() {
		try {
			return flush;
		} finally {
			flush = false;
		}
	}
	
	public boolean isSorting() {
		return sorting;
	}
	
	public boolean isSorted() {
		return sorted;
	}
	
	public abstract void update();
	public abstract void render();
}
