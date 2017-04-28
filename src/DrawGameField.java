import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

class DrawGameField extends Panel{
    private int fieldWidth = 500;
    private int fieldHeight = 500;
    private int currentPlr = 1;
    private String figPlr1 = "Cross";
    private String figPlr2 = "Circle";
    private Color colPlr1 = Color.RED;
    private Color colPlr2 = Color.BLUE;
    private int cellsCount = 3;
    private int cellSize = fieldWidth/cellsCount;
    private int mouseX = 0;
    private int mouseY = 0;
    private int[][] usedCells = new int[cellsCount][cellsCount];
    private Random rndTurn = new Random();


//----------------------------------------------------------------------

    DrawGameField(int fieldWidth, int fieldHeight, String plr1, String plr2, String figPlr1, String figPlr2, Color colPlr1, Color colPlr2, int cellsCount) {
        this.fieldWidth = fieldWidth;
        this.fieldHeight = fieldHeight;
        this.figPlr1 = figPlr1;
        this.figPlr2 = figPlr2;
        this.colPlr1 = colPlr1;
        this.colPlr2 = colPlr2;
        this.cellsCount = cellsCount;
        this.cellSize = fieldWidth/cellsCount;
        this.usedCells = new int[cellsCount][cellsCount];

        setBackground(Color.PINK);

        // Обработка клика по игровому полю с последующей постановкой фишки
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent me) {
                mouseX = me.getX();
                mouseY = me.getY();
                if( (currentPlr == 1 && plr1.equals("Human")) || (currentPlr == 2 && plr2.equals("Human")) )
                    humanTurn();
                if( (currentPlr == 1 && plr1.equals("AI")) || (currentPlr == 2 && plr2.equals("AI")) )
                    aiTurn();
            }
        });

    }

//----------------------------------------------------------------------

    @Override
    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;

        // Отрисовка сетки игрового поля
        g2.setStroke(new BasicStroke(3));
        for(int i = 0; i < cellsCount + 1; i++)
            g2.drawLine( aX(i), 0, aX(i), fieldHeight);
        for(int i = 0; i < cellsCount + 1; i++)
            g2.drawLine(0, aY(i), fieldWidth, aY(i));

        // Заполнение ячеек игрового поля
        g2.setStroke(new BasicStroke(5));
        for(int i = 0; i < cellsCount; i++) {
            for(int j = 0; j < cellsCount; j++) {
                if(usedCells[i][j] == 11) {     // Если клетка первого игрока
                    switch(figPlr1) {
                        case "Cross":
                            g2.setColor(Color.BLACK);
                            g2.drawLine( aX(i)+5,  aY(j)+8, aX(i+1)-9, aY(j+1)-5 );
                            g2.drawLine( aX(i)+10, aY(j+1)-4, aX(i+1)-4, aY(j)+10 );
                            g2.setColor(colPlr1);
                            g2.drawLine( aX(i)+7,  aY(j)+7, aX(i+1)-7, aY(j+1)-7 );
                            g2.drawLine( aX(i)+7, aY(j+1)-7, aX(i+1)-7, aY(j)+7 );
                            break;
                        case "Circle":
                            g2.setColor(Color.BLACK);
                            g2.drawOval( aX(i)+10, aY(j)+10, cellSize-14, cellSize-14 );
                            g2.setColor(colPlr1);
                            g2.drawOval( aX(i)+7, aY(j)+7, cellSize-14, cellSize-14 );
                            break;
                        case "Square":
                            g2.setColor(Color.BLACK);
                            g2.drawRect( aX(i)+10, aY(j)+10, cellSize-14, cellSize-14 );
                            g2.setColor(colPlr1);
                            g2.drawRect( aX(i)+7, aY(j)+7, cellSize-14, cellSize-14 );
                            break;
                        case "fCircle":
                            g2.setColor(Color.BLACK);
                            g2.fillOval( aX(i)+10, aY(j)+10, cellSize-14, cellSize-14 );
                            g2.setColor(colPlr1);
                            g2.fillOval( aX(i)+7, aY(j)+7, cellSize-14, cellSize-14 );
                            break;
                        case "fSquare":
                            g2.setColor(Color.BLACK);
                            g2.fillRect( aX(i)+10, aY(j)+10, cellSize-14, cellSize-14 );
                            g2.setColor(colPlr1);
                            g2.fillRect( aX(i)+7, aY(j)+7, cellSize-14, cellSize-14 );
                            break;
                    }
                }
                if(usedCells[i][j] == 22) {     // Если клетка второго игрока
                    switch(figPlr2) {
                        case "Cross":
                            g2.setColor(Color.BLACK);
                            g2.drawLine( aX(i)+5,  aY(j)+8, aX(i+1)-9, aY(j+1)-5 );
                            g2.drawLine( aX(i)+10, aY(j+1)-4, aX(i+1)-4, aY(j)+10 );
                            g2.setColor(colPlr2);
                            g2.drawLine( aX(i)+7,  aY(j)+7, aX(i+1)-7, aY(j+1)-7 );
                            g2.drawLine( aX(i)+7, aY(j+1)-7, aX(i+1)-7, aY(j)+7 );
                            break;
                        case "Circle":
                            g2.setColor(Color.BLACK);
                            g2.drawOval( aX(i)+10, aY(j)+10, cellSize-14, cellSize-14 );
                            g2.setColor(colPlr2);
                            g2.drawOval( aX(i)+7, aY(j)+7, cellSize-14, cellSize-14 );
                            break;
                        case "Square":
                            g2.setColor(Color.BLACK);
                            g2.drawRect( aX(i)+10, aY(j)+10, cellSize-14, cellSize-14 );
                            g2.setColor(colPlr2);
                            g2.drawRect( aX(i)+7, aY(j)+7, cellSize-14, cellSize-14 );
                            break;
                        case "fCircle":
                            g2.setColor(Color.BLACK);
                            g2.fillOval( aX(i)+10, aY(j)+10, cellSize-14, cellSize-14 );
                            g2.setColor(colPlr2);
                            g2.fillOval( aX(i)+7, aY(j)+7, cellSize-14, cellSize-14 );
                            break;
                        case "fSquare":
                            g2.setColor(Color.BLACK);
                            g2.fillRect( aX(i)+10, aY(j)+10, cellSize-14, cellSize-14 );
                            g2.setColor(colPlr2);
                            g2.fillRect( aX(i)+7, aY(j)+7, cellSize-14, cellSize-14 );
                            break;
                    }
                }

                if(isFieldFull()) {
                    g2.setFont(new Font("Helvetica", Font.BOLD, 72));
                    g2.setColor(Color.BLACK);
                    g2.drawString("Game Over", 50, 100);
                    g2.setColor(Color.ORANGE);
                    g2.drawString("Game Over", 46, 96);
                }

            }
        }

    }

//----------------------------------------------------------------------

    // Вспомогательные методы для упрощения кода
    private int aX(int i) {
        return (fieldWidth*i)/cellsCount;
    }

    private int aY(int j) {
        return (fieldHeight*j)/cellsCount;
    }


//----------------------------------------------------------------------

    // Метод отвечает за ход человека
    private void humanTurn() {
        int cellX = mouseX/cellSize;
        int cellY = mouseY/cellSize;
        if(usedCells[cellX][cellY] == 0) {
            if(currentPlr == 1) {
                usedCells[cellX][cellY] = 11;
                this.repaint();
                currentPlr = 2;
            } else {
                usedCells[cellX][cellY] = 22;
                this.repaint();
                currentPlr = 1;
            }
        }

    }

    // Метод отвечает за ход AI
    private void aiTurn() {
        // Если поле заполнено, сразу выходим из метода
        if(isFieldFull()) {
            return;
        }

        int cellX;
        int cellY;
        do {
            cellX = rndTurn.nextInt(cellsCount);
            cellY = rndTurn.nextInt(cellsCount);
        } while(usedCells[cellX][cellY] != 0);

        if(currentPlr == 1) {
            usedCells[cellX][cellY] = 11;
            this.repaint();
            currentPlr = 2;
        } else {
            usedCells[cellX][cellY] = 22;
            this.repaint();
            currentPlr = 1;
        }
    }

    // Метод проверяет заполненность поля
    private boolean isFieldFull() {
        for(int[] x1 : usedCells) {
            for(int x2 : x1) {
                if(x2 == 0)
                    return false;
            }
        }
        return true;
    }

}
