package examples;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.locks.ReentrantLock;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import sorting.takingturns.TT_BubbleSort;
import sorting.takingturns.TT_InsertionSort;
import sorting.takingturns.TT_MergeSort;
import sorting.takingturns.TT_SelectionSort;
import sorting.takingturns.TT_ShellSort;
import sorting.takingturns.TT_SortMain;
import utilities.MyTimer;

public class TakingTurnsSortingExamples {
	private final static ReentrantLock SORT_LOCK = new ReentrantLock();
	
	public static void main(String [] args) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		JFrame frame = new JFrame("Sorting Examples");
		TT_SortMain [][] sortGrid = new TT_SortMain [DataType.values().length][SortType.values().length];
		
		// Background to frame.
		JPanel background = new JPanel();
		background.setLayout(new BoxLayout(background, BoxLayout.Y_AXIS));
		frame.add(background);
		
		// Top header section
		JPanel topHeader = new JPanel();
		topHeader.setLayout(new BoxLayout(topHeader, BoxLayout.X_AXIS));
		background.add(topHeader);
		
		// Top header - left
		JPanel topHeaderLeft = new JPanel();
		topHeaderLeft.setLayout(new BoxLayout(topHeaderLeft, BoxLayout.Y_AXIS));
		topHeaderLeft.setPreferredSize(new Dimension(120,70));
		topHeader.add(topHeaderLeft);

		JButton resetAll = new JButton("Reset All");
		JButton sortAll = new JButton("Sort All");
		topHeaderLeft.add(resetAll);
		topHeaderLeft.add(sortAll);
		
		resetAll.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				SORT_LOCK.lock();
				try {
					for(int i = 0; i < DataType.values().length; i++) {
						for(int j = 0; j < SortType.values().length; j++) {
							sortGrid[i][j].reset();
						}
					}
				} finally {
					SORT_LOCK.unlock();
				}
			}
		});
		
		sortAll.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				SORT_LOCK.lock();
				try {
					for(int i = 0; i < DataType.values().length; i++) {
						for(int j = 0; j < SortType.values().length; j++) {
							sortGrid[i][j].start();
						}
					}
				} finally {
					SORT_LOCK.unlock();
				}
			}
		});
		
		// Top header - right
		JPanel topHeaderRight = new JPanel();
		topHeaderRight.setLayout(new GridLayout(0, SortType.values().length));
		topHeader.add(topHeaderRight);
		for(int j = 0; j < SortType.values().length; j++) {
			JPanel sortPanel = new JPanel();
			sortPanel.setLayout(new BoxLayout(sortPanel, BoxLayout.Y_AXIS));
			sortPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			sortPanel.setPreferredSize(new Dimension(180,60));
			topHeaderRight.add(sortPanel);
			
			// button & label
			JButton sortButton = new JButton("Sort Column");
			sortPanel.add(sortButton);
			JLabel sortLabel = new JLabel(SortType.values()[j].name());
			sortPanel.add(sortLabel);
			
			final int sj = j;
			sortButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					SORT_LOCK.lock();
					try {
						for(int i = 0; i < DataType.values().length; i++) {
							for(int j = 0; j < SortType.values().length; j++) {
								if(j == sj) sortGrid[i][j].start();
								else sortGrid[i][j].reset();
							}
						}
					} finally {
						SORT_LOCK.unlock();
					}
				}
			});
		}
		
		// Bottom section
		JPanel bottom = new JPanel();
		bottom.setLayout(new BoxLayout(bottom, BoxLayout.X_AXIS));
		background.add(bottom);
		
		// Setup bottom header -- bottom left
		JPanel leftHeader = new JPanel();
		leftHeader.setLayout(new GridLayout(DataType.values().length, 0));
		bottom.add(leftHeader);
		for(int i = 0; i < DataType.values().length; i++) {
			JPanel dataPanel = new JPanel();
			dataPanel.setLayout(new BoxLayout(dataPanel, BoxLayout.Y_AXIS));
			dataPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			dataPanel.setPreferredSize(new Dimension(120,180));
			leftHeader.add(dataPanel);
			
			// button & label
			JButton dataButton = new JButton("Sort Row");
			dataPanel.add(dataButton);
			JLabel dataLabel = new JLabel(DataType.values()[i].name());
			dataPanel.add(dataLabel);
			
			final int di = i;
			dataButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					SORT_LOCK.lock();
					try {
						for(int i = 0; i < DataType.values().length; i++) {
							for(int j = 0; j < SortType.values().length; j++) {
								if(i == di) sortGrid[i][j].start();
								else sortGrid[i][j].reset();
							}
						}
					} finally {
						SORT_LOCK.unlock();
					}
				}
			});
		}
		
		// Setup sorting grid grid -- bottom right
		JPanel grid = new JPanel();
		grid.setLayout(new GridLayout(DataType.values().length, SortType.values().length));
		bottom.add(grid);
		for(int i = 0; i < DataType.values().length; i++) {
			DataType dt = DataType.values()[i];
			for(int j = 0; j < SortType.values().length; j++) {
				SortType st = SortType.values()[j];
				TT_SortMain ttsm = (TT_SortMain) st.sortClass()
						.getDeclaredConstructor(new Class<?> [] {dt.data().getClass()})
						.newInstance(new Object [] {dt.data()});
				ttsm.setBorder(BorderFactory.createLineBorder(Color.BLACK));
				ttsm.setPreferredSize(new Dimension(180,180));
				grid.add(ttsm);
				sortGrid[i][j] = ttsm;
			}
		}
		
		// Finalize frame size.
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack(); // allows us to center on screen
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension windowSize = frame.getSize();
        frame.setLocation((int)(screenSize.getWidth()/2 - windowSize.getWidth()/2),
                          (int)(screenSize.getHeight()/2 - windowSize.getHeight()/2));
		frame.setResizable(false);
		topHeaderLeft.setSize(new Dimension(120,70));
		frame.pack();
		frame.setVisible(true);
		
		// Update and Render forever
		MyTimer timer = new MyTimer();
		timer.start();
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				resetAll.doClick();
			}
		});
		while(true) {
			SORT_LOCK.lock();
			try {
				for(int i = 0; i < DataType.values().length; i++) {
					for(int j = 0; j < SortType.values().length; j++) {
						sortGrid[i][j].render();
					}
				}
				timer.lap();
				for(int i = 0; i < DataType.values().length; i++) {
					for(int j = 0; j < SortType.values().length; j++) {
						sortGrid[i][j].update();
					}
				} 
			} finally {
				SORT_LOCK.unlock();
			}
			while(timer.time() < 75) continue;
		}
	}
	
	private enum SortType {
		BUBBLE(TT_BubbleSort.class),
		SELECTION(TT_SelectionSort.class),
		INSERTION(TT_InsertionSort.class),
		SHELL(TT_ShellSort.class),
		MERGE(TT_MergeSort.class);
		
		private final Class<?> sortClass;
		
		private SortType(Class<?> sortClass) {
			this.sortClass = sortClass;
		}
		
		public Class<?> sortClass() {
			return sortClass;
		}
	}
	
	private enum DataType {
		RANDOM(new Integer [] {2, 13, 5, 11, 20, 18, 4, 15, 16, 6, 14, 9, 3, 19, 12, 1, 7, 17, 8, 10}),
		NEARLY_SORTED(new Integer [] {1, 2, 4, 5, 3, 6, 7, 9, 8, 10, 12, 11, 14, 15, 13, 16, 18, 17, 19, 20}),
		REVERSE(new Integer [] {20, 19, 18, 17, 16, 15, 14, 13, 12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1}),
		FEW_UNIQUE(new Integer [] {20, 5, 15, 10, 20, 20, 10, 5, 20, 10, 15, 20, 20, 15, 5, 10, 20, 20, 10, 20});
		
		private final Integer [] data;
		
		private DataType(Integer [] data) {
			this.data = data;
		}
		
		public Integer [] data() {
			return data;
		}
	}
}
