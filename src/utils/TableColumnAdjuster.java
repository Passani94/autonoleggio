package utils;

import java.awt.Component;
import java.awt.event.ActionEvent;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import java.util.HashMap;
import java.util.Map;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

/** 
 * La classe TableColumnAdjuster modifica la larghezza delle colonne di una tabella in base al contenuto. <br><br>
 * 
 * Questa classe è stata presa dalla rete.
 */

public class TableColumnAdjuster implements PropertyChangeListener, TableModelListener {
	
	private int spacing;
	private boolean isColumnHeaderIncluded;
	private boolean isColumnDataIncluded;
	private boolean isOnlyAdjustLarger;
	private boolean isDynamicAdjustment;
	private JTable table;
	private Map<TableColumn, Integer> columnSizes = new HashMap<TableColumn, Integer>();

	/**
	 * Inizializza un nuovo oggetto TableColumnAdjuster e imposta la larghezza delle colonne della tabella {@code table} ad un valore di default.
	 * 
	 * @param table la tabella da ridimensionare.
	 */
	public TableColumnAdjuster(JTable table) {
		
		this(table, 6);
	}

	/**
	 * Inizializza un nuovo oggetto TableColumnAdjuster e imposta la larghezza delle colonne della tabella {@code table} al valore {@code spacing} passato come argomento.
	 * 
	 * @param table la tabella da ridimensionare.
	 * @param spacing la larghezza delle colonne desiderata.
	 */
	public TableColumnAdjuster(JTable table, int spacing) {
		
		this.table = table;
		this.spacing = spacing;
		setColumnHeaderIncluded(true);
		setColumnDataIncluded(true);
		setOnlyAdjustLarger(false);
		setDynamicAdjustment(false);
		installActions();
	}

	
	/**
	 * Ridimensiona la larghezza di tutte le colonne della tabella.
	 */
	public void adjustColumns() {
		
		TableColumnModel tcm = table.getColumnModel();
		for (int i = 0; i < tcm.getColumnCount(); i++) {
			adjustColumn(i);
		}
	}

	
	/**
	 * Ridimensiona la larghezza della colonna passata come argomento.
	 * 
	 * @param column la colonna da ridimensionare.
	 */
	public void adjustColumn(final int column) {
		
		TableColumn tableColumn = table.getColumnModel().getColumn(column);
		if (!tableColumn.getResizable()) return;
		int columnHeaderWidth = getColumnHeaderWidth(column);
		int columnDataWidth = getColumnDataWidth(column);
		int preferredWidth = Math.max(columnHeaderWidth, columnDataWidth);
		updateTableColumn(column, preferredWidth);
	}

	//Calcola la larghezza in base all'intestazione della colonna passata come argomento.
	private int getColumnHeaderWidth(int column) {
		
		if (!isColumnHeaderIncluded) return 0;
		TableColumn tableColumn = table.getColumnModel().getColumn(column);
		Object value = tableColumn.getHeaderValue();
		TableCellRenderer renderer = tableColumn.getHeaderRenderer();
		if (renderer == null) {
			renderer = table.getTableHeader().getDefaultRenderer();
		}
		Component c = renderer.getTableCellRendererComponent(table, value, false, false, -1, column);
		return c.getPreferredSize().width;
	}

	
	//Calcola la larghezza in base all'elemento più largo della colonna passata come argomento.
	private int getColumnDataWidth(int column) {
		
		if (!isColumnDataIncluded) return 0;
		int preferredWidth = 0;
		int maxWidth = table.getColumnModel().getColumn(column).getMaxWidth();
		for (int row = 0; row < table.getRowCount(); row++) {
			preferredWidth = Math.max(preferredWidth, getCellDataWidth(row, column));
			//  Se si supera la larghezza massima, non vengono controllate le righe successive.
			if (preferredWidth >= maxWidth) break;
		}
		return preferredWidth;
	}

	//Calcola la larghezza della colonna in base all'elemento corrente.
	private int getCellDataWidth(int row, int column) {
		
		TableCellRenderer cellRenderer = table.getCellRenderer(row, column);
		Component c = table.prepareRenderer(cellRenderer, row, column);
		int width = c.getPreferredSize().width + table.getIntercellSpacing().width;
		return width;
	}

	
	//Ridimensiona la colonna con la nuova larghezza calcolata.
	private void updateTableColumn(int column, int width) {
		
		final TableColumn tableColumn = table.getColumnModel().getColumn(column);
		if (!tableColumn.getResizable()) return;
		width += spacing;
		//  Non restringere la larghezza della colonna.
		if (isOnlyAdjustLarger)	{
			width = Math.max(width, tableColumn.getPreferredWidth());
		}
		columnSizes.put(tableColumn, new Integer(tableColumn.getWidth()));
		table.getTableHeader().setResizingColumn(tableColumn);
		tableColumn.setWidth(width);
	}

	/**
	 * Ripristina la larghezza delle colonne ai loro precedenti valori.
	 */
	public void restoreColumns() {
		
		TableColumnModel tcm = table.getColumnModel();
		for (int i = 0; i < tcm.getColumnCount(); i++) {
			restoreColumn(i);
		}
	}
	
	//Ripristina la larghezza della colonna passata come argomento al suo valore precendente.
	private void restoreColumn(int column) {
		
		TableColumn tableColumn = table.getColumnModel().getColumn(column);
		Integer width = columnSizes.get(tableColumn);
		if (width != null) {
			table.getTableHeader().setResizingColumn(tableColumn);
			tableColumn.setWidth( width.intValue() );
		}
	}

	/**
	 * Specifica se includere l'intestazione della colonna nel calcolo della larghezza.
	 * 
	 * @param isColumnHeaderIncluded indica se l'intestazione della colonna è inclusa nel calcolo della larghezza.
	 */
	public void setColumnHeaderIncluded(boolean isColumnHeaderIncluded) {
		
		this.isColumnHeaderIncluded = isColumnHeaderIncluded;
	}

	/**
	 * Specifica se includere l'elemento della colonna nel calcolo della larghezza.
	 * 
	 * @param isColumnDataIncluded indica se l'elemento della colonna è incluso nel calcolo della larghezza.
	 */
	public void setColumnDataIncluded(boolean isColumnDataIncluded) {
		
		this.isColumnDataIncluded = isColumnDataIncluded;
	}

	/**
	 * Specifica se la larghezza delle colonne può essere solo aumentata.
	 * 
	 * @param isOnlyAdjustLarger indica se la larghezza delle colonne può essere solo aumentata.
	 */
	public void setOnlyAdjustLarger(boolean isOnlyAdjustLarger) {
		
		this.isOnlyAdjustLarger = isOnlyAdjustLarger;
	}
	
	/**
	 * Specifica se ricalcolare la larghezza delle colonne in caso di modifiche agli elementi della tabella.
	 * 
	 * @param isDynamicAdjustment indica se ricalcolare la larghezza delle colonne in caso di modifiche agli elementi della tabella.
	 */
	public void setDynamicAdjustment(boolean isDynamicAdjustment) {
		
		if (this.isDynamicAdjustment != isDynamicAdjustment) {
			if (isDynamicAdjustment) {
				table.addPropertyChangeListener( this );
				table.getModel().addTableModelListener( this );
			}else {
				table.removePropertyChangeListener( this );
				table.getModel().removeTableModelListener( this );
			}
		}
		this.isDynamicAdjustment = isDynamicAdjustment;
	}

	/**
	 * Implementa il PropertyChangeListener.
	 */
	public void propertyChange(PropertyChangeEvent e) {

		if ("model".equals(e.getPropertyName())) {
			TableModel model = (TableModel)e.getOldValue();
			model.removeTableModelListener( this );

			model = (TableModel)e.getNewValue();
			model.addTableModelListener( this );
			adjustColumns();
		}
	}

	/**
	 * Implementa il TableModelListener.
	 */
	public void tableChanged(TableModelEvent e) {
		
		if (!isColumnDataIncluded) return;
		SwingUtilities.invokeLater(new Runnable() { public void run() {
				
				int column = table.convertColumnIndexToView(e.getColumn());
				if (e.getType() == TableModelEvent.UPDATE && column != -1) {
					if (isOnlyAdjustLarger) {
						int	row = e.getFirstRow();
						TableColumn tableColumn = table.getColumnModel().getColumn(column);
						if (tableColumn.getResizable()) {
							int width =	getCellDataWidth(row, column);
							updateTableColumn(column, width);
						}
					} else {
						adjustColumn( column );
					}
				} else {
					adjustColumns();
				}
			}
		});
	}

	// Imposta delle Actions per dare all'utente il controllo di determinate funzionalità.
	private void installActions() {
		
		installColumnAction(true, true, "adjustColumn", "control ADD");
		installColumnAction(false, true, "adjustColumns", "control shift ADD");
		installColumnAction(true, false, "restoreColumn", "control SUBTRACT");
		installColumnAction(false, false, "restoreColumns", "control shift SUBTRACT");

		installToggleAction(true, false, "toggleDynamic", "control MULTIPLY");
		installToggleAction(false, true, "toggleLarger", "control DIVIDE");
	}

	// Aggiorna l'input e l'action map con un nuovo ColumnAction.
	private void installColumnAction(boolean isSelectedColumn, boolean isAdjust, String key, String keyStroke) {
		
		Action action = new ColumnAction(isSelectedColumn, isAdjust);
		KeyStroke ks = KeyStroke.getKeyStroke( keyStroke );
		table.getInputMap().put(ks, key);
		table.getActionMap().put(key, action);
	}

	// Aggiorna l'input e l'action map con un nuovo ToggleAction.
	private void installToggleAction(boolean isToggleDynamic, boolean isToggleLarger, String key, String keyStroke) {
		
		Action action = new ToggleAction(isToggleDynamic, isToggleLarger);
		KeyStroke ks = KeyStroke.getKeyStroke( keyStroke );
		table.getInputMap().put(ks, key);
		table.getActionMap().put(key, action);
	}

	// Implementa una Action per ridimensionare/ripristinare la larghezza di una singola colonna (o di tutte le colonne).
	class ColumnAction extends AbstractAction {
		
		private static final long serialVersionUID = 1L; 
		private boolean isSelectedColumn;
		private boolean isAdjust;

		public ColumnAction(boolean isSelectedColumn, boolean isAdjust) {
			
			this.isSelectedColumn = isSelectedColumn;
			this.isAdjust = isAdjust;
		}

		public void actionPerformed(ActionEvent e) {
			
			if (isSelectedColumn) {
				int[] columns = table.getSelectedColumns();
				for (int i = 0; i < columns.length; i++) {
					if (isAdjust)
						adjustColumn(columns[i]);
					else
						restoreColumn(columns[i]);
				}
			}else {
				if (isAdjust)
					adjustColumns();
				else
					restoreColumns();
			}
		}
	}

	// Cambia le proprietà del TableColumnAdjuster in modo che l'utente possa personalizzare le funzionalità.
	class ToggleAction extends AbstractAction {
		
		private static final long serialVersionUID = 1L; 
		private boolean isToggleDynamic;
		private boolean isToggleLarger;

		public ToggleAction(boolean isToggleDynamic, boolean isToggleLarger) {
			
			this.isToggleDynamic = isToggleDynamic;
			this.isToggleLarger = isToggleLarger;
		}

		public void actionPerformed(ActionEvent e) {
			
			if (isToggleDynamic) {
				setDynamicAdjustment(! isDynamicAdjustment);
				return;
			}
			if (isToggleLarger) {
				setOnlyAdjustLarger(! isOnlyAdjustLarger);
				return;
			}
		}
	}
}