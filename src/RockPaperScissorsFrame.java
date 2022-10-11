import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import static java.awt.BorderLayout.NORTH;

public class RockPaperScissorsFrame extends JFrame
{
    JPanel mainPanel, titlePanel, buttonPanel, gamePanel, statsPanel, displayPanel;
    JButton quitBtn, rockButton, paperButton, scissorsButton;
    JLabel titleLbl, compWinLabel, playWinLabel, tieLabel;
    JTextArea result;
    JTextField compWinTxt, playWinTxt, tieTxt;
    JScrollPane scroller;
    ImageIcon icon, rock, paper, scissors;

    int computerWin = 0;
    int playerWin = 0;
    int tie = 0;

    int min = 1;
    int max = 3;

    Random rnd = new Random();

    public RockPaperScissorsFrame()
    {
        setTitle("Rock Paper Scissors");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1000, 600);

        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBackground(Color.GREEN);
        add(mainPanel);

        createTitlePanel();
        createButtonPanel();
        createDisplayPanel();
        createCommandPanel();
        createStatsPanel();

        setVisible(true);
    }

    public void createTitlePanel()
    {
        titlePanel = new JPanel();
        icon = new ImageIcon("src\\rockpaperscissors.jpg", "rock paper and scissors");
        this.setIconImage(icon.getImage());
        titleLbl = new JLabel("Rock Paper Scissors", JLabel.CENTER);
        titleLbl.setVerticalTextPosition(JLabel.BOTTOM);
        titleLbl.setHorizontalTextPosition(JLabel.CENTER);
        titleLbl.setFont(new Font("Comic Sans Ms", Font.BOLD, 30));
        mainPanel.add(titleLbl, NORTH);
    }

    public void createStatsPanel()
    {
        statsPanel = new JPanel();

        compWinLabel = new JLabel("Computer Wins: ");
        compWinTxt = new JTextField(" " + computerWin + " ", 3);
        compWinLabel.setFont(new Font("Comic Sans Ms", Font.PLAIN, 20));
        compWinTxt.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));

        playWinLabel = new JLabel("Player Wins: ");
        playWinTxt = new JTextField(" " + playerWin + " ", 3);
        playWinLabel.setFont(new Font("Comic Sans Ms", Font.PLAIN, 20));
        playWinTxt.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));

        tieLabel = new JLabel("Ties: ");
        tieTxt = new JTextField(" " + tie + " ", 3);
        tieLabel.setFont(new Font("Comic Sans Ms", Font.PLAIN, 20));
        tieTxt.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));

        statsPanel.add(compWinLabel);
        statsPanel.add(compWinTxt);
        statsPanel.add(playWinLabel);
        statsPanel.add(playWinTxt);
        statsPanel.add(tieLabel);
        statsPanel.add(tieTxt);

        mainPanel.add(statsPanel, BorderLayout.NORTH);
    }

    private void createDisplayPanel()
    {
        displayPanel = new JPanel();
        result = new JTextArea(5, 20);
        scroller = new JScrollPane(result);
        result.setFont(new Font("Comic Sans Ms", Font.PLAIN, 25));

        displayPanel.add(scroller);
        mainPanel.add(displayPanel, BorderLayout.EAST);
    }

    private void createButtonPanel()
    {
        rock = new ImageIcon(new ImageIcon("src/rock.png").getImage().getScaledInstance(150, 150, Image.SCALE_DEFAULT));
        paper = new ImageIcon(new ImageIcon("src/paper.png").getImage().getScaledInstance(150, 150, Image.SCALE_DEFAULT));
        scissors = new ImageIcon(new ImageIcon("src/scissors.png").getImage().getScaledInstance(150, 150, Image.SCALE_DEFAULT));

        buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 2));

        rockButton = new JButton(rock);
        paperButton = new JButton(paper);
        scissorsButton = new JButton(scissors);

        rockButton.addActionListener
        (
            (ActionEvent ae) ->
            {
                result.append(run(0) + "\n");
            }
        );
        paperButton.addActionListener
        (
             (ActionEvent ae) ->
                {
                    result.append(run(1) + "\n");
                }
         );
        scissorsButton.addActionListener
        (
               (ActionEvent ae) ->
               {
                   result.append(run(2) + "\n");
               }
         );

        buttonPanel.add(rockButton);
        buttonPanel.add(paperButton);
        buttonPanel.add(scissorsButton);

        mainPanel.add(BorderLayout.CENTER, buttonPanel);
    }

    private String run(int playerChoice)
    {
        Random rnd = new Random();
        int computerChoice = rnd.nextInt(3);
        String gameResult = "";

        if (computerChoice == playerChoice)
        {
            gameResult = "Tie Game!";
            tie++;
            tieTxt.setText(tie + "");
        }
        else
        {
            //rock = 0, paper = 1, scissors = 2
            switch(playerChoice)
            {
                case 0 ->
                {
                    if (computerChoice == 1)
                    {
                        gameResult = "Paper beats Rock!  You lose!";
                        computerWin++;
                        compWinTxt.setText(computerWin + "");
                    }
                    else
                    {
                        gameResult = "Rock beats Scissors!  You win!";
                        playerWin++;
                        playWinTxt.setText(playerWin + "");
                    }
                }
                case 1 ->
                {
                    if (computerChoice == 2)
                    {
                        gameResult = "Scissors beats paper!  You lose!";
                        computerWin++;
                        compWinTxt.setText(computerWin + "");
                    }
                    else
                    {
                        gameResult = "Paper beats rock!  You Win!";
                        playerWin++;
                        playWinTxt.setText(playerWin + "");
                    }
                }
                case 2 ->
                {
                    if (computerChoice == 1)
                    {
                        gameResult = "Scissors beats paper!  You win!";
                        playerWin++;
                        playWinTxt.setText(playerWin + "");
                    }
                    else
                    {
                        gameResult = "Rock beats scissors!  You lose!";
                        computerWin++;
                        compWinTxt.setText(computerWin + "");
                    }
                }
            }
        }
        return gameResult;
    }

    public void createCommandPanel()
    {
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 2));

        quitBtn = new JButton("Quit");
        quitBtn.setBackground(Color.RED);

        buttonPanel.add(quitBtn);
        quitBtn.addActionListener((ActionEvent ae) -> System.exit(0));

        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
    }
}