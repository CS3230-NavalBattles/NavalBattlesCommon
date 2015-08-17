package Common;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Joint class by Battleship group on 7/16/2015.
 */

public class Gameboard extends JPanel
{
    private int cellWidth;
    private int cellHeight;
    private ArrayList<Ship> ships;
    private int[][] gameboard;

    public Gameboard()
    {
        super();
        this.setLayout(new GridLayout(10, 10, 0, 0));
        this.setBorder((new CompoundBorder(new EtchedBorder(), new LineBorder(Color.black))));
        this.setOpaque(false);
        ships = new ArrayList<Ship>();
        gameboard = new int[10][10];
    }

    public void paintComponent(Graphics g)
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

        if (ships.size() > 0);
        {
            for(Ship s: ships)
            {
                if(s.isSet())
                {
                    try
                    {
                        final BufferedImage shipimage;
                        if(s.getDirection().equals(Ship.HORIZONTAL))
                        {
                            shipimage = getHorizontalImage(s.getName());
                            g.drawImage(shipimage, (cellWidth*s.getXcoordinate()), (cellHeight*s.getYcoordinate()), (cellWidth*s.getSize()), cellHeight, null);
                        }
                        else
                        {
                            shipimage = getVerticalImage(s.getName());
                            g.drawImage(shipimage, (cellWidth*s.getXcoordinate()), (cellHeight*s.getYcoordinate()), cellWidth, (cellHeight*s.getSize()), null);
                        }


                    }catch (IOException e)
                    {
                        //currently don't care. only going to show up if there are issues with resource folder.
                    }
                }

            }
        }

        for(int row = 0; row<10; row++)
        {
            for(int col = 0; col<10; col++)
            {
                if(gameboard[row][col] == GameboardArray.HIT || gameboard[row][col] == GameboardArray.MISS)
                {
                    if (gameboard[row][col] == GameboardArray.MISS) {
                        g.setColor(Color.WHITE);

                    } else {
                        g.setColor(Color.RED);
                    }
                    g.fillRect(((cellWidth * col)+1), ((cellHeight * row)+1), (cellWidth-1), (cellHeight-1));
                }
            }
        }

    }

    private BufferedImage getHorizontalImage(String name) throws IOException
    {
        BufferedImage image;
        if(name.equals("Aircraft Carrier"))
        {
            image = ImageIO.read(new File("Resources/Images/AircraftCarrierHoriz.png"));
        }
        else if(name.equals("Battleship"))
        {
            image = ImageIO.read(new File("Resources/Images/BattleshipHoriz.png"));
        }
        else if(name.equals("Cruiser"))
        {
            image = ImageIO.read(new File("Resources/Images/CruiserHoriz.png"));
        }
        else if(name.equals("Submarine"))
        {
            image = ImageIO.read(new File("Resources/Images/SubmarineHoriz.png"));
        }
        else
        {
            image = ImageIO.read(new File("Resources/Images/DestroyerHoriz.png"));
        }

        return image;
    }

    private BufferedImage getVerticalImage(String name) throws IOException
    {
        BufferedImage image;
        if(name.equals("Aircraft Carrier"))
        {
            image = ImageIO.read(new File("Resources/Images/AircraftCarrierVert.png"));
        }
        else if(name.equals("Battleship"))
        {
            image = ImageIO.read(new File("Resources/Images/BattleshipVert.png"));
        }
        else if(name.equals("Cruiser"))
        {
            image = ImageIO.read(new File("Resources/Images/CruiserVert.png"));
        }
        else if(name.equals("Submarine"))
        {
            image = ImageIO.read(new File("Resources/Images/SubmarineVert.png"));
        }
        else
        {
            image = ImageIO.read(new File("Resources/Images/DestroyerVert.png"));
        }

        return image;
    }

    public void updateGameboardArray(int[][] board)
    {
        gameboard = board;
        repaint();
    }

    public void addShip(Ship s)
    {
        if(ships.contains(s))
        {
            ships.remove(s);
        }
        ships.add(s);
    }

    private void clearboard()
    {
        for(int row = 0; row < 10; row++)
        {
            for (int col = 0; col < 10; col++)
            {
                gameboard[row][col] = 0;
            }
        }
    }

//    private void printhitmissboard(Boolean[][] hitmiss)
//    {
//        System.out.println("Printing Hit Miss");
//        System.out.println("Row: " + hitmiss.length + "  Cols: " + hitmiss[0].length);
//        for(int row = 0; row < hitmiss.length; row++)
//        {
//            for(int col = 0; col < hitmiss[0].length; col++)
//            {
//                if(hitmiss[row][col] == null)
//                {
//                    System.out.print("N ");
//                    continue;
//                }
//                if(hitmiss[row][col])
//                {
//                    System.out.print("T ");
//                }
//                else
//                {
//                    System.out.print("F ");
//                }
//            }
//            System.out.println();
//        }
//    }

    public void addHitMiss(Boolean[][] hitmiss)
    {
        clearboard();
//        printhitmissboard(hitmiss);
        for (int row = 0; row < 10; row++)
        {
            for (int col = 0; col < 10; col++) {
                if (hitmiss[row][col] == null)
                {
                    continue;
                }
                if (hitmiss[row][col]) {
                    gameboard[row][col] = GameboardArray.HIT;
                }
                else
                {
                    gameboard[row][col] = GameboardArray.MISS;
                }
            }
        }
    }

    public void clearShips()
    {
        ships.clear();
    }

    public int getCellWidth()
    {
        return cellWidth;
    }

    public int getCellHeight()
    {
        return cellHeight;
    }

    //Test Method for class
//    public static void main(String[] args)
//    {
//        Gameboard panel = new Gameboard();
//        JFrame frame = new JFrame();
//        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
//        frame.setSize(305, 305);
//        frame.add(panel);
//        frame.setVisible(true);
//        GameboardArray test = new GameboardArray();
//        test.addHit(1, 1);
//        test.addMiss(3, 3);
//        panel.updateGameboardArray(test.getGameboardArray());
//        Ship one = new Ship(5,"Aircraft Carrier");
//        one.setLocation(1, 1);
//        Ship two = new Ship(4,"Battleship");
//        two.setLocation(2, 2);
//        Ship three = new Ship(3,"Cruiser");
//        three.setLocation(3, 3);
//        Ship four = new Ship(3,"Submarine");
//        four.setLocation(4, 4);
//        Ship five = new Ship(2,"Destroyer");
//        five.setLocation(5, 5);
//        panel.addShip(one);
//        panel.addShip(two);
//        panel.addShip(three);
//        panel.addShip(four);
//        panel.addShip(five);
//        panel.updateUI();
//        try {
//            Thread.sleep(5000);
//        }catch (InterruptedException e)
//        {
//
//        }
//        test = new GameboardArray();
//        test.addMiss(0,0);
//        test.addHit(3,3);
//        panel.updateGameboardArray(test.getGameboardArray());
//        one.changeDirection();
//        two.changeDirection();
//        three.changeDirection();
//        four.changeDirection();
//        five.changeDirection();
//        panel.addShip(one);
//        panel.addShip(two);
//        panel.addShip(three);
//        panel.addShip(four);
//        panel.addShip(five);
//        panel.repaint();
//
//    }
}