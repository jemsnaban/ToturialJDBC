package toturialJDBC;

import java.util.List;

import javax.swing.RowSorter;
import javax.swing.table.AbstractTableModel;

public class TableTask extends AbstractTableModel {// extend fungsi2 yang ada
													// dalam kelas abstrak

	/**
	 * 
	 */
	private static final long serialVersionUID = 6041155667940817464L;
	private List<Task> dataTask;
	private String[] kolom = { "Task Name", "deadline", "deskipsi" };
	private Database db = new Database();
	
	

	public TableTask() {
		// db = new Database();
		dataTask = db.getData();
	}
	
	public void reset(){
		dataTask = db.getData();
		fireTableDataChanged();
		
	}
	
	public void sortData(){
		
	}

	public String getColumnName(int idx) {
		return this.kolom[idx];
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return kolom.length;
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return dataTask.size();
	}

	public void removeRow(int row) {
		dataTask.remove(row);
		fireTableRowsDeleted(row, row);
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		if (columnIndex == 0) {
			return dataTask.get(rowIndex).getTask_name();
		} else if (columnIndex == 1) {
			return dataTask.get(rowIndex).getWaktu();
		} else if (columnIndex == 2) {
			return dataTask.get(rowIndex).getDeskripsi();
		}
		return null;
	}

	public Object getIdToDelete(int convertRowIndexToModel) {
		// TODO Auto-generated method stub
		return dataTask.get(convertRowIndexToModel).getId();
	}


	public void RowSorter(RowSorter<TableTask> sorter) {
		
		
		
	}

}
