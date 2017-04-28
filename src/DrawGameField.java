import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

class DrawGameField extends Panel{
    private int fieldWidth = 500;
    private int fieldHeight = 500;
    private int cellsCount = 3;
    private int cellSize = fieldWidth/cellsCount;
    private int mouseX = 0;
    private int mouseY = 0;
    private int[][] usedCells = new int[cellsCount][cellsCount];

//----------------------------------------------------------------------

    DrawGameField(int fieldWidth, int fieldHeight, int cellsCount) {
        this.fieldWidth = fieldWidth;
        this.fieldHeight = fieldHeight;
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


        // Узнать размер панели
//        System.out.println(mouseX);
//        System.out.println(mouseY);
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
                if(usedCells[i][j] == 1) {
                    g2.setColor(Color.RED);
                    g2.drawLine( aX(i)+7,  aY(j)+7, aX(i+1)-7, aY(j+1)-7 );
                    g2.drawLine( aX(i)+7, aY(j+1)-7, aX(i+1)-7, aY(j)+7 );
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
            usedCells[cellX][cellY] = 1;
            this.repaint();
        }
    }
}
