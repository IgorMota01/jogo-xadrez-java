package boardgame;

public class Board {
	private int rows;
	private int columns;
	private Piece[][] piece;
	
	public Board(int rows, int columns) {
		if (rows < 1 || columns < 1) {
			throw new BoardException("Erro ao criar Tabuleiro. É necessário que haja ao menos 1 linha e 1 coluna");
		}
		this.rows = rows;
		this.columns = columns;
		piece = new Piece[rows][columns];
	}

	public int getRows() {
		return rows;
	}

	public int getColumns() {
		return columns;
	}
	
	public Piece piece(int row, int column) {
		if (!positionExists(row, column)) {
			throw new BoardException("Posição não existe no tabuleiro");
		}
		return piece[row][column];
	}
	
	public Piece piece(Position position) {
		if (!positionExists(position)) {
			throw new BoardException("Posição não existe no tabuleiro");
		}
		return piece[position.getRow()][position.getColumn()];
	}
	
	public void placePiece(Piece pieces, Position position) {
		if (thereIsAPiece(position)) {
			throw new BoardException("Já existe uma peça nessa posição" + position);
		}
		piece[position.getRow()][position.getColumn()] = pieces;
		pieces.position = position;
	}
	
	public Piece removePiece(Position position) {
		if (!positionExists(position)) { 
			throw new BoardException("Posição não existe no tabuleiro.");
		}
		if (piece(position) == null) {
			return null;
		}
		Piece aux = piece(position);
		aux.position = null;
		piece[position.getRow()][position.getColumn()] = null;
		return aux;
	}
	
	public boolean positionExists(int row, int column) {
		return row >= 0 && row < rows && column >= 0 && column < columns;
	}
	public boolean positionExists(Position position) {
		return positionExists(position.getRow(), position.getColumn());
	}
	
	public boolean thereIsAPiece(Position position) {
		if (!positionExists(position)) {
			throw new BoardException("Posição não existe no tabuleiro");
		}
		return piece(position) != null;
	}
}
