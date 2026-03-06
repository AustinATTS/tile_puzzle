package src;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/** Representation of the overall frame in a sliding tile puzzle. */
public class TilePuzzle extends JFrame implements ActionListener {
  TilePiece[] tile_pieces = new TilePiece[12]; // Array of the split up image as tiles.
  TilePiece blank_tile; // A blank image tile.

  /** Creates an instance of a TilePuzzle. */
  public TilePuzzle() {
    super("Tile Puzzle"); // Name of the Frame.
    setLayout(new GridLayout(3, 4));
    int index = 0; // The tile index.
    for (int y = 0; y < 3; y++) {
      for (int x = 0; x < 4; x++) {
        TilePiece tile_piece;
        if (index == 11) {
          tile_piece = new TilePiece("tiles/blank_tile.jpg", x, y);
          blank_tile = tile_piece;
        } else {
          tile_piece = new TilePiece("tiles/tile_" + index + ".jpg", x, y);
        }
        tile_piece.addActionListener(this);
        tile_pieces[index] = tile_piece;
        add(tile_piece);
        index++; // Increment the index by 1.
      }
    }
    setSize(475, 400);
    setDefaultCloseOperation(EXIT_ON_CLOSE); // Finish the program on close.
    setVisible(true);
  }

  /**
   * Checks if the selected tile is adjacent to the blank tile and swaps them.
   *
   * @param event The ActionListener event when a tile is clicked.
   */
  public void actionPerformed(ActionEvent event) {
    TilePiece clicked_tile = (TilePiece) event.getSource();
    if (clicked_tile.isAdjacentTo(blank_tile)) {
      clicked_tile.exchangeImageWith(blank_tile);
      int blank_x = blank_tile.getXPos();
      int blank_y = blank_tile.getYPos();
      int clicked_x = clicked_tile.getXPos();
      int clicked_y = clicked_tile.getYPos();
      clicked_tile.setGridPosition(blank_x, blank_y);
      blank_tile.setGridPosition(clicked_x, clicked_y);
      clicked_tile = blank_tile;
    }
  }
}
