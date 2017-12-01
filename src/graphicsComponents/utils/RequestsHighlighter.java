package graphicsComponents.utils;

import java.awt.Color;
import java.awt.Component;
import java.util.Calendar;
import java.util.Collection;
import java.util.LinkedList;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;

import graphicsComponents.frames.CommonFrame;
import main.SpaceSystem;
import schedule.Booking;
import schedule.Month;

public class RequestsHighlighter implements Highlighter {

	private CommonFrame frame;
	private SpaceSystem system;
	private JTable table;
	private Month month;
	CellHighlighterRenderer cellRenderer = new CellHighlighterRenderer();
	
	public RequestsHighlighter(SpaceSystem system, CommonFrame frame) {
		this.system = system;
		this.frame = frame;
		this.table = (JTable) frame.returnComponent(frame.TABLE);
		this.month = (Month) ((WideComboBox) frame.returnComponent(frame.MONTH_CB)).getSelectedItem();
	}
	
	
	public void highlight() {
		Collection<LinkedList<Booking>> pendingRequests = system.getPendingRequests().values();
		table.setDefaultRenderer(Object.class, new CellHighlighterRenderer());
		
		/*
		for(LinkedList<Booking> r: pendingRequests) {
			for(int i = 1; i <= month.getDays(); i++) {
				Calendar requestday = r.peekFirst().getDate();
				int day = requestday.get(Calendar.DAY_OF_MONTH);
				System.out.println("day " +day);
				if(day == i) {
					System.out.println("inside if highlight");
					TableColumn column = table.getColumnModel().getColumn(frame.getTableColumn(day));
					column.setCellRenderer(cellRenderer);
				}
			}
		}*/
	}
	
	class CellHighlighterRenderer extends DefaultTableCellRenderer {

		public Component getTableCellRendererComponent(JTable table, Object obj,
		        boolean isSelected, boolean hasFocus, int row, int column) {
			System.out.println("inside renderer");
		    Component cell = super.getTableCellRendererComponent(table, obj, isSelected, hasFocus, row, column);
		    
		    Collection<LinkedList<Booking>> pendingRequests = system.getPendingRequests().values();
		    for(LinkedList<Booking> r: pendingRequests) {
				for(int i = 1; i <= month.getDays(); i++) {
					Calendar requestday = r.peekFirst().getDate();
					int day = requestday.get(Calendar.DAY_OF_MONTH);
					System.out.println("day " +day);
					if(day == i) {
						System.out.println("inside if highlight");
						
						column.setCellRenderer(cellRenderer);
					}
				}
			}
	
		  
		    return cell;
		}
	}

}
