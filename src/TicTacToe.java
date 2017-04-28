import java.applet.Applet;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*
  <applet code="TicTacToe" width=500 height=500>
  </apple>
 */

public class TicTacToe extends Applet {
    // Переменные содержат объекты панелей и их компоненты
    private CardLayout cardLayout = new CardLayout();
    private Panel pnlApp = new Panel(cardLayout);
    private Panel pnlGenMenu = new Panel();
    private Panel pnlSetMenu = new Panel();
    private GridBagConstraints gbc = new GridBagConstraints();
    private DrawGameField gameField;
    private Choice chsTypePlayer1 = new Choice();
    private Choice chsTypePlayer2 = new Choice();
    private Choice chsFigureTypePlayer1 = new Choice();
    private Choice chsFigureTypePlayer2 = new Choice();
    private Choice chsFigureColorPlayer1 = new Choice();
    private Choice chsFigureColorPlayer2 = new Choice();
    private Choice chsCellsCount = new Choice();
    private Choice chsCombToWin = new Choice();

    // Переменные содержат игровые параметры
    private int fieldWidth = 500;
    private int fieldHeight = 500;
    private String typePlr1 = "Human";
    private String typePlr2 = "AI";
    private String figureTypePlayer1 = "Cross";
    private String figureTypePlayer2 = "Circle";
    private String figureColorPlayer1 = "Red";
    private String figureColorPlayer2 = "Blue";
    private Color figColPlr1 = Color.RED;
    private Color figColPlr2 = Color.BLUE;
    private int cellsCount = 3;
    private int combToWin = 3;

//----------------------------------------------------------------------

    @Override
    public void init() {
        // Задаем параметры апплета
        setSize(500, 500);
        setLayout(new BorderLayout());
        // Создаем панели меню
        pnlGenMenu();
        pnlSetMenu();
        gameFieldSet();
        // Создаем игровое поле с заданными в конструкторе параметрами
        gameField = new DrawGameField(fieldWidth, fieldHeight, typePlr1, typePlr2, figureTypePlayer1, figureTypePlayer2, figColPlr1, figColPlr2, cellsCount, combToWin);

        pnlApp.add(gameField, "Game Field");

        add(pnlApp, BorderLayout.CENTER);
    }

//----------------------------------------------------------------------

    // Метод отвечает за работу главного меню
    private void pnlGenMenu() {
        pnlGenMenu.setLayout(new GridBagLayout());          // Задаем компоновщик панели
        pnlGenMenu.setBackground(Color.CYAN);
        pnlApp.add(pnlGenMenu, "General Menu");  // Добавляем нашу панель к компоновщику карт

        // Название игры
        // Задаем название
        Label lblGenMenu = new Label();
        lblGenMenu.setText("TIC-TAC-TOE");
        // Задаем шрифт
        Font fntLbl = new Font("Helvetica", Font.BOLD, 54);
        lblGenMenu.setFont(fntLbl);
        lblGenMenu.setSize(150, 40);
        // Задаем параметры компоновщика и добавляем на панель
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weighty = 8;
        pnlGenMenu.add(lblGenMenu, gbc);

        // Кнопки в главном меню
        // Создаем объекты типа кнопка и задаем их название
        Button btnStartGame = new Button("Start Game");
        Button btnSettings = new Button("Settings");
        Button btnScore = new Button("Score");
        Button btnExit = new Button("Exit");
        // Задаем шрифт
        Font fntBtn = new Font("Helvetica", Font.BOLD, 44);
        btnStartGame.setFont(fntBtn);
        btnSettings.setFont(fntBtn);
        btnScore.setFont(fntBtn);
        btnExit.setFont(fntBtn);
        // Задаем параметры компоновщика и добавляем на панель
        gbc.gridy = 1;
        gbc.weighty = 2;
        gbc.ipadx = 20;
        pnlGenMenu.add(btnStartGame, gbc);
        gbc.gridy = 2;
        gbc.ipadx = 81;
        pnlGenMenu.add(btnSettings, gbc);
        gbc.gridy = 3;
        gbc.ipadx = 132;
        pnlGenMenu.add(btnScore, gbc);
        gbc.weighty = 4;
        gbc.gridy = 4;
        gbc.ipadx = 172;
        pnlGenMenu.add(btnExit, gbc);


        // Обработка событий
        // Переход на игровое поле
        btnStartGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameFieldSet();         // Перед вызовом поля обновляем все игровые параметры
                cardLayout.show(pnlApp, "Game Field");
            }
        });
        // Переход в меню настроек по нажатию Settings
        btnSettings.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(pnlApp, "Settings Menu");
            }
        });
        // Закрытие программы по нажатию Exit
        btnExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

//----------------------------------------------------------------------

    // Метод отвечающий за параметры игрового поля
    private void gameFieldSet() {
        fieldWidth = this.getWidth();
        fieldHeight = this.getHeight();

        // Задаем игроков
        if( !typePlr1.equals(chsTypePlayer1.getSelectedItem()) )
            typePlr1 = chsTypePlayer1.getSelectedItem();
        if( !typePlr2.equals(chsTypePlayer2.getSelectedItem()) )
            typePlr2 = chsTypePlayer2.getSelectedItem();

        // Фишки игроков
        if( !figureTypePlayer1.equals(chsFigureTypePlayer1.getSelectedItem()) )
            figureTypePlayer1 = chsFigureTypePlayer1.getSelectedItem();
        if( !figureTypePlayer2.equals(chsFigureTypePlayer2.getSelectedItem()) )
            figureTypePlayer2 = chsFigureTypePlayer2.getSelectedItem();

        // Цвет фишки игроков
        if( !figureColorPlayer1.equals(chsFigureColorPlayer1.getSelectedItem()) ) {
            figureColorPlayer1 = chsFigureColorPlayer1.getSelectedItem();
            figColPlr1 = selectColor(figureColorPlayer1, figColPlr1);
        }
        if( !figureColorPlayer2.equals(chsFigureColorPlayer2.getSelectedItem()) ) {
            figureColorPlayer2 = chsFigureColorPlayer2.getSelectedItem();
            figColPlr2 = selectColor(figureColorPlayer2, figColPlr2);
        }

        // Размер игрового поля
        if( cellsCount != Integer.parseInt(chsCellsCount.getSelectedItem()) )
            cellsCount = Integer.parseInt(chsCellsCount.getSelectedItem());

        // Выигрышная комбинация
        if( combToWin != Integer.parseInt(chsCombToWin.getSelectedItem()) )
            combToWin = Integer.parseInt(chsCombToWin.getSelectedItem());

        // Заменяем панель игрового поля новой, с новыми параметрами
        gameField = new DrawGameField(fieldWidth, fieldHeight, typePlr1, typePlr2, figureTypePlayer1, figureTypePlayer2, figColPlr1, figColPlr2, cellsCount, combToWin);
        pnlApp.add(gameField, "Game Field");
    }

//----------------------------------------------------------------------

    // Вспомогательные методы для упрощения кода
    private Color selectColor(String figColPlrStr, Color figColPlr) {
        Color resClr = figColPlr;
        switch(figColPlrStr) {
            case "Red":
                resClr = Color.RED;
                break;
            case "Blue":
                resClr = Color.BLUE;
                break;
            case "Yellow":
                resClr = Color.YELLOW;
                break;
            case "Green":
                resClr = Color.GREEN;
                break;
            case "Purple":
                resClr = Color.MAGENTA;
                break;
        }
        return resClr;
    }

//----------------------------------------------------------------------

    // Метод отвечает за работу меню настроек
    private void pnlSetMenu() {
        pnlSetMenu.setLayout(new GridBagLayout());             // Задаем компоновщик панели
        pnlApp.add(pnlSetMenu, "Settings Menu");    // Добавляем нашу панель к компоновщику карт

        // Заглавие меню
        Label lblSettings = new Label("Settings");
        Font fntLblSettings = new Font("Helvetica", Font.BOLD, 34);
        lblSettings.setFont(fntLblSettings);
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.ipadx = 40;
        gbc.gridwidth = 2;
        pnlSetMenu.add(lblSettings, gbc);

        // Добавляем составляющие меню
        Font fntSetElements = new Font("Helvetica", Font.BOLD, 18);
        // Настройки игрока 1
        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.ipadx = 20;
        Label lblPlayer1 = new Label("Player 1");
        lblPlayer1.setFont(fntSetElements);
        pnlSetMenu.add(lblPlayer1, gbc);

        gbc.anchor = GridBagConstraints.EAST;
        gbc.gridx = 0;
        gbc.gridy = 2;
        Label lblPlayer1Name = new Label("Name: ");
        lblPlayer1Name.setFont(fntSetElements);
        pnlSetMenu.add(lblPlayer1Name, gbc);

        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridx = 1;
        gbc.gridy = 2;
        TextField txtNamePlayer1 = new TextField("Player 1");
        txtNamePlayer1.setFont(fntSetElements);
        pnlSetMenu.add(txtNamePlayer1, gbc);

        gbc.anchor = GridBagConstraints.EAST;
        gbc.gridx = 0;
        gbc.gridy = 3;
        Label lblPlayer1Type = new Label("Player: ");
        lblPlayer1Type.setFont(fntSetElements);
        pnlSetMenu.add(lblPlayer1Type, gbc);

        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridx = 1;
        gbc.gridy = 3;
        chsTypePlayer1.add("Human");
        chsTypePlayer1.add("AI");
        chsTypePlayer1.setFont(fntSetElements);
        pnlSetMenu.add(chsTypePlayer1, gbc);

        gbc.anchor = GridBagConstraints.EAST;
        gbc.gridx = 0;
        gbc.gridy = 4;
        Label lblPlayer1FigureType = new Label("Figure: ");
        lblPlayer1FigureType.setFont(fntSetElements);
        pnlSetMenu.add(lblPlayer1FigureType, gbc);

        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridx = 1;
        gbc.gridy = 4;
        chsFigureTypePlayer1.add("Cross");
        chsFigureTypePlayer1.add("Circle");
        chsFigureTypePlayer1.add("Square");
        chsFigureTypePlayer1.add("fCircle");
        chsFigureTypePlayer1.add("fSquare");
        chsFigureTypePlayer1.setFont(fntSetElements);
        pnlSetMenu.add(chsFigureTypePlayer1, gbc);

        gbc.anchor = GridBagConstraints.EAST;
        gbc.gridx = 0;
        gbc.gridy = 5;
        Label lblPlayer1FigureColor = new Label("Color: ");
        lblPlayer1FigureColor.setFont(fntSetElements);
        pnlSetMenu.add(lblPlayer1FigureColor, gbc);

        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridx = 1;
        gbc.gridy = 5;
        chsFigureColorPlayer1.add("Red");
        chsFigureColorPlayer1.add("Blue");
        chsFigureColorPlayer1.add("Yellow");
        chsFigureColorPlayer1.add("Green");
        chsFigureColorPlayer1.add("Purple");
        chsFigureColorPlayer1.setFont(fntSetElements);
        pnlSetMenu.add(chsFigureColorPlayer1, gbc);

        // Настройки игрока 2
        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridx = 0;
        gbc.gridy = 6;
        Label lblPlayer2 = new Label("Player 2");
        lblPlayer2.setFont(fntSetElements);
        pnlSetMenu.add(lblPlayer2, gbc);

        gbc.anchor = GridBagConstraints.EAST;
        gbc.gridx = 0;
        gbc.gridy = 7;
        Label lblPlayer2Name = new Label("Name: ");
        lblPlayer2Name.setFont(fntSetElements);
        pnlSetMenu.add(lblPlayer2Name, gbc);

        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridx = 1;
        gbc.gridy = 7;
        TextField txtNamePlayer2 = new TextField("Player 2");
        txtNamePlayer2.setFont(fntSetElements);
        pnlSetMenu.add(txtNamePlayer2, gbc);

        gbc.anchor = GridBagConstraints.EAST;
        gbc.gridx = 0;
        gbc.gridy = 8;
        Label lblPlayer2Type = new Label("Player: ");
        lblPlayer2Type.setFont(fntSetElements);
        pnlSetMenu.add(lblPlayer2Type, gbc);

        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridx = 1;
        gbc.gridy = 8;
        chsTypePlayer2.add("AI");
        chsTypePlayer2.add("Human");
        chsTypePlayer2.setFont(fntSetElements);
        pnlSetMenu.add(chsTypePlayer2, gbc);

        gbc.anchor = GridBagConstraints.EAST;
        gbc.gridx = 0;
        gbc.gridy = 9;
        Label lblPlayer2FigureType = new Label("Figure: ");
        lblPlayer2FigureType.setFont(fntSetElements);
        pnlSetMenu.add(lblPlayer2FigureType, gbc);

        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridx = 1;
        gbc.gridy = 9;
        chsFigureTypePlayer2.add("Circle");
        chsFigureTypePlayer2.add("Cross");
        chsFigureTypePlayer2.add("Square");
        chsFigureTypePlayer2.add("fCircle");
        chsFigureTypePlayer2.add("fSquare");
        chsFigureTypePlayer2.setFont(fntSetElements);
        pnlSetMenu.add(chsFigureTypePlayer2, gbc);

        gbc.anchor = GridBagConstraints.EAST;
        gbc.gridx = 0;
        gbc.gridy = 10;
        Label lblPlayer2FigureColor = new Label("Color: ");
        lblPlayer2FigureColor.setFont(fntSetElements);
        pnlSetMenu.add(lblPlayer2FigureColor, gbc);

        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridx = 1;
        gbc.gridy = 10;
        chsFigureColorPlayer2.add("Blue");
        chsFigureColorPlayer2.add("Red");
        chsFigureColorPlayer2.add("Yellow");
        chsFigureColorPlayer2.add("Green");
        chsFigureColorPlayer2.add("Purple");
        chsFigureColorPlayer2.setFont(fntSetElements);
        pnlSetMenu.add(chsFigureColorPlayer2, gbc);

        // Игровые параметры
        gbc.anchor = GridBagConstraints.EAST;
        gbc.gridx = 0;
        gbc.gridy = 11;
        Label lblGameFieldSize = new Label("Field Size: ");
        lblGameFieldSize.setFont(fntSetElements);
        pnlSetMenu.add(lblGameFieldSize, gbc);

        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridx = 1;
        gbc.gridy = 11;
        chsCellsCount.add("3");
        chsCellsCount.add("4");
        chsCellsCount.add("5");
        chsCellsCount.add("6");
        chsCellsCount.add("7");
        chsCellsCount.add("8");
        chsCellsCount.add("9");
        chsCellsCount.setFont(fntSetElements);
        pnlSetMenu.add(chsCellsCount, gbc);

        gbc.anchor = GridBagConstraints.EAST;
        gbc.gridx = 0;
        gbc.gridy = 12;
        Label lblCombToWin = new Label("Combo to Win: ");
        lblCombToWin.setFont(fntSetElements);
        pnlSetMenu.add(lblCombToWin, gbc);

        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridx = 1;
        gbc.gridy = 12;
        chsCombToWin.add("3");
        chsCombToWin.add("4");
        chsCombToWin.add("5");
        chsCombToWin.add("6");
        chsCombToWin.add("7");
        chsCombToWin.add("8");
        chsCombToWin.add("9");
        chsCombToWin.setFont(fntSetElements);
        pnlSetMenu.add(chsCombToWin, gbc);

        // Добавляем кнопку выхода из меню настроек
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.gridx = 0;
        gbc.gridy = 13;
        gbc.gridwidth = 2;
        Button btnToGenMenu = new Button("Back To Menu");
        btnToGenMenu.setFont(fntSetElements);
        pnlSetMenu.add(btnToGenMenu, gbc);


        // Обработка событий
        // Обработка клавиш меню настроек
        btnToGenMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(pnlApp, "General Menu");
            }
        });

    }
}
