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
        // Отрисовка сетки игрового поля
        for(int i = 0; i < cellsCount + 1; i++)
            g.drawLine(((fieldWidth * i) / cellsCount), 0, ((fieldWidth * i) / cellsCount), fieldHeight);
        for(int i = 0; i < cellsCount + 1; i++)
            g.drawLine(0, ((fieldHeight * i) / cellsCount), fieldWidth, ((fieldHeight * i) / cellsCount));

        // Заполнение ячеек игрового поля
        for(int i = 0; i < cellsCount; i++) {
            for(int j = 0; j < cellsCount; j++) {
                if(usedCells[i][j] == 1) {
                    g.drawLine( i*cellSize,  j*cellSize, (i+1)*cellSize, (j+1)*cellSize );
                    g.drawLine( i*cellSize, (j+1)*cellSize, (i+1)*cellSize, j*cellSize );
                }
            }
        }

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
