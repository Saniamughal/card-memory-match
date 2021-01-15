
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

public class Board extends JFrame{

	List<Card> cardsList = new ArrayList<Card>();
    List<Integer> cardVals = new ArrayList<Integer>();
   private List<Card> cards;
    private Card selectedCard,c1,c2;
    private Timer t;

    public Board(){

        setTitle("CARD Match");
    	 setLocation(500, 250);
         setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         setBounds(400, 200, 500, 600);
         JPanel j=new JPanel(new GridLayout(4, 5));
         j.setBackground(Color.MAGENTA);

        int noOfPairs = 10;
       

        for (int i = 0; i < noOfPairs; i++){
            cardVals.add(i);
            cardVals.add(i);
        }
        Collections.shuffle(cardVals);

        for (int val : cardVals){
            Card c = new Card();
            c.setCardNo(val);
            c.setFont(new Font("Arial",Font.PLAIN,40));
            c.addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent e){
                    selectedCard = c;
                    flip();
                }
            });
            cardsList.add(c);
        }
        cards = cardsList;
        //set up the timer
        t = new Timer(750, new ActionListener(){
            public void actionPerformed(ActionEvent e){
                checkCards();
            }
        });

        t.setRepeats(false);
        
        for (Card c : cards){
        	c.setBackground(Color.pink);
            j.add(c);
            add(j);
        }
    }

    public void flip(){
        if (c1 == null && c2 == null){
            c1 = selectedCard;
            c1.setText(String.valueOf(c1.getCardNo()));
        }

        if (c1 != null && c1 != selectedCard && c2 == null){
            c2 = selectedCard;
            c2.setText(String.valueOf(c2.getCardNo()));
            t.start();

        }
    }

   
    public void checkCards(){
        if (c1.getCardNo() == c2.getCardNo()){//match condition
            c1.setEnabled(false); //disables the button
            c2.setEnabled(false);
            c1.setCardsMatched(true); //flags the button as having been matched
            c2.setCardsMatched(true);
            if (uWin()){
                JOptionPane.showMessageDialog(this, "You win!");
                System.exit(0);
            }
        }

        else{
            c1.setText(""); //'hides' text
            c2.setText("");
        }
        
        c1 = null; //reset c1 and c2
        c2 = null;
    }

    public boolean uWin(){
        for(Card c: this.cards){
            if (c.getCardsMatched() == false){
                return false;
            }
        }
        return true;
    }
    

}