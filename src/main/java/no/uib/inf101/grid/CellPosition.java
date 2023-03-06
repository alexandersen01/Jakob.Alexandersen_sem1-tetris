package no.uib.inf101.grid;

public record CellPosition(int row, int col) {

    /**
     * Create a new CellPosition with the given row and col
     * @param deltaRow
     * @param deltaCol
     * @return a new CellPosition with the given row and col
     */
    public CellPosition shiftedBy(int deltaRow, int deltaCol) {
        return new CellPosition(row + deltaRow, col + deltaCol);
    }}
