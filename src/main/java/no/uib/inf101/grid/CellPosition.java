package no.uib.inf101.grid;

public record CellPosition(int row, int col) {

    public CellPosition shiftedBy(int deltaRow, int deltaCol) {
        return new CellPosition(row + deltaRow, col + deltaCol);
    }}
