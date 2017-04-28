import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

class DrawGameField extends Panel{
    private int fieldWidth = 500;
    private int fieldHeight = 500;
    private int currentPlr = 1;
    private int oppositePlr = 2;
    private String figPlr1 = "Cross";
    private String figPlr2 = "Circle";
    private Color colPlr1 = Color.RED;
    private Color colPlr2 = Color.BLUE;
    private int cellsCount = 3;
    private int combToWin = 3;
    private int cellSize = fieldWidth/cellsCount;
    private int mouseX = 0;
    private int mouseY = 0;
    private int[][] usedCells = new int[cellsCount][cellsCount];
    private Random rndTurn = new Random();
    private String gameOver = "Nothing";


//----------------------------------------------------------------------

    DrawGameField(int fieldWidth, int fieldHeight, String plr1, String plr2, String figPlr1, String figPlr2, Color colPlr1, Color colPlr2, int cellsCount, int combToWin) {
        this.fieldWidth = fieldWidth;
        this.fieldHeight = fieldHeight;
        this.figPlr1 = figPlr1;
        this.figPlr2 = figPlr2;
        this.colPlr1 = colPlr1;
        this.colPlr2 = colPlr2;
        this.cellsCount = cellsCount;
        this.combToWin = combToWin;
        this.cellSize = fieldWidth/cellsCount;
        this.usedCells = new int[cellsCount][cellsCount];

        setBackground(Color.PINK);

        // Обработка клика по игровому полю с последующей постановкой фишки
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent me) {
                mouseX = me.getX();
                mouseY = me.getY();
                if((currentPlr == 1 && plr1.equals("Human")) || (currentPlr == 2 && plr2.equals("Human"))) {
                    humanTurn();
                    gameOver();
                } else {
                    if((currentPlr == 1 && plr1.equals("AI")) || (currentPlr == 2 && plr2.equals("AI"))) {
                        aiTurn();
                        gameOver();
                    }
                }
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
                if(usedCells[i][j] == 1) {     // Если клетка первого игрока
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
                if(usedCells[i][j] == 2) {     // Если клетка второго игрока
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

                if(!gameOver.equals("Nothing")) {
                    g2.setFont(new Font("Helvetica", Font.BOLD, 72));
                    g2.setColor(Color.BLACK);
                    g2.drawString(gameOver, 10, 200);
                    g2.setColor(Color.ORANGE);
                    g2.drawString(gameOver, 6, 196);
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
                usedCells[cellX][cellY] = 1;
                this.repaint();
                currentPlr = 2;
                oppositePlr = 1;
            } else {
                usedCells[cellX][cellY] = 2;
                this.repaint();
                currentPlr = 1;
                oppositePlr = 2;
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
            usedCells[cellX][cellY] = 1;
            this.repaint();
            currentPlr = 2;
            oppositePlr = 1;
        } else {
            usedCells[cellX][cellY] = 2;
            this.repaint();
            currentPlr = 1;
            oppositePlr = 2;
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

    // Метод проверяет наличие выигрышной комбинации
    private boolean checkWin(int player) {
        for(int i = 0; i < cellsCount; i++)
            for(int j = 0; j < cellsCount; j++) {
                if(checkLine(i, j, 1, 0, combToWin, player) ||
                        checkLine(i, j, 0, 1, combToWin, player) ||
                        checkLine(i, j, 1, 1, combToWin, player) ||
                        checkLine(i, j, 1, -1, combToWin, player)) {
                    if(player == 1)
                        gameOver = " Player 1 WON";
                    if(player == 2)
                        gameOver = " Player 2 WON";
                    return true;
                }
            }
        return false;
    }

    // Метод проверяет линию по заданным параметрам
    private boolean checkLine(int sx, int sy, int vx, int vy, int l, int currentPlayer) {
        if(sx + l * vx > cellsCount ||
                sy + l * vy > cellsCount ||
                sy + l * vy < -1)
            return false;
        for(int i = 0; i < l; i++) {
            if(usedCells[sy + vy * i][sx + vx * i] != currentPlayer)
                return false;
        }
        return true;
    }




/*
    // Метод принимает значение фишки и проверяет наличие выигрышной комбинации на поле
    private boolean isWinComb() {
        int d1 = 0;
        int d2 = 0;
        int a = 0;
        int b = 0;
        int plr;
        if(currentPlr == 1) plr = 2;
        else plr = 1;
        // Проверяем диагонали
        for(int i = 0; i < cellsCount; i++) {
            if(usedCells[i][i] == plr)
                d1++;
            else
                d1 = 0;

            if(usedCells[cellsCount-1-i][i] == plr)
                d2++;
            else
                d2 = 0;

            if(d1 == combToWin || d2 == combToWin)
                return true;
        }

        return false;
    }
*/

    // Метод проверяет все варианты завершения игры
    private void gameOver() {
        if(checkWin(oppositePlr)) {
            for(int i = 0; i < cellsCount; i++)
                for(int j = 0; j < cellsCount; j++)
                    if(usedCells[i][j] == 0)
                        usedCells[i][j] = -1;
            repaint();
            return;
        }

        if(isFieldFull()) {
            gameOver = "        Draw";
            repaint();

        }
    }

}
