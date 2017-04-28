import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

class DrawGameField extends Panel{
    private int fieldWidth = 500;
    private int fieldHeight = 500;
    private String plr1 = "Human";
    private String plr2 = "AI";
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

//----------------------------------------------------------------------

    DrawGameField(int fieldWidth, int fieldHeight, String plr1, String plr2, String figPlr1, String figPlr2, Color colPlr1, Color colPlr2, int cellsCount) {
        this.fieldWidth = fieldWidth;
        this.fieldHeight = fieldHeight;
        this.plr1 = plr1;
        this.plr2 = plr2;
        this.figPlr1 = figPlr1;
        this.figPlr2 = figPlr2;
        this.colPlr1 = colPlr1;
        this.colPlr2 = colPlr2;
        this.cellsCount = cellsCount;
        this.cellSize = fieldWidth/cellsCount;
        this.usedCells = new int[cellsCount][cellsCount];

        setBackground(Color.PINK);

        // Адаптер позволяет запоминить координты мыши при клике
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent me) {
                mouseX = me.getX();
                mouseY = me.getY();
                drawCross();
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
                    g2.setColor(colPlr1);
                    switch(figPlr1) {
                        case "Cross":
                            g2.drawLine( aX(i)+7,  aY(j)+7, aX(i+1)-7, aY(j+1)-7 );
                            g2.drawLine( aX(i)+7, aY(j+1)-7, aX(i+1)-7, aY(j)+7 );
                            break;
                        case "Circle":
                            g2.drawOval( aX(i)+7, aY(j)+7, cellSize-14, cellSize-14 );
                            break;
                        case "Square":
                            g2.drawRect( aX(i)+7, aY(j)+7, cellSize-14, cellSize-14 );
                            break;
                        case "fCircle":
                            g2.fillOval( aX(i)+7, aY(j)+7, cellSize-14, cellSize-14 );
                            break;
                        case "fSquare":
                            g2.fillRect( aX(i)+7, aY(j)+7, cellSize-14, cellSize-14 );
                            break;
                    }
                }
                if(usedCells[i][j] == 22) {     // Если клетка второго игрока
                    g2.setColor(colPlr2);
                    switch(figPlr2) {
                        case "Cross":
                            g2.drawLine( aX(i)+7,  aY(j)+7, aX(i+1)-7, aY(j+1)-7 );
                            g2.drawLine( aX(i)+7, aY(j+1)-7, aX(i+1)-7, aY(j)+7 );
                            break;
                        case "Circle":
                            g2.drawOval( aX(i)+7, aY(j)+7, cellSize-14, cellSize-14 );
                            break;
                        case "Square":
                            g2.drawRect( aX(i)+7, aY(j)+7, cellSize-14, cellSize-14 );
                            break;
                        case "fCircle":
                            g2.fillOval( aX(i)+7, aY(j)+7, cellSize-14, cellSize-14 );
                            break;
                        case "fSquare":
                            g2.fillRect( aX(i)+7, aY(j)+7, cellSize-14, cellSize-14 );
                            break;
                    }
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

    // Метод проверяет занятость ячейки
    private void drawCross() {
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
}
