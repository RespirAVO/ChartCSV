package alessandrosalerno.chartcsv.application.uielements;

import javax.print.PrintService;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.plaf.TableUI;
import javax.swing.table.*;
import java.awt.*;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.beans.BeanProperty;
import java.text.MessageFormat;
import java.util.EventObject;
import java.util.Vector;

public class ScrollableTable extends JPanel {
    private DefaultTableModel model;
    private JScrollPane scrollablePanel;
    private JTable table;

    public ScrollableTable() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.init();
    }

    private JTable createTable() {
        return new JTable(this.model) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
    }

    private void init() {
        this.model = new DefaultTableModel();
        this.table = this.createTable();
        this.table.setVisible(true);
        this.scrollablePanel = new JScrollPane(this.table);
        this.add(this.scrollablePanel, BorderLayout.CENTER);
        this.scrollablePanel.setVisible(true);
    }

    public void empty() {
        this.remove(this.scrollablePanel);
        this.init();
        this.updateUI();
    }

    public Vector<Vector> getDataVector() {
        return model.getDataVector();
    }

    public void setDataVector(Object[][] dataVector, Object[] columnIdentifiers) {
        model.setDataVector(dataVector, columnIdentifiers);
    }

    public void newDataAvailable(TableModelEvent event) {
        model.newDataAvailable(event);
    }

    public void newRowsAdded(TableModelEvent e) {
        model.newRowsAdded(e);
    }

    public void rowsRemoved(TableModelEvent event) {
        model.rowsRemoved(event);
    }

    public void setNumRows(int rowCount) {
        model.setNumRows(rowCount);
    }

    public void addRow(Vector<?> rowData) {
        model.addRow(rowData);
    }

    public void addRow(Object[] rowData) {
        model.addRow(rowData);
    }

    public void insertRow(int row, Vector<?> rowData) {
        model.insertRow(row, rowData);
    }

    public void insertRow(int row, Object[] rowData) {
        model.insertRow(row, rowData);
    }

    public void moveRow(int start, int end, int to) {
        model.moveRow(start, end, to);
    }

    public void removeRow(int row) {
        model.removeRow(row);
    }

    public void setColumnIdentifiers(Vector<?> columnIdentifiers) {
        model.setColumnIdentifiers(columnIdentifiers);
    }

    public void setColumnIdentifiers(Object[] newIdentifiers) {
        model.setColumnIdentifiers(newIdentifiers);
    }

    public void addColumn(Object columnName) {
        model.addColumn(columnName);
    }

    public void addColumn(Object columnName, Vector<?> columnData) {
        model.addColumn(columnName, columnData);
    }

    public void addColumn(Object columnName, Object[] columnData) {
        model.addColumn(columnName, columnData);
    }

    public int findColumn(String columnName) {
        return model.findColumn(columnName);
    }

    public void addTableModelListener(TableModelListener l) {
        model.addTableModelListener(l);
    }

    public void removeTableModelListener(TableModelListener l) {
        model.removeTableModelListener(l);
    }

    public TableModelListener[] getTableModelListeners() {
        return model.getTableModelListeners();
    }

    public void fireTableDataChanged() {
        model.fireTableDataChanged();
    }

    public void fireTableStructureChanged() {
        model.fireTableStructureChanged();
    }

    public void fireTableRowsInserted(int firstRow, int lastRow) {
        model.fireTableRowsInserted(firstRow, lastRow);
    }

    public void fireTableRowsUpdated(int firstRow, int lastRow) {
        model.fireTableRowsUpdated(firstRow, lastRow);
    }

    public void fireTableRowsDeleted(int firstRow, int lastRow) {
        model.fireTableRowsDeleted(firstRow, lastRow);
    }

    public void fireTableCellUpdated(int row, int column) {
        model.fireTableCellUpdated(row, column);
    }

    public void fireTableChanged(TableModelEvent e) {
        model.fireTableChanged(e);
    }

    public JTableHeader getTableHeader() {
        return table.getTableHeader();
    }

    @BeanProperty(description = "The JTableHeader instance which renders the column headers.")
    public void setTableHeader(JTableHeader tableHeader) {
        table.setTableHeader(tableHeader);
    }

    public int getRowHeight() {
        return table.getRowHeight();
    }

    @BeanProperty(description = "The height of the specified row.")
    public void setRowHeight(int rowHeight) {
        table.setRowHeight(rowHeight);
    }

    @BeanProperty(description = "The height in pixels of the cells in <code>row</code>")
    public void setRowHeight(int row, int rowHeight) {
        table.setRowHeight(row, rowHeight);
    }

    public int getRowHeight(int row) {
        return table.getRowHeight(row);
    }

    public int getRowMargin() {
        return table.getRowMargin();
    }

    @BeanProperty(description = "The amount of space between cells.")
    public void setRowMargin(int rowMargin) {
        table.setRowMargin(rowMargin);
    }

    public Dimension getIntercellSpacing() {
        return table.getIntercellSpacing();
    }

    @BeanProperty(bound = false, description = "The spacing between the cells, drawn in the background color of the JTable.")
    public void setIntercellSpacing(Dimension intercellSpacing) {
        table.setIntercellSpacing(intercellSpacing);
    }

    public Color getGridColor() {
        return table.getGridColor();
    }

    @BeanProperty(description = "The grid color.")
    public void setGridColor(Color gridColor) {
        table.setGridColor(gridColor);
    }

    @BeanProperty(description = "The color used to draw the grid lines.")
    public void setShowGrid(boolean showGrid) {
        table.setShowGrid(showGrid);
    }

    public boolean getShowHorizontalLines() {
        return table.getShowHorizontalLines();
    }

    @BeanProperty(description = "Whether horizontal lines should be drawn in between the cells.")
    public void setShowHorizontalLines(boolean showHorizontalLines) {
        table.setShowHorizontalLines(showHorizontalLines);
    }

    public boolean getShowVerticalLines() {
        return table.getShowVerticalLines();
    }

    @BeanProperty(description = "Whether vertical lines should be drawn in between the cells.")
    public void setShowVerticalLines(boolean showVerticalLines) {
        table.setShowVerticalLines(showVerticalLines);
    }

    public int getAutoResizeMode() {
        return table.getAutoResizeMode();
    }

    @BeanProperty(enumerationValues = {"JTable.AUTO_RESIZE_OFF", "JTable.AUTO_RESIZE_NEXT_COLUMN", "JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS", "JTable.AUTO_RESIZE_LAST_COLUMN", "JTable.AUTO_RESIZE_ALL_COLUMNS"}, description = "Whether the columns should adjust themselves automatically.")
    public void setAutoResizeMode(int mode) {
        table.setAutoResizeMode(mode);
    }

    public boolean getAutoCreateColumnsFromModel() {
        return table.getAutoCreateColumnsFromModel();
    }

    @BeanProperty(description = "Automatically populates the columnModel when a new TableModel is submitted.")
    public void setAutoCreateColumnsFromModel(boolean autoCreateColumnsFromModel) {
        table.setAutoCreateColumnsFromModel(autoCreateColumnsFromModel);
    }

    public void createDefaultColumnsFromModel() {
        table.createDefaultColumnsFromModel();
    }

    public void setDefaultRenderer(Class<?> columnClass, TableCellRenderer renderer) {
        table.setDefaultRenderer(columnClass, renderer);
    }

    public TableCellRenderer getDefaultRenderer(Class<?> columnClass) {
        return table.getDefaultRenderer(columnClass);
    }

    public void setDefaultEditor(Class<?> columnClass, TableCellEditor editor) {
        table.setDefaultEditor(columnClass, editor);
    }

    public TableCellEditor getDefaultEditor(Class<?> columnClass) {
        return table.getDefaultEditor(columnClass);
    }

    public boolean getDragEnabled() {
        return table.getDragEnabled();
    }

    @BeanProperty(bound = false, description = "determines whether automatic drag handling is enabled")
    public void setDragEnabled(boolean b) {
        table.setDragEnabled(b);
    }

    public DropMode getDropMode() {
        return table.getDropMode();
    }

    public void setDropMode(DropMode dropMode) {
        table.setDropMode(dropMode);
    }

    @BeanProperty(bound = false)
    public JTable.DropLocation getDropLocation() {
        return table.getDropLocation();
    }

    public boolean getAutoCreateRowSorter() {
        return table.getAutoCreateRowSorter();
    }

    @BeanProperty(preferred = true, description = "Whether or not to turn on sorting by default.")
    public void setAutoCreateRowSorter(boolean autoCreateRowSorter) {
        table.setAutoCreateRowSorter(autoCreateRowSorter);
    }

    public boolean getUpdateSelectionOnSort() {
        return table.getUpdateSelectionOnSort();
    }

    @BeanProperty(expert = true, description = "Whether or not to update the selection on sorting")
    public void setUpdateSelectionOnSort(boolean update) {
        table.setUpdateSelectionOnSort(update);
    }

    public RowSorter<? extends TableModel> getRowSorter() {
        return table.getRowSorter();
    }

    @BeanProperty(description = "The table's RowSorter")
    public void setRowSorter(RowSorter<? extends TableModel> sorter) {
        table.setRowSorter(sorter);
    }

    @BeanProperty(enumerationValues = {"ListSelectionModel.SINGLE_SELECTION", "ListSelectionModel.SINGLE_INTERVAL_SELECTION", "ListSelectionModel.MULTIPLE_INTERVAL_SELECTION"}, description = "The selection mode used by the row and column selection models.")
    public void setSelectionMode(int selectionMode) {
        table.setSelectionMode(selectionMode);
    }

    public boolean getRowSelectionAllowed() {
        return table.getRowSelectionAllowed();
    }

    @BeanProperty(visualUpdate = true, description = "If true, an entire row is selected for each selected cell.")
    public void setRowSelectionAllowed(boolean rowSelectionAllowed) {
        table.setRowSelectionAllowed(rowSelectionAllowed);
    }

    public boolean getColumnSelectionAllowed() {
        return table.getColumnSelectionAllowed();
    }

    @BeanProperty(visualUpdate = true, description = "If true, an entire column is selected for each selected cell.")
    public void setColumnSelectionAllowed(boolean columnSelectionAllowed) {
        table.setColumnSelectionAllowed(columnSelectionAllowed);
    }

    public boolean getCellSelectionEnabled() {
        return table.getCellSelectionEnabled();
    }

    @BeanProperty(visualUpdate = true, description = "Select a rectangular region of cells rather than rows or columns.")
    public void setCellSelectionEnabled(boolean cellSelectionEnabled) {
        table.setCellSelectionEnabled(cellSelectionEnabled);
    }

    public void selectAll() {
        table.selectAll();
    }

    public void clearSelection() {
        table.clearSelection();
    }

    public void setRowSelectionInterval(int index0, int index1) {
        table.setRowSelectionInterval(index0, index1);
    }

    public void setColumnSelectionInterval(int index0, int index1) {
        table.setColumnSelectionInterval(index0, index1);
    }

    public void addRowSelectionInterval(int index0, int index1) {
        table.addRowSelectionInterval(index0, index1);
    }

    public void addColumnSelectionInterval(int index0, int index1) {
        table.addColumnSelectionInterval(index0, index1);
    }

    public void removeRowSelectionInterval(int index0, int index1) {
        table.removeRowSelectionInterval(index0, index1);
    }

    public void removeColumnSelectionInterval(int index0, int index1) {
        table.removeColumnSelectionInterval(index0, index1);
    }

    @BeanProperty(bound = false)
    public int getSelectedRow() {
        return table.getSelectedRow();
    }

    @BeanProperty(bound = false)
    public int getSelectedColumn() {
        return table.getSelectedColumn();
    }

    @BeanProperty(bound = false)
    public int[] getSelectedRows() {
        return table.getSelectedRows();
    }

    @BeanProperty(bound = false)
    public int[] getSelectedColumns() {
        return table.getSelectedColumns();
    }

    @BeanProperty(bound = false)
    public int getSelectedRowCount() {
        return table.getSelectedRowCount();
    }

    @BeanProperty(bound = false)
    public int getSelectedColumnCount() {
        return table.getSelectedColumnCount();
    }

    public boolean isRowSelected(int row) {
        return table.isRowSelected(row);
    }

    public boolean isColumnSelected(int column) {
        return table.isColumnSelected(column);
    }

    public boolean isCellSelected(int row, int column) {
        return table.isCellSelected(row, column);
    }

    public void changeSelection(int rowIndex, int columnIndex, boolean toggle, boolean extend) {
        table.changeSelection(rowIndex, columnIndex, toggle, extend);
    }

    public Color getSelectionForeground() {
        return table.getSelectionForeground();
    }

    @BeanProperty(description = "A default foreground color for selected cells.")
    public void setSelectionForeground(Color selectionForeground) {
        table.setSelectionForeground(selectionForeground);
    }

    public Color getSelectionBackground() {
        return table.getSelectionBackground();
    }

    @BeanProperty(description = "A default background color for selected cells.")
    public void setSelectionBackground(Color selectionBackground) {
        table.setSelectionBackground(selectionBackground);
    }

    public TableColumn getColumn(Object identifier) {
        return table.getColumn(identifier);
    }

    public int convertColumnIndexToModel(int viewColumnIndex) {
        return table.convertColumnIndexToModel(viewColumnIndex);
    }

    public int convertColumnIndexToView(int modelColumnIndex) {
        return table.convertColumnIndexToView(modelColumnIndex);
    }

    public int convertRowIndexToView(int modelRowIndex) {
        return table.convertRowIndexToView(modelRowIndex);
    }

    public int convertRowIndexToModel(int viewRowIndex) {
        return table.convertRowIndexToModel(viewRowIndex);
    }

    @BeanProperty(bound = false)
    public int getRowCount() {
        return table.getRowCount();
    }

    public void setRowCount(int rowCount) {
        model.setRowCount(rowCount);
    }

    @BeanProperty(bound = false)
    public int getColumnCount() {
        return table.getColumnCount();
    }

    public void setColumnCount(int columnCount) {
        model.setColumnCount(columnCount);
    }

    public String getColumnName(int column) {
        return table.getColumnName(column);
    }

    public Class<?> getColumnClass(int column) {
        return table.getColumnClass(column);
    }

    public Object getValueAt(int row, int column) {
        return table.getValueAt(row, column);
    }

    public void setValueAt(Object aValue, int row, int column) {
        table.setValueAt(aValue, row, column);
    }

    public boolean isCellEditable(int row, int column) {
        return table.isCellEditable(row, column);
    }

    public void addColumn(TableColumn aColumn) {
        table.addColumn(aColumn);
    }

    public void removeColumn(TableColumn aColumn) {
        table.removeColumn(aColumn);
    }

    public void moveColumn(int column, int targetColumn) {
        table.moveColumn(column, targetColumn);
    }

    public int columnAtPoint(Point point) {
        return table.columnAtPoint(point);
    }

    public int rowAtPoint(Point point) {
        return table.rowAtPoint(point);
    }

    public Rectangle getCellRect(int row, int column, boolean includeSpacing) {
        return table.getCellRect(row, column, includeSpacing);
    }

    @Deprecated
    public void sizeColumnsToFit(boolean lastColumnOnly) {
        table.sizeColumnsToFit(lastColumnOnly);
    }

    public void sizeColumnsToFit(int resizingColumn) {
        table.sizeColumnsToFit(resizingColumn);
    }

    public boolean getSurrendersFocusOnKeystroke() {
        return table.getSurrendersFocusOnKeystroke();
    }

    public void setSurrendersFocusOnKeystroke(boolean surrendersFocusOnKeystroke) {
        table.setSurrendersFocusOnKeystroke(surrendersFocusOnKeystroke);
    }

    public boolean editCellAt(int row, int column) {
        return table.editCellAt(row, column);
    }

    public boolean editCellAt(int row, int column, EventObject e) {
        return table.editCellAt(row, column, e);
    }

    @BeanProperty(bound = false)
    public boolean isEditing() {
        return table.isEditing();
    }

    @BeanProperty(bound = false)
    public Component getEditorComponent() {
        return table.getEditorComponent();
    }

    public int getEditingColumn() {
        return table.getEditingColumn();
    }

    public void setEditingColumn(int aColumn) {
        table.setEditingColumn(aColumn);
    }

    public int getEditingRow() {
        return table.getEditingRow();
    }

    public void setEditingRow(int aRow) {
        table.setEditingRow(aRow);
    }

    @BeanProperty(hidden = true, visualUpdate = true, description = "The UI object that implements the Component's LookAndFeel.")
    public void setUI(TableUI ui) {
        table.setUI(ui);
    }

    public TableModel getModel() {
        return table.getModel();
    }

    @BeanProperty(description = "The model that is the source of the data for this view.")
    public void setModel(TableModel dataModel) {
        table.setModel(dataModel);
    }

    public TableColumnModel getColumnModel() {
        return table.getColumnModel();
    }

    @BeanProperty(description = "The object governing the way columns appear in the view.")
    public void setColumnModel(TableColumnModel columnModel) {
        table.setColumnModel(columnModel);
    }

    public ListSelectionModel getSelectionModel() {
        return table.getSelectionModel();
    }

    @BeanProperty(description = "The selection model for rows.")
    public void setSelectionModel(ListSelectionModel selectionModel) {
        table.setSelectionModel(selectionModel);
    }

    public void sorterChanged(RowSorterEvent e) {
        table.sorterChanged(e);
    }

    public void tableChanged(TableModelEvent e) {
        table.tableChanged(e);
    }

    public void columnAdded(TableColumnModelEvent e) {
        table.columnAdded(e);
    }

    public void columnRemoved(TableColumnModelEvent e) {
        table.columnRemoved(e);
    }

    public void columnMoved(TableColumnModelEvent e) {
        table.columnMoved(e);
    }

    public void columnMarginChanged(ChangeEvent e) {
        table.columnMarginChanged(e);
    }

    public void columnSelectionChanged(ListSelectionEvent e) {
        table.columnSelectionChanged(e);
    }

    public void valueChanged(ListSelectionEvent e) {
        table.valueChanged(e);
    }

    public void editingStopped(ChangeEvent e) {
        table.editingStopped(e);
    }

    public void editingCanceled(ChangeEvent e) {
        table.editingCanceled(e);
    }

    public Dimension getPreferredScrollableViewportSize() {
        return table.getPreferredScrollableViewportSize();
    }

    @BeanProperty(bound = false, description = "The preferred size of the viewport.")
    public void setPreferredScrollableViewportSize(Dimension size) {
        table.setPreferredScrollableViewportSize(size);
    }

    public int getScrollableUnitIncrement(Rectangle visibleRect, int orientation, int direction) {
        return table.getScrollableUnitIncrement(visibleRect, orientation, direction);
    }

    public int getScrollableBlockIncrement(Rectangle visibleRect, int orientation, int direction) {
        return table.getScrollableBlockIncrement(visibleRect, orientation, direction);
    }

    @BeanProperty(bound = false)
    public boolean getScrollableTracksViewportWidth() {
        return table.getScrollableTracksViewportWidth();
    }

    @BeanProperty(bound = false)
    public boolean getScrollableTracksViewportHeight() {
        return table.getScrollableTracksViewportHeight();
    }

    public boolean getFillsViewportHeight() {
        return table.getFillsViewportHeight();
    }

    @BeanProperty(description = "Whether or not this table is always made large enough to fill the height of an enclosing viewport")
    public void setFillsViewportHeight(boolean fillsViewportHeight) {
        table.setFillsViewportHeight(fillsViewportHeight);
    }

    public TableCellEditor getCellEditor() {
        return table.getCellEditor();
    }

    @BeanProperty(description = "The table's active cell editor.")
    public void setCellEditor(TableCellEditor anEditor) {
        table.setCellEditor(anEditor);
    }

    public TableCellRenderer getCellRenderer(int row, int column) {
        return table.getCellRenderer(row, column);
    }

    public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
        return table.prepareRenderer(renderer, row, column);
    }

    public TableCellEditor getCellEditor(int row, int column) {
        return table.getCellEditor(row, column);
    }

    public Component prepareEditor(TableCellEditor editor, int row, int column) {
        return table.prepareEditor(editor, row, column);
    }

    public void removeEditor() {
        table.removeEditor();
    }

    public boolean print() throws PrinterException {
        return table.print();
    }

    public boolean print(JTable.PrintMode printMode) throws PrinterException {
        return table.print(printMode);
    }

    public boolean print(JTable.PrintMode printMode, MessageFormat headerFormat, MessageFormat footerFormat) throws PrinterException {
        return table.print(printMode, headerFormat, footerFormat);
    }

    public boolean print(JTable.PrintMode printMode, MessageFormat headerFormat, MessageFormat footerFormat, boolean showPrintDialog, PrintRequestAttributeSet attr, boolean interactive) throws PrinterException, HeadlessException {
        return table.print(printMode, headerFormat, footerFormat, showPrintDialog, attr, interactive);
    }

    public boolean print(JTable.PrintMode printMode, MessageFormat headerFormat, MessageFormat footerFormat, boolean showPrintDialog, PrintRequestAttributeSet attr, boolean interactive, PrintService service) throws PrinterException, HeadlessException {
        return table.print(printMode, headerFormat, footerFormat, showPrintDialog, attr, interactive, service);
    }

    public Printable getPrintable(JTable.PrintMode printMode, MessageFormat headerFormat, MessageFormat footerFormat) {
        return table.getPrintable(printMode, headerFormat, footerFormat);
    }
}