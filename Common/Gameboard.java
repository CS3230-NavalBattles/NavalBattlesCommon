package Common;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import java.awt.*;

/**
 * Joint class by Battleship group on 7/16/2015.
 */

public class Gameboard extends JPanel
{
    private int cellWidth;
    private int cellHeight;

    public Gameboard()
    {
        this.setBorder((new CompoundBorder(new EtchedBorder(),new LineBorder(Color.black))));
        this.setOpaque(true);
        this.setLayout(new GridLayout(10,10,0,0));
    }
    public void paintComponenet(Graphics g)
    {
        super.paintComponent(g);

        int width = getWidth();
        int height = getHeight();
        cellWidth = width/10;
        cellHeight = height/10;

        for(int row = 1; row < 10; row++)
        {
            int vPos = cellHeight*row;
            g.drawLine(0,vPos,width,vPos);
        }

        for(int col = 1; col < 10; col++)
        {
            int hPos = cellWidth*col;
            g.drawLine(hPos,0,hPos,height);
        }
    }

    protected int getCellWidth()
    {
        return cellWidth;
    }

    protected int getCellHeight()
    {
        return cellHeight;
    }
}