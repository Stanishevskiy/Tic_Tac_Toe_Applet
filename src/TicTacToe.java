import java.applet.Applet;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*
  <applet code="TicTacToe" width=500 height=500>
  </apple>
 */

public class TicTacToe extends Applet {
    // Переменные содержат объекты панелей используемые всеми методами
    private CardLayout cardLayout = new CardLayout();
    private Panel pnlApp = new Panel(cardLayout);
    private Panel pnlGenMenu = new Panel();
    private Panel pnlSetMenu = new Panel();
    private GridBagConstraints gbc = new GridBagConstraints();
    private DrawGameField gameField;

    // Переменные содержащат игровые параметры
    private int fieldWidth = 500;
    private int fieldHeight = 500;
    private Choice chsCellsCount = new Choice();
    private int cellsCount = 3;
    private Choice chsCombToWin = new Choice();
//    private int comboToWin = Integer.parseInt(chsComboToWin.getSelectedItem());

//----------------------------------------------------------------------

    @Override
    public void init() {
        // Создаем панели
        setSize(500, 500);
        setLayout(new BorderLayout());

        pnlGenMenu();
        pnlSetMenu();
        gameFieldSet();

        gameField = new DrawGameField(fieldWidth, fieldHeight, cellsCount);
        pnlApp.add(gameField, "Game Field");

        add(pnlApp, BorderLayout.CENTER);

        // Узнать размер апплета
//        System.out.println(fieldWidth);
//        System.out.println(fieldHeight);
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
        lblGenMenu.setBackground(Color.LIGHT_GRAY);
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

    // Метод отвечающий за создание игрового поля
    private void gameFieldSet() {
        fieldWidth = this.getWidth();
        fieldHeight = this.getHeight();


        if( cellsCount != Integer.parseInt(chsCellsCount.getSelectedItem()) ) {
            cellsCount = Integer.parseInt(chsCellsCount.getSelectedItem());
            gameField = new DrawGameField(fieldWidth, fieldHeight, cellsCount);
            pnlApp.add(gameField, "Game Field");
        }
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
        Choice chsTypePlayer1 = new Choice();
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
        Choice chsFigureTypePlayer1 = new Choice();
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
        Choice chsFigureColorPlayer1 = new Choice();
        chsFigureColorPlayer1.add("Red");
        chsFigureColorPlayer1.add("Blue");
        chsFigureColorPlayer1.add("Yellow");
        chsFigureColorPlayer1.add("Green");
        chsFigureColorPlayer1.add("Brown");
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
        Choice chsTypePlayer2 = new Choice();
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
        Choice chsFigureTypePlayer2 = new Choice();
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
        Choice chsFigureColorPlayer2 = new Choice();
        chsFigureColorPlayer2.add("Blue");
        chsFigureColorPlayer2.add("Red");
        chsFigureColorPlayer2.add("Yellow");
        chsFigureColorPlayer2.add("Green");
        chsFigureColorPlayer2.add("Brown");
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

//----------------------------------------------------------------------

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
