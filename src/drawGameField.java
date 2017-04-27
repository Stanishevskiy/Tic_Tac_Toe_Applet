import java.awt.*;

class drawGameField extends Panel {
    private int fieldWidth = 500;
    private int fieldHeight = 500;
    private int cellsCount = 3;

    drawGameField(int fieldWidth, int fieldHeight, int cellsCount) {
        this.fieldWidth = fieldWidth;
        this.fieldHeight = fieldHeight;
        this.cellsCount = cellsCount;

        setBackground(Color.PINK);

        // Узнать размер панели
//        System.out.println(this.getWidth());
//        System.out.println(this.getHeight());
    }

    @Override
    public void paint(Graphics g) {
        for(int i = 0; i < cellsCount + 1; i++)
            g.drawLine(((fieldWidth * i) / cellsCount), 0, ((fieldWidth * i) / cellsCount), fieldHeight);
        for(int i = 0; i < cellsCount + 1; i++)
            g.drawLine(0, ((fieldHeight * i) / cellsCount), fieldWidth, ((fieldHeight * i) / cellsCount));
    }

}
